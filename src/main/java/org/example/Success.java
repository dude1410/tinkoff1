package org.example;

public class Success {
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
