public class Square {

	private int type;
	private boolean marked;
	private int row;
	private int col;
	private Square prev;
	public char mark;
	
	public Square(int row, int col, int type){
		this.type = type;		
		this.marked = false;
		this.row = row;
		this.col = col;
		this.prev = null;
		this.mark = 'n';
	}
	
	public Square getPrev()
	{
	    return prev;
	}
	
	public void setPrev(Square sq)
	{
	    prev = sq;
	}
	
	public String toString(){
		if(!this.marked){
			if(this.type == 0) return "_";
			else if(this.type == 1) return "#";
			else if(this.type == 2) return "S";
			else return "E";
		} else return "" + this.mark;
	}
	
	public boolean isMarked()
	{
	    return marked;
	}
	public int getRow(){
		return this.row;
	}
	
	public int getCol(){
		return this.col;
	}
	public String getCoordinates()
	{
	    String coordinates = "[";
	    coordinates += this.row;
	    coordinates += ",";
	    coordinates += this.col;
	    coordinates += "]";
	    return coordinates;
	}
	
	public int getType(){
		return this.type;
	}
	public void mark()
	{
	    this.marked = true;
	}
	public void reset(){
		this.prev = null;
		this.marked = false;
		this.mark = 'n';
	}
}