package org.example.task1;

public class Response {
    public enum Result {
        SUCCESS,
        RETRY_AFTER,
        FAILURE
    }

    public static class Success {

        private final String applicationId;
        private final String applicationStatus;

        public Success(String applicationId, String applicationStatus) {
            this.applicationId = applicationId;
            this.applicationStatus = applicationStatus;
        }

        public String getApplicationId() {
            return applicationId;
        }

        public String getApplicationStatus() {
            return applicationStatus;
        }
    }

    public static class RetryAfter {
        private final long delay;

        public RetryAfter(long delay) {
            this.delay = delay;
        }

        public long getDelay() {
            return delay;
        }
    }

    public static class Failure {

        private final int errorCode;
        private final String errorInfo;

        public Failure(int errorCode, String errorInfo) {
            this.errorCode = errorCode;
            this.errorInfo = errorInfo;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public String getErrorInfo() {
            return errorInfo;
        }
    }
}
