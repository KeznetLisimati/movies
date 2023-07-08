package zw.co.kez.movies.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zw.co.kez.movies.dtos.MovieDTO;
import zw.co.kez.movies.models.Movie;
import zw.co.kez.movies.services.MovieService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movie")
@RequiredArgsConstructor
@Slf4j
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<String> saveMovie(@Valid @RequestBody MovieDTO movieDTO) {
        log.info("Received this payload inside controller -> {}", movieDTO);
        movieService.saveMovie(movieDTO);
        return new ResponseEntity<>("Movie " + movieDTO.getName() + " was saved successfully", HttpStatus.CREATED);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Movie>> findAllMovies() {
        log.debug("inside MoviesController.findAllMovies() method");
        List<Movie> movies = movieService.findAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Movie> findById(@PathVariable("id") Long id) {
        Movie movie = movieService.findMovieById(id);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteMovie() {
        movieService.deleteAllMovies();
        return new ResponseEntity<>("All movies successfully deleted", HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") MovieDTO movieDTO, Long id) {
        movieService.deleteMovieById(id);
        return new ResponseEntity<>("Movie " + movieDTO.getName() + " deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/updateMovie/{id}")
    public ResponseEntity<String> updateMovie(@PathVariable("id") MovieDTO movieDTO, Long id) {
        log.info("Received this payload to update -> {}", movieDTO);
        movieService.updateMovie(movieDTO, id);
        return new ResponseEntity<>("Movie " + movieDTO.getName() + " was updated successfully", HttpStatus.OK);
    }
}
