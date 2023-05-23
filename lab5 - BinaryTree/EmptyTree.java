/** implementation of BinaryTree that creates empty trees
 * serves as the base case for ConsTree
 * 
 * @author Aviva Blonder
 *
 * @param <Type>
 */

public class EmptyTree<Type> implements BinaryTree<Type> {
	
	/** redirects to parameterized toString
	 * 
	 * @return outcome of parameterized toString
	 */
	public String toString(){
		return toString("");
	}
	
	/** visualization of the empty tree
	 * 
	 * @return empty string
	 */
	public String toString(String indent){
		return "";
	}
	
	/** determines if the tree is empty
	 * 
	 * @return true
	 */
	public boolean isEmpty(){
		return true;
	}
	
	/** calculates the number of edges along the longest path in the tree
	 * 
	 * @return -1
	 */
	public int height() { return -1; }
	
	/** calculates the total number of nodes in the tree
	 * 
	 * @return 0
	 */
	public int nodeCount() { return 0; }
	
	/** calculates the total number of leaves in the tree
	 * 
	 * @return 0
	 */
	public int leafCount() { return 0; }
	
	/** calculates the number of nodes at level in the tree
	 * 
	 * @return 0
	 */
	public int levelCount(int level) { return 0; }
	
	/** creates a new tree which is this tree mirrored around the root
	 * 
	 * @return new EmptyTree
	 */
	public BinaryTree<Type> mirrorImage() { return new EmptyTree<Type>(); }
	
	/** calculates the weight balance factor of the tree
	 * 
	 * @return 0
	 */
	public int weightBalanceFactor() { return 0; }
	
	/** calculates the sum of the data in the tree
	 * 
	 * @return 0
	 */
	public int nodeSum() { return 0; }
	
	/** doubles the value of data
	 */
	public void doubles(){	}

	/** calculates the largest sum of all the data on a path from the root to a leaf
	 * 
	 * @return 0
	 */
	public int  maxPathSum(){ return 0;	}
	
	/** traverses the tree in preorder
	 * 
	 * @return ""
	 */
	public String preOrder(){ return ""; }
	
	/** traverses the tree in postorder
	 * 
	 * @return ""
	 */
	public String postOrder(){ return ""; }
	
	/** traverses the tree in inorder
	 * 
	 * @return ""
	 */
	public String inOrder(){ return ""; }
	
}
