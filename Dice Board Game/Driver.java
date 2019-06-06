/*
 * Driver creates instances of Players and GameMaster and calls registerPlayer() and playGame()
 * 
 * @Noah Beer
 */
public class Driver {

	public static void main(String[] args) {
		
		Player p1 = new Player("Adam"); // creates Player named Adam
		Player p2 = new Player("Noah"); // creates Player named Noah
		Player p3 = new AutomatedPlayer("Matt"); // creates AutomatedPlayer named Matt
		Player p4 = new AutomatedPlayer("Jack"); // creates AutomatedPlayer named Jack
		GameMaster gm = new GameMaster(); // creates GameMaster gm
		gm.registerPlayer(p1); // registers p1
		gm.registerPlayer(p2); // registers p2
		gm.registerPlayer(p3); // registers p3
		gm.registerPlayer(p4); // registers p5
		gm.playGame(); // plays the game
	}
} //end class

