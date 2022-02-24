package com.co.taller2.servicebooking.client;



import com.co.taller2.servicebooking.model.ShowTime;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="service-showtime")
public interface ShowTimeClient {

    @GetMapping("/cine/v1/showtimes/{id}")
    public ResponseEntity<ShowTime> getShowtime(@PathVariable("id") Long id);
}
