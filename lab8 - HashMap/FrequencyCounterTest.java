/**
 * Test of FrequencyCounter, SuffixCounter, and TextGenerator
 * 
 * @author Aviva Blonder
 */

import static org.junit.Assert.*;

import org.junit.Test;


public class FrequencyCounterTest {

	@Test
	public void FrequencyCounterTest() {
		// test of handling NumberFormatException
		String[] args = {"lalala"};
		FrequencyCounter.main(args);
		
		// test of normal operation
		args[0] = "2";
		FrequencyCounter.main(args);
	}
	
	@Test
	public void SuffixCounterTest(){
		// test of normal operation
		String[] args = {"2"};
		SuffixCounter.main(args);
	}
	
	@Test
	public void TextGeneratorTest(){
		// test of handling IndexOutOfBoundsException
		//String[] args = {};
		//TextGenerator.main(args);
		
		// test of handling NumberFormatException
		String[] args1 = {"lalala", "la", "la"};
		TextGenerator.main(args1);
		
		// test of handling FileNotFoundException
		args1[0] = "1";
		args1[1] = "2";
		TextGenerator.main(args1);
		
		// test of insufficient letters
		args1[2] = "empty.txt";
		TextGenerator.main(args1);
		
		// test of normal operation
		args1[0] = "2";
		args1[1] = "8";
		args1[2] = "notempty.txt";
		TextGenerator.main(args1);
		System.out.println();
		
		args1[0] = "10";
		args1[1] = "10000";
		args1[2] = "mlp-season-1.txt";
		TextGenerator.main(args1);
	}

}
