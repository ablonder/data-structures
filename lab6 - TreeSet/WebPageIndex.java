/**
 * index representation of a webpage
 * 
 * @author Aviva Blonder
 * 
 */

import java.io.IOException;
import java.util.*;

public class WebPageIndex {
    
	public MyTreeMap<String, LinkedList<Integer>> wordIndices;
	public MyTreeSet<String> links;
	public String pageURL;
	public int wordcount;

	/**
	 * Initializes pageURL to baseURL and goes through the webpage, adding to wordcount, wordIndicies, and links
	 * 
	 * @param baseURL
	 */
	public WebPageIndex(String baseURL) throws IOException{
		pageURL = baseURL;
		wordcount = 0;
		wordIndices = new MyTreeMap<String, LinkedList<Integer>>();
		links = new MyTreeSet<String>();
		HTMLScanner reader = new HTMLScanner(baseURL);
		String word;
		while(reader.hasNext()){
			word = reader.next().toLowerCase();
			if(wordIndices.containsKey(word)){
				wordIndices.get(word).add(wordcount);
			} else {
				LinkedList<Integer> list = new LinkedList<Integer>();
				list.add(wordcount);
				wordIndices.put(word, list);
			}
			wordcount++;
		}
		while(reader.hasNextLink()) links.add(reader.nextLink().toLowerCase());
	}
	
	/**
	 * returns the number of words in the webpage designated by pageURL
	 * 
	 * @return wordcount
	 */
    public int getWordCount() {
        return wordcount;
    }
    
    /**
     * returns the URL of the webpage indexed
     * 
     * @return pageURL
     */
    public String getUrl() {
        return pageURL;
    }
    
    
    /**
     * returns whether the index contains s
     * 
     * @param s
     * @return true if s is a key in wordIndices
     * @return false if s is not a key in wordIndices
     */
    public boolean contains(String s) {
        if(wordIndices.containsKey(s)) return true;
        else return false;
    }
    
    
    /**
     * returns the number of times s appears in the webpage designated by pageURL
     * 
     * @param s
     * @return the size of the list corresponding to s in wordIndices (and 0 if it is not a key)
     */
    public int getCount(String s) {
        if(wordIndices.containsKey(s)){
        	return wordIndices.get(s).size();
        }
        return 0;
    }
    
    
    /**
     * Returns the frequency of s on the webpage designated by pageURL
     * 
     * @param s
     * @return getCount() of s divided by wordcount
     */
    public double getFrequency(String s) {
    	if(wordcount > 0) return (double)getCount(s)/wordcount;
    	return 0;
    }

    
    /**
     * Returns the locations of s in the webpage designated by pageURL
     * 
     * @param s
     * @return the list associated with the key s in wordIndices (or an empty LinkedList if it is not a key)
     */
    public List<Integer> getLocations(String s) {
    	if(wordIndices.containsKey(s)) return wordIndices.get(s);
    	return new LinkedList<Integer>();
    }
    
    /**
     * Returns an iterator that goes through wordIndices in alphabetical order
     * 
     * @return iterator that goes through the keys of wordIndices inorder
     */
    public Iterator<String> words() {
        return wordIndices.keys();
    }
    
    /**
     * Returns a string representation of the WebPageIndex
     * 
     * @return toString() of wordIndices
     */
    public String toString() {
        return wordIndices.toString();
    }
    
    /* 
     * additional features you might add to support multi-word phrases 
     *
     * work on these after you have the previous methods working correctly
     */

    /**
     * returns whether the index contains the phrase s
     * 
     * @param s
     * @return true if all the words in phrase s are keys in wordList in order and false otherwise
     */
    public boolean containsPhrase(String s) {
        String[] phrase = s.split("\\s+");
        String word = phrase[0];
        if(this.contains(word)){
        	for(int index : wordIndices.get(word)){
        		if(containsPhraseRecurse(Arrays.copyOfRange(phrase, 1, phrase.length), index+1)) return true;
        	}
        }
        return false;
    }
    
    /**
     * goes through phrase, checking if the first word is on the webpage and if any of its indices are at index
     * 
     * @param phrase
     * @param index
     * @return true if the first word in phrase is at index followed by all the other words in the phrase and false if not
     */
    public boolean containsPhraseRecurse(String[] phrase, Integer index){
    	if(phrase.length == 0) return true;
    	String word = phrase[0];
        if(this.contains(word)){
        	for(int dex : wordIndices.get(word)){
        		if(dex == index && containsPhraseRecurse(Arrays.copyOfRange(phrase, 1, phrase.length), dex+1)) return true;
        	}
        }
        return false;
    }
    
    /**
     * Adds up the number of times s appears in the webpage
     * 
     * @param s
     * @return the sum of phraseCountRecurse() for every time the first word of s appears on the webpage
     */
    public int getPhraseCount(String s) {
    	String[] phrase = s.split("\\s+");
        String word = phrase[0];
        int total = 0;
        if(this.contains(word)){
        	for(int index : wordIndices.get(word)){
        		total += phraseCountRecurse(Arrays.copyOfRange(phrase, 1, phrase.length), index+1);
        	}
        }
        return total;
    }
    
    /**
     * goes through phrase, counting the number of times the first word of phrase is
     * at index, followed by all the other words in phrase
     * 
     * @param phrase
     * @param index
     * @return phaseCountRecurse() of the rest of the phrase at index+1 if the first word of phrase appears at index
     * @return 0 otherwise 
     */
    public int phraseCountRecurse(String[] phrase, Integer index){
    	if(phrase.length == 0) return 1;
    	String word = phrase[0];
    	if(this.contains(word)){
        	for(int dex : wordIndices.get(word)){
        		if(dex == index)
        			return phraseCountRecurse(Arrays.copyOfRange(phrase, 1, phrase.length), dex+1);
        	}
        }
        return 0;
    }
    
    /**
     * returns the frequency of the phrase in the webpage by word
     * 
     * @param s
     * @return getPhraseCount() of s, times the number of words in it, divided by wordcount
     */
    public double getPhraseFrequency(String s) {
    	double lens = s.split("\\s+").length;
        return lens*getPhraseCount(s)/wordcount;
    }

    /**
     * returns the indices where the phrase s occurs in the webpage
     * 
     * @param s
     * @return the list of locations where the first word of s is followed by the others
     */
    public List<Integer> getPhraseLocations(String s) {
    	LinkedList<Integer> locations = new LinkedList<Integer>();
    	String[] phrase = s.split("\\s+");
        String word = phrase[0];
        if(this.contains(word)){
        	for(int index : wordIndices.get(word)){
        		if(containsPhraseRecurse(Arrays.copyOfRange(phrase, 1, phrase.length), index+1))
        			locations.add(index);
        	}
        }
        return locations;
    }
    
    /**
     * prints out the frequencies and indices of words in the urls in args
     * 
     * @param args
     */
    public static void main(String[] args){
    	try{
	    	for(String url : args){
	        	WebPageIndex index = new WebPageIndex(url);
	        	Iterator<String> words = index.wordIndices.keys();
	        	System.out.printf("Frequency and indices of words in %s:\n", url);
	        	while(words.hasNext()){
	        		String word = words.next();
	        		String toprint = word;
	        		if(word.length() > 7) toprint += "\t";
	        		else toprint += "\t\t";
	        		System.out.printf(toprint + "%.6f\t" + index.getLocations(word) + "\n", index.getFrequency(word));
	        	}
	        	System.out.println();
	        	System.out.println("Links:");
	        	for(String link : index.links) System.out.println(link);
	        	System.out.println();
	        	System.out.println();
	    	}
        } catch(IOException e){
        	System.out.println("I'm sorry, url not found. Try again.");
        }
    }
    
}
