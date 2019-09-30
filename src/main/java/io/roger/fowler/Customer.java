package io.roger.fowler;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {
    private String _name;
    private Vector<Rental> _rentals = new Vector<Rental>();

    public Customer(String name) {
        _name = name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;

        Enumeration<Rental> rentals = _rentals.elements();

        String result = String.format("Rental Record for %s\n", getName());
        while (rentals.hasMoreElements()) {
            double amount = 0;
            Rental nextElement = rentals.nextElement();

            String movieTitle = nextElement.getMovie().getTitle();
            int daysRented = nextElement.getDaysRented();
            int priceCode = nextElement.getMovie().getPriceCode();

            // Determine amount for each line
            amount += nextElement.getMovie().getPrice(daysRented);

            // Add frequent renter points
            frequentRenterPoints++;

            // Add bonus for a two day new release rental
            if ((priceCode == Movie.NEW_RELEASE) && daysRented > 1) {
                frequentRenterPoints++;
            }

            // Show figures for this rental
            result += "\t" + movieTitle + "\t" + String.valueOf(amount) + "\n";
            totalAmount += amount;
        }

        // Add footer lines
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";

        return result;
    }

    public void addRental(Rental rental) {
        _rentals.addElement(rental);
    }

    public String getName() {
        return _name;
    }
}
