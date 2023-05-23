import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Representation of a boggle board
 * 
 * @author Aviva Blonder
 *
 */

public class Boggle {
	MyTrie lex;
	Square[][] board;
	MyTrie foundwords;
	MyTrie guesses;
	String[] dice;
	
	/**
	 * Initializes lex to new Trie containing all of the words in dict
	 * @param dict
	 */
	public Boggle(String dict){
		lex = new MyTrie();
		Scanner f;
		try{
			f = new Scanner(new File(dict));
			while(f.hasNextLine()){
				lex.add(f.nextLine());
			}
			fillDice();
		} catch (IOException e){
			System.out.println("File not found. Try again.");
		}
	}
	
	/**
	 * Returns the board
	 * @return board
	 */
	public Square[][] getBoard(){
		return board;
	}
	
	/**
	 * Returns the number of guesses made
	 * @return size of guesses
	 */
	public int numGuesses(){
		return guesses.size();
	}
	
	/**
	 * Returns a depiction of the board
	 * @return string of each square in board
	 */
	public String toString(){
		String str = "";
		for(Square[] row : board){
			for(Square sq : row){
				str += sq;
			}
			str += "\n";
		}
		return str;
	}
	
	/**
	 * Returns whether the board contains word
	 * @param word
	 * @return whether foundwords contains word
	 */
	public boolean contains(String word){
		return foundwords.contains(word);
	}
	
	/**
	 * Adds guess to guesses if it is on the board
	 * @param guess
	 * @return true if contains guess and false otherwise
	 */
	public boolean addGuess(String guess){
		if(contains(guess)){
			guesses.add(guess);
			return true;
		}
		return false;
	}
	
	/**
	 * Reinitializes the board for a new game by rerolling dice and filling foundwords
	 */
	public void newGame(){
		board = new Square[4][4];
		fillBoardFromDice();
		fillFoundWords();
		guesses = new MyTrie();
	}
	
	/**
	 * Tries to find word starting from any square on the board
	 * @param word
	 * @return a list of one set of squares that make up word
	 */
	public ArrayList<Square> squaresForWord(String word){
		ArrayList<Square> list = new ArrayList<Square>();
		if(word.equals("")) return list;
		for(Square[] squares : board){
			for(Square sq : squares){
				String str = sq.toString();
				if(str.equals(word.substring(0,str.length()))){
					list.add(sq);
					sq.mark();
					list = squaresForWord(sq, word.substring(str.length()), list);
					sq.unmark();
					if(!list.isEmpty()) return list;
				}
			}
		}
		return list;
	}
	
	/**
	 * Fills dice with 16 strings of letters from dice.txt
	 */
	private void fillDice(){
		dice = new String[16];
		Scanner f;
		try{
			f = new Scanner(new File("dice.txt"));
			int i = 0;
			while(f.hasNextLine()){
				dice[i] = f.nextLine();
				i++;
			}
		} catch(IOException e){
			System.out.println("dice file not found.");
		}
	}
	
	/**
	 * Loops through board creating new squares from randomly selected letters of
	 * randomly selected elements of dice
	 */
	private void fillBoardFromDice(){
		Random rand = new Random();
		String[] d = new String[16];
		int a = 0;
		for(String die : dice){
			d[a] = die;
			a++;
		}
		for(int i = 0; i < 16; i++){
			int num = rand.nextInt(16);
			String die = d[num];
			while(die.equals("")){
				if(num == 15) num = 0;
				else num++;
				die = d[num];
			}
			int facenum = rand.nextInt(6);
			String face = die.substring(facenum, facenum+1);
			if(face.toLowerCase().equals("q"))
				face += "u";
			d[num] = "";
			board[i/4][i%4] = new Square(i/4, i%4, face);
		}
	}
	
	/**
	 * Adds all words that start with prefix in lex that can be found from sq to
	 * foundwords
	 * @param sq
	 * @param prefix
	 */
	private void search(Square sq, String prefix){
		if(lex.contains(prefix))
			foundwords.add(prefix);
		for(int x = sq.getX() - 1; x < sq.getX()+2; x++)
			if(x >= 0 && x < 4){
				for(int y = sq.getY() - 1; y < sq.getY()+2; y++)
					if(y >= 0 && y < 4){
						Square neighbor = board[x][y];
						if(!neighbor.isMarked() && lex.containsPrefix(prefix + neighbor)){
							neighbor.mark();
							search(neighbor, prefix+neighbor);
							neighbor.unmark();
						}
					}
		}
	}
	
	/**
	 * Initializes foundwords as a new MyTrie and fills it by searching each square in
	 * board
	 */
	private void fillFoundWords(){
		foundwords = new MyTrie();
		for(Square[] squares : board){
			for(Square sq : squares){
				sq.mark();
				search(sq, sq.toString());
				sq.unmark();
			}
		}
	}
	
	private ArrayList<Square> squaresForWord(Square sq, String word, ArrayList<Square> list){
		if(word.equals(""))
			return list;
		for(int x = sq.getX() - 1; x < sq.getX()+2; x++)
			if(x >= 0 && x < 4){
				for(int y = sq.getY() - 1; y < sq.getY()+2; y++)
					if(y >= 0 && y < 4){
						Square neighbor = board[x][y];
						String str = neighbor.toString();
						String letter = word.substring(0, 1);
						if(word.length() >= str.length()) letter = word.substring(0, str.length());
						if(!neighbor.isMarked() && neighbor.toString().equals(letter)){
							neighbor.mark();
							ArrayList<Square> newlist = (ArrayList<Square>) list.clone();
							newlist.add(neighbor);
							newlist = squaresForWord(neighbor, word.substring(str.length()), newlist);
							neighbor.unmark();
							if(!newlist.isEmpty()) return newlist;
						}
					}
			}
		return new ArrayList<Square>();
	}
	
	public static void main(String[] args){
		Boggle game = new Boggle(args[0]);
		BoggleFrame frame = new BoggleFrame(game);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
}
