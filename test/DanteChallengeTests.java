package test;

import static org.junit.Assert.*;

import org.junit.Test;

import src.DanteChallenge;
import src.MySubmission;

public class DanteChallengeTests {

	/*
	 * The terrain may or may not hold any water.
	 */
	@Test
	public void testFlatTerrain() {

		int[][] testTerrain = new int[][] {
			{ 2,  2,  2,  2,  2 },
			{ 2,  2,  2,  2,  2 },
			{ 2,  2,  2,  2,  2 },
			{ 2,  2,  2,  2,  2 },
			{ 2,  2,  2,  2,  2 }
		};

		int expectedResult = 0;

		DanteChallenge submission = new MySubmission();

		assertEquals(expectedResult, submission.determineMaximumWaterHeld(testTerrain));
	}

	/*
	 * The terrain may have one or more indices that contain water.
	 */
	@Test
	public void testSimpleTerrain() {
	
		int[][] testTerrain = new int[][] {
			{ 0,  1,  0 },
			{ 1,  0,  1 }, // This row holds one.
			{ 0,  1,  0 }
		};

		int expectedResult = 1;

		DanteChallenge submission = new MySubmission();

		assertEquals(expectedResult, submission.determineMaximumWaterHeld(testTerrain));
	}

	/*
	 * An index that could contain water may only contain water up to its lowest neighbor.
	 */
	@Test
	public void testSimpleTerrainWithElevation() {

		int[][] testTerrain = new int[][] {
			{ 2,  1,  2 },
			{ 2,  0,  4 }, // This row holds one.
			{ 2,  3,  2 }
		};

		int expectedResult = 1;

		DanteChallenge submission = new MySubmission();

		assertEquals(expectedResult, submission.determineMaximumWaterHeld(testTerrain));
	}

	/*
	 * A region can be bounded and filled with water.
	 * 
	 * Please note that the region is bounded on the top, bottom, left and right. The diagonal 
	 * (top left and top right) indices are not considered when determining if the region is 
	 * bounded.
	 */
	@Test
	public void testSimpleTerrainExpanded() {

		int[][] testTerrain = new int[][] {
			{ 0,  1,  1,  1,  0 },
			{ 1,  0,  0,  0,  1 }, // This row holds three.
			{ 0,  1,  0,  0,  1 }, // This row holds two.
			{ 0,  1,  0,  0,  1 }, // This row holds two.
			{ 1,  1,  1,  1,  1 }
		};

		int expectedResult = 7;

		DanteChallenge submission = new MySubmission();

		assertEquals(expectedResult, submission.determineMaximumWaterHeld(testTerrain));
	}

	@Test
	public void testSimpleHill() {

		int[][] testTerrain = new int[][] {
			{ 2,  2,  2,  2,  2 },
			{ 2,  3,  3,  3,  2 },
			{ 2,  3,  4,  3,  2 },
			{ 2,  3,  3,  3,  2 },
			{ 2,  2,  2,  2,  2 }
		};

		int expectedResult = 0;

		DanteChallenge submission = new MySubmission();

		assertEquals(expectedResult, submission.determineMaximumWaterHeld(testTerrain));
	}

	@Test
	public void testSwimmingPool() {

		int[][] testTerrain = new int[][] {
			{ 2,  2,  2,  2,  2 },
			{ 2,  0,  0,  0,  2 }, // This row can hold 6
			{ 2,  0,  0,  0,  2 }, // This row can hold 6
			{ 2,  0,  0,  0,  2 }, // This row can hold 6
			{ 2,  2,  2,  2,  2 }
		};

		int expectedResult = 18;

		DanteChallenge submission = new MySubmission();

		assertEquals(expectedResult, submission.determineMaximumWaterHeld(testTerrain));
	}

	@Test
	public void testUnevenTerrain() {

		int[][] testTerrain = new int[][] {
			{ 3,  3,  3,  2,  2,  2,  2 },
			{ 2,  1,  2,  2,  2,  2,  2 },  // This row holds 1
			{ 2,  0,  2,  2,  2,  2,  2 },  // This row holds 2
			{ 2,  1,  2,  2,  3,  3,  3 },  // This row holds 1
			{ 2,  2,  2,  2,  2,  4,  3 },
			{ 2,  2,  2,  2,  3,  5,  3 },
			{ 2,  2,  2,  2,  2,  4,  3 }
		};

		int expectedResult = 4;

		DanteChallenge submission = new MySubmission();

		assertEquals(expectedResult, submission.determineMaximumWaterHeld(testTerrain));
	}
}
