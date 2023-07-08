package zw.co.kez.movies.services;
import zw.co.kez.movies.dtos.MovieDTO;
import zw.co.kez.movies.models.Movie;

import java.util.List;

public interface MovieService {
     void saveMovie(MovieDTO movieDTO);

     List<Movie> findAllMovies();

     Movie findMovieById(Long id);

     void deleteAllMovies();

     void deleteMovieById(Long id);

     void updateMovie(MovieDTO movieDTO, Long id);
}
