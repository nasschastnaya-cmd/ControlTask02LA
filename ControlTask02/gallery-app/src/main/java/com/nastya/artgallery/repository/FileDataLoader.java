package com.nastya.artgallery.repository;

import com.nastya.artgallery.model.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class FileDataLoader {
	public static List<Artwork> loadArtworks(String filePath) throws IOException {
		List<Artwork> list = new ArrayList<>();
		List<String> lines = Files.readAllLines(Paths.get(filePath));

		for (String line : lines) {
			if (line.trim().isEmpty())
				continue;
			String[] p = line.split(";");
			list.add(new Artwork(Long.parseLong(p[0]), p[1], p[2], Long.parseLong(p[3]), Double.parseDouble(p[4]),
					Boolean.parseBoolean(p[5])));
		}
		return list;
	}

	public static List<Customer> loadCustomers(String filePath) throws IOException {
		List<Customer> list = new ArrayList<>();
		List<String> lines = Files.readAllLines(Paths.get(filePath));

		for (String line : lines) {
			if (line.trim().isEmpty())
				continue;
			String[] p = line.split(";");
			list.add(new Customer(Long.parseLong(p[0]), p[1], p[2], Double.parseDouble(p[3]), p[4]));
		}
		return list;
	}

	public static List<Artist> loadArtists(String filePath) throws IOException {
		List<Artist> list = new ArrayList<>();
		List<String> lines = Files.readAllLines(Paths.get(filePath));
		for (String line : lines) {
			if (line.trim().isEmpty())
				continue;
			String[] p = line.split(";");
			list.add(new Artist(Long.parseLong(p[0]), p[1], p[2]));
		}
		return list;
	}

	public static List<Exhibition> loadExhibitions(String filePath) throws IOException {
		List<Exhibition> list = new ArrayList<>();
		List<String> lines = Files.readAllLines(Paths.get(filePath));
		for (String line : lines) {
			if (line.trim().isEmpty())
				continue;
			String[] p = line.split(";");
			Exhibition ex = new Exhibition(Long.parseLong(p[0]), p[1], java.time.LocalDate.parse(p[2]),
					java.time.LocalDate.parse(p[3]));
			if (p.length > 4 && !p[4].isEmpty()) {
				String[] ids = p[4].split(",");
				for (String id : ids)
					ex.addArtwork(Long.parseLong(id.trim()));
			}
			list.add(ex);
		}
		return list;
	}
}
