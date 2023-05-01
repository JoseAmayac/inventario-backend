package com.stock.inventario.common;

public class ApiResponse {
    private Boolean ok;
    private Object data;
    private String message;
    private Object error;

    public ApiResponse(Boolean ok, Object data, String message, Object error) {
        this.ok = ok;
        this.data = data;
        this.message = message;
        this.error = error;
    }

    public ApiResponse(Boolean ok, Object data) {
        this.ok = ok;
        this.data = data;
    }

    public ApiResponse(Boolean ok, String message, Object error) {
        this.ok = ok;
        this.message = message;
        this.error = error;
    }

    public ApiResponse(Boolean ok, String message) {
        this.ok = ok;
        this.message = message;
    }

    public Boolean getOk() {
        return ok;
    }

    public Object getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public Object getError() {
        return error;
    }
}
