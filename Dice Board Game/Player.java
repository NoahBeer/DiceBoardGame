import java.util.ArrayList;
import java.util.Scanner;

/*
 * This class creates an object player. It reports all the information given to it by GameMaster.
 * It requests and takes in values to play the game from the user using the Scanner class. 
 * 
 * @Noah Beer
 */
public class Player {
	private String name; // Declares instance variable String name
	private Scanner scan; // Declares instance variable Scanner scan
	private ArrayList<Integer> diceInfo; // Declares instance variable ArrayList diceInfo that stores integers. This ArrayList keeps track of how many sides are on each of the randomly generated die
	
	/*
	 * The constructor takes in a parameter String name and assigns this.name to name, also it initializes scan and diceInfo
	 */
	public Player(String name) {
		this.name = name; // initializes instance variable this.name to name
		scan = new Scanner(System.in); // initializes instance variable scan
		diceInfo = new ArrayList<Integer>(); // initializes instance variable diceInfo
	}
	
	/*
	 * Prints to GameMaster what each registered player's turn number is
	 */
	public void receiveMyTurnNumber(int turn) {
		System.out.println(getName()  + ", your turn number is " + turn + ":");
	}
	
	/*
	 * Prints to GameMaster what each registered player's current turn number is
	 */
	public void receiveCurrentTurnNumber(int turn) {
		System.out.println("Your current turn number is " + turn);
	}
	
	/*
	 * Prints to GameMaster how many players are registered
	 */
	public void receiveNumberofPlayers(int pNum) { 
		System.out.println("There are " + pNum + " players in this game.\n\n");
	}
	
	/*
	 * Prints to GameMaster how many dice there are in the current game and how many sides each die has
	 */
	public void receiveDiceInfo(ArrayList<Integer> info) { 
		diceInfo = info; 	
		System.out.println("\nThere are " + diceInfo.size()  +" dice in this game. \nThe following array shows how many sides are on each die: " + diceInfo);
	}
	
	/*
	 * Prints to GameMaster the results of the prior roll
	 */
	public void receiveRollResults(ArrayList<Integer> results) { 
		System.out.println("The following array shows the results of the prior roll\n" +  results);
	}
	
	/*
	 * GameMaster prompts the user to decide whether or not the player wants to guess. If the player wants to guess, true
	 * is returned. If the user doesn't want to guess, false is returned.
	 */
	public boolean wantsToGuess() {
		int userInput;
		userInput = 0;
		System.out.println(getName() + ", would you like to guess which dice are the nonrepeating die?\nIf so, please enter 1. If not, please enter 0:");
		userInput = scan.nextInt();
		while(userInput != 1 && userInput != 0) { // if an invalid answer is entered, the user will be prompted to enter a new valid answer
			System.out.println("ERROR: Please enter either 1 or 0");
			userInput = scan.nextInt();
		}
		if(1 == userInput) { 
			return true;
		}
		return false;
	}
	
	/*
	 * the player is prompted to enter which two dice he/she believes are the NonRepeatingDie. The ID, which is the same
	 * as the index of the Die, should be entered. The guesses are then returned to GameMaster.
	 */
	public int[] guess() { 
		int guess1;
		int guess2;
		System.out.println("Please enter your first guess:");
		guess1 = scan.nextInt();
		while(guess1 >= diceInfo.size() || guess1 < 0) { // Makes sure guess1 is within the index of diceInfo. If not, the player is prompted to enter a new guess
			System.out.println("ERROR: Please enter a new number for guess 1 within the index of the Array List");
			guess1 = scan.nextInt();
		}
		System.out.println("Please enter your second guess:");
		guess2 = scan.nextInt();
		while(guess2 >= diceInfo.size() || guess2 == guess1 || guess2 < 0) { // Makes sure guess1 is within the index of diceInfo and not the same as guess1. If not, the player is prompted to enter a new guess
			System.out.println("ERROR: Please enter a new number for guess 2 within the index of the Array List and a different number then guess 1.");
			guess2 = scan.nextInt();
		}
		int[] storeGuesses;
		storeGuesses = new int[] {1,2}; // initializes storeGuesses to size 2 in order to store guess1 and guess2
		storeGuesses[0] = guess1; // stores guess1 at index 0 
		storeGuesses[1] = guess2; // stores guess2 and index 2
		
		return storeGuesses;
	}
	
	/*
	 * GameMaster calls reroll and asks the player whether he/she would like to reroll. If the player would like to reroll
	 * the player is then prompted to enter each ID of the Die he/she would like to reroll
	 */
	public ArrayList<Integer> reroll() { 
		ArrayList<Integer> subset;
		subset = new ArrayList<Integer>(); // Initializes ArrayList subset which stores the original roll value of each Die the player doesn't want to reroll and replaces the original roll value of each Die the player does want to reroll
		int userInput;
		
		System.out.println(getName() + ", would you like to reroll? If so, please enter 1. If not, please enter 0\n");
		userInput = scan.nextInt();
		while(userInput != 1 && userInput != 0 ) { // Makes sure guess1 is within the index of diceInfo. If not, the player is prompted to enter a new answer
			System.out.println("ERROR: Please enter either 1 or 0");
			userInput = scan.nextInt();
		}
		if(userInput == 1) {
			for(int i = 0; i < diceInfo.size(); i++) {
				System.out.println("Would you like to reroll the die at index " + i + "?\nIf so, please enter 1. If not, please enter 0.");
				userInput = scan.nextInt();
				while(userInput != 1 && userInput != 0 ) { // Makes sure guess1 is within the index of diceInfo. If not, the player is prompted to enter a new answer
					System.out.println("ERROR: Please enter either 1 or 0");
					userInput = scan.nextInt();
				}
				if(userInput == 1) { // replaces the previous roll with a reroll if 1 is entered
					subset.add(i);
				}
			}
		}
		return subset;
	}
	/*
	 * returns the name of the player
	 */
	public String getName() {
		return this.name;
	}
	
} // end class
