package pacman.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import pacman.Direction;
import pacman.MazeMap;
import pacman.Square;

class SquareTest {

 	MazeMap mazeMap = new MazeMap(4, 3, new boolean[] {
		true, false, true, true,
		true, true, false, true,
		false, true, true, true
 	});
	Square square02 = Square.of(mazeMap, 0, 2);
	
	@Test
	void testGetMazeMap() {
		assertEquals(mazeMap, square02.getMazeMap());
	}
	
	@Test
	void testGetRowIndex() {
		assertEquals(0, square02.getRowIndex());
	}
	
	@Test
	void testGetColumnIndex() {
		assertEquals(2, square02.getColumnIndex());
	}
	
	@Test
	void testGetNeighbor() {
		assertTrue(Square.of(mazeMap, 0, 1).equals(square02.getNeighbor(Direction.LEFT)));
		assertTrue(Square.of(mazeMap, 0, 3).equals(square02.getNeighbor(Direction.RIGHT)));
		assertTrue(Square.of(mazeMap, 1, 2).equals(square02.getNeighbor(Direction.DOWN)));
		assertTrue(Square.of(mazeMap, 2, 2).equals(square02.getNeighbor(Direction.UP)));
		assertTrue(Square.of(mazeMap, 1, 0).equals(Square.of(mazeMap, 1, 3).getNeighbor(Direction.RIGHT)));
		assertTrue(Square.of(mazeMap, 0, 2).equals(Square.of(mazeMap, 2, 2).getNeighbor(Direction.DOWN)));
		assertTrue(Square.of(mazeMap, 0, 3).equals(Square.of(mazeMap, 0, 0).getNeighbor(Direction.LEFT)));
	}
	
	@Test
	void testCanMove() {
		assertTrue(square02.canMove(Direction.RIGHT));
		assertFalse(square02.canMove(Direction.LEFT));
		assertTrue(square02.canMove(Direction.UP));
		assertFalse(square02.canMove(Direction.DOWN));
	}
	
	@Test
	void testGetPassableDirectionsExcept() {
		assertEquals(new HashSet<Direction>(Arrays.asList(new Direction[] {Direction.UP})), new HashSet<Direction>(Arrays.asList(square02.getPassableDirectionsExcept(Direction.RIGHT))));
	}

}
