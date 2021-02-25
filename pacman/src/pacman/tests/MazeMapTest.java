package pacman.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pacman.MazeMap;

class MazeMapTest {

 	MazeMap mazeMap = new MazeMap(4, 3, new boolean[] {
		true, false, true, true,
		true, true, false, true,
		false, true, true, true
 	});
	
 	@Test
	void testGetWidth() {
		assertEquals(4, mazeMap.getWidth());
 	}
 	
 	@Test
 	void testGetHeight() {
		assertEquals(3, mazeMap.getHeight());
 	}
 	
 	@Test
 	void testIsPassable() {
		assertTrue(mazeMap.isPassable(0, 0));
		assertFalse(mazeMap.isPassable(0, 1));
		assertTrue(mazeMap.isPassable(0, 2));
		assertTrue(mazeMap.isPassable(1, 0));
		assertTrue(mazeMap.isPassable(1, 1));
		assertFalse(mazeMap.isPassable(1, 2));
	}

}
