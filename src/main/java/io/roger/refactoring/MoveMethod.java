package io.roger.refactoring;

public class MoveMethod {

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
}
