import static org.junit.Assert.*;

import org.junit.Test;

public class MazeSolverQueueTest {

    @Test
    public void testAdd() {
	Maze m = new Maze();
	m.loadMaze("maze-3");
	MazeSolverQueue test = new MazeSolverQueue(m);
	test.add(m.getStart());
    }

    @Test
    public void testMazeSolverQueue() {
	Maze m = new Maze();
	m.loadMaze("maze-3");
	MazeSolverQueue test = new MazeSolverQueue(m);
    }

    @Test
    public void testGetPath() {
	Maze m = new Maze();
	m.loadMaze("maze-7");
	System.out.println(m.toString());
	MazeSolverQueue test = new MazeSolverQueue(m);
	test.solve();
	assertTrue(test.isSolved());
	System.out.println(test.getPath());
    }

    @Test
    public void testStep() {
	Maze m = new Maze();
	m.loadMaze("maze-3");
	MazeSolverQueue test = new MazeSolverQueue(m);
	test.step();
    }

    @Test
    public void testSolve() {
	Maze m = new Maze();
	m.loadMaze("maze-3");
	MazeSolverQueue test = new MazeSolverQueue(m);
	test.solve();
	assertTrue("Isn't solved", test.isSolved());
    }

}