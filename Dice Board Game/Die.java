import java.util.Random; 
/*
 * Die class creates a die to be used by the GameMaster. The Die object consists of two parameters, numSides
 * and id. It has methods getId(), roll(), getNumberOfSides(), and toString()
 * 
 * @Noah Beer
 */

public class Die {
	private int numSides; // Declares instance variable int numSides
	private int id; // Declares instance variable int id
	private Random rand; // Declares instance variable Random rand
	
	/*
	 * Constructor for Die. Gives the Die numSides and id and initializes rand
	 */
	public Die(int numSides, int id) {
		this.numSides = numSides; // initializes instance variable this.numSides to numSides
		this.id = id; // initializes instance variable this.id to id
		rand = new Random(); // initializes instance variable rand
	}
	
	/*
	 * Returns the id of the Die
	 */
	public int getId() {
		return this.id;
	}
	
	/*
	 * Randomly returns an integer between one and number of sides that will be used as the Die's roll
	 */
	public int roll() {
		int storeRoll;
		storeRoll = rand.nextInt(this.numSides) + 1;
		return storeRoll;
	}
	
	/*
	 * returns the numSides of the Die
	 */
	public int getNumberOfSides() {
		return this.numSides;
	}
	
	/*
	 * returns the numSides and id of the Die in a String
	 */
	public String toString() {
		return "The ID of Die is: " + this.id + "\nThe number of sides of the Die is: " + this.numSides;
	}
} // end class
