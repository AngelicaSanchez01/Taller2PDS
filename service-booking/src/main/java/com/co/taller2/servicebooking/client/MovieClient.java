package com.co.taller2.servicebooking.client;


import com.co.taller2.servicebooking.model.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="service-movie")
public interface MovieClient {

    @GetMapping("/cine/v1/movies/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable("id") Long id);
}