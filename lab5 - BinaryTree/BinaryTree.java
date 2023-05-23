/** abstract representation of a binary tree
 * 
 * @author Aviva Blonder
 *
 */


public interface BinaryTree<Type> {
	
	public abstract String toString(String indent);
		// returns a depiction of the tree
	
	public abstract int height();
	// returns the height of the tree (by number of edges)
	
	public abstract boolean isEmpty();
		// returns true if the tree is empty and false if it is not
	
	public abstract int nodeCount();
		// returns the total number of nodes in the tree
	
	public abstract int leafCount();
		// returns the total number of nodes that are leaves in the tree
	
	public abstract int levelCount(int level);
		// returns the number of nodes at level in the tree
	
	public abstract BinaryTree<Type> mirrorImage();
		// returns a new tree that is this tree mirrored around the root
		// shallow clone - data objects should be the same as in the original, not clones
	
	public abstract int weightBalanceFactor();
		// returns the difference between the number of nodes in the left subtree and the number of nodes in the right subtree
	
	public abstract int nodeSum();
		// returns the sum of the data values in the tree (if they are integers)
	
	public abstract void doubles();
		// doubles the value of every node in the tree (if they are integers)
	
	public abstract int maxPathSum();
		// returns the largest sum of the values of each node on a path from the root to a leaf (if integers)
	
	public abstract String preOrder();
		// traverses the nodes in preorder
		// returns a string of the values of each node visited in order
	
	public abstract String postOrder();
		// traverses the nodes in postorder
		// returns a string of the values of each node visited in order
	
	public abstract String inOrder();
		// traverses the nodes in inorder
		// returns a string of the values of each node visited in order
	
}
