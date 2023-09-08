package com.csc340.Assignment2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Assignment2Application {

	public static void main(String[] args) {
		SpringApplication.run(Assignment2Application.class, args);
		APITest();
	}

	public static void APITest() {
		try {
			String url = "https://api.zippopotam.us/us/27214";
			RestTemplate restTemplate = new RestTemplate();
			ObjectMapper mapper = new ObjectMapper();

			String jSonPrice = restTemplate.getForObject(url, String.class);
			JsonNode root = mapper.readTree(jSonPrice);

			//gets the name of the place the postal code is from
			String placeName = root.findValue("place name").asText();
			//gets the longitude and latitude of the place
			double longitude = root.findValue("longitude").asDouble();
			double latitude = root.findValue("latitude").asDouble();
			//print the data we retrieved from the api call
			System.out.println("Place Name: " + placeName);
			System.out.println("Longitude: " + longitude);
			System.out.println("Latitude: " + latitude);
		} catch (JsonProcessingException ex) {
			System.out.println("Json Processing Exception");
		}
	}

}
