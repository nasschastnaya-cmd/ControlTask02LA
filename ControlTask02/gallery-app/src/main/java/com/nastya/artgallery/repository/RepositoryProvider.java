package com.nastya.artgallery.repository;


import com.nastya.artgallery.model.Artist;
import com.nastya.artgallery.model.Artwork;
import com.nastya.artgallery.model.Customer;
import com.nastya.artgallery.model.Exhibition;

public class RepositoryProvider {
	private static RepositoryProvider instance = new RepositoryProvider();
	private RepositoryProvider() {}
	
	private final Repository<Artwork> artworkRepo = new ArtworkRepository();
	private final Repository<Artist> artistRepo = new ArtistRepository();
	private final Repository<Exhibition> exhibitionRepo = new ExhibitionRepository();
	private final Repository<Customer> customerRepo = new CustomerRepository();
	
	
	 public  Repository<Artwork> getArtworkRepository() { return artworkRepo; }
	 public  Repository<Artist> getArtistRepository() { return artistRepo; }
	 public  Repository<Exhibition> getExhibitionRepository() { return exhibitionRepo; }
	 public  Repository<Customer> getCustomerRepository() { return customerRepo; }
	 
	 public static RepositoryProvider getInstance() {
		 return instance;
	 }
	 public void initData() {
		 try {
		        FileDataLoader.loadArtists("artists.txt").forEach(artistRepo::save);
		        FileDataLoader.loadArtworks("artworks.txt").forEach(artworkRepo::save);
		        FileDataLoader.loadCustomers("customers.txt").forEach(customerRepo::save);
		        FileDataLoader.loadExhibitions("exhibitions.txt").forEach(exhibitionRepo::save);

		        System.out.println("[SYSTEM]: All data successfully loaded from files.");
		    } catch (java.io.IOException e) {
		        System.err.println("[ERROR]: Failed to load initial data: " + e.getMessage());
		    }
	 }
}

