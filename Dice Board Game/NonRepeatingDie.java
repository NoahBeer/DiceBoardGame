import java.util.ArrayList; 
import java.util.Random;
/*
 * NonRepeatingDie extends Die and does all the same things as die except in its roll() method. The roll method doesn't return a previously rolled side until
 * each of the other sides has been rolled
 * 
 * @Noah Beer
 */

public class NonRepeatingDie extends Die {
	private ArrayList<Integer> storeResults; // declares instance variable ArrayList storeResults that will store each prior roll until all sides have been rolled
	
	/*
	 * Constructor calls super which assigns numSides and id. Also creates storeResults ArrayList<Integer>
	 */
	public NonRepeatingDie(int numSides, int id) { // constructor
		super(numSides, id); // calls the super of Die
		storeResults = new ArrayList<Integer>(); // initializes instance variable storeResults
	}
	
	/*
	 * Rolls the die and makes sure that each number of the die isn't rolled again until each number has been rolled once
	 */
	public int roll() {	
		Random rand;
		int result; // declares local int results which is then added to the ArrayList storeResults to make sure sides aren't repeated
		rand = new Random(); 
		result = rand.nextInt(getNumberOfSides()) + 1; // initializes result as a random integer between 1 and the total number of sides on the die.
													   
		if(storeResults.size() == getNumberOfSides()) { // checks to see if every number has been used already by seeing if the size of the array is equal to the total number of sides. If true, it clears the ArrayList.
			storeResults.clear(); 
		}
		while(storeResults.contains(result)) {  // runs the loop while the number rolled has already been rolled before each other number has been rolled. 
			result = rand.nextInt(getNumberOfSides()) + 1; // generates a new result and re-loops
		}
		storeResults.add(result); // adds results to storeResults
		return result;
	}
} // end class
