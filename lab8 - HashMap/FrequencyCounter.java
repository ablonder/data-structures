/**
 * Calculates the frequency of each substring of length args[0] in user input
 * 
 * @author Aviva Blonder
 */

import java.util.Iterator;
import java.util.Scanner;

public class FrequencyCounter {

	public static void main(String[] args){
		try{
			int k = Integer.parseInt(args[0]);
			MyHashMap<String, Markov> table;
			Scanner input = new Scanner(System.in);
			String str;
			String sub;
			boolean done = false;
			System.out.println("What string to do you want to look at? (Hit enter to exit.)");
			while(!done && input.hasNextLine()){
				str = input.nextLine();
				table = new MyHashMap<String, Markov>(str.length(), (float) .75);
				if(str.equals("")) done = true;
				else try{
					for(int i = 0; i + k <= str.length(); i++){
						sub = str.substring(i, i+k);
						if(!table.containsKey(sub)){
							table.put(sub, new Markov(sub));
						}
						table.get(sub).add();
					}
					System.out.println(table.size + " distinct keys");
					Iterator<String> keys = table.keys();
					while(keys.hasNext()){
						String key = keys.next();
						System.out.println(table.get(key).count + " " + key);
					}
					System.out.println("What string to do you want to look at? (Hit enter to exit.)");
				} catch (IndexOutOfBoundsException e) {
					System.out.println("The string wasn't long enough to generate substrings of length " + k + " .");
				}
			}
		} catch (NumberFormatException e){
			System.out.println("I'm sorry, you didn't enter a number, try again.");
		} catch (IndexOutOfBoundsException e){
			System.out.println("I'm sorry, you didn't include enough arguments, try again.");
		}
	}
}