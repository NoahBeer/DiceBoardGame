import java.util.ArrayList;
import java.util.Random;

/*
 * This class is responsible for managing the entire game using Die, NonRepeatingDie, Player, and AutomatedPlayer
 * 
 * @Noah Beer
 */
public class GameMaster {
	private boolean gameStarted; // declares instance variable boolean gameStarted and assigns it to false
	private ArrayList<Player> storePlayers; // declares instance variable ArrayList of type Player storePlayers
	private ArrayList<Integer> diceInfo; // declares instance variable ArrayList of type Integer diceInfo
	private ArrayList<Die> dice; // declares instance variable ArrayLisy of type Die dice
	private ArrayList<Integer> curRoll; // declares instance variable ArrayLisy of type Integer curRoll
	private ArrayList<Integer> score; // declares instance variable ArrayLisy of type Integer score
	private ArrayList<Integer> reRoll; // declares instance variable ArrayLisy of type Integer reRoll
	private Random rand; // declares instance variable Random rand
	private int checkPrime; // declares instance variable int checkPrime
	private int storeDieAmount; // declares instance variable int storeDieAmount
	private int indexOfNRD1; // declares instance variable int indexOfNRD1
	private int indexOfNRD2; // declares instance variable int indexOfNRD2
	private int sides; // declares instance variable int sides
	private int round; // declares instance variable int round
	private int storeWinnerScore; // declares instance variable int storeWinnerScore
	private int storeWinnerIndex; // declares instance variable int  storeWinnerIndex
	
	/*
	 * the GameMaster constructor decides how many die of each type there will be and how many sides each die will have.
	 * It also initializes all the instance variables in the class.
	 */
	public GameMaster() { 
		gameStarted = false; // initializes instance variable gameStarted to false
		storePlayers = new ArrayList<Player>(); // initializes instance variable storePlayers
		diceInfo = new ArrayList<Integer>(); // initializes instance variable diceInfo
		dice = new ArrayList<Die>(); // initializes instance variable dice
		curRoll = new ArrayList<Integer>(); // initializes instance variable curRoll
		score = new ArrayList<Integer>(); // initializes instance variable score
		reRoll = new ArrayList<Integer>(); // initializes instance variable reRoll
		rand = new Random(); // initializes instance variable rand
		storeDieAmount = 4 + rand.nextInt(6); // Generates a random amount of Die between 4-9
		indexOfNRD1 = rand.nextInt(storeDieAmount); // stores a random index for NRD1
		indexOfNRD2 = rand.nextInt(storeDieAmount); // stores a random index for NRD2
		round = 1; // initializes instance variable round to 1
		checkPrime = 0; // initializes instance variable checkPrime to 0
		int storeWinnerScore = -1; // initializes instance variable storeWinnerIndex to -1
		int storeWinnerIndex = -1; // initializes instance variable storeWinnerScore to -1
		
		while(indexOfNRD1 == indexOfNRD2) { // Makes sure NRD1 doesn't have the same index of NRD2. If it does, it continues to create a new index for NRD2 until it is different than NRD1
			indexOfNRD2 = rand.nextInt(storeDieAmount);
		}
		
		for(int i = 0; i < storeDieAmount; i++) { // Randomly generates how many sides each Die will have (between 5-21) and stores the Die and NRD
			sides = 5 + rand.nextInt(17); 
			if(i == indexOfNRD1 || i == indexOfNRD2) { // if i equals the index of NRD1 or NRD2 then that NRD is stored at index i
				NonRepeatingDie nrd = new NonRepeatingDie(sides, i); // creates a NRD with sides amount of sides and ID i
				dice.add(nrd);
				diceInfo.add(sides); // adds how many sides each Die has to diceInfo
			}
			else { 
				Die die = new Die(sides, i); // creates a Die with sides amount of sides and ID i
				dice.add(die);
				diceInfo.add(sides); // adds how many sides each die has to diceInfo
			}
		}
	}
	
	/*
	 * registerPlayer() creates a new player and stores it in the storePlayers ArrayList if the game hasn't
	 * already begun. It then gives the player their turn number.
	 */
	public boolean registerPlayer(Player player) {
		if(! gameStarted) {
			storePlayers.add(player);
			player.receiveMyTurnNumber(storePlayers.size() - 1);
			player.receiveDiceInfo(diceInfo);
			player.receiveNumberofPlayers(storePlayers.size());
			score.add(0); 
			return true;
		}
		return false;
	}
	
	/*
	 * roll() uses the roll() method from Die in order to work in class GameMaster. 
	 */
	private ArrayList<Integer> roll() {
		int curRoll;
		ArrayList<Integer> storeRollResults;
		storeRollResults = new ArrayList<Integer>();
		for(int i = 0; i < dice.size(); i++) { // loops through and roll each Die/NRD. The results of the roll are then stored in ArrayList storeRollResults
			curRoll = dice.get(i).roll();
			storeRollResults.add(curRoll);
		}
		return storeRollResults;
	}
	
	/*
	 * playGame() is where the game is able to be played using the instance variables and method of GameMaster, Player,
	 * AutomatedPlayer, Die and NonRepeatingDie
	 */
	public void playGame() {
		gameStarted = true;
		storePlayers.get(0).receiveNumberofPlayers(storePlayers.size());
		while(round <= 15) { // Continues to play the game until the end of round 15
			System.out.println("Round " + round + " will now begin");
			for(int i = 0; i < storePlayers.size(); i++) { // loops through storePlayers and allows each player their chance to play each round
				if(storePlayers.get(i) != null) { // checks to see if the current turn player is null or not. If he's not null, the player gets the opportunity to play the round
					if(storePlayers.get(i).wantsToGuess() == true) { // asks the player is he/she wants to guess and then allows them to if he/she would like to guess
						int[] guesses = storePlayers.get(i).guess(); // gets each guess
						if((dice.get(guesses[0]) instanceof NonRepeatingDie && dice.get(guesses[1]) instanceof NonRepeatingDie)) { // checks to see if each guess is an instance of NRD
							System.out.println(storePlayers.get(i).getName() + " guessed correctly! You Win!");
							round = 16; // sets round equal to 16 so the while loop ends, ending the game
							break; // breaks out of the current while loop so the game will end since round now equals 16
						}
						else { // called if guess is entered incorrectly
							System.out.println("You guessed incorrectly. You have been elimated\n1");
							storePlayers.set(i, null); // boots the player from the game and sets the player as null
							int countNull = 0;
							for(int isAllNull = 0; isAllNull < storePlayers.size(); isAllNull++) { // loops through storePlayers to see if each player is null. If all players are null. Game ends and is declared a draw
								if(storePlayers.get(isAllNull) == null) { // checks to see if the player at index isAllNull is null
									countNull++;
									if(countNull == storePlayers.size()) { // if countNull is equal to storePlayers.size() then all players are null and the game is over. 
										round = 16;
										System.out.println("All players have been eliminated. Game is over.");
									}
									
								}
							}
						}
					}
					if(storePlayers.get(i) != null) { // if the current player isn't null, the player gets to roll and potentially reroll
						curRoll = roll(); // stores ArrayList curRoll with the results from roll()
						storePlayers.get(i).receiveRollResults(curRoll); // prints the results from the roll for the player to see
						reRoll = storePlayers.get(i).reroll(); // gives players option to reroll
						for(int j = 0; j < reRoll.size(); j++) { // loops through reRoll
							for(int n = 0; n < dice.size(); n++) { // loops through ArrayList dice
								if(reRoll.get(j) == dice.get(n).getId()) { // if the player chose to reroll the current Die, the current Die is rerolled and then stored in curRoll
									curRoll.set(n, dice.get(n).roll());
								}
							}
						}
						if(curRoll.containsAll(reRoll) == false) { // checks to see if the player rerolled and prints the results of the reroll if the player rerolled
							storePlayers.get(i).receiveRollResults(curRoll);
						}
						checkPrime = 0;
						for(int k = 0; k < curRoll.size(); k ++) { // loops through curRoll and adds up the score from the round. Then the score from the round is checked to see if it is a prime number
							score.set(i, score.get(i) + curRoll.get(k));
							checkPrime = checkPrime + curRoll.get(k);
						} 
						if(isPrime(checkPrime)) { // if checkPrime is true. It's printed that the player won, round is set to 16, and break is called to end the while loop, therefore ending the game.
							System.out.println(storePlayers.get(i).getName() + ", your sum from the prior roll was a prime number. You are the winner! Game is over.");
							round = 16;
							break;
						} 
						System.out.println("The Score is now " + score + "\n"); // Prints score
						
						int over500 = 0; // declares local int over500 
						for(int m = 0; m < score.size(); m++) { // loops through score to check to see if all the scores are over 500
							if(round == 15 && i == storePlayers.size() -1) { // checks to see if round is 15 and m is at the last index of storePlayers
								if(score.get(m) > 500) { // if player at index m has a score over 500, over500++
									over500++;
								}
								if(over500 == storePlayers.size()) { // if over500 is the same number as the size of storePlayers, print draw
									System.out.println("All 15 rounds have been played. All players accumalated more than 500 points, so the game is declared a draw.");
									round = 16;
									break;
								}
							}
						}
					
						for(int m = 0; m < score.size(); m++) { // loops through score too see who has the highest score without exceeding 500
							if(round == 15 && i == storePlayers.size() -1) { // if round is 15 and index is on the last player, go to next if statement
								if(score.get(m) < 500 && score.get(m) > storeWinnerScore) { // if player at index m has less than 500 and a higher score than any previous player with a score under 500, store the players info
									storeWinnerScore = score.get(m);
									storeWinnerIndex = m;
								}
								if(m == storePlayers.size() - 1 && storeWinnerIndex != -1) { // if all players have been checked, print winner
									System.out.println("All 15 rounds have been played. " + storePlayers.get(storeWinnerIndex).getName() + " is the winner due to having the most points, without exceeding 500, at the end of the game!");
								}
								
							}
						}
					}
				}
			}
			round++;
		}
	}
	
	public int numPlayers() { // returns how many players are in the game
		return storePlayers.size();
	}
	
	private boolean isPrime(int score) { // checks to see if a number is prime and returns a boolean value
		for(int i = 2; 2 * i < score; i++) {
			if(score % i==0) {
				return false;
			}
		}
		    return true;
	}
} // end class
