package modelMapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import movieapp.dto.MovieSimple;
import movieapp.entity.Movie;

class TestModelMapper {
	static ModelMapper modelMapper;

	
	@BeforeAll
	static void initModelMapper() {
		modelMapper = new ModelMapper();
	}
	
	@Test
	void testEntityDto() {
		Movie movieEntity =new Movie("dfsdfd",1982,153);
		movieEntity.setId(1);
		//convert to dto
		MovieSimple movieDto = modelMapper.map(movieEntity, MovieSimple.class);
		
		System.out.println(movieDto.getTitle());
		assertEquals(movieEntity.getId(),movieDto.getId());
		assertEquals(movieEntity.getTitle(),movieDto.getTitle());
		assertEquals(movieEntity.getYear(),movieDto.getYear());
	}
	@Test
	void testDtoToEntity() {
		MovieSimple movieDto=new MovieSimple();
		movieDto.setTitle("BladeRunner");
		movieDto.setYear(1982);
		//convert to Entity
		Movie movieEntity=  modelMapper.map(movieDto, Movie.class);
		
		System.out.println(movieEntity);
		assertEquals(movieDto.getId(),movieEntity.getId());
		assertEquals(movieDto.getTitle(),movieEntity.getTitle());
		assertEquals(movieDto.getYear(),movieEntity.getYear());
		assertNull(movieEntity.getDirector());
		assertTrue(movieEntity.getActors().isEmpty());
	}
	
	@Test
	void testDtoinToEntity() {
		MovieSimple movieDto=new MovieSimple();
		movieDto.setTitle("BladeRunner dfdsf");
		movieDto.setYear(1982);
		movieDto.setId(1);
		//convert to Entity
		Movie movieEntity=  modelMapper.map(movieDto, Movie.class);
		
		System.out.println(movieEntity);
		assertEquals(movieDto.getId(),movieEntity.getId());
		assertEquals(movieDto.getTitle(),movieEntity.getTitle());
		assertEquals(movieDto.getYear(),movieEntity.getYear());
		assertNull(movieEntity.getDirector());
		assertTrue(movieEntity.getActors().isEmpty());
	}
	

}
