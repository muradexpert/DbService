package com.litse.dbservice.exception;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class CustomErrorAtributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String,Object> errorDetails=super.getErrorAttributes(webRequest, includeStackTrace);
        errorDetails.put("message","This url doesn't exists");
        errorDetails.put("details",webRequest.getDescription(false));
        errorDetails.put("status", HttpStatus.BAD_REQUEST);
        return errorDetails;
    }
}
