package org.example.task1;

import java.util.Random;

public class ApplicationStatusHandler {

    public Object performOperation(String id) {

        long startTime = System.currentTimeMillis();

        int retriesCount = 0;

        while (true) {
            Object response1 = getRandomResponse1(id);
            if (response1 instanceof Response.Success) {
                Response.Success successResponse1 = (Response.Success) response1;
                System.out.println("SUCCESS 1");
                return new ApplicationStatusResponse.Success(successResponse1.getApplicationId(), successResponse1.getApplicationStatus());
            } else if (response1 instanceof Response.RetryAfter) {
                Response.RetryAfter retryAfter = (Response.RetryAfter) response1;
                // Check if timeout has exceeded
                if (System.currentTimeMillis() - startTime > 15000) {
                    return new ApplicationStatusResponse.Failure(System.currentTimeMillis(), retriesCount);
                }
                // Sleep for the specified delay
                try {
                    Thread.sleep(retryAfter.getDelay() * 1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } else {
                retriesCount++;
            }

            // Get application status from service 2
            Object response2 = getRandomResponse2(id);
            if (response2 instanceof Response.Success) {
                Response.Success successResponse2 = (Response.Success) response2;
                System.out.println("SUCCESS 2");
                return new ApplicationStatusResponse.Success(successResponse2.getApplicationId(), successResponse2.getApplicationStatus());
            } else if (response2 instanceof Response.RetryAfter) {
                Response.RetryAfter retryAfter = (Response.RetryAfter) response2;
                // Check if timeout has exceeded
                if (System.currentTimeMillis() - startTime > 15000) {
                    return new ApplicationStatusResponse.Failure(System.currentTimeMillis(), retriesCount);
                }
                // Sleep for the specified delay
                try {
                    Thread.sleep(retryAfter.getDelay() * 1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } else {
                retriesCount++;
            }

            // Check if timeout has exceeded
            if (System.currentTimeMillis() - startTime > 15000) {
                return new ApplicationStatusResponse.Failure(System.currentTimeMillis(), retriesCount);
            }
        }
    }

    public Object getRandomResponse1(String applicationId) {
        Random random = new Random();
        int randomInt = random.nextInt(3);
        switch (randomInt) {
            case 0:
                return getSuccessResponse1(applicationId);

            case 1:
                return getFailureResponse1(applicationId);

            case 2:
                return getRetryAfterResponse1(applicationId);
        }
        return null;
    }

    public Object getRandomResponse2(String applicationId) {
        Random random = new Random();
        int randomInt = random.nextInt(3);
        switch (randomInt) {
            case 0:
                return getSuccessResponse2(applicationId);

            case 1:
                return getFailureResponse2(applicationId);

            case 2:
                return getRetryAfterResponse2(applicationId);
        }
        return null;
    }

    // mocks

    private Response.Success getSuccessResponse1(String applicationId) {
        return new Response.Success(applicationId, "Approved");
    }

    private Response.Failure getFailureResponse1(String applicationId) {
        return new Response.Failure(500, "denied");
    }

    private Response.RetryAfter getRetryAfterResponse1(String applicationId) {
        return new Response.RetryAfter(5);
    }

    private Response.Success getSuccessResponse2(String applicationId) {
        return new Response.Success(applicationId, "Approved");
    }

    private Response.Failure getFailureResponse2(String applicationId) {
        return new Response.Failure(500, "denied");
    }

    private Response.RetryAfter getRetryAfterResponse2(String applicationId) {
        return new Response.RetryAfter(4);
    }
}