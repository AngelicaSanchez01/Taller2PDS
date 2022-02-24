package com.co.taller2.serviceshowtime.service;


import com.co.taller2.serviceshowtime.Client.MovieClient;
import com.co.taller2.serviceshowtime.entities.ShowTime;
import com.co.taller2.serviceshowtime.entities.ShowTimeMovie;
import com.co.taller2.serviceshowtime.model.Movie;
import com.co.taller2.serviceshowtime.repository.ShowtimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ShowTimeServiceImpl implements ShowTimeService {

    private final ShowtimeRepository showtimeRepository;
    private final MovieClient movieClient;

    @Override
    public ShowTime createShowTime(ShowTime showTime) {
        return showtimeRepository.save(showTime);
    }

    @Override
    public List<ShowTime> listAllShowTime() {
        return showtimeRepository.findAll();
    }

    @Override
    public ShowTime getShowTimeById(Long id) {
        ShowTime showtime= showtimeRepository.findById(id).orElse(null);
        if (null != showtime ){
            List<ShowTimeMovie> listItem=showtime.getMovies().stream().map(movieItem -> {
                Movie movie = movieClient.getMovie(movieItem.getMovieId()).getBody();
                movieItem.setMovie(movie);
                return movieItem;
            }).collect(Collectors.toList());
            showtime.setMovies(listItem);
        }
        return showtime;
    }

    @Override
    public void deleteShowTime(Long id) {
        showtimeRepository.deleteById(id);
    }

    @Override
    public ShowTime updateShowTime(ShowTime showTime) {

        ShowTime showTimeDB = getShowTimeById(showTime.getId());
        if (showTimeDB == null){
            return  null;
        }
        showTimeDB.setId(showTime.getId());
        showTimeDB.setDate(showTime.getDate());
        showTimeDB.getMovies().clear();
        showTimeDB.setMovies(showTime.getMovies());

        return showtimeRepository.save(showTimeDB);
    }

}

