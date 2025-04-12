package pl.coderslab.finalproject.controller;

import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pl.coderslab.finalproject.dto.PlaceApiDTO;
import pl.coderslab.finalproject.dto.PlaceDTO;
import pl.coderslab.finalproject.entity.Place;
import pl.coderslab.finalproject.service.PlaceService;

import java.util.List;
import static pl.coderslab.finalproject.service.PlaceService.singlePlaceToJSON;

@Controller
@AllArgsConstructor
@RequestMapping("/geoapi")
public class PlaceApiController {
    private final PlaceService placeService;

    // do app properties @value
    private static final String API_KEY
            = "9f97967260db4356adf1836958f7f9f8";
    private static final String API_URL
            = "https://api.geoapify.com/v2/places?categories=leisure.park&filter=circle:20.966266009075426,52.2134828,5000&bias=proximity:20.966266009075426,52.2134828&limit=20&apiKey={apiKey}"; //example

    private static final String API_URL1 = "https://api.geoapify.com/v2/places?categories="; //categories
    private static final String API_URL2 = "&filter=circle:"; //coords
    private static final String API_URL3 = ",5000&bias=proximity:";//coords
    private static final String API_URL4 = "&limit=100&apiKey={apiKey}";


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

    @GetMapping("/places/{placeId}/{categories}")
    public String getPlaces(@PathVariable Long placeId, @PathVariable String categories, Model model) {
        PlaceDTO placeDTO = placeService.getByIdDTO(placeId).getBody();
        model.addAttribute("place", placeDTO);
        model.addAttribute("placeJSON", singlePlaceToJSON(placeDTO));
        String coords =  placeDTO.getLongitude() + "," + placeDTO.getLatitude();

        RestTemplate rest = new RestTemplate();
        String API_URL = API_URL1 + categories
                + API_URL2 + coords
                + API_URL3 + coords
                + API_URL4;

        ResponseEntity<String> exchange = rest.exchange(
                API_URL,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                String.class,
                API_KEY);

        JSONObject root = new JSONObject(exchange.getBody());
//        List<PlaceApiDTO> placeApiDTOS = PlaceApiDTO.JSONtoDTOwithName(root);
//        model.addAttribute("placeApiDTOS", placeApiDTOS);
        model.addAttribute("placeApiDTOS", PlaceApiDTO.JSONWithNames(root));
        return "map_places_nearby";

    }

}
