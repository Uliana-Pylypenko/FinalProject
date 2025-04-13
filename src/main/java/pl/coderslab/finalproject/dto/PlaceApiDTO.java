package pl.coderslab.finalproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//@JsonIgnoreProperties(ignoreUnknown = true) // to ignore fields which are not listed in dto
@Getter
@Setter
@ToString
public class PlaceApiDTO {
    private String place_id;
    private String name;
    private String country;
    private String city;
    private String address;
    private double longitude;
    private double latitude;
    private List<String> categories;
    private int distance; //m

    public static JSONArray JSONWithNames(JSONObject root) {
        JSONArray array = root.getJSONArray("features");
        JSONArray resultArray = new JSONArray();
        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonObject = array.getJSONObject(i).optJSONObject("properties");
            if (jsonObject.has("name")) {
                JSONObject resultJSON = new JSONObject();
                resultJSON.put("lat", jsonObject.getDouble("lat"));
                resultJSON.put("lon", jsonObject.getDouble("lon"));
                resultJSON.put("name", jsonObject.getString("name").replace("\"", ""));
                if (jsonObject.has("formatted")) {
                    resultJSON.put("address", jsonObject.getString("formatted").replace("\"", ""));
                }
                if (jsonObject.has("distance")) {
                    resultJSON.put("distance", jsonObject.getInt("distance"));
                }
                resultArray.put(resultJSON);
            }
        }
        return resultArray;
    }

    public static List<PlaceApiDTO> JSONtoDTO(JSONObject root) {
        JSONArray array = root.getJSONArray("features");

        List<PlaceApiDTO> placeApiDTOS = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonObject = array.getJSONObject(i).optJSONObject("properties");
            PlaceApiDTO placeApiDTO = new PlaceApiDTO();
            placeApiDTO.setCountry(jsonObject.getString("country"));
            placeApiDTO.setPlace_id(jsonObject.getString("place_id"));
            try {
                String name = jsonObject.getString("name");
                placeApiDTO.setName(name);
            } catch (JSONException e) {
                e.getMessage();
            }
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

    public static List<PlaceApiDTO> JSONtoDTOwithName(JSONObject root) {
        JSONArray array = root.getJSONArray("features");

        List<PlaceApiDTO> placeApiDTOS = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonObject = array.getJSONObject(i).optJSONObject("properties");
            if (jsonObject.has("name")) {
                PlaceApiDTO placeApiDTO = new PlaceApiDTO();
                placeApiDTO.setCountry(jsonObject.getString("country"));
                placeApiDTO.setPlace_id(jsonObject.getString("place_id"));
                placeApiDTO.setName(jsonObject.optString("name"));
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
        }
        return placeApiDTOS;

    }
}
