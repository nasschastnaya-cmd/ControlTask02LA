package com.nastya.artgallery.controller;

import com.nastya.artgallery.dto.*;
import com.nastya.artgallery.model.*;
import com.nastya.artgallery.service.*;
import java.util.List;

public class GalleryCommander {
	private final GalleryService service = ServiceProvider.getInstance().getGalleryService();

	public ActionResponse handleSale(Long artId, Long custId, String password) {
		try {
			service.sellArtwork(new SaleRequest(artId, custId, password));
			return new ActionResponse(true, "Сделка успешно завершена!");
		} catch (Exception e) {
			return new ActionResponse(false,"Ошибка сделки: "+ e.getMessage());
		}
	}
	public ActionResponse handleArtistRegistration(String name, String country) {
	    try {
	        Long id = (System.currentTimeMillis() % 1000) + 100;
	        Artist artist = new Artist(id, name, country);
	        service.registerArtist(new RegisterRequest<>(artist));
	        return new ActionResponse(true, "Регистрация прошла успешно! ID: " + id);
	    } catch (Exception e) {
	        return new ActionResponse(false, "Произошла ошибка: " + e.getMessage());
	    }
	}
	
	public ActionResponse handleArtworkRegistration(String title, String style, double price, Long artistId) {
	    try {
	        Long id = System.currentTimeMillis() % 1000;
	        
	        Artwork artwork = new Artwork(id, title, style, artistId, price, false);
	        service.registerArtwork(new RegisterRequest<>(artwork));
	        return new ActionResponse(true, "Произведение искусства добавлено! ID: " + id);
	    } catch (Exception e) {
	        return new ActionResponse(false, "Произошла ошибка: " + e.getMessage());
	    }
	}
	public ActionResponse handleCustomerRegistration(String name, double budget, String password) {
	    try {
	        
	        Long id = System.currentTimeMillis() % 1000;
	        Customer customer = new Customer(id, name, "user@mail.ru", budget, password);
	        service.registerCustomer(new RegisterRequest<>(customer));
	        
	        return new ActionResponse(true, "Покупатель " + name + " Регистрация прошла успешно! ID: " + id);
	    } catch (Exception e) {
	        return new ActionResponse(false, "Произошла ошибка: " + e.getMessage());
	    }
	}

	public DataResponse<Artwork> handleGetAvailable() {
		try {
			List<Artwork> artworks = service.getAvailableArtworks();
			String msg = artworks.isEmpty() ? "Произведения искусства отсутствуют." : "Доступные произведения искусства:";
			return new DataResponse<>(artworks, msg);
		} catch (Exception e) {
			return new DataResponse<>(List.of(), "Ошибка: " + e.getMessage());
		}
	}
	public DataResponse<Exhibition> handleAllExhibitions() {
	    try {
	        List<Exhibition> data = service.getAllExhibitions();
	        return new DataResponse<>(data, "Список всех выставок:");
	    } catch (Exception e) {
	        return new DataResponse<>(List.of(), "Ошибка: " + e.getMessage());
	    }
	}
	public DataResponse<Exhibition> handleExhibitionsByDate(String dateStr) {
	    try {
	        java.time.LocalDate date = java.time.LocalDate.parse(dateStr);
	        List<Exhibition> data = service.getExhibitionsByDate(new SearchRequest<>(date));
	        return new DataResponse<>(data, "Выставки по датам " + dateStr + ":");
	    } catch (Exception e) {
	        return new DataResponse<>(List.of(), "Неверный формат даты. Используйте ГГГГ-ММ-ДД");
	    }
	}
	public DataResponse<Artwork> handleExhibitionArtworks(Long exId) {
	    try {
	        List<Artwork> data = service.getArtworksOnExhibition(new SearchRequest<>(exId));
	        return new DataResponse<>(data, "Представленные на выставке произведения искусства  ID " + exId + ":");
	    } catch (Exception e) {
	        return new DataResponse<>(List.of(), "Ошибка: " + e.getMessage());
	    }
	}

	public DataResponse<Artwork> handleSearchByStyle(String style) {
		try {
			List<Artwork> artworks = service.getArtworksByStyle(new SearchRequest<>(style));
			return new DataResponse<>(artworks, "Результаты поиска по стилю '" + style + "':");
		} catch (Exception e) {
			return new DataResponse<>(List.of(), "Поиск не удался: " + e.getMessage());
		}
	}
	public DataResponse<Artwork> handleSearchByBudget(double budget) {
	    try {
	        List<Artwork> data = service.getArtworksWithinBudget(new SearchRequest<>(budget));
	        
	        String message;
	        if (data.isEmpty()) {
	            message = "Картин в пределах бюджета " + budget + " не найдено.";
	        } else {
	            message = "Найденные картины (бюджет до " + budget + "):";
	        }
	            
	        return new DataResponse<>(data, message);
	    } catch (Exception e) {
	        return new DataResponse<>(java.util.List.of(), "Ошибка поиска: " + e.getMessage());
	    }
	}

	public <T> ActionResponse handleRegistration(T entity, String type) {
		try {
			if (entity instanceof Artist)
				service.registerArtist(new RegisterRequest<>((Artist) entity));
			else if (entity instanceof Artwork)
				service.registerArtwork(new RegisterRequest<>((Artwork) entity));
			else if (entity instanceof Customer)
				service.registerCustomer(new RegisterRequest<>((Customer) entity));

			return new ActionResponse(true, type + " Регистрация прошла успешно!");
		} catch (Exception e) {
			return new ActionResponse(false, "Произошла ошибка" + type + ": " + e.getMessage());
		}
	}
}
