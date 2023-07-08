package zw.co.kez.movies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.kez.movies.enums.MovieStatus;
import zw.co.kez.movies.models.Movie;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
    List<Movie> findAllByMovieStatus(MovieStatus movieStatus);
}
