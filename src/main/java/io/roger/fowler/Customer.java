package io.roger.fowler;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {
	private String name;
	private Vector<Rental> rentals = new Vector<Rental>();

	public Customer(String name) {
		this.name = name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;

		Enumeration<Rental> rentals = this.rentals.elements();

		String result = String.format("Rental Record for %s\n", getName());

		while (rentals.hasMoreElements()) {
			Rental rental = rentals.nextElement();

			int daysRented = rental.getDaysRented();
			MovieType movieType = rental.getMovie().getMovieType();

			// Determine amount for each line
			double amount = movieType.getPrice(daysRented);

			// Add frequent renter points
			frequentRenterPoints++;

			// Add bonus for a two day new release rental
			if ((movieType.getClass() == MovieType.NEW_RELEASE.class) && daysRented > 1) {
				frequentRenterPoints++;
			}

			// Show figures for this rental
			String movieTitle = rental.getMovie().getTitle();
			result += "\t" + movieTitle + "\t" + String.valueOf(amount) + "\n";
			totalAmount += amount;
		}

		// Add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";

		return result;
	}

	public void addRental(Rental rental) {
		rentals.addElement(rental);
	}

	public String getName() {
		return name;
	}
}
