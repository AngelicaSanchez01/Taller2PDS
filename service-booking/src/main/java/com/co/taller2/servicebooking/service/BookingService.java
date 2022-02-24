package com.co.taller2.servicebooking.service;

import com.co.taller2.servicebooking.entities.Booking;

import java.util.List;

public interface BookingService {

    public Booking createBooking(Booking booking);

    public List<Booking> listAllBooking();

    public Booking getBookingById(Long id);

    public Booking updateBooking(Booking booking);
    public void deleteBooking(Long id);


}
