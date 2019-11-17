package io.roger.fowler;

abstract class MovieType {
	abstract double getPrice(int days);

	class REGULAR extends MovieType {
		@Override
		double getPrice(int days) {
			double amount = 2;

			if (days > 2) {
				amount += (days - 2) * 1.5;
			}

			return amount;
		}
	}

	class NEW_RELEASE extends MovieType {
		@Override
		double getPrice(int days) {
			return days * 3;
		}
	}

	class CHILDRENS extends MovieType {
		@Override
		double getPrice(int days) {
			double amount = 1.5;

			if (days > 3) {
				amount += (days - 3) * 1.5;
			}

			return amount;
		}
	}
}
