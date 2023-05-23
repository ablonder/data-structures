/** calculates the relative frequency of numbers as the first digit
 * in files (named as arguments) and creates a scaled chart
 * 
 * The different data sets follow Benford's law to different degrees: 
 * follow - census county est, census pop est, census zip emp, fib 1001, perfect squares 1000
 * somewhat - cia country pop, primes 10000, primes 1000000
 * don't - e 2 million, pi 1 million
 * 
 * The two that don't aren't really numbers in the sense that we typically use them, instead they're the digits of very long decimals divided
 * up arbitrarily into even sections. Since they are irrational numbers and therefore have no repeating pattern, each digit has an equal chance
 * of being first, which is what happens.
 * 
 * I have adhered to the honor code in this assignment.
 * @Aviva Blonder
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Benford {
	public static final int MAXWIDTH = 50;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] numcount = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		Scanner file = null;
		for(int i = 0; i < args.length; i++){
			try{
				file = new Scanner(new File(args[i]));
			} catch(FileNotFoundException e){
				System.out.println("The file " + args[i] + " does not exist.");
				System.exit(1);
			}
			while(file.hasNext()){
				Scanner line = new Scanner(file.nextLine());
				while(line.hasNext()){
					String word = line.next();
					char first = word.charAt(0);
					if(Character.isDigit(first)){
						String f = word.substring(0,1);
						int num = Integer.parseInt(f);
						numcount[num]++;
					}
				}
			}
			
			int tallest = 0;
			int total = 0;
			for(int count : numcount){
				if(count > tallest) tallest = count;
				total += count;
			}
			for(int dex = 0; dex < 10; dex++){
				System.out.print(dex);
				int freq = 100*numcount[dex]/total;
				System.out.printf("%8d %4d%% : ", numcount[dex], freq);
				for(int n = 0; n < MAXWIDTH*numcount[dex]/tallest; n++){
					System.out.print("*");
				}
				System.out.println();
			}
		}

	}

}
