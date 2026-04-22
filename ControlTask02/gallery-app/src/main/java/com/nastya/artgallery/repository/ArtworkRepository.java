package com.nastya.artgallery.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.nastya.artgallery.model.Artwork;

public class ArtworkRepository implements Repository<Artwork> {
	private final List<Artwork> artworks = new ArrayList<>();
	
	@Override
	public void save(Artwork artwork) {
		artworks.add(artwork);
	}
	
	@Override
	public List<Artwork> findAll(){
		return new ArrayList<>(artworks);
		}
	@Override
	public Optional<Artwork>findById(Long id){
		return artworks.stream().filter(a->a.getId().equals(id)).findFirst();
	}
	@Override
	public void deleteById(Long id) {
		artworks.removeIf(a -> a.getId().equals(id));
	}
	

}
