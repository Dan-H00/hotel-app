package com.example.HotelApp.controller;

import com.example.HotelApp.model.Root;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.*;
import java.io.*;

@RestController
public class GeolocationController {
    private String ip;
    private final String api = "https://ipgeolocation.abstractapi.com/v1/?api_key=1df68f93a6e2462eba5ad07418197cb7&ip_address=";
    private Double[] coords = new Double[2];

    @GetMapping("/latAndLong")
    private Double[] findLatAndLong() throws IOException, InterruptedException {
        URL whatIsMyIp = new URL("http://checkip.amazonaws.com");
        BufferedReader in = new BufferedReader(new InputStreamReader(
                whatIsMyIp.openStream()));
        ip = in.readLine();
        String url = api + ip;
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        Root root = mapper.readValue(response.body(), Root.class);
        coords[0] = root.getLatitude();
        coords[1] = root.getLongitude();
        return coords;
    }
}
