package com.example.HotelApp.client;

import com.example.HotelApp.model.Root;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "abstractApi", url = "https://ipgeolocation.abstractapi.com")
public interface AbstractApiClient {
    @GetMapping(value = "/v1/?api_key=1df68f93a6e2462eba5ad07418197cb7&ip_address={ipAddress}", consumes = "application/json")
    Root getCoords(@PathVariable String ipAddress);
}
