package com.ruoyi.framework.config;


import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

/**
 * @author 陈君本
 * @description
 * @date 2020/12/18 17:08
 */
@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return (
                response.getStatusCode().series() == CLIENT_ERROR
                        || response.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode()
                .series() == SERVER_ERROR) {
        } else if (response.getStatusCode()
                .series() == CLIENT_ERROR) {
            // handle CLIENT_ERROR

            if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new IllegalStateException("HTTP STATUS RETURN NOT_FOUND");
            }
        }
    }
}