import static org.junit.Assert.*;

import org.junit.Test;


public class MazeSolverStackTest {

	@Test
	public void testMakeEmpty() {
		Maze m = new Maze();
		m.loadMaze("maze-1");
		MazeSolverStack solver = new MazeSolverStack(m);
		solver.step();
		solver.makeEmpty();
		assertTrue("emptied solver", solver.isEmpty());
	}

	@Test
	public void testIsEmpty() {
		Maze m = new Maze();
		m.loadMaze("maze-1");
		MazeSolverStack solver = new MazeSolverStack(m);
		assertTrue("new solver", solver.isEmpty());
		solver.add(m.getStart());
		assertFalse("after step 2", solver.isEmpty());
	}
	
	/** tests Add() and Next() methods
	 * 
	 */
	@Test
	public void testAdd() {
		Maze m = new Maze();
		m.loadMaze("maze-1");
		MazeSolverStack solver = new MazeSolverStack(m);
		solver.add(m.getStart());
		assertFalse("empty after addition", solver.isEmpty());
		assertEquals("added item", solver.next(), m.getStart());
	}

	@Test
	public void testMazeSolverStack() {
		Maze m = new Maze();
		m.loadMaze("maze-1");
		MazeSolverStack solver = new MazeSolverStack(m);
		assertTrue("initialized worklist is empty", solver.isEmpty());
	}

}
