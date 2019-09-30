package io.roger.refactoring;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class ExtractVariable {

	String platform;
	String browser;
	boolean wasInitialized;
	int resize = 0;

	void simple() {
		boolean platformMac = platform.toUpperCase().indexOf("MAC") > -1;
		boolean browserIE = browser.toUpperCase().indexOf("IE") > -1;

		if (platformMac && browserIE && wasInitialized && resize > 0) {
			// do something
		}
	}

	int plusOne() {
		resize = resize + 1;
		return resize;
	}

	int dangerWillRobinson() {
		// Extract subexpression/"Replace All" in Eclipse
		// (Goodie below)

		return plusOne() + plusOne() + plusOne();
	}

	@Test
	public void test1() {
		assertEquals(3, dangerWillRobinson());
	}
}
