package pacman.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import pacman.Direction;
import pacman.Dot;
import pacman.Ghost;
import pacman.MazeMap;
import pacman.PacMan;
import pacman.Square;

class PacManTest {

 	MazeMap mazeMap = new MazeMap(4, 3, new boolean[] {
		true, false, true, true,
		true, true, false, true,
		false, true, true, true
 	});
	PacMan pacMan = new PacMan(4, Square.of(mazeMap, 1, 0));
	
	@Test
	void testGetNbLives() {
		assertEquals(4, pacMan.getNbLives());
	}
	
	@Test
	void testGetSquare() {
		assertTrue(Square.of(mazeMap, 1, 0).equals(pacMan.getSquare()));
	}
	
	@Test
	void testSetSquare() {
		pacMan.setSquare(Square.of(mazeMap, 2, 3));
		assertTrue(Square.of(mazeMap, 2, 3).equals(pacMan.getSquare()));
	}
	
	@Test
	void testDie() {
		pacMan.die();
		assertEquals(3, pacMan.getNbLives());
	}

}
