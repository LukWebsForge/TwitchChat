package de.lukweb.twitchchat.irc;

public class QueuedMessage implements Comparable<QueuedMessage> {

    private String message;
    private boolean operator;
    private int queuedTimestamp;
    private int sentTimestamp;

    public QueuedMessage(String message, boolean operator) {
        this.message = message;
        this.operator = operator;
        this.queuedTimestamp = (int) (System.currentTimeMillis() / 1000);
        this.sentTimestamp = -1;
    }

    public String getMessage() {
        return message;
    }

    public boolean isOperator() {
        return operator;
    }

    public int getQueuedTimestamp() {
        return queuedTimestamp;
    }

    public void setSend() {
        sentTimestamp = (int) (System.currentTimeMillis() / 1000);
    }

    public int getSentTimestamp() {
        return sentTimestamp;
    }

    public boolean isSent() {
        return sentTimestamp >= 0;
    }

    @Override
    public int compareTo(QueuedMessage o) {
        if (queuedTimestamp > o.queuedTimestamp) return -1;
        if (queuedTimestamp < o.queuedTimestamp) return 1;
        return 0;
    }
}
