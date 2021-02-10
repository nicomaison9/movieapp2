package movieapp.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.eq;

import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;



import movieapp.dto.ArtistSimple;
import movieapp.entity.Artist;
import movieapp.persistence.ArtistRepository;

@ExtendWith(MockitoExtension.class)
class TestArtistServiceJpa {
	
	
	//layer to mock
	@Mock
	ArtistRepository artistRepository;
	
	//layer to test using layer mocked
	@InjectMocks    //équivalent du autowired
	ArtistServiceJpa artistService;

	@Test
	void testGetById() {;
		// 1. given
		int id=1;
		
		String name="Will Smith";
		LocalDate birthdate = LocalDate.of(1968, 9, 25);
		//perfect answer from mock
		Artist ArtistEntity=new Artist(name, birthdate);
		ArtistEntity.setId(id);
		BDDMockito.given(artistRepository.findById(id))
			.willReturn(Optional.of(ArtistEntity));
		// 2. when
		Optional<ArtistSimple> optartistSimpleDto = artistService.GetById(id);
		// 3. then
		BDDMockito.then(artistRepository)
			.should()
			.findById(BDDMockito.eq(id));
		assertTrue(optartistSimpleDto.isPresent());
		optartistSimpleDto.ifPresent(
				optartistSimpleDto -> assertAll(
				() -> assertEquals(id,optartistSimpleDto.getId()),
				() -> assertEquals(name,optartistSimpleDto.getName()),
				() -> assertEquals(birthdate,optartistSimpleDto.getBirthdate())
				));
		
	}
	
	

}
