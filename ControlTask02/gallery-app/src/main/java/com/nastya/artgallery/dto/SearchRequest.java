package com.nastya.artgallery.dto;

public class SearchRequest<T> {

	private final T criteria;

	public SearchRequest(T criteria) {
		this.criteria = criteria;
	}

	public T getCriteria() {
		return criteria;
	}
}
