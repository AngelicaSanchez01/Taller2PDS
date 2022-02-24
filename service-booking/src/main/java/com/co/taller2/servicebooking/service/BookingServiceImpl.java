package com.co.taller2.servicebooking.service;


import com.co.taller2.servicebooking.client.MovieClient;
import com.co.taller2.servicebooking.client.ShowTimeClient;
import com.co.taller2.servicebooking.client.UserClient;
import com.co.taller2.servicebooking.entities.Booking;
import com.co.taller2.servicebooking.entities.BookingMovie;
import com.co.taller2.servicebooking.model.Movie;
import com.co.taller2.servicebooking.model.ShowTime;
import com.co.taller2.servicebooking.model.User;
import com.co.taller2.servicebooking.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserClient userClient;
    private final ShowTimeClient showTimeClient;
    private final MovieClient movieClient;

    @Override
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> listAllBooking() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(Long id) {
        Booking booking= bookingRepository.findById(id).orElse(null);
        if (null != booking ){
            User user = userClient.getUser(booking.getUserId()).getBody();
            booking.setUser(user);
            ShowTime showTime = showTimeClient.getShowtime(booking.getShowtimeId()).getBody();
            booking.setShowTime(showTime);
            List<BookingMovie> listItem=booking.getMovies().stream().map(movieItem -> {
                Movie movie = movieClient.getMovie(movieItem.getMovieId()).getBody();
                movieItem.setMovie(movie);
                return movieItem;
            }).collect(Collectors.toList());
            booking.setMovies(listItem);
        }
        return booking;
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public Booking updateBooking(Booking booking) {

        Booking bookingDB = getBookingById(booking.getId());
        if (bookingDB == null){
            return  null;
        }
        bookingDB.setId(booking.getId());
        bookingDB.setUserId(booking.getUserId());
        bookingDB.setShowtimeId(booking.getShowtimeId());
        bookingDB.getMovies().clear();
        bookingDB.setMovies(booking.getMovies());

        return bookingRepository.save(bookingDB);
    }


}
