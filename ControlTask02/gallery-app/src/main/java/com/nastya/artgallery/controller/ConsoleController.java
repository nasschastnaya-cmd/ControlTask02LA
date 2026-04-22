package com.nastya.artgallery.controller;
import com.nastya.artgallery.dto.*;
import java.util.Scanner;

public class ConsoleController {

    private final GalleryCommander commander = new GalleryCommander();
    private final Scanner scanner = new Scanner(System.in);

	public void start() {
		while (true) {
			System.out.println("\n===== ГЛАВНОЕ МЕНЮ ГАЛЕРЕИ =====");
			System.out.println("1. Посмотреть каталог картин");
			System.out.println("2. Поиск картины (стиль/бюджет)");
			System.out.println("3. Купить картину");
			System.out.println("4. Выставки");
			System.out.println("5. Зарегестрироваться");
			System.out.println("0. Выход");
			System.out.print("Выберите действие: ");

			String choice = scanner.nextLine();
			switch (choice) {
			case "1" -> showAvailable();
			case "2" -> searchMenu();
			case "3" -> processSale();
			case "4" -> exhibitionsMenu();
			case "5" -> registrationMenu();
			case "0" -> {
				return;
			}
			default -> System.out.println("Ошибка: попробуйте снова.");
			
			}
		}
	}

	private void showAvailable() {
		System.out.println("\n--- КАТАЛОГ КАРТИН В НАЛИЧИИ ---");
		printDataResponse(commander.handleGetAvailable());
	}

	private void searchMenu() {
		System.out.println("\n. Поиск по стилю");
		System.out.println("2. Поиск по бюджету");
		String subChoice = scanner.nextLine();
		if (subChoice.equals("1")) {
			System.out.println("Введите стиль: ");
			printDataResponse(commander.handleSearchByStyle(scanner.nextLine()));
		} else if (subChoice.equals("2")) {
			System.out.println("Максимальная цена: ");
			double budget = readDouble();
			printDataResponse(commander.handleSearchByBudget(budget));
		}
	}

	private void processSale() {
		showAvailable();
	    System.out.print("ID картины: ");
	    Long artId = readLong();
	    System.out.print("Ваш ID покупателя: ");
	    Long custId = readLong();
	    System.out.print("Введите пароль: "); 
	    String password = scanner.nextLine();

	    ActionResponse response = commander.handleSale(artId, custId, password);
	    
	    printActionResponse(response);
	}
	private void exhibitionsMenu() {
		System.out.println("\n--- РАБОТА С ВЫСТАВКАМИ ---");
		System.out.println("1. Показать все выставки");
		System.out.println("2. Найти выставки по дате");
		System.out.println("3. Посмотреть картины на выставке (по ID)");

		String choice = scanner.nextLine();
		switch (choice) {
		case "1" -> printDataResponse(commander.handleAllExhibitions());
		case "2" -> {
			System.out.print("Введите дату (ГГГГ-ММ-ДД): ");
		    printDataResponse(commander.handleExhibitionsByDate(scanner.nextLine()));
		}
		case "3" -> {
			System.out.print("Введите ID выставки: ");
			try {
				Long exId = readLong();
				printDataResponse(commander.handleExhibitionArtworks(exId));
			} catch (Exception e) {
				System.out.println("Некорректный ID.");
			}
		}
		}
	}

	private void registerCustomer() {
	    System.out.print("Ваше имя: ");
	    String name = readString();
	    System.out.print("Бюджет: ");
	    double budget = readDouble();
	    System.out.print("Введите пароль: ");
	    String pass = scanner.nextLine();
	    
	    ActionResponse response = commander.handleCustomerRegistration(name, budget, pass);
	    printActionResponse(response);
	}

	private void registerArtist() {
		System.out.println("\n--- Регистрация художника ---");
		System.out.print("Введите ваше имя: ");
		String name = readString();
		System.out.print("Ваша страна: ");
		String country = readString();
		
		ActionResponse response = commander.handleArtistRegistration(name, country);
	    printActionResponse(response);

	}
	private void registerArtwork() {
	    System.out.println("\n--- Добавление новой картины ---");
	    System.out.print("Введите название картины: ");
	    String title = scanner.nextLine();
	    System.out.print("Введите стиль (например, Реализм): ");
	    String style = readString();
	    System.out.print("Введите вашу цену: ");
	    double price = readDouble();
	    System.out.print("Введите ваш ID художника: ");
	    Long artistId = readLong();

	    ActionResponse response = commander.handleArtworkRegistration(title, style, price, artistId);
	    printActionResponse(response);
	}

	private void registrationMenu() {
	    System.out.println("\n--- ОКНО РЕГИСТРАЦИИ ---");
	    System.out.println("1. Зарегистрироваться как Клиент");
	    System.out.println("2. Зарегистрироваться как Художник");
	    System.out.println("3. Добавить новую картину (для художников)");
	    System.out.println("0. Назад");
	    
	    String choice = scanner.nextLine();
	    switch (choice) {
	        case "1" -> registerCustomer();
	        case "2" -> registerArtist();
	        case "3" -> registerArtwork();
	        case "0" -> { return; }
	    default -> System.out.println("Неверный выбор.");
		}
	}

	private <T> void printDataResponse(DataResponse<T> response) {
		System.out.println("\n[СИСТЕМА]: " + response.getMessage());
		if (response.getData().isEmpty()) {
			System.out.println("   (пусто)");
		} else {
			response.getData().forEach(item -> System.out.println(" • " + item));
		}
	}

	private void printActionResponse(ActionResponse response) {
		if (response.isSuccess()) {
			System.out.println("✅ УСПЕХ: " + response.getMessage());
		} else {
			System.out.println("❌ ОТКАЗ: " + response.getMessage());
		}
	}
	private Long readLong() {
	    while (true) {
	        try {
	            return Long.parseLong(scanner.nextLine());
	        } catch (NumberFormatException e) {
	            System.out.print("Ошибка! Введите целое число (ID): ");
	        }
	    }
	}
	private double readDouble() {
	    while (true) {
	        try {
	            return Double.parseDouble(scanner.nextLine());
	        } catch (NumberFormatException e) {
	            System.out.print("Ошибка! Введите число (например, 50000.0): ");
	        }
	    }
	}
	private String readString() {
	    while (true) {
	        String input = scanner.nextLine().trim();
	        if (!input.isEmpty() && input.matches("^[a-zA-Zа-яА-ЯёЁ\\s\\-]+$")) {
	            return input;
	        }
	        System.out.print("Ошибка! Введите корректное имя (только буквы): ");
	    }
	}
}
