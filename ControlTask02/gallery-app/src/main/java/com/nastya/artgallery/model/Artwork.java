package com.nastya.artgallery.model;

import java.util.Objects;

public class Artwork {

	private Long id;
	private String title;
	private String style;
	private Long artistId;
	private double price;
	private boolean isSold;
	
	public Artwork() {}
	
	public Artwork(Long id, String title, String style, Long artistId, double price, boolean isSold) {
		this.id = id;
		this.title = title;
		this.style = style;
		this.artistId = artistId;
		this.price = price;
		this.isSold = isSold;
	}
	public Long getId() {return id;}
	public String getTitle() { return title;}
	public String getStyle() {return style;}
	public Long getArtistId() {return artistId;}
	public double getPrice() { return price;}
	public boolean isSold() {return isSold;}
	
	public void setSold(boolean sold) { isSold = sold;}
	public void setPrice(double price) { this.price = price;}
	

	@Override
	public int hashCode() {
		return Objects.hash(artistId, id, isSold, price, style, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artwork other = (Artwork) obj;
		return Objects.equals(artistId, other.artistId) && Objects.equals(id, other.id) && isSold == other.isSold
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Objects.equals(style, other.style) && Objects.equals(title, other.title);
	}
	@Override
	public String toString() {
		return String.format("ID: %d | '%s' | Стиль: %s | Цена: %.2f | Статус: %s", 
                id, title, style, price, (isSold ? "Продана" : "В наличии"));
	}
	
	
	
	
}
