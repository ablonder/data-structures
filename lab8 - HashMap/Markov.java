import java.util.TreeMap;

/**
 * Generates a random character to follow the provided substring
 * 
 * @author Aviva Blonder
 */

public class Markov {
	String substr;
	int count;
	TreeMap<Character, Integer> suffixes;
	
	/**
	 * Initializes substr to substring and count to 0
	 * 
	 * @param substring
	 */
	public Markov(String substring){
		substr = substring;
		count = 0;
		suffixes = new TreeMap<Character, Integer>();
	}
	
	/** increments count */
	public void add(){
		count++;
	}
	
	/**
	 * Adds c to suffixes if necessary and increments it
	 * 
	 * @param c
	 */
	public void add(char c){
		if(suffixes.containsKey(c)){
			int val = suffixes.get(c);
			suffixes.put(c, val+1);
		} else suffixes.put(c, 1);
	}
	
	/**
	 * Chooses a semi-random character from suffixes based on each character's count
	 * 
	 * @return the suffix that corresponds to a generated random number
	 */
	public char random(){
		double current = 0;
		double num = Math.random();
		for(char suf : suffixes.keySet()){
			double val = (double) suffixes.get(suf);
			current += val/count;
			if(num <= current) return suf;
		}
		return suffixes.lastKey();
	}
	
	/**
	 * Returns it in String form
	 * 
	 * @return substr
	 */
	public String toString(){
		String str = "" + count + " " + substr + ": ";
		for(char suf : suffixes.keySet()){
			str += suffixes.get(suf) + " " + suf + " ";
		}
		return str;
	}
}
