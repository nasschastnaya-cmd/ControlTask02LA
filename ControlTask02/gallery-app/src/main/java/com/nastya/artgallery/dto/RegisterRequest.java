package com.nastya.artgallery.dto;

public class RegisterRequest<T> {
	private final T entity;

	public RegisterRequest(T entity) {
		this.entity = entity;
	}

	public T getEntity() {
		return entity;
	}
}
