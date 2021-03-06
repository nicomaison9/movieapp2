package movieapp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import movieapp.dto.MovieSimple;
import movieapp.entity.Movie;
import movieapp.persistence.MovieRepository;
import movieapp.service.IMovieService;

@Service
@Transactional
public class MovieServiceJpa implements IMovieService {
	@Autowired
	MovieRepository movieRepository;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public MovieSimple add(MovieSimple movie) {
		Movie movieEntityIn = modelMapper.map(movie, Movie.class);
		Movie movieEntityOut = movieRepository.save(movieEntityIn);
		MovieSimple movieDtoRes = modelMapper.map(movieEntityOut, MovieSimple.class);
		
		return movieDtoRes;
	}

	@Override
	public List<MovieSimple> getAll() {
		List<Movie> moviesEntity = movieRepository.findAll();
		List<MovieSimple> moviesDto = moviesEntity.stream()
			.map(me -> modelMapper.map(me, MovieSimple.class))
			.collect(Collectors.toList());
		return moviesDto;
	}

	@Override
	public Optional<MovieSimple> getById(int id) {
		Optional<Movie> optMovieEntity= movieRepository.findById(id);
		Optional<MovieSimple> optMovieDto=optMovieEntity.map(
				me -> modelMapper.map(me, MovieSimple.class));
		return optMovieDto;
		
	}

	@Override
	public List<Movie> findByTitle(String title) {
		List<Movie> optMovieEntity= movieRepository.findByTitle(title);
		Stream<Movie> optMovieDto=((Stream<Movie>) optMovieEntity).map(
				me -> modelMapper.map(me, Movie.class));
		return null;
	}

}
