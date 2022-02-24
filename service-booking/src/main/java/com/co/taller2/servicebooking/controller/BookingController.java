package com.co.taller2.servicebooking.controller;


import com.co.taller2.servicebooking.entities.Booking;
import com.co.taller2.servicebooking.service.BookingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {


    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<Booking>> listBooking(){
        List<Booking> bookings = bookingService.listAllBooking();
        if(bookings.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bookings);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable("id") Long idBooking) {
        Booking bookings =  bookingService.getBookingById(idBooking);
        if (null==bookings){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookings);
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(@Valid @RequestBody Booking booking, BindingResult result) {
        if(result.hasErrors()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage((result)));
        }
        Booking bookingsCreate = bookingService.createBooking(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingsCreate);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking booking) {
        booking.setId(id);
        Booking bookingDB = bookingService.updateBooking(booking);
        if(bookingDB == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookingDB);
    }


    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable("id") Long id) {
        try {
            bookingService.deleteBooking(id);
            return ResponseEntity.ok("Eliminado con éxito");
        }catch(Exception e){
            return ResponseEntity.ok("No pudo eliminarse");
        }

    }

    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String,String> error = new HashMap<>();
                    error.put(err.getField(),err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors)
                .build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";

        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return  jsonString;
    }
}
