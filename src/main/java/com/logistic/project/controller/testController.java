package com.logistic.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping(value = "/test")
public class testController {

    private static final Logger logger = LoggerFactory.getLogger(testController.class);

    @Value("${livebarn.static.data.manager.config.api.protocol:http://}")
    private String staticDataManagerConfigApiProtocol;

    @Value("${livebarn.static.data.manager.config.api.host:localhost:9000}")
    private String staticDataManagerConfigApiHost;

    @Value("${livebarn.static.data.manager.config.api.path:/api/v1.0.0/config}")
    private String staticDataManagerConfigApiPath;

    @Value("${livebarn.static.data.manager.config.api.lastmodifed.path:/lastmodified}")
    private String staticDataManagerConfigApiLastModifiedPath;

    private RestTemplate restTemplate;

    @PostConstruct
    public void init() {
        restTemplate = new RestTemplate();
    }


    @GetMapping(value = "/config")
    public Object getConfig() {
        Object res = null;
        try {
            String url = staticDataManagerConfigApiProtocol + staticDataManagerConfigApiHost + staticDataManagerConfigApiPath;
            res = restTemplate.getForObject(url, Object.class);
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
        }
        return res;
    }

    @GetMapping(value = "/config/lastmodified")
    public Object getLastModified() {
        Object res = null;
        try {
            String url = staticDataManagerConfigApiProtocol + staticDataManagerConfigApiHost + staticDataManagerConfigApiPath + staticDataManagerConfigApiLastModifiedPath;
            res = restTemplate.getForObject(url, Object.class);
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
        }
        return res;
    }
}
