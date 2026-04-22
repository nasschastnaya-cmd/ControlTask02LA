package com.nastya.artgallery.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
	void save(T item);
	List<T> findAll();
	Optional<T> findById(Long id);
	void deleteById(Long id);

}
