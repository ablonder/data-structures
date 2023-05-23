/** the computer generates a random number that the user must guess
 * based on high/low responses
 * @Aviva Blonder
 */

import java.util.Random;
import java.util.Scanner;

public class HiLo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random numgen = new Random();
		int number = numgen.nextInt(1000)+1;
		
		Scanner input = new Scanner(System.in);
		boolean correct = false;
		int guesscount = 0;
		
		System.out.println("I'm thinking of a number.");
		
		while (!correct){
			System.out.println("Guess a number between 1 and 1000!");
			
			String line = input.nextLine();
			Scanner s2 = new Scanner(line);
		
			if(s2.hasNextInt()){
				guesscount++;
				int guess = s2.nextInt();
				if(guess == number){
					correct = true;
					System.out.println("You guessed it!");
					System.out.println("It took you " + guesscount + " tries.");
				} else if(guess > number) {
					System.out.println("Too high!");
				} else {
					System.out.println("Too low!");
				}
			} else {
				System.out.println("That's not a number. Try again.");
			}
		
			System.out.println();
			
		}

	}

}
