package pacman.tests;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import pacman.Direction;
import pacman.Maze;
import pacman.MazeDescriptions;
import pacman.PowerPellet;
import pacman.Square;
import pacman.wormholes.ArrivalPortal;
import pacman.wormholes.DeparturePortal;
import pacman.wormholes.Wormhole;

class MazeTest {
	
	Maze maze = MazeDescriptions.createMazeFromDescription(new Random(), """
			#####################
			#A........#........D#
			#.###.###.#.###.###.#
			#p###.###.#.###.###p#
			#.###.###.#.###.###.#
			#...................#
			#.###.#.#####.#.###.#
			#.###.#.#####.#.###.#
			#.....#...#...#.....#
			#####.### # ###.#####
			    #.#   G   #.#    
			    #.# #   # #.#    
			#####.# #   # #.#####
			  D  .  #GGG#  .  A  
			#####.# ##### #.#####
			    #.#       #.#    
			    #.# ##### #.#    
			#####.# ##### #.#####
			#.........#.........#
			#.###.###.#.###.###.#
			#p..#.....P.....#..p#
			###.#.#.#####.#.#.###
			###.#.#.#####.#.#.###
			#.....#...#...#.....#
			#.#######.#.#######.#
			#D.................A#
			#####################
			""");
	DeparturePortal[] departurePortals = maze.getDeparturePortals();
	ArrivalPortal[] arrivalPortals = maze.getArrivalPortals();
	Wormhole[] wormholes = {
			new Wormhole(departurePortals[0], arrivalPortals[2]),
			new Wormhole(departurePortals[0], arrivalPortals[0]),
			new Wormhole(departurePortals[1], arrivalPortals[1])
	};
	{
		maze.addWormhole(wormholes[0]);
		maze.addWormhole(wormholes[1]);
		maze.addWormhole(wormholes[2]);
	}
	PowerPellet[] powerPellets =
		Arrays.stream(maze.getFoodItems())
			.flatMap(i -> i instanceof PowerPellet ? Stream.of((PowerPellet)i) : Stream.of())
			.sorted(Comparator.<PowerPellet>comparingInt(i -> i.getSquare().getRowIndex())
					          .thenComparingInt(i -> i.getSquare().getColumnIndex()))
			.toArray(n -> new PowerPellet[n]);

	@Test
	void testPowerPelletsFromMazeDescription() {
		assert powerPellets.length == 4;
		assert powerPellets[0].getSquare().equals(Square.of(maze.getMap(), 3, 1));
		assert powerPellets[1].getSquare().equals(Square.of(maze.getMap(), 3, 19));
		assert powerPellets[2].getSquare().equals(Square.of(maze.getMap(), 20, 1));
		assert powerPellets[3].getSquare().equals(Square.of(maze.getMap(), 20, 19));
	}
	
	@Test
	void testGetSize() {
		assert powerPellets[0].getSize() == 2;
	}
	
	@Test
	void testDeparturePortalsFromMazeDescription() {
		assert departurePortals.length == 3;
		assert departurePortals[0].getSquare().equals(Square.of(maze.getMap(), 1, 19));
		assert departurePortals[1].getSquare().equals(Square.of(maze.getMap(), 13, 2));
		assert departurePortals[2].getSquare().equals(Square.of(maze.getMap(), 25, 1));
	}
	
	@Test
	void testArrivalPortalsFromMazeDescription() {
		assert arrivalPortals.length == 3;
		assert arrivalPortals[0].getSquare().equals(Square.of(maze.getMap(), 1, 1));
		assert arrivalPortals[1].getSquare().equals(Square.of(maze.getMap(), 13, 18));
		assert arrivalPortals[2].getSquare().equals(Square.of(maze.getMap(), 25, 19));
	}
	
	@Test
	void testWormholes() {
		Wormhole[] wormholes = maze.getWormholes();
		assert Arrays.equals(wormholes, this.wormholes);
	}
	
	@Test
	void testMovePacManOntoDeparturePortal() {
		maze.getPacMan().setSquare(Square.of(maze.getMap(), 1, 18));
		maze.movePacMan(Direction.RIGHT);
		assert maze.getPacMan().getSquare().equals(Square.of(maze.getMap(), 1, 1)) ||
			maze.getPacMan().getSquare().equals(Square.of(maze.getMap(), 25, 19));
		
		maze.getPacMan().setSquare(Square.of(maze.getMap(), 13, 3));
		maze.movePacMan(Direction.LEFT);
		assert maze.getPacMan().getSquare().equals(Square.of(maze.getMap(), 13, 18));
		
		maze.getPacMan().setSquare(Square.of(maze.getMap(), 24, 1));
		maze.movePacMan(Direction.DOWN);
		assert maze.getPacMan().getSquare().equals(Square.of(maze.getMap(), 25, 1));
	}
	
}
