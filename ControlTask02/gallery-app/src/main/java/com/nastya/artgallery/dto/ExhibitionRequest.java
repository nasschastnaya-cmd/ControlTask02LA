package com.nastya.artgallery.dto;

public class ExhibitionRequest {
	private final Long artworkId;
	private final Long exhibitionId;

	public ExhibitionRequest(Long artworkId, Long exhibitionId) {
		this.artworkId = artworkId;
		this.exhibitionId = exhibitionId;
	}

	public Long getArtworkId() {
		return artworkId;
	}

	public Long getExhibitionId() {
		return exhibitionId;
	}
}
