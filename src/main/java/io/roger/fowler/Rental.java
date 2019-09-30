package io.roger.fowler;

public class Rental {
    private Movie _movie;
    private int _daysRented;

    public Rental(Movie movie, int daysRented) {
        _movie = movie;
        _daysRented = daysRented;
    }

    public Movie getMovie() {
        return _movie;
    }

    public int getDaysRented() {
        return _daysRented;
    }

    public int getPrice() {
        int amount = 0;

        int priceCode = _movie.getPriceCode();

        switch (priceCode) {
        case Movie.REGULAR:
            amount += 2;
            if (_daysRented > 2) {
                amount += (_daysRented - 2) * 1.5;
            }
            break;
        case Movie.NEW_RELEASE:
            amount += _daysRented * 3;
            break;
        case Movie.CHILDREN:
            amount += 1.5;
            if (_daysRented > 3) {
                amount += (_daysRented - 3) * 1.5;
            }
            break;
        }

        return amount;
    }
}
