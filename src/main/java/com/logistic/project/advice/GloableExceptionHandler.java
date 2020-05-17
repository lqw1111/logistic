package com.logistic.project.advice;

import com.logistic.project.dto.JsonResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GloableExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResponse defultExcepitonHandler(HttpServletRequest request, Exception e) {
        return new JsonResponse().fail().message(e.getMessage());
    }

}
