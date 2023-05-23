import java.util.ArrayList;

public abstract class MazeSolver {
    private boolean solved = false;
    private Maze mze;
    public MazeSolver(Maze maze) 
    {
	mze = maze;
    }

    abstract void makeEmpty();
    abstract boolean isEmpty();
    abstract void add(Square sq);
    abstract Square next();
    public boolean isSolved()
    {
	if(this.isEmpty())
	{
	    return true;
	}
	else
	{
	    return solved;
	}
    }
    String getPath()
    {
	if(this.isSolved())
	{
	    if(solved)
	    {
		MyStack<String> losc = new MyStack<String>(); //Queue of Coordinates
		Square current = mze.getFinish();
		while(current != mze.getStart())
		{
		    losc.push(current.getCoordinates());
		    current = current.getPrev();
		}
		String colist = mze.getStart().getCoordinates();
		while(losc.size()>1)
		{
		    colist += losc.pop() + ",";
		}
		colist += losc.pop();
		return colist;
	    }
	    else
	    {
		return "No such path exists";
	    }
	}
	else
	{
	    return "Not solved yet";
	}
    }
    Square step()
    {
	if(!this.isEmpty())
	{
	    Square nsq = this.next();
	    if(nsq != mze.getFinish())
	    {
		ArrayList<Square> newstuff = mze.getNeighbors(nsq);
		for(int i = 0; i < newstuff.size(); i++)
		{
		    Square trump = newstuff.get(i);
		    if(!trump.isMarked() && trump.getType() != 1)
		    {
			trump.setPrev(nsq);
			trump.mark();
			this.add(trump);
		    }
		}
		return nsq;
	    }
	    else
	    {
		return nsq;
	    }
	}
	else
	{
	    return null;
	}
    }
    public void solve()
    {
	boolean cont = true;
	Square s = null;
	this.add(mze.getStart());
	while(cont)
	{
	    s = this.step(); 
	    if((s == null || s == mze.getFinish()))
	    {
		cont = false;
	    }
	}
	if(s == null)
	{
	    
	}
	else
	{
	    solved = true;
	}
    }
}