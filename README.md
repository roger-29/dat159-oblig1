# Obligatory Exercise 1: Refactoring

> DAT159

By Arne Molland and Sondre Gjellestad


### B. SLOC and cyclomatic complexity

We will now use a couple different metrics to evaluate the effect the refactorings had on our code, specifically on the `Customer#statement`-method.

#### SLOC

After all the refactorings we did, we are now down to this (excluding comments and whitespace).

- SLOC before: 35
- SLOC after: 22

Moving the switch statement out of the method had the biggest effect on this metric.

#### Cyclomatic complexity

We can calculate the cyclomatic complexity using M = E - N + 2P, where E is the number of edges, N is the number of nodes and P is the number of connected components.

- Before: 36 edges - 31 nodes + 2 \* 1 connected components = 17
- After: 21 edges - 20 nodes + 2 \* 1 connected components = 3

![Before](docs/cyclomatic-before.png)
![After](docs/cyclomatic-after.png)

Removing the switch statement also had the biggest impact on this metric.

## Task 2 | Self Contained Example Showing the Pull Up Method

Here is some code containing a few classes with inheritance. We have an ancestor Spacecraft class, and a couple types of spacecraft that inherit from it. Looking at the child classes, it might seem that the calculate range function is duplicated, so it might look like an opportunity to lift up the `calculateRange`-method.

```java
public class BeforePullUp {
	static class Spacecraft {
		long MAX_RANGE;

		private long range;

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

		System.out.println("The lunar shuttle can travel " + shuttle.getRange() + "km.");
		System.out.println("The interstellar spacecraft can travel " + interstellar.getRange() + "km.");
	}
}
```

After doing the refactoring, our code now looks like this. We pulled up the `calculateRange`-method.

```java
public class AfterPullUp {
	static class Spacecraft {
		long MAX_RANGE;

		long range;

		public long getRange() {
			return this.range;
		}

		public long calculateRange(int cargoAmount, int fuelPercentage) {
			long range = (MAX_RANGE * fuelPercentage / 100) - (cargoAmount * 150);

			return range >= 0 ? range : 0;
		}
	}

	static class LunarShuttle extends Spacecraft {
		long MAX_RANGE = 770000l;

		public LunarShuttle(int cargoAmount, int fuelPercentage) {
			range = calculateRange(cargoAmount, fuelPercentage);
		}
	}

	static class Interstellar extends Spacecraft {
		long MAX_RANGE = 50000000000000l;

		public Interstellar(int cargoAmount, int fuelPercentage) {
			range = calculateRange(cargoAmount, fuelPercentage);
		}
	}

	public static void main(String[] args) {
		LunarShuttle shuttle = new LunarShuttle(8, 90);
		Interstellar interstellar = new Interstellar(1000, 70);

		System.out.println("The lunar shuttle can travel " + shuttle.getRange() + "km.");
		System.out.println("The interstellar spacecraft can travel " + interstellar.getRange() + "km.");
	}
}

```

This is what we get before refactoring, and what we should expect afterwards if the refactoring was appropriate.

```bash
The lunar shuttle can travel 691800km.
The interstellar spacecraft can travel 34999999995000km.
```

However, this is what we actually get. There are a couple things that have gone wrong here. First off, the `MAX_RANGE`-constant now refers to the uninitialized one in the ancestor class and therefore gives zero. We also missed a small difference between the methods, the multipler for `cargoAmount`, which would have made the results different if it wasn't for the first error.

```bash
The lunar shuttle can travel 0km.
The interstellar spacecraft can travel 0km.
```

The conclusion is that one must be careful before pulling up, as a method might depend upon specific things in the child class and there might also be other differences between the methods. In these cases an abstract class or interface might be a better option.

## Task 1

### a)

#### Extract Variable

Before:

```java
void simple() {
	if ((platform.toUpperCase().indexOf("MAC") > -1) && (browser.toUpperCase().indexOf("IE") > -1) && wasInitialized && resize > 0) {
		// do something
	}
}
```

After:

```java
void simple() {
	boolean platformMac = platform.toUpperCase().indexOf("MAC") > -1;
	boolean browserIE = browser.toUpperCase().indexOf("IE") > -1;

	if (platformMac && browserIE && wasInitialized && resize > 0) {
		// do something
	}
}
```

#### Move Method

Before:

```java
class Project {
	Person manager;
	Person[] participants;

	void shouldntreallyBeHere() {
		System.out.println(manager.id);
		System.out.println(manager.name);
	}
}

class Person {
	int id;
	String name;

	boolean participate(Project p) {
		for (int i = 0; i < p.participants.length; i++) {
			if (p.participants[i].id == id)
				return (true);
		}
		return (false);
	}
}
```

After:

```java
class Project {
	Person manager;
	Person[] participants;
}

class Person {
	int id;
	String name;

	boolean isAParticipantOf(Project p) {
		for (int i = 0; i < p.participants.length; i++) {
			if (p.participants[i].id == id) {
				return true;
			}
		}
		return false;
	}

	void shouldBeHere() {
		System.out.println(this.id);
		System.out.println(this.name);
	}
}
```

### b)

McCabe's Cyclomatic Complexity before refactorings:

- 1 + 1 + 4 = 6

After refactorings:

- 1 + 1 = 2
