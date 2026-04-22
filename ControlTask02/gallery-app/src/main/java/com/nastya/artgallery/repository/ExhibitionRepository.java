package com.nastya.artgallery.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.nastya.artgallery.model.Exhibition;

public class ExhibitionRepository implements Repository<Exhibition> {
	
	private final List<Exhibition> exhibitions = new ArrayList<>();
	
	public void save (Exhibition exhibition) {
		exhibitions.add(exhibition);
	}
	public List<Exhibition>findAll(){
		return new ArrayList<>(exhibitions);
	}
	public Optional<Exhibition>findById(Long id){
		return exhibitions.stream().filter(e -> e.getId().equals(id)).findFirst();
	}
	public void deleteById (Long id) {
		exhibitions.removeIf(e->e.getId().equals(id));
	}

}
