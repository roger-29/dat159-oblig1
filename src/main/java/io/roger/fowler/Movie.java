package io.roger.fowler;

public class Movie {
    public static final int CHILDREN = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

    private String _title;
    private int _priceCode;

    public Movie(String title, int priceCode) {
        _title = title;
        _priceCode = priceCode;
    }

    public int getPriceCode() {
        return _priceCode;
    }

    public void setPriceCode(int _priceCode) {
        this._priceCode = _priceCode;
    }

    public int getPrice(int daysRented) {
        int amount = 0;

        switch (_priceCode) {
        case Movie.REGULAR:
            amount += 2;
            if (daysRented > 2) {
                amount += (daysRented - 2) * 1.5;
            }
            break;
        case Movie.NEW_RELEASE:
            amount += daysRented * 3;
            break;
        case Movie.CHILDREN:
            amount += 1.5;
            if (daysRented > 3) {
                amount += (daysRented - 3) * 1.5;
            }
            break;
        }

        return amount;
    }

    public String getTitle() {
        return _title;
    }
}
