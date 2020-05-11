package com.logistic.project.controller;

import com.logistic.project.dto.JsonResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
public class LoginController {

    @RequestMapping("/login")
    public JsonResponse loginPage() {
        // request this page means the user is asked to login
        // status code 401: https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/401
        return new JsonResponse().code(401).message("please login");
    }
}
