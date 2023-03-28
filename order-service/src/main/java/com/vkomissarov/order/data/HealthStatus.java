package com.vkomissarov.order.data;

public enum HealthStatus {
    UP("UP"),
    DOWN("DOWN");

    private final String status;

    HealthStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return status;
    }
}