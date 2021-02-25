package pacman.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pacman.Direction;
import pacman.Ghost;
import pacman.MazeMap;
import pacman.Square;

class GhostTest {

 	MazeMap mazeMap = new MazeMap(4, 3, new boolean[] {
		true, false, true, true,
		true, true, false, true,
		false, true, true, true
 	});
	Ghost ghost = new Ghost(Square.of(mazeMap, 2, 1), Direction.DOWN);

 	@Test
	void testGetSquare() {
		assertTrue(Square.of(mazeMap, 2, 1).equals(ghost.getSquare()));
 	}
 	
 	@Test
 	void testGetDirection() {
		assertEquals(Direction.DOWN, ghost.getDirection());
 	}
 	
 	@Test
 	void testSetSquare() {
		ghost.setSquare(Square.of(mazeMap, 2, 2));
		assertTrue(Square.of(mazeMap, 2, 2).equals(ghost.getSquare()));
 	}
 	
 	@Test
 	void testSetDirection() {
		ghost.setDirection(Direction.RIGHT);
		assertEquals(Direction.RIGHT, ghost.getDirection());
	}

}
