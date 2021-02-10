package movieapp.service;

import java.util.Optional;

import movieapp.dto.ArtistSimple;

public interface IArtistService {
	Optional<ArtistSimple> GetById(int id);
}
