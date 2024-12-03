package com.scaler.productservicejune24.commons;

import com.scaler.productservicejune24.dtos.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthUtil {
    private RestTemplate restTemplate;

    public AuthUtil(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDto validateToken(String tokenValue) {
        return restTemplate.getForObject(
                "http://USERSERVICENOV24/users/validate/" + tokenValue,
                UserDto.class
        );
    }
}
