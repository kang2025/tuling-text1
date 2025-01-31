package com.hrms.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(sanitizeMessage(message));
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(sanitizeMessage(message), cause);
    }

    private static String sanitizeMessage(String message) {
        // 对消息进行清理，移除敏感信息
        return message == null ? "Resource not found" : message.replaceAll("[^a-zA-Z0-9]", "_");
    }
}
