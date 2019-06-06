import java.util.ArrayList;
/*
 * This class creates an AutomatedPlayer to be used in GameMaster that is capable of making logical decisions within
 * playGame() in order to have the best chance of winning the game.
 * 
 * @Noah Beer
 */

public class AutomatedPlayer extends Player {
	
	private ArrayList<Integer> diceInfo; // declares instance variable ArrayList of type Integer diceInfo
	private ArrayList<Integer> curRoll; // declares instance variable ArrayList of type Integer curRoll
	private ArrayList<ArrayList<Integer>> rollHistory; // declares instance variable ArrayList of type Integer rollHistory
	private int averageRoll; // declares instance variable int averageRoll
	private int totalScore; // declares instance variable int  totalScore
	private int resultsTotal; // declares instance variable int resultsTotal
	private double round; // declares instance variable double round
	private int numPlayers; // declares instance variable int numPlayers
	
	/*
	 * The constructor takes in the Player's name as a parameter. It initializes diceInfo, curRoll, and rollHistory, and
	 * gives averageRoll the value of 0, totalScore the value of 0, totalScore the value of 0, resultsTotal the value of 0,
	 * round the value of 1, and numPlayers the value of 0. It also gives value to curDieHistory
	 */
	public AutomatedPlayer(String name) {
		super(name); // calls super
		diceInfo = new ArrayList<Integer>(); // initializes instance variable diceInfo
		curRoll = new ArrayList<Integer>(); // initializes instance variable curRoll
		rollHistory = new ArrayList<ArrayList<Integer>>(); // initializes instance variable rollHistory
		averageRoll = 0; // initializes instance variable averageRoll to 0 
		totalScore = 0; // initializes instance variable totalScore to 0
		resultsTotal = 0; // initializes instance variable resultsTotal to 0
		round = 1.0; // initializes instance variable round to 1.0
		numPlayers = 0; // initializes instance variable numPlayer to 0
		
		for(int i = 0; i < diceInfo.size(); i++) { // loops through diceInfo
			ArrayList<Integer> curDieHistory = new ArrayList<Integer>(); // // initializes curDieHistory
			rollHistory.add(curDieHistory);
		}
	}
	/*
	 * Assigns diceInfo to info
	 */
	public void receiveDiceInfo(ArrayList<Integer> info) {
		this.diceInfo = info;
	}
	/*
	 * Assigns numPlayers to pNum
	 */
	public void receiveNumberOfPlayers(int pNum) {
		numPlayers = pNum;
	}
	
	/*
	 * This method increments round, adds up the results of the roll and stores it, and changes average roll
	 */
	public void receiveRollResults(ArrayList<Integer> results) {
		round = (round + 1) / numPlayers; // increments round to a whole number after each player gets their turn
		curRoll = results;
		resultsTotal = 0;
		
		for(int i = 0; i < results.size(); i++) { // loops through results and adds up the rolls
			resultsTotal = resultsTotal + results.get(i);
		}
		averageRoll = resultsTotal / results.size(); // updates averageRoll
		totalScore = totalScore + resultsTotal; // updates totalScore
	}
	/*
	 * Always returns false since guessing is risky, hard to predict, and rolling a prime number is the easiest way to win
	 */
	public boolean wantsToGuess() { 
		return false;
	}
	/*
	 * Checks to see if the score is prime and return a boolean
	 */
	private boolean isPrime(int score) {
		for(int i = 2; 2 * i < score; i++) {
			if(score % i==0) {
				return false;
			}
		}
		    return true;
	}
	/*
	 * rerolls if the score from the round isn't prime. Doesn't reRoll if the score of the round is less than the
	 * average roll and the player has more than 300 points and it is past round 10 because the Player doesn't want 
	 * to risk going over 500
	 */
	public ArrayList<Integer> reroll() {
		ArrayList<Integer> reRoll = new ArrayList<Integer>();
		if(isPrime(resultsTotal) == false) { // checks to see if it's a prime number
			for(int i = 0; i < diceInfo.size(); i++)
			reRoll.add(i);
		}
		if(resultsTotal < averageRoll && totalScore < 300 && round > 10) { // checks to see is resultsTotal is less than averageRoll, totalScore is less than 300, and it's past round 10
			reRoll.clear();
		}
		return reRoll;
	}
} //end class
