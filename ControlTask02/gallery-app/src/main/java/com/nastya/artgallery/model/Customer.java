package com.nastya.artgallery.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer {
	
	private Long id;
	private String fullName;
	private String email;
	private double budget;
	private String password;
	private List<Long> purchasedArtworkIds = new ArrayList<>();
	
	public Customer() {}
	
	public Customer(Long id, String name, String email, double budget, String password) {
        this.id = id;
        this.fullName = name;
        this.email = email;
        this.budget = budget;
        this.password = password;
        this.purchasedArtworkIds = new ArrayList<>();
    }
	public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullname() { return fullName; }
    public void setFullname(String name) { this.fullName = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public double getBudget() { return budget; }
    public void setBudget(double budget) { this.budget = budget; }
    
    public String getPassword() { return password; }

    public List<Long> getPurchasedArtworkIds() { return purchasedArtworkIds; }

    public void addPurchase(Long artworkId, double price) {
    	this.budget -= price;
    	this.purchasedArtworkIds.add(artworkId);
    }
    @Override
    public String toString() {
        return String.format("Покупатель ID: %d | %s | Бюджет: %.2f | Куплено работ: %d", 
                id, fullName, budget, purchasedArtworkIds.size());
    }

	@Override
	public int hashCode() {
		return Objects.hash(budget, email, fullName, id, purchasedArtworkIds);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Double.doubleToLongBits(budget) == Double.doubleToLongBits(other.budget)
				&& Objects.equals(email, other.email) && Objects.equals(fullName, other.fullName)
				&& Objects.equals(id, other.id) && Objects.equals(purchasedArtworkIds, other.purchasedArtworkIds);
	}
    
}
