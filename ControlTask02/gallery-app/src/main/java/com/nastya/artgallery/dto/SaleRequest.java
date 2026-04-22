package com.nastya.artgallery.dto;

public class SaleRequest {

	private final Long artworkId;
	private final Long customerId;
	 private final String password;

	public SaleRequest(Long artworkId, Long customerId, String password) {
		this.artworkId = artworkId;
		this.customerId = customerId;
		this.password = password;
	}

	public Long getArtworkId() {
		return artworkId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public String getPassword() {
		return password;
	}
}
