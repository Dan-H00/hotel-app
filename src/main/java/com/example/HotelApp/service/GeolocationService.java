package com.example.HotelApp.service;

import com.example.HotelApp.client.AbstractApiClient;
import com.example.HotelApp.client.AwsIpClient;
import com.example.HotelApp.model.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeolocationService {
    private final AwsIpClient awsIpClient;
    private final AbstractApiClient abstractApiClient;

    public Double[] getCoordinates() {
        String ip = awsIpClient.getIp();
        System.out.println(ip);
        Root root = abstractApiClient.getCoords(ip);

        return new Double[] {root.getLatitude(), root.getLongitude()};
    }
}
