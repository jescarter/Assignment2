package com.csc340.Assignment2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/apidemo")
public class APIDemoController {

    @GetMapping("/whereis")
    public Object whereIs(
            @RequestParam(value = "country", defaultValue = "us") String country,
            @RequestParam(value = "zip", defaultValue = "27214") int zip
    ) {
        try {
            String url = "https://api.zippopotam.us/";
            url = url.concat(country + "/");
            url = url.concat(Integer.toString(zip));
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jSonPrice = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonPrice);

            //gets the name of the place the postal code is from
            String placeName = root.findValue("place name").asText();
            //gets the longitude and latitude of the place
            double longitude = root.findValue("longitude").asDouble();
            double latitude = root.findValue("latitude").asDouble();
            //print the data we retrieved from the api call to console
            System.out.println("Place Name: " + placeName);
            System.out.println("Longitude: " + longitude);
            System.out.println("Latitude: " + latitude);

            return root;
        } catch (JsonProcessingException ex) {
            System.out.println("Json Processing Exception");
        }
        return "Finished Request";
    }

}
