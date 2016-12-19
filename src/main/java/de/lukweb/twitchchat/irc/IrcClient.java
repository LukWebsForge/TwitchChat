package de.lukweb.twitchchat.irc;

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
import java.util.logging.Level;
import java.util.logging.Logger;

public class IrcClient {

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
    private Thread readThread;

    private boolean close;

    public IrcClient(String host, int port, boolean ssl)
            throws IOException, GeneralSecurityException {
        if (sslSocketFactory == null) init();
        this.host = host;
        this.port = port;
        this.ssl = ssl;
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

        readThread = new Thread(() -> {
            while (!close) {
                try {
                    String line = in.readLine();
                    if (line == null) continue;
                    System.out.println("> " + line);
                } catch (IOException e) {
                    catchException(e);
                }
            }
        });
        readThread.start();
    }

    public void sendString(String message) {
        try {
            System.out.println("< " + message);
            out.write((message + "\n").getBytes());
            out.flush();
        } catch (IOException ex) {
            catchException(ex);
        }
    }

    public boolean isConnected() {
        if (close) return false;
        return socket.isConnected();
    }

    public void close() {
        close = true;
        try {
            in.close();
            out.close();
        } catch (IOException e) {
            catchException(e);
        }
    }

    private void catchException(Throwable throwable) {
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, throwable);
    }
}
