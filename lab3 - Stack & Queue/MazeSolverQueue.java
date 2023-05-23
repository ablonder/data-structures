/* Implementation of MazeSolverQueue class, an implementation of the abstract MazeSolver class using MyQueue
 * 
 * @author Aviva Blonder
 */

public class MazeSolverQueue extends MazeSolver {
	private MyQueue<Square> worklist;
	
	/** initializes worklist as a new queue
	 * 
	 * @param maze
	 */
	public MazeSolverQueue(Maze maze){
	    super(maze);
	    this.worklist = new MyQueue<Square>();
	    this.add(maze.getStart());
	}
	
	/** implements abstract makeEmpty method, makes worklist a new, empty queue
	 */
	public void makeEmpty(){
		this.worklist = new MyQueue<Square>();
	}
	
	/**implements abstract isEmpty method
	 * 
	 * @return true if worklist is empty and false if not
	 */
	public boolean isEmpty(){
		if(this.worklist.isEmpty()) return true;
		else return false;
	}
	
	/**implements abstract add method, enqueues a square to the worklist
	 */
	public void add(Square sq){
		worklist.enqueue(sq);
	}
	
	/**implements abstract next method
	 * 
	 * @return the next square in the worklist
	 */
	public Square next(){
		return this.worklist.dequeue();
	}

}