package com.nastya.artgallery.service;

public class ServiceProvider {

	private static ServiceProvider instance = new ServiceProvider();

	private final GalleryService galleryService = new GalleryServiceImpl();

	private ServiceProvider() {
	}

	public static ServiceProvider getInstance() {
		return instance;
	}

	public GalleryService getGalleryService() {
		return galleryService;
	}

}
