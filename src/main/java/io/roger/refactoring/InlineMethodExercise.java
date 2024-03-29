package io.roger.refactoring;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class InlineMethodExercise {

	static class A {
		int foo() {
			return 1;
		}

		public int doIt() {
			int result = foo();

			System.out.println(result);

			return result;
		}
	}

	static class B extends A {
		int foo() {
			return 2;
		}
	}

	@Test
	public void test() {
		assertEquals(2, new B().doIt());
	}
}
