package src;

public class MySubmission implements DanteChallenge {

	@Override
	public int determineMaximumWaterHeld(int[][] terrain) {

		/*
		 * This is a dynamic programming algorithm that runs in O(n * m) for an
		 * n by m matrix, which is linear time with respect to a two dimensional
		 * matrix.
		 *
		 * Let us define the capacity of a cell to be the combined height from
		 * the natural elevation of the terrain corresponding to the cell and
		 * the rain water accumulated when the entire terrain is optimally
		 * filled. The capacity of any cell should be greater than equal to the
		 * elevation of the terrain in that cell.
		 *
		 * The recurrence relation regarding capacity is as follows:
		 *
		 * First, the base case applies to cells on the outermost layer of the
		 * entire terrain. If a cell is on the corners or edge, its capacity is
		 * equal to its natural elevation. (This is due to water draining off
		 * the edge of the map).
		 *
		 * Next, the capacity for a normal cell is the minimum of the capacity
		 * of all four of the cell's neighbors. If the minimum of the
		 * neighboring capacities is less than the elevation of the current
		 * cell, then the capacity of the cell is simply the elevation of the
		 * cell itself.
		 */

		// Check for valid terrain. The terrain must be at least a 3 by 3
		// grid to contain any water.
		if (terrain == null || terrain.length < 3 || terrain[0].length < 3) {
			return 0;
		}

		int n = terrain.length;
		int m = terrain[0].length;
		int[][] capacity = new int[n][m];

		// Fill out the base case values. For each cell on the corners or
		// edge, the capacity of the cell is equal to the elevation of the
		// cell. (In other words, edges and corners can't hold any water.)
		for (int i = 0; i < n; i++) {
			capacity[i][0] = terrain[i][0];
			capacity[i][m - 1] = terrain[i][m - 1];
		}
		for (int i = 0; i < m; i++) {
			capacity[0][i] = terrain[0][i];
			capacity[n - 1][i] = terrain[n - 1][i];
		}

		// Set all inner cells to -1. This value means the capacity
		// of the cell is completely unknown. I need this because for my
		// recursive calls in the helper method, I need to avoid reading
		// information from these uninitialized cells.
		for (int i = 1; i < n - 1; i++) {
			for (int j = 1; j < m - 1; j++) {
				capacity[i][j] = -1;
			}
		}

		// For each (inner) cell, find the capacity of the cell.
		// This nested loop and method call should be a total of O(n * m).
		// Although the helper method runs in O(n * m) worst case, it does
		// not increase the runtime of this nested loop, as the runtime of
		// the method averages out to a constant when called over all cells.
		// (This happens because the runtime of the function is directly
		// related to the number of updates it makes. The total number of
		// updates that occur is directly related to the size of the grid,
		// so each cell is responsible on average for a constant number
		// of updates.
		for (int i = 1; i < n - 1; i++) {
			for (int j = 1; j < m - 1; j++) {
				updateCapacity(terrain, capacity, i, j);
			}
		}

		// For each cell, the water held in each cell is the capacity of the
		// cell minus the elevation of the cell. I do not need to check the
		// corners or edges because they obviously hold no water.
		int total = 0;
		for (int i = 1; i < n - 1; i++) {
			for (int j = 1; j < m - 1; j++) {
				total += capacity[i][j] - terrain[i][j];
			}
		}

		// Return the total amount of water collected.
		return total;
	}

	/**
	 * Calculates the upper bound of the capacity of the cell at the given row
	 * and column using currently known capacity data.
	 *
	 * If the capacity upper bound of this cell gets updated, this method will
	 * recursively broadcast this update to its neighbors by having its
	 * neighbors recalculate their capacity upper bounds.
	 *
	 * @param terrain
	 *            The elevation of each cell.
	 * @param capacity
	 *            The current upper bounds of the capacity of each cell.
	 * @param row
	 *            The row of the cell.
	 * @param col
	 *            The column of the cell.
	 */
	private void updateCapacity(int[][] terrain, int[][] capacity, int row, int col) {
		int n = terrain.length;
		int m = terrain[0].length;

		// Return if the current cell is on the corners or edges.
		// (These cells were already established as base cases.)
		if (row == 0 || row == n - 1 || col == 0 || col == m - 1) {
			return;
		}

		// Get the capacity of the neighbors.
		int up = capacity[row][col + 1];
		int down = capacity[row][col - 1];
		int left = capacity[row - 1][col];
		int right = capacity[row + 1][col];

		// Get the lowest capacity among all neighbors that doesn't have a
		// value of -1.
		int updatedCapacity = up;
		if ((down != -1 && down < updatedCapacity) || updatedCapacity == -1) {
			updatedCapacity = down;
		}
		if ((left != -1 && left < updatedCapacity) || updatedCapacity == -1) {
			updatedCapacity = left;
		}
		if ((right != -1 && right < updatedCapacity) || updatedCapacity == -1) {
			updatedCapacity = right;
		}

		// If all neighbors were uninitialized, we learned absolutely nothing
		// from this recursive call. We can't even set the capacity of this
		// cell to be its elevation, since there is the possibility that the
		// capacity of all neighbors is larger than this well.
		// Since we don't call this method from the center of the matrix, this
		// should never evaluate to be true.
		if (updatedCapacity == -1) {
			return;
		}

		// If the one capacity among the neighbors is lower than this cell's
		// elevation, that means any water on this cell will be drained off the
		// edge. In this case, the capacity will just be the elevation.
		if (updatedCapacity < terrain[row][col]) {
			updatedCapacity = terrain[row][col];
		}

		// If there is no update, we can just return, since there is no
		// information to share.
		if (capacity[row][col] == updatedCapacity) {
			return;
		}

		// Update this cell's capacity.
		capacity[row][col] = updatedCapacity;

		// Broadcast this cell's update to all neighbors.
		updateCapacity(terrain, capacity, row - 1, col);
		updateCapacity(terrain, capacity, row + 1, col);
		updateCapacity(terrain, capacity, row, col - 1);
		updateCapacity(terrain, capacity, row, col + 1);
	}

}
