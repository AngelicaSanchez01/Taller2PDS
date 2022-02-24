package com.co.taller2.servicebooking.client;


import com.co.taller2.servicebooking.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-user")
public interface UserClient {

    @GetMapping("/store/v1/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id);
}
