
public class MazeSolverStack extends MazeSolver{
    MyStack<Square> worklist;
    public MazeSolverStack(Maze maze)
    {
	super(maze);
	this.makeEmpty();
    }
    public void makeEmpty()
    {
	worklist = new MyStack<Square>();
    }
    public boolean isEmpty()
    {
	return worklist.isEmpty();
    }
    public Square next()
    {
	return worklist.pop();
    }
    public void add(Square sq)
    {
	worklist.push(sq);
    }

}