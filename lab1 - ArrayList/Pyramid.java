/** draws a pyramid composed of *'s of height specified by the argument
 * @Aviva Blonder
 */

public class Pyramid {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length != 1){
			System.out.println("Wrong number of arguments: " + args);
			System.exit(1);
		}
		
		int height = Integer.parseInt(args[0]);
		for(int row = 0; row < height; ++row){
		    for(int space = 1; space <= height+row; ++space){
		    	if(space < height-row) System.out.print(" ");
		    	else System.out.print("*");
		    }
		    System.out.println();
		}

	}

}
