/** Composes a list of words from one file (named in the first argument)
 * and replaces instances of them in files named in the remaining arguments with "XXXXXX"
 * @Aviva Blonder
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Redaction {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner file = null;
		try {
			file = new Scanner(new File(args[0]));
		} catch(FileNotFoundException e){
			System.out.println("The file " + args[0] + " does not exist.");
			System.exit(1);
		}
		
	int linecount = 0;
	while(file.hasNext()){
		linecount++;
		file.nextLine();
	}
	
	Scanner ftake2 = null;
	try {
		ftake2 = new Scanner(new File(args[0]));
	} catch(FileNotFoundException e){
		System.out.println("The file " + args[0] + " does not exist.");
		System.exit(1);
	}
	String[] bannedlist = new String[linecount];
	for(int i = 0; i < linecount; i++){
		String word = ftake2.nextLine();
		bannedlist[i] = word;
	}
	
	Scanner file2 = null;
	boolean banned = false;
	for(int dex = 1; dex < args.length; dex++){
		try {
			file2 = new Scanner(new File(args[dex]));
		} catch(FileNotFoundException e){
			System.out.println("The file " + args[dex] + " does not exist.");
			continue;
		}
		while(file2.hasNext()){
			Scanner line = new Scanner(file2.nextLine());
			while(line.hasNext()){
				String word = line.next();
				for(String bannedword : bannedlist){
					banned = true;
					if(bannedword.length() == word.length()){
						for(int index = 0; index < bannedword.length(); index++){
							if(bannedword.charAt(index) != word.charAt(index)){
								banned = false;
								break;
							}
						}
					} else banned = false;
					if(banned) break;
				}
				if(banned) System.out.print("XXXXXX");
				else System.out.print(word);
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	}

}
