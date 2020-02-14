package com.project_server.exception;

public class DbException extends RuntimeException {
    public DbException(Throwable cause) {
        super(cause);
    }

    public DbException(String message) {
        super(message);
    }
}
