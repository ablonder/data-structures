

/** implementation of BinaryTree that creates constructed trees
 * 
 * @author Aviva Blonder
 *
 * @param <Type>
 */

public class ConsTree<Type> implements BinaryTree<Type> {
	private Type data;
	private BinaryTree<Type> left;
	private BinaryTree<Type> right;
	
	/** constructor that sets data and creates empty children
	 * @param data
	 */
	public ConsTree(Type data){
		this(data, new EmptyTree<Type>(), new EmptyTree<Type>());
	}
	
	/** constructor that sets data and children
	 * 
	 * @param data
	 * @param left
	 * @param right
	 */
	public ConsTree(Type data, BinaryTree<Type> left, BinaryTree<Type> right){
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	/** toString method that directs to the parameterized toString method
	 * 
	 * @return outcome of parameterized toString
	 */
	public String toString(){
		return toString("");
	}
	
	/** visualization of the tree
	 * 
	 * @return sideways tree depiction
	 */
	public String toString(String indent){
		return right.toString(indent + "   ") + "\n" + indent + "\n" + indent +
				data + "\n" + indent + left.toString(indent + "   ");
	}
	
	/** visualization of the tree as a nested list
	 * 
	 * @return data with the string of left and right under it
	 */
//	public String toString(String indent){
//		return indent + this.data + "\n" + left.toString(indent + "   ") + right.toString(indent + "   ");
//	}
	
	/** determines if the tree is empty or not
	 * 
	 * @return false
	 */
	public boolean isEmpty(){
		return false;
	}
	
	/** calculates the number of edges along the longest path in the tree
	 * 
	 * @return the height of its tallest subtree plus one
	 */
	public int height(){
		return 1 + Math.max(left.height(), right.height());
	}
	
	/** calculates the total number of nodes in the tree
	 * 
	 * @return the sum of the number of nodes in its subtrees plus one
	 */
	public int nodeCount(){
		return 1 + left.nodeCount() + right.nodeCount();
	}
	
	/** calculates the total number of leaves in the tree
	 * 
	 * @return the sum of the number of leaves in its subtrees
	 */
	public int leafCount(){
		if(left.isEmpty() && right.isEmpty()) return 1;
		else return left.leafCount() + right.leafCount();
	}
	
	/** calculates the number of nodes at level in the tree
	 * 
	 * @return 1 if level is 0 or the sum of the number of leaves at level-1 in its subtrees
	 */
	public int levelCount(int level){
		if(level == 0) return 1;
		else return right.levelCount(level-1) + left.levelCount(level-1);
	}
	
	/** creates a copy of the tree mirrored around the root
	 * 
	 * @return a new tree with the same data and the mirrors of left and right reversed
	 */
	public BinaryTree<Type> mirrorImage(){
		return new ConsTree<Type>(this.data, this.right.mirrorImage(), this.left.mirrorImage());
	}
	
	/** calculates the weight-balance-factor of the tree
	 * 
	 * @return the larger of the differences between the number of nodes in the subtrees
	 */
	public int weightBalanceFactor(){
		return Math.max(Math.abs(this.left.nodeCount() - this.right.nodeCount()), 
				Math.max(this.left.weightBalanceFactor(), this.right.weightBalanceFactor()));
	}
	
	/** calculates the sum of the values in the tree if Type is Integer
	 * 
	 * @return the sum of the nodes in each of the subtrees plus data
	 */
	public int nodeSum(){
		try{
			return Integer.parseInt((String) this.data) + this.left.nodeSum() + this.right.nodeSum();
		} catch (NumberFormatException e){
			System.out.println("That's not an integer!");
			return 0;
		}	
	}
	
	/** doubles the value of data
	 */
	public void doubles(){
		try{
			if(this.data instanceof String){
				int temp = Integer.parseInt((String) this.data) * 2;
				this.data = (Type) ("" + temp);
				this.left.doubles();
				this.right.doubles();
			}
		} catch (NumberFormatException e){
			System.out.println("That's not an integer!");
		}
	}
	
	/** calculates the largest sum of all the data on a path from the root to a leaf
	 * 
	 * @return data plus the larger of the left max path sum and the right max path sum
	 */
	public int maxPathSum(){
		try{
			return Integer.parseInt((String) this.data) + Math.max(this.left.maxPathSum(), this.right.maxPathSum());
		} catch (NumberFormatException e) {
				System.out.println("That's not an integer!");
				return 0;
		}
	}
	
	/** traverses the tree in preorder
	 * 
	 * @return data followed the preOrder of left and right
	 */
	public String preOrder(){
		return this.data + "\n" + this.left.preOrder() + this.right.preOrder();
	}
	
	
	/** traverses the tree in postorder
	 * 
	 * @return postOrder of left and right, followed by data
	 */
	public String postOrder(){
		return this.left.postOrder() + this.right.postOrder() + this.data + "\n";
	}
	
	/** traverses the tree in inorder
	 * 
	 * @return inOrder of left, data, and inOrder of right
	 */
	public String inOrder(){
		return this.left.inOrder() + this.data + "\n" + this.right.inOrder();
	}

}
