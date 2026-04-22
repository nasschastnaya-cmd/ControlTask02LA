package com.nastya.artgallery.service;

import java.util.List;

import com.nastya.artgallery.dto.ExhibitionRequest;
import com.nastya.artgallery.dto.RegisterRequest;
import com.nastya.artgallery.dto.SaleRequest;
import com.nastya.artgallery.dto.SearchRequest;
import com.nastya.artgallery.model.Artist;
import com.nastya.artgallery.model.Artwork;
import com.nastya.artgallery.model.Customer;
import com.nastya.artgallery.model.Exhibition;

public interface GalleryService {
	
	 void sellArtwork(SaleRequest request);
	    void addArtworkToExhibition(ExhibitionRequest request);
	    void registerArtist(RegisterRequest<Artist> request);
	    void registerArtwork(RegisterRequest<Artwork> request);
	    void registerCustomer(RegisterRequest<Customer> request);

	    
	    List<Artwork> getAvailableArtworks();
	    List<Artwork> getArtworksByStyle(SearchRequest<String> request);
	    List<Artwork> getArtworksOnExhibition(SearchRequest<Long> request);
	    List<Artwork> getArtworksWithinBudget(SearchRequest<Double> request);
	    
	    List<Exhibition> getAllExhibitions();
	    List<Exhibition> getExhibitionsByDate(SearchRequest<java.time.LocalDate> request);
	}
	


