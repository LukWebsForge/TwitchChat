package de.lukweb.twitchchat.irc;

import de.lukweb.twitchchat.twitch.utils.DebugLogger;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TurboIrcClient implements IrcClient {

    private static SSLSocketFactory sslSocketFactory;

    private static void init() throws GeneralSecurityException {
        SSLContext sslContext = SSLContext.getInstance("TLSv1");
        sslContext.init(new KeyManager[]{}, new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] x509Certificates, String s)
                            throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] x509Certificates, String s)
                            throws CertificateException {
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                }
        }, new SecureRandom());

        sslSocketFactory = sslContext.getSocketFactory();
    }

    private String host;
    private int port;
    private boolean ssl;

    private Socket socket;
    private BufferedReader in;
    private DataOutputStream out;
    private IrcInputHandler inputHandler;
    private Consumer<Throwable> errorHandler;
    private Logger debugLogger;

    private boolean close;

    public TurboIrcClient(String host, int port, boolean ssl) throws IOException, GeneralSecurityException {
        if (sslSocketFactory == null) init();
        this.host = host;
        this.port = port;
        this.ssl = ssl;
        this.debugLogger = DebugLogger.get("IrcClient");
        connect();
    }

    private void connect() throws IOException, GeneralSecurityException {
        if (ssl) {
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(host, port);
            sslSocket.setUseClientMode(true);

            sslSocket.setEnabledProtocols(sslSocket.getSupportedProtocols());
            sslSocket.setEnabledCipherSuites(sslSocket.getEnabledCipherSuites());

            sslSocket.startHandshake();

            socket = sslSocket;
        } else {
            socket = new Socket(host, port);
        }

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new DataOutputStream(socket.getOutputStream());

        new Thread(() -> {
            while (!close) {
                try {
                    String line = in.readLine();
                    if (line == null || inputHandler == null) continue;
                    inputHandler.handle(line);
                    this.debugLogger.fine("Received: " + line);
                } catch (IOException e) {
                    catchException(e);
                }
            }
        }, "TwitchChat-InputHandler").start();
    }

    @Override
    public void setInputHandler(IrcInputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    @Override
    public void setErrorHandler(Consumer<Throwable> errorHandler) {
        this.errorHandler = errorHandler;
    }

    @Override
    public void sendString(String message) {
        try {
            out.write((message + "\n").getBytes());
            out.flush();
            this.debugLogger.fine("Sent: " + message);
        } catch (IOException ex) {
            catchException(ex);
        }
    }

    @Override
    public boolean isConnected() {
        return !close && socket.isConnected();
    }

    @Override
    public boolean isClosed() {
        return close;
    }

    @Override
    public void close() {
        close = true;
        try {
            in.close();
            out.close();
        } catch (IOException e) {
            catchException(e);
        }
    }

    public void setDebug(boolean debug) {
        debugLogger.setLevel(debug ? Level.ALL : Level.OFF);
    }

    private void catchException(Throwable throwable) {
        if (errorHandler != null) {
            errorHandler.accept(throwable);
        } else {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, throwable);
        }
    }
}
