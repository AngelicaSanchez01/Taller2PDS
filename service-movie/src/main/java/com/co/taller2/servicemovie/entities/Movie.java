package com.co.taller2.servicemovie.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false,nullable = false,unique = true)
    private Long id;

    @NotEmpty(message = "el titulo no puede estar vacio")
    @Column(name="title")
    private String title;

    @NotEmpty(message = "el nombre no puede estar vacio")
    @Column(name="name_director")
    private String director;

    @Size(min = 1, max = 5, message = "Debe ser entre 1 o 5 caracteres")
    @Column(name="raiting")
    private int raiting;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
