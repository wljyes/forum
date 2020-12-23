package com.ftt.forum.dto;

import java.util.HashMap;
import java.util.Map;

public class Response {
    private final Object data;
    private final int code;


    public Response(Object data, Status status) {
        this.data = data;
        this.code = status.code;
    }

    public Object getData() {
        return data;
    }

    public int getCode() {
        return code;
    }


    public static Response success(Object data) {
        return new Response(data, Status.SUCCESS);
    }

    public static Response fail(Object error) {
        return new Response(error, Status.FAIL);
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
