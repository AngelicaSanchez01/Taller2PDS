package com.co.taller2.serviceshowtime.entities;


import com.co.taller2.serviceshowtime.model.Movie;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "showtimes")
public class ShowTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false,nullable = false,unique = true)
    private Long id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Valid
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "showtime_id")
    private List<ShowTimeMovie> movies;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShowTime showTime = (ShowTime) o;
        return Objects.equals(id, showTime.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
