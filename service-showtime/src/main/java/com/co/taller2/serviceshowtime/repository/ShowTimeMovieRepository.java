package com.co.taller2.serviceshowtime.repository;

import com.co.taller2.serviceshowtime.entities.ShowTimeMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShowTimeMovieRepository  extends JpaRepository<ShowTimeMovie,Long> {
}
