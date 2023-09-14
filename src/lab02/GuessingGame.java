//////////// PACKAGES ////////////
package lab02;

//////////// IMPORTS ////////////
import java.util.*;

////////////CLASS DEFINITION ////////////
public class GuessingGame {

////////////MAIN METHOD ////////////
	public static void main(String args[]) {
	/*
	   This is the main method; it runs the game by calling the other methods. First, the game is set up 
	   by asking each player for their name and whether the instructions should be displayed. Next, it runs the main
	   gameplay by giving both players three turns to input guesses.   
	*/
		
		//////////// VARIABLES ////////////
		// scanner object
		Scanner sc = new Scanner(System.in);
		// random object
		Random random = new Random();
		// player names
		String player_1;
		String player_2;
		// random number (correct answer)
		int num;
		// player guesses
		int g; 
		
		//////////// GAME SETUP ////////////
		// get player names
		System.out.println("Player 1, enter your name: ");
		player_1 = "\u001B[36m" + sc.nextLine() + "\u001B[0m";
		
		System.out.println("Player 2, enter your name: ");
		player_2 = "\u001B[31m" + sc.nextLine() + "\u001B[0m";
		
		// print instructions
		System.out.println(player_1 + " and " + player_2 + ", would you like to read the instructions? (y/n)");
		String ans = sc.nextLine();
		// test validity
		do {
		   if(ans.equalsIgnoreCase("y")) {
		       instructions();
		   } else if (!(ans.equalsIgnoreCase("n"))) {
		       System.out.println("Please enter 'y' or 'n'");
		       ans = sc.nextLine();
		   }
		} while(!(ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("n")));
		
		// generate random number between 1 and 100
		num = random.nextInt(100) + 1;
		
		//////////// RUN THE GAME ////////////
		// give both players 3 turns
		for(int t = 1; t < 4; t++) {
		   // player 1 turn
		   g = checkValidInput(sc, player_1);
		   if(giveHint(g, num, player_1)) {
		       break;
		   }
		
		   // player 2 turn
		   g = checkValidInput(sc, player_2);
		   if(giveHint(g, num, player_2)) {
		       break;
		   }
		   
		   // display the correct answer if both players lose (all three turns are used up)
		   if (t > 2) {
		       System.out.println("The correct answer was " + num + ". Better luck next time!");
		   }
		}
		// close scanner object
		sc.close();
	}
		
	////////////OTHER METHODS ////////////
	public static void instructions() {
	/*
	   Prints the game instructions
	   Parameters: n/a
	   Returns: n/a
	*/
		System.out.println("SUMMARY: This is a two-player luck-based game where opponents take alternating turns\n" +
		                  "         guessing a number between 1 and 100 inclusive.\n\n" +
		                  "RULES:   * Each player gets up to 3 turns\n" + 
		                  "         * Guesses must be between 1 and 100 inclusive\n" +
		                  "         * A player must give the correct answer within their 3 turns to win\n" +
		                  "         * If neither player gives the correct answer in 3 turns, they both lose\n"
		                 );
	}
	public static int checkValidInput(Scanner sc, String player) {
	/*
	   Asks player to enter an integer between 1 and 100 and tests for input validity
	   Parameters: Scanner object, String object storing the player's name
	   Returns: int storing the player's valid guess
	*/
		String g;
		Boolean valid = false;
		do {
		   System.out.println(player + ", enter an integer between 1 and 100: ");
		   g = sc.nextLine(); 
		   // test if the input is a valid integer
		   try {
		       Integer.parseInt(g);
		       // test if the input is between 1 and 100 inclusive
		       if(Integer.parseInt(g) >= 1 && Integer.parseInt(g) <= 100) {
		           valid = true;
		       } else {
		           // if the integer is out of range
		           System.out.println(g + " is not a valid integer!");
		       }
		   } catch(NumberFormatException e) {
		       // if the input is not an integer
		       System.out.println(g + " is not an integer!");
		   }
		} while(!valid);
		
		return Integer.parseInt(g);
	}
	public static Boolean giveHint(int g, int c, String player) {
	/*
	   This method displays a hint revealing whether an incorrect guess is lower than, higher than, or equal 
	   to the answer.
	   Parameters: int storing the player's guess, int storing the correct answer, 
	               String object storing the player's name
	   Returns: Boolean determining if the game should continue given the player's guess
	
	*/
		if(g < c) {
		   System.out.println(g + " is too low...");
		   return false;
		} else if (g > c) {
		   System.out.println(g + " is too high...");
		   return false;
		} else {
		   // congratulate a player if they guess the number correctly
		   System.out.println(player + " has guessed correctly! You win!");
		   return true;
		}
	}
}
