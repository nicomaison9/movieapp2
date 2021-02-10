package movieapp.service;

import java.util.List;
import java.util.Optional;

import movieapp.dto.MovieSimple;
import movieapp.entity.Movie;

public interface IMovieService {
	MovieSimple add(MovieSimple movie);
	List<MovieSimple> getAll();
	Optional<MovieSimple> getById(int id);
	List<Movie> findByTitle(String title);
}
