/**
 * Test of Boggle
 * @author Aviva Blonder
 */

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Ignore;
import org.junit.Test;


public class BoggleTest {

	/**
	 * Test of Boggle methods without GUI
	 */
	@Ignore
	public void test() {
		// test of constructor FileNotFoundException
		Boggle test = new Boggle("nope");
		
		// test of constructor and fillDice()
		test = new Boggle("enable.txt");
		System.out.println(test.lex);
		for(String die : test.dice){
			System.out.print(die + " ");
		}
		System.out.println();
		
		// test of newGame, fillBoardFromDice, toString, search and fillFoundWords
		test.newGame();
		System.out.print(test.toString());
		System.out.println();
		System.out.println(test.foundwords);
		
		// test of squaresForWord
		System.out.println(test.squaresForWord("not present"));
		System.out.println(test.squaresForWord("aaaaa"));
		for(String word : test.foundwords){
			System.out.println(word + " " + test.squaresForWord(word));
		}
	}
	
	
	/**
	 * Test of Boggle methods with GUI
	 */
	@Test
	public void GUItest(){
		String[] args = {"lexicon.txt"};
		Boggle.main(args);
		Scanner wait = new Scanner(System.in);
		int i = wait.nextInt();
	}

}
