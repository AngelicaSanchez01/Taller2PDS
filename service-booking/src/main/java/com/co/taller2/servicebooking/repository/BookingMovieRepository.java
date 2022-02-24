package com.co.taller2.servicebooking.repository;


import com.co.taller2.servicebooking.entities.BookingMovie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingMovieRepository extends JpaRepository<BookingMovie, Long> {
}
