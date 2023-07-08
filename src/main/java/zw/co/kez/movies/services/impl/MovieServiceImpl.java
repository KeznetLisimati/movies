package zw.co.kez.movies.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.kez.movies.dtos.MovieDTO;
import zw.co.kez.movies.enums.MovieStatus;
import zw.co.kez.movies.models.Category;
import zw.co.kez.movies.models.Movie;
import zw.co.kez.movies.repositories.CategoryRepository;
import zw.co.kez.movies.repositories.MovieRepository;
import zw.co.kez.movies.services.MovieService;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public void saveMovie(MovieDTO movieDTO) {

        Category category = Category.builder()
                .name(movieDTO.getCategory().getName())
                .description(movieDTO.getCategory().getDescription())
                .build();

        categoryRepository.save(category);

        Movie newMovie = Movie.builder()
                .name(movieDTO.getName())
                .description(movieDTO.getDescription())
                .director(movieDTO.getDirector())
                .publisher(movieDTO.getPublisher())
                .datePublished(movieDTO.getDatePublished())
                .movieStatus(movieDTO.getMovieStatus() == null ? MovieStatus.ACTIVE : movieDTO.getMovieStatus())
                //.category(category)
                .build();

        log.info("Just b4 saving the movie -> {}", newMovie);

        movieRepository.save(newMovie);
    }

    @Override
    public List<Movie> findAllMovies() {
        log.debug("inside findAllMovies() method");
        return movieRepository.findAllByMovieStatus(MovieStatus.ACTIVE);
    }
    @Override
    public Movie findMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow();
    }

    @Override
    public void deleteAllMovies() {
        List<Movie> movies = findAllMovies();
        for (Movie movie : movies){
            movie.setMovieStatus(MovieStatus.INACTIVE);

            movieRepository.save(movie);
        }
    }

    @Override
    public void deleteMovieById(Long id) {
        Movie movie = findMovieById(id);
        movie.setMovieStatus(MovieStatus.INACTIVE);

        movieRepository.save(movie);
    }

    @Override
    public void updateMovie(MovieDTO movieDTO, Long id) {
        Movie updatedMovie = movieRepository.findById(id).orElseThrow();
        updatedMovie.setName(movieDTO.getName() == null? updatedMovie.getName() : movieDTO.getName());
        updatedMovie.setDescription(movieDTO.getDescription() == null? updatedMovie.getDescription() : movieDTO.getDescription());
        updatedMovie.setPublisher(movieDTO.getPublisher() == null? updatedMovie.getPublisher() : movieDTO.getPublisher());
        updatedMovie.setDirector(movieDTO.getDirector() == null? updatedMovie.getDirector() : movieDTO.getDirector());
        updatedMovie.setDatePublished(movieDTO.getDatePublished() == null? updatedMovie.getDatePublished() : movieDTO.getDatePublished());

        log.info("Just b4 updating the movie -> {}", updatedMovie);
        movieRepository.save(updatedMovie);
    }
}
