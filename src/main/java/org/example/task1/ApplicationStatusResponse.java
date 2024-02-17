package org.example.task1;

public class ApplicationStatusResponse {

    public enum Status {
        SUCCESS,
        FAILURE
    }

    public static class Success {
        private final String id;
        private final String status;

        public Success(String id, String status) {
            this.id = id;
            this.status = status;
        }

        public String getId() {
            return id;
        }

        public String getStatus() {
            return status;
        }
    }

    public static class Failure {
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


}
