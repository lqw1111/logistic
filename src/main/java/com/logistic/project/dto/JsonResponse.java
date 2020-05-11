package com.logistic.project.dto;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

public class JsonResponse extends HashMap<String, Object> {

    public JsonResponse code(Object code) {
        this.put("code", code);
        return this;
    }

    public JsonResponse message(Object msg) {
        this.put("message", msg);
        return this;
    }

    public JsonResponse obj(Object o) {
        this.put("obj", o);
        return this;
    }

    public JsonResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    public JsonResponse succ() {
        this.code(HttpStatus.OK.value());
        return this;
    }

    public JsonResponse fail() {
        this.code(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return this;
    }

    public String json() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Program should not come here");
    }
}