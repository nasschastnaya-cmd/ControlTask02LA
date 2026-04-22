package com.nastya.artgallery.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.nastya.artgallery.model.Artist;

public class ArtistRepository implements Repository<Artist> {
	
	private final List<Artist> artists = new ArrayList<>();
	
	public void save (Artist artist) {
		artists.add(artist);
	}
	public List<Artist>findAll(){
		return new ArrayList<>(artists);
	}
	public Optional<Artist>findById(Long id){
		return artists.stream().filter(a -> a.getId().equals(id)).findFirst();
	}
	public void deleteById(Long id) {
		artists.removeIf(a->a.getId().equals(id));
	}

}
