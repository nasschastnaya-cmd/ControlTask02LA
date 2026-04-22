package com.nastya.artgallery.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Exhibition {
	private Long id;
	private String theme;
	private LocalDate startDate;
	private LocalDate endDate;
	private List<Long> artworkIds = new ArrayList<>();
	
	public Exhibition() {}
	
	public Exhibition(long id, String title, LocalDate startDate, LocalDate endDate) {
	this.id = id;
    this.theme = title;
    this.startDate = startDate;
    this.endDate = endDate;
	}
	
	public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return theme; }
    public void setTitle(String title) { this.theme = title; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public List<Long> getArtworkIds() { return artworkIds; }

   public void addArtwork(Long artworkId) {
	   if(!this.artworkIds.contains(artworkId)) {
		   this.artworkIds.add(artworkId);
	   }
	   
    }

    @Override
    public String toString() {
        return String.format("Выставка ID: %d | '%s' | С %s по %s | Картины (ID): %s", 
                id, theme, startDate, endDate, artworkIds);
    }

	@Override
	public int hashCode() {
		return Objects.hash(artworkIds, endDate, id, startDate, theme);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exhibition other = (Exhibition) obj;
		return Objects.equals(artworkIds, other.artworkIds) && Objects.equals(endDate, other.endDate)
				&& Objects.equals(id, other.id) && Objects.equals(startDate, other.startDate)
				&& Objects.equals(theme, other.theme);
	}
	

}
