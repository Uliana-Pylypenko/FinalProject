package pl.coderslab.finalproject.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pl.coderslab.finalproject.dto.PlaceApiDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class PlaceApiController {
    private static final String API_KEY
            = "9f97967260db4356adf1836958f7f9f8";
    private static final String API_URL
            = "https://api.geoapify.com/v2/places?categories=leisure.park&filter=circle:20.966266009075426,52.2134828,5000&bias=proximity:20.966266009075426,52.2134828&limit=20&apiKey={apiKey}";
         //   = "https://apiv3.apifootball.com/?action=get_countries&APIkey={apiKey}";
    

    @GetMapping("/test")
    public String callGet() {
        RestTemplate rest = new RestTemplate();
        ResponseEntity<String> exchange = rest.exchange(
                API_URL,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                String.class,
                API_KEY);

        return exchange.getBody();
    }

    @GetMapping("/geoapi/places")
    public List<PlaceApiDTO> getPlaces() {
        RestTemplate rest = new RestTemplate();
        ResponseEntity<String> exchange = rest.exchange(
                API_URL,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                String.class,
                API_KEY);

        JSONObject root = new JSONObject(exchange.getBody());
        return PlaceApiDTO.JSONtoDTO(root);

    }

}
