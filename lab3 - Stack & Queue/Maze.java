import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Maze {
	private Square[][] maze;
	private int numRows;
	private int numCols;
	private Square start;
	private Square finish;
	
	public boolean loadMaze(String fname){
		Scanner file = null;
		try{
			file = new Scanner(new File(fname));
		}catch(FileNotFoundException e){
			System.out.println("The file " + fname + " does not exist.");
			return false;
		}
		
		Scanner line = new Scanner(file.nextLine());
		if(!line.hasNextInt()) return false;
		this.numRows = line.nextInt();
		if(!line.hasNextInt()) return false;
		this.numCols = line.nextInt();
		if(this.numRows < 0 || this.numCols < 0) return false;
		
		this.maze = new Square[this.numRows][this.numCols];
		this.start = null;
		this.finish = null;
		
		for(int row = 0; row < this.numRows; row++){
			line = new Scanner(file.nextLine());
			for(int col = 0; col < this.numCols; col++){
				if(!line.hasNextInt()) return false;
				int type = line.nextInt();
				this.maze[row][col] = new Square(row, col, type);
				if(type == 2) this.start = this.maze[row][col];
				else if(type == 3) this.finish = this.maze[row][col];
			}
		}
		if(this.start == null || this.finish == null) return false;
		
		return true;
	}
	
	
	public ArrayList<Square> getNeighbors(Square sq){
		ArrayList<Square> neighbors = new ArrayList<Square>(4);
		int row = sq.getRow();
		int col = sq.getCol();
		if(row > 0) neighbors.add(this.maze[row-1][col]);
		if(col < this.numCols - 1) neighbors.add(this.maze[row][col+1]);
		if(row < this.numRows-1) neighbors.add(this.maze[row+1][col]);
		if(col > 0) neighbors.add(this.maze[row][col-1]);
		return neighbors;
	}
	
	
	public Square getStart(){
		return this.start;
	}
	
	public Square getFinish(){
		return this.finish;
	}
	
	public void reset(){
		for(int row = 0; row < this.numRows; row++){
			for(int col = 0; col < this.numCols; col++){
				this.maze[row][col].reset();
			}
		}
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int row = 0; row < this.numRows; row++){
			for(int col = 0; col < this.numCols; col++){
				sb.append(this.maze[row][col].toString() + " ");
			}
			sb.append("\n");
		}
		
		String s = new String(sb);
		return s;
	}

}