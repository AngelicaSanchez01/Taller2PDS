package com.co.taller2.servicemovie.service;

import com.co.taller2.servicemovie.entities.Movie;

import java.util.List;

public interface MovieService {

    void save(Movie movie);
    void delete(Movie movie);
    List<Movie> findAll();
    Movie findById(Long id);
}
