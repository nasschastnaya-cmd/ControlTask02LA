package com.nastya.artgallery.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.nastya.artgallery.model.Customer;

public class CustomerRepository implements Repository<Customer> {
	
	private final List<Customer> customers = new ArrayList<>();
	
	@Override
	public void save(Customer customer) {
		customers.add(customer);
	}
	@Override
	public List<Customer> findAll() {
		return new ArrayList<>(customers);
	}
	@Override
	public Optional<Customer> findById(Long id){
		return customers.stream().filter(c -> c.getId().equals(id)).findFirst();
	}
		@Override
		public void deleteById(Long id) {
		    customers.removeIf(c -> c.getId().equals(id));
		}

}
