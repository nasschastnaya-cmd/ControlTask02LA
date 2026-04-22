package com.nastya.artgallery;

import com.nastya.artgallery.controller.ConsoleController;
import com.nastya.artgallery.repository.RepositoryProvider;

public class Main {

	public static void main(String[] args) {
		RepositoryProvider.getInstance().initData();
		
		ConsoleController controller = new ConsoleController();
		
		controller.start();

	}

}
