/**
 * Generates pseudo-random text based on a file
 * 
 * @author Aviva Blonder
 */

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class TextGenerator {

	public static void main(String[] args){
		try{
			int k = Integer.parseInt(args[0]);
			int M = Integer.parseInt(args[1]);
			String file = args[2];
			MyHashMap<String, Markov> table;
			try{
				FileReader input = new FileReader(file);
				table = new MyHashMap<String, Markov>();
				char nextChar = (char) input.read();
				String sub = "";
				while(nextChar != (char) -1){
					sub += nextChar;
					if(sub.length() > k) sub = sub.substring(1);
					if(sub.length() == k){
						if(!table.containsKey(sub)) table.put(sub, new Markov(sub));
						table.get(sub).add();
						nextChar = (char) input.read();
						table.get(sub).add(nextChar);
					} else nextChar = (char) input.read();
				}
				input = new FileReader(file);
				nextChar = (char) input.read();
				sub = "";
				String printstr = "";
				String start = "";
				while(nextChar != (char) -1 && sub.length() < k){
					//System.out.println("second loop " + sub + " " + printstr);
					printstr += nextChar;
					sub += nextChar;
					start += nextChar;
					nextChar = (char) input.read();
				}
				if(sub != "") for(int i = 0; i < M; i++){
					//System.out.println("third loop " + sub + " " + printstr);
					char next;
					try{
						next = table.get(sub).random();
					} catch (NullPointerException e) {
						next = table.get(start).random();
					}
					printstr += next;
					sub += next;
					if(sub.length() > k) sub = sub.substring(1);
				}
				System.out.print(printstr);
				if(table.size < 100){
					Iterator<Markov> vals = table.values();
					System.out.println();
					while(vals.hasNext()) System.out.println(vals.next());
				}
			} catch (IOException e){
				System.out.println("File not found.");
			}
		} catch (NumberFormatException e){
			System.out.println("I'm sorry, you didn't enter a number for the 1st or 2nd argument, try again.");
		} // catch (IndexOutOfBoundsException e){
			// System.out.println("I'm sorry, you didn't include enough arguments, try again.");
		// }
		System.out.println();
	}
}
