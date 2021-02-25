package pacman.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import pacman.Direction;
import pacman.Dot;
import pacman.MazeMap;
import pacman.Square;

class DotTest {

 	MazeMap mazeMap = new MazeMap(4, 3, new boolean[] {
		true, false, true, true,
		true, true, false, true,
		false, true, true, true
 	});
	Dot dot = new Dot(Square.of(mazeMap, 1, 3));
	
	@Test
	void testGetSquare() {
		assertTrue(Square.of(mazeMap, 1, 3).equals(dot.getSquare()));
	}

}
