//package lab3;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class MazeTest {

	@Test
	public void testLoadMaze() {
	    Maze m = new Maze();
	    assertTrue(m.loadMaze("maze-3"));
	}

	@Test
	public void testGetNeighbors() {
	   Maze m = new Maze();
	   m.loadMaze("maze-3");
	   ArrayList<Square> SNeighbors = m.getNeighbors(m.getStart());//Start, in the middle of the left edge
	   assertEquals("Number of neighbors wrong on left edge", 3, SNeighbors.size());
	   assertEquals("Start neighbor wrong row up", 0, SNeighbors.get(0).getRow());
	   assertEquals("Start neighbor wrong col up", 0, SNeighbors.get(0).getCol());
	   assertEquals("Start neighbor wrong row right", 1, SNeighbors.get(1).getRow());
	   assertEquals("Start neighbor wrong col right", 1, SNeighbors.get(1).getCol());
	   assertEquals("Start neighbor wrong row down", 2, SNeighbors.get(2).getRow());
	   assertEquals("Start neighbor wrong col down", 0, SNeighbors.get(2).getCol());
	   ArrayList<Square> Nbors2 = m.getNeighbors(SNeighbors.get(1));//To the right of start
	   assertEquals("Number of neighbors wrong in middle", 4, Nbors2.size());
	   assertEquals("Middle neighbor wrong row up", 0, Nbors2.get(0).getRow());
	   assertEquals("Middle neighbor wrong col up", 1, Nbors2.get(0).getCol());
	   assertEquals("Middle neighbor wrong row right", 1, Nbors2.get(1).getRow());
	   assertEquals("Middle neighbor wrong col right", 2, Nbors2.get(1).getCol());
	   assertEquals("Middle neighbor wrong row down", 2, Nbors2.get(2).getRow());
	   assertEquals("Middle neighbor wrong col down", 1, Nbors2.get(2).getCol());
	   assertEquals("Middle neighbor wrong row left", 1, Nbors2.get(3).getRow());
	   assertEquals("Middle neighbor wrong col left", 0, Nbors2.get(3).getCol());
	   ArrayList<Square> TNbors = m.getNeighbors(Nbors2.get(0));//Top edge
	   assertEquals("Number of neighbors wrong at Top", 3, TNbors.size());
	   assertEquals("Top neighbor wrong row right", 0, TNbors.get(0).getRow());
	   assertEquals("Top neighbor wrong col right", 2, TNbors.get(0).getCol());
	   assertEquals("Top neighbor wrong row down", 1, TNbors.get(1).getRow());
	   assertEquals("Top neighbor wrong col down", 1, TNbors.get(1).getCol());
	   assertEquals("Top neighbor wrong row left", 0, TNbors.get(2).getRow());
	   assertEquals("Top neighbor wrong col left", 0, TNbors.get(2).getCol());
	   ArrayList<Square> BNbors = m.getNeighbors(Nbors2.get(2));//Bottom edge
	   assertEquals("Bottom neighbor wrong row up", 1, BNbors.get(0).getRow());
	   assertEquals("Bottom neighbor wrong col up", 1, BNbors.get(0).getCol());
	   assertEquals("Bottom neighbor wrong row right", 2, BNbors.get(1).getRow());
	   assertEquals("Bottom neighbor wrong col right", 2, BNbors.get(1).getCol());
	   assertEquals("Bottom neighbor wrong row left", 2, BNbors.get(2).getRow());
	   assertEquals("Bottom neighbor wrong col left", 0, BNbors.get(2).getCol());
	   ArrayList<Square> Nbors3 = m.getNeighbors(SNeighbors.get(0));//Top Left Corner
	   assertEquals("Number of neighbors wrong on Top Left corner", 2, Nbors3.size());
	   assertEquals("Top Left neighbor wrong row right", 0, Nbors3.get(0).getRow());
	   assertEquals("Top Left neighbor wrong col right", 1, Nbors3.get(0).getCol());
	   assertEquals("Top Left neighbor wrong row down", 1, Nbors3.get(1).getRow());
	   assertEquals("Top Left neighbor wrong col down", 0, Nbors3.get(1).getCol());
	   ArrayList<Square> Nbors4 = m.getNeighbors(SNeighbors.get(2));//Bottom Left Corner
	   assertEquals("Number of neighbors wrong on Bottom Left corner", 2, Nbors4.size());
	   assertEquals("Bottom Left neighbor wrong row top", 1, Nbors4.get(0).getRow());
	   assertEquals("Bottom Left neighbor wrong col top", 0, Nbors4.get(0).getCol());
	   assertEquals("Bottom Left neighbor wrong row right", 2, Nbors4.get(1).getRow());
	   assertEquals("Bottom Left neighbor wrong col right", 1, Nbors4.get(1).getCol());
	   ArrayList<Square> ENbors = m.getNeighbors(m.getFinish());//Right side
	   assertEquals("Number of neighbors wrong on right edge", 3, ENbors.size());
	   assertEquals("Number of neighbors wrong on Right Edge corner", 2, Nbors3.size());
	   assertEquals("Right Edge neighbor wrong row top", 0, ENbors.get(0).getRow());
	   assertEquals("Right Edge neighbor wrong col top", 4, ENbors.get(0).getCol());
	   assertEquals("Right Edge neighbor wrong row down", 2, ENbors.get(1).getRow());
	   assertEquals("Right Edge neighbor wrong col down", 4, ENbors.get(1).getCol());
	   assertEquals("Right Edge neighbor wrong row left", 1, ENbors.get(2).getRow());
	   assertEquals("Right Edge neighbor wrong col left", 3, ENbors.get(2).getCol());
	   ArrayList<Square> ENbors2 = m.getNeighbors(ENbors.get(1));//Bottom Right
	   assertEquals("Number of neighbors wrong on Bottom Right", 2, ENbors2.size());
	   assertEquals("Bottom Right neighbor wrong row top", 1, ENbors2.get(0).getRow());
	   assertEquals("Bottom Right neighbor wrong col top", 4, ENbors2.get(0).getCol());
	   assertEquals("Bottom Right neighbor wrong row left", 2, ENbors2.get(1).getRow());
	   assertEquals("Bottom Right neighbor wrong col left", 3, ENbors2.get(1).getCol());
	   ArrayList<Square> ENbors3 = m.getNeighbors(ENbors.get(0));//Top Right
	   assertEquals("Number of neighbors wrong on Top Right", 2, ENbors3.size());
	   assertEquals("Top Right neighbor wrong row bottom", 1, ENbors3.get(0).getRow());
	   assertEquals("Top Right neighbor wrong col bottom", 4, ENbors3.get(0).getCol());
	   assertEquals("Top Right neighbor wrong row left", 0, ENbors3.get(1).getRow());
	   assertEquals("Top Right neighbor wrong col left", 3, ENbors3.get(1).getCol());
	   }

	@Test
	public void testGetStart() {
	    Maze m = new Maze();
	    m.loadMaze("maze-3");
	    Square s = m.getStart();
	    assertEquals("Start row wrong", 1, s.getRow());
	    assertEquals("Start col wrong", 0, s.getCol());
	}

	@Test
	public void testGetFinish() {
	    Maze m = new Maze();
	    m.loadMaze("maze-3");
	    Square f = m.getFinish();
	    assertEquals("Finish row wrong", 1, f.getRow());
	    assertEquals("Finish col wrong", 4, f.getCol());
	}

	@Test
	public void testReset() {
	}

	@Test
	public void testToString() {
		Maze m = new Maze();
		m.loadMaze("maze-3");
		String maze = m.toString();
		System.out.print(maze);
	}

}