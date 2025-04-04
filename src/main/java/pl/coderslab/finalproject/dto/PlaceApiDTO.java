package pl.coderslab.finalproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//@JsonIgnoreProperties(ignoreUnknown = true) // to ignore fields which are not listed in dto
@Getter
@Setter
@ToString
public class PlaceApiDTO {
    private String place_id;
    private String country;
    private String city;
    private String address;
    private double longitude;
    private double latitude;
    private List<String> categories;
    private int distance; //m

    public static List<PlaceApiDTO> JSONtoDTO(JSONObject root) {
        JSONArray array = root.getJSONArray("features");

        List<PlaceApiDTO> placeApiDTOS = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonObject = array.getJSONObject(i).optJSONObject("properties");
            PlaceApiDTO placeApiDTO = new PlaceApiDTO();
            placeApiDTO.setCountry(jsonObject.getString("country"));
            placeApiDTO.setPlace_id(jsonObject.getString("place_id"));
            placeApiDTO.setCity(jsonObject.optString("city"));
            placeApiDTO.setAddress(jsonObject.optString("formatted"));
            placeApiDTO.setLongitude(jsonObject.getDouble("lon"));
            placeApiDTO.setLatitude(jsonObject.getDouble("lat"));
            placeApiDTO.setDistance(jsonObject.getInt("distance"));

            JSONArray categoriesJson = jsonObject.optJSONArray("categories");
            List<String> categoriesList = new ArrayList<>();
            for (int j = 0; j < categoriesJson.length(); j++) {
                categoriesList.add(categoriesJson.getString(j));
            }

            placeApiDTO.setCategories(categoriesList);
            placeApiDTOS.add(placeApiDTO);
        }

        return placeApiDTOS;

    }
}
