package io.roger.example;

public class BeforePullUp {
	static class Spacecraft {
		long MAX_RANGE;

		long range;

		public long getRange() {
			return this.range;
		}
	}

	static class LunarShuttle extends Spacecraft {
		long MAX_RANGE = 770000l;

		public LunarShuttle(int cargoAmount, int fuelPercentage) {
			range = calculateRange(cargoAmount, fuelPercentage);
		}

		public long calculateRange(int cargoAmount, int fuelPercentage) {
			long range = (MAX_RANGE * fuelPercentage / 100) - (cargoAmount * 150);

			return range >= 0 ? range : 0;
		}
	}

	static class Interstellar extends Spacecraft {
		long MAX_RANGE = 50000000000000l;

		public Interstellar(int cargoAmount, int fuelPercentage) {
			range = calculateRange(cargoAmount, fuelPercentage);
		}

		public long calculateRange(int cargoAmount, int fuelPercentage) {
			long range = (MAX_RANGE * fuelPercentage / 100) - (cargoAmount * 5);

			return range >= 0 ? range : 0;
		}
	}

	public static void main(String[] args) {
		LunarShuttle shuttle = new LunarShuttle(8, 90);
		Interstellar interstellar = new Interstellar(1000, 70);

		System.out.println("The lunar shuttle can travel " + shuttle.range + "km.");
		System.out.println("The interstellar spacecraft can travel " + interstellar.range + "km.");
	}
}
