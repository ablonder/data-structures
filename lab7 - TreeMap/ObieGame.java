import java.io.IOException;

/**
 * Find the common links
 * @author gryfalconess
 */
public class ObieGame {
	
	public static void main(String[] args){
		try{
			WebPageIndex TedCruz = new WebPageIndex("https://en.wikipedia.org/wiki/Special:WhatLinksHere/Ted_Cruz");
			WebPageIndex Zodiac = new WebPageIndex("https://en.wikipedia.org/wiki/Zodiac_Killer");
			for(String link : Zodiac.links){
				try{
					WebPageIndex SecondDegree = new WebPageIndex(link);
					for(String link2 : SecondDegree.links){
						if(TedCruz.links.contains(link2))
								System.out.println(link + " " + link2);
					}
				}catch (IOException e){	}
			}
		} catch (IOException e){
			System.out.println("Links didn't work.");
		}
	}
	
}
