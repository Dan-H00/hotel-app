package com.example.hotel_app.service;

import com.example.hotel_app.client.AbstractApiClient;
import com.example.hotel_app.client.AwsIpClient;
import com.example.hotel_app.model.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeolocationService {
    private final AwsIpClient awsIpClient;
    private final AbstractApiClient abstractApiClient;

    private static final String API_KEY = "1df68f93a6e2462eba5ad07418197cb7";

    public Double[] getCoordinates() {
        String ip = awsIpClient.getIp().trim();
        Root root = abstractApiClient.getCoords(API_KEY, ip);

        return new Double[] {root.getLatitude(), root.getLongitude()};
    }
}
