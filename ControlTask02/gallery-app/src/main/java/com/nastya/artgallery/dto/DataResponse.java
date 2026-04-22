package com.nastya.artgallery.dto;

import java.util.List;

public class DataResponse<T> {
	private final List<T> data;
	private final String message;

	public DataResponse(List<T> data, String message) {
		this.data = data;
		this.message = message;
	}

	public List<T> getData() {
		return data;
	}

	public String getMessage() {
		return message;
	}
}
