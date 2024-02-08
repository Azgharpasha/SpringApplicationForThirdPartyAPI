package com.stackroute.resttemplate.service;

import com.stackroute.resttemplate.controller.WeatherController;
import com.stackroute.resttemplate.model.Weather;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Service
public class WeatherService {

    //add your api key here
    private static final String API_KEY = "0e6ab43b0c614e1660a3218d61e7b3d8";

    //add the base api url here
    private static final String API_URL = "http://api.weatherstack.com/current";

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //using rest template, get the weather details of a city
    public Weather getWeather(String city) {
        Weather weather = null;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(API_URL)
                .queryParam("access_key", API_KEY)
                .queryParam("query", city);
        String urlWithParams = builder.toUriString();

        ResponseEntity<Weather> responseEntity=restTemplate.exchange(urlWithParams, HttpMethod.GET,null,Weather.class);
        return responseEntity.getBody();
    }
//    public HttpEntity<?> getEntity(){
//        HttpHeaders headers=new HttpHeaders();
//        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
//        return new HttpEntity<>(headers);
//    }
}
