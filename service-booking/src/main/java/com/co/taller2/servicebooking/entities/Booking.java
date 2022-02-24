package com.co.taller2.servicebooking.entities;


import com.co.taller2.servicebooking.model.Movie;
import com.co.taller2.servicebooking.model.ShowTime;
import com.co.taller2.servicebooking.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false,nullable = false,unique = true)
    private Long id;

    @Column(name = "userId" ,nullable = false)
    private Long userId;
    @Transient
    private User user;

    @Column(name = "showtime_id", nullable = false)
    private Long showtimeId;

    @Transient
    private ShowTime showTime;

    @Valid
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_id")
    private List<BookingMovie> movies;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
