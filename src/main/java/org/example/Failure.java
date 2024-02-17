package org.example;

public class Failure {
    private final long lastRequestTime;
    private final int retriesCount;

    public Failure(long lastRequestTime, int retriesCount) {
        this.lastRequestTime = lastRequestTime;
        this.retriesCount = retriesCount;
    }

    public long getLastRequestTime() {
        return lastRequestTime;
    }

    public int getRetriesCount() {
        return retriesCount;
    }
}
