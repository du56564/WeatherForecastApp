package com.apple.assignment.helper;

import com.apple.assignment.constants.CommonConstants;
import com.apple.assignment.constants.SearchErrors;
import com.apple.assignment.exception.WeatherForcastBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WebClientHelper {
    private RestTemplate restTemplate;

    @Autowired
    public WebClientHelper(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Object getRestData(String url) throws WeatherForcastBusinessException {
        ResponseEntity<Object> response = null;
        try {
            response = restTemplate.getForEntity(url, Object.class);
            if (response != null && response.getStatusCode().equals(HttpStatus.OK)) {
                return response.getBody();
            }
        } catch (Exception e) {
            throw new WeatherForcastBusinessException(CommonConstants.ERROR_MESSAGE_TECHNICAL, SearchErrors.CODE_INTERNAL_SERVER_ERROR, this.getClass().getSimpleName());
        }
        return response;
    }
}
