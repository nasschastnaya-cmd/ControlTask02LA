package com.nastya.artgallery.service;

import com.nastya.artgallery.dto.ExhibitionRequest;
import com.nastya.artgallery.dto.RegisterRequest;
import com.nastya.artgallery.dto.SaleRequest;
import com.nastya.artgallery.dto.SearchRequest;
import com.nastya.artgallery.model.*;
import com.nastya.artgallery.repository.*;
import java.util.List;

public class GalleryServiceImpl implements GalleryService {
	private final RepositoryProvider provider = RepositoryProvider.getInstance();

	private final Repository<Artwork> artworkRepo = provider.getArtworkRepository();
	private final Repository<Artist> artistRepo = provider.getArtistRepository();
	private final Repository<Exhibition> exhibitionRepo = provider.getExhibitionRepository();
	private final Repository<Customer> customerRepo = provider.getCustomerRepository();

	@Override
	public void sellArtwork(SaleRequest request) {
		Artwork artwork = artworkRepo.findById(request.getArtworkId())
				.orElseThrow(() -> new RepositoryException("Artwork not found"));

		Customer customer = customerRepo.findById(request.getCustomerId())
				.orElseThrow(() -> new RepositoryException("Customer not found"));

		if (!customer.getPassword().equals(request.getPassword())) {
			throw new ServiceException("Invalid password");
		}

		if (artwork.isSold()) {
			throw new ServiceException("Artwork is already sold");
		}
		artwork.setSold(true);
		customer.addPurchase(artwork.getId(), artwork.getPrice());
	}

	@Override
	public void addArtworkToExhibition(ExhibitionRequest request) {
		Artwork artwork = artworkRepo.findById(request.getArtworkId())
				.orElseThrow(() -> new RepositoryException("Artwork not found"));
		Exhibition exhibition = exhibitionRepo.findById(request.getExhibitionId())
				.orElseThrow(() -> new RepositoryException("Exhibition not found"));

		if (artwork.isSold()) {
			throw new ServiceException("Sold artwork cannot be added to exhibition");
		}
		for (Exhibition e : exhibitionRepo.findAll()) {
			if (e.getArtworkIds().contains(artwork.getId()) && isDatesOverlap(exhibition, e)) {
				throw new ServiceException("Date conflict with exhibition: " + e.getTitle());
			}
		}
		exhibition.addArtwork(artwork.getId());
	}

	private boolean isDatesOverlap(Exhibition e1, Exhibition e2) {
		return !e1.getStartDate().isAfter(e2.getEndDate()) && !e1.getEndDate().isBefore(e2.getStartDate());
	}

	@Override
	public List<Artwork> getArtworksWithinBudget(SearchRequest<Double> request) {
		double budget = request.getCriteria();
		return artworkRepo.findAll().stream().filter(a-> !a.isSold()).filter(a -> !a.isSold() && a.getPrice() <= budget).toList();
	}

	public List<Artwork> getArtworksByStyle(SearchRequest<String> request) {
		String style = request.getCriteria().trim();
		return artworkRepo.findAll().stream().filter(a-> !a.isSold()).filter(a -> a.getStyle().equalsIgnoreCase(style)).toList();
	}

	@Override
	public List<Artwork> getAvailableArtworks() {
		return artworkRepo.findAll().stream().filter(a -> !a.isSold()).toList();
	}

	public List<Artwork> getArtworksOnExhibition(SearchRequest<Long> request) {
		Exhibition exhibition = exhibitionRepo.findById(request.getCriteria())
				.orElseThrow(() -> new RepositoryException("Exhibition not found with ID: " + request.getCriteria()));

		return exhibition.getArtworkIds().stream().map(id -> artworkRepo.findById(id))
				.filter(java.util.Optional::isPresent).map(java.util.Optional::get).toList();
	}

	@Override
	public void registerArtist(RegisterRequest<Artist> request) {
		artistRepo.save(request.getEntity());
	}

	@Override
	public void registerArtwork(RegisterRequest<Artwork> request) {
		artworkRepo.save(request.getEntity());
	}

	@Override
	public void registerCustomer(RegisterRequest<Customer> request) {
		customerRepo.save(request.getEntity());
	}

	@Override
	public List<Exhibition> getAllExhibitions() {
		return exhibitionRepo.findAll();
	}

	@Override
	public List<Exhibition> getExhibitionsByDate(SearchRequest<java.time.LocalDate> request) {
		java.time.LocalDate date = request.getCriteria();

		return exhibitionRepo.findAll().stream()
				.filter(ex -> !date.isBefore(ex.getStartDate()) && !date.isAfter(ex.getEndDate())).toList();
	}
}
