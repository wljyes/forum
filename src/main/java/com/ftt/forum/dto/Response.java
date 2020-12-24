package com.ftt.forum.dto;

public class Response<T> {
    private final T data;
    private final int code;
    private final String message;


    public Response(T data, Status status, String message) {
        this.data = data;
        this.code = status.code;
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(data, Status.SUCCESS, "success");
    }

    public static <T> Response<T> fail(T emptyData, String errorMsg) {
        return new Response<>(emptyData, Status.FAIL, errorMsg);
    }

    public static enum Status {
        SUCCESS(1000), FAIL(1001);

        private final int code;

        private Status(int code) {
            this.code = code;
        }

        public int value() {
            return this.code;
        }
    }
}
