/** test of BinaryTree and related classes
 * 
 * @author Aviva Blonder
 */

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;


public class BinaryTreeTest {

	/** test of ConsTree constructor with empty left and right tree (and toString methods)
	 */
	@Test
	public void testConsTreeType() {
		ConsTree<Integer> test = new ConsTree<Integer>(3);
		System.out.print(test.toString());
	}

	/** test of ConsTree constructor with nonempty left and right trees
	 */
	@Test
	public void testConsTreeTypeBinaryTreeOfTypeBinaryTreeOfType() {
		ConsTree<Integer> lefttest = new ConsTree<Integer>(2);
		ConsTree<Integer> righttest = new ConsTree<Integer>(3);
		ConsTree<Integer> test = new ConsTree<Integer>(1, lefttest, righttest);
		System.out.print(test.toString());
	}
	
	/** test of TreeLoader loadTreeFromFile method
	 */
	@Test
	public void testTreeLoader(){
		TreeLoader test = new TreeLoader();
		try	{
			BinaryTree<String> tree = test.loadTreeFromFile("tree1.txt");
			System.out.print(tree.toString());
		} catch (IOException e) {
			System.out.println("That file doesn't exist");
		}
	}
	
	/** test of ConsTree and EmptyTree isEmpty methods
	 */
	@Test
	public void testIsEmpty() {
		ConsTree<Integer> testcons = new ConsTree<Integer>(1);
		EmptyTree<Integer> testempt = new EmptyTree<Integer>();
		assertTrue("empty tree is empty", testempt.isEmpty());
		assertFalse("cons tree is not empty", testcons.isEmpty());
	}

	/** test of ConsTree and EmptyTree height, nodeCount, leafCount,
	 * and levelCount methods
	 */
	@Test
	public void testCounters() {
		BinaryTree<Integer> temp;
		BinaryTree<Integer> test = new ConsTree<Integer>(-1);
		BinaryTree<Integer> empt = new EmptyTree<Integer>();
		assertEquals("empt height", empt.height(), -1);
		assertEquals("empt nodeCount", empt.nodeCount(), 0);
		assertEquals("empt leafCount", empt.leafCount(), 0);
		int nodes = 1;
		int leaves = 1;
		for(int i = 1; i < 5; i++){
			assertEquals("Cons height", test.height(), i-1);
			assertEquals("Cons nodeCount", test.nodeCount(), nodes);
			assertEquals("Cons leafCount", test.leafCount(), leaves);
			for(int level = 0; level < i; level++){
				int expected = (int) Math.pow(2, level);
				assertEquals("Cons levelCount", test.levelCount(level), expected);
				assertEquals("empt levelCount", empt.levelCount(level), 0);
			}
			System.out.print(test);
			temp = test;
			test = new ConsTree<Integer>(i, temp, temp);
			nodes = nodes*2 + 1;
			leaves = leaves*2;
		}
	}
	
	/** test of Cons and Empty tree mirrorImage methods 
	 */
	@Test
	public void testMirrorImage() {
		TreeLoader loader = new TreeLoader();
		BinaryTree<String> test;
		for (int tree = 1; tree < 11; tree++){
			String treename = "tree" + tree + ".txt";
			try{
				test = loader.loadTreeFromFile(treename);
				System.out.println(treename);
				System.out.print(test.toString());
				System.out.println(treename + "mirror");
				System.out.print(test.mirrorImage().toString());
			} catch (IOException e){
				System.out.println("The file " + treename + "does not exist");
			}
		}
	}
	
	/**test of weightBalanceFactor method
	 */
	@Test
	public void testWeightBalanceFactor() {
		TreeLoader loader = new TreeLoader();
		BinaryTree<String> test;
		for(int tree = 1; tree < 11; tree++){
			String treename = "tree" + tree + ".txt";
			int balance;
			try{
				test = loader.loadTreeFromFile(treename);
				switch(tree){
				case 2:
					balance = 9;
					break;
				case 4:
					balance = 4;
					break;
				case 5:
					balance = 13;
					break;
				case 7:
					balance = 2;
					break;
				case 8:
					balance = 6;
					break;
				case 9:
					balance = 10;
					break;
				case 10:
					balance = 3;
					break;
				default:
					balance = 0;
				}
				assertEquals("balance of tree " + tree, test.weightBalanceFactor(), balance);
			} catch (IOException e) {
				System.out.println("The file " + treename + " does not exist.");
			}
		}
	}

	/** test of nodeSum and maxPathSum methods
	 */
	@Test
	public void testNumbers() {
		TreeLoader loader = new TreeLoader();
		BinaryTree<String> test;
		int nodesum;
		int pathsum;
		for(int i = 1; i < 11; i++){
			String treename = "tree" + i + ".txt";
			try{
				test = loader.loadTreeFromFile(treename);
				switch(i){
				case 6:
					nodesum = 123;
					pathsum = 123;
					break;
				case 7:
					nodesum = 333;
					pathsum = 209;
					break;
				case 8:
					nodesum = 252;
					pathsum = 123;
					break;
				case 9:
					nodesum = 124;
					pathsum = 124;
					break;
				case 10:
					nodesum = 450;
					pathsum = 350;
					break;
				default:
					nodesum = 0;
					pathsum = 0;
				}
				assertEquals("nodesum of tree " + i, test.nodeSum(), nodesum);
				assertEquals("max pathsum of tree " + i, test.maxPathSum(), pathsum);
			} catch (IOException e) {
				System.out.println("The file " + treename + " doesn't exist!");
			}
		}
	}

	@Test
	public void testDoubles() {
		TreeLoader loader = new TreeLoader();
		BinaryTree<String> test;
		for (int tree = 1; tree < 11; tree++){
			String treename = "tree" + tree + ".txt";
			try{
				test = loader.loadTreeFromFile(treename);
				System.out.println(treename);
				System.out.print(test.toString());
				System.out.println(treename + "double");
				test.doubles();
				System.out.print(test.toString());
			} catch (IOException e){
				System.out.println("The file " + treename + " does not exist");
			}
		}
	}

	/** test of preorder, postorder, and inorder methods
	 */
	@Test
	public void testOrders() {
		TreeLoader loader = new TreeLoader();
		BinaryTree<String> test;
		for (int tree = 1; tree < 11; tree++){
			String treename = "tree" + tree + ".txt";
			try{
				test = loader.loadTreeFromFile(treename);
				System.out.println(treename);
				System.out.print(test.toString());
				System.out.println(treename + " preorder");
				System.out.print(test.preOrder());
				System.out.println(treename + "postorder");
				System.out.print(test.postOrder());
				System.out.println(treename + "inorder");
				System.out.println(test.inOrder());
			} catch (IOException e){
				System.out.println("The file " + treename + " does not exist.");
			}
		}
	}
	
}