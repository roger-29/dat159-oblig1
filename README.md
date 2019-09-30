# Obligatory Exercise 1: Refactoring

> DAT159

By Arne Molland and Sondre Gjellestad

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

## Task 2

To pull up means to move a method from a subclass up to a superclass.

```java
public class BankAccount {
...
}

public class CheckingAccount extends BankAccount {
	...

	public void deposit(int amount) {
		this.balance += amount
	}
	...
}

public class SavingsAccount extends BankAccount {
...
}
```

After Pull Up:

```java
public class BankAccount {
	...
	public void deposit(int amount) {
		this.balance += amount
	}
	...
}

public class CheckingAccount extends BankAccount {
	...
}

public class SavingsAccount extends BankAccount {
	...
}
```
