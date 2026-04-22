package com.nastya.artgallery.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Artist {
	
	private Long id;
	private String name;
	private String country;
	private List<Long> artworkIds = new ArrayList<>();
	
	public Artist() {}
	
	public Artist (Long id, String name, String country) {
		this.id = id;
		this.name = name;
		this.country = country;
	}
	public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя художника не может быть пустым");
        }
        this.name = name;
    }
    
    public String getCountry() { return country; }
    public List<Long> getArtworkIds() { return artworkIds; }

    public void addArtwork(Long artworkId) {
        this.artworkIds.add(artworkId);
    }
    @Override
    public String toString() {
        return String.format("Художник: %s (%s), Работ в базе: %d", 
                name, country, artworkIds.size());
    }

	@Override
	public int hashCode() {
		return Objects.hash(artworkIds, country, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artist other = (Artist) obj;
		return Objects.equals(artworkIds, other.artworkIds) && Objects.equals(country, other.country)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
    

	
}
