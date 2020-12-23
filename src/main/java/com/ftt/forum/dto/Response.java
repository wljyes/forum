package com.ftt.forum.dto;

import java.util.HashMap;
import java.util.Map;

public class Response {
    private final Map<String, Object> data;
    private final int code;
    private final String error;


    public Response(Map<String, Object> data, Status status, String error) {
        this.data = data;
        this.code = status.code;
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    public Response append(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public static Response success() {
        return new Response(new HashMap<>(), Status.SUCCESS, null);
    }

    public static Response fail(String error) {
        return new Response(new HashMap<>(), Status.FAIL, error);
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
