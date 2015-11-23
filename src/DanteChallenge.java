package src;

public interface DanteChallenge {

	/**
	 * Determine the maximum amount of rain water that can be held by the terrain. 
	 * Water will be collected in areas that are bounded above, below, to the left
	 * and to the right!
	 * 
	 * 
	 * @param terrain
	 * @return
	 */
	int determineMaximumWaterHeld(int[][] terrain);

}
