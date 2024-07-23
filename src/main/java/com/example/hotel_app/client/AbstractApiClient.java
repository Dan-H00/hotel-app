package com.example.hotel_app.client;

import com.example.hotel_app.model.Root;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "abstractApi", url = "https://ipgeolocation.abstractapi.com")
public interface AbstractApiClient {
    @GetMapping(value = "/v1")
    Root getCoords(@RequestParam(name = "api_key") String apiKey, @RequestParam(name = "ip_address") String ipAddress);
}
