package com.example.hotel_app.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "awsIpClient", url = "http://checkip.amazonaws.com")
public interface AwsIpClient {
    @GetMapping
    String getIp();
}
