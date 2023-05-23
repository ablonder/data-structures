import java.util.Comparator;

/**
 * Compares WebPageIndex objects based on a query
 * 
 * @author Aviva Blonder
 */

public class URLComparator implements Comparator<WebPageIndex>{
	String[] query;
	
	/**
	 * Initializes query to searchquery
	 * @param searchquery
	 */
	public URLComparator(String searchquery){
		query = searchquery.split("\\s+");
	}
	
	/**
	 * Returns a positive number if page1 has a larger score than page2, a negative number if page2 has a larger score
	 * and zero if their scores are equal
	 * 
	 * @param page1, page2
	 * @return the calculated score of page1 minus the score of page2
	 */
	public int compare(WebPageIndex page1, WebPageIndex page2){
		return scoreCalc(page1) - scoreCalc(page2);
	}
	
	/**
	 * Returns page's total score for all the words in query
	 * 
	 * @param page
	 * @return sum of the scores of all the words in query in page
	 */
	public int scoreCalc(WebPageIndex page){
		int total = 0;
		boolean p = false;
		String phrase = "";
		for(String word : query){
			if(word.charAt(0) == '"' || Character.toString(word.charAt(0)).equals("'")){
				phrase = word.substring(1, word.length());
				p = true;
			}else if(p){
				phrase += " " + word.toLowerCase();
			}
			if(p && (word.charAt(word.length()-1) == '"' || Character.toString(word.charAt(word.length()-1)).equals("'"))){
				phrase = phrase.substring(0, phrase.length()-1);
				total +=  page.getPhraseCount(phrase.toLowerCase());
				p = false;
				phrase = "";
			}else if(!p){
				total += page.getCount(word.toLowerCase());
			}
		}
		return total;
	}

}
