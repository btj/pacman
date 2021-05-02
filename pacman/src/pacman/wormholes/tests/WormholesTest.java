package pacman.wormholes.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import pacman.MazeMap;
import pacman.Square;
import pacman.wormholes.ArrivalPortal;
import pacman.wormholes.DeparturePortal;
import pacman.wormholes.Wormhole;

class WormholesTest {

	@Test
	void test() {
		MazeMap map = new MazeMap(3, 3, new boolean[] {
			true, true, true,
			true, true, true,
			true, true, true
		});
		
		DeparturePortal d1 = new DeparturePortal(Square.of(map, 0, 0));
		assertTrue(d1.getSquare().equals(Square.of(map, 0, 0)));
		assertEquals(Set.of(), d1.getWormholes());
		
		DeparturePortal d2 = new DeparturePortal(Square.of(map, 0, 1));
		
		ArrivalPortal a1 = new ArrivalPortal(Square.of(map, 2, 0));
		assertTrue(a1.getSquare().equals(Square.of(map, 2, 0)));
		assertEquals(Set.of(), a1.getWormholes());
		
		ArrivalPortal a2 = new ArrivalPortal(Square.of(map, 2, 1));
		
		Wormhole w1 = new Wormhole(d1, a1);
		assertEquals(d1, w1.getDeparturePortal());
		assertEquals(a1, w1.getArrivalPortal());
		assertEquals(Set.of(w1), d1.getWormholes());
		assertEquals(Set.of(w1), a1.getWormholes());
		
		Wormhole w2 = new Wormhole(d1, a2);
		assertEquals(d1, w2.getDeparturePortal());
		assertEquals(a2, w2.getArrivalPortal());
		assertEquals(Set.of(w1, w2), d1.getWormholes());
		assertEquals(Set.of(w2), a2.getWormholes());
		
		Wormhole w3 = new Wormhole(d1, a2);
		assertEquals(d1, w3.getDeparturePortal());
		assertEquals(a2, w3.getArrivalPortal());
		assertEquals(Set.of(w1, w2, w3), d1.getWormholes());
		assertEquals(Set.of(w2, w3), a2.getWormholes());
		
		w2.setDeparturePortal(d2);
		assertEquals(d2, w2.getDeparturePortal());
		assertEquals(Set.of(w1, w3), d1.getWormholes());
		assertEquals(Set.of(w2), d2.getWormholes());
		
		w2.setArrivalPortal(a1);
		assertEquals(a1, w2.getArrivalPortal());
		assertEquals(Set.of(w3), a2.getWormholes());
		assertEquals(Set.of(w1, w2), a1.getWormholes());
		
		w2.setDeparturePortal(d1);
		assertEquals(d1, w2.getDeparturePortal());
		assertEquals(Set.of(w1, w2, w3), d1.getWormholes());
		assertEquals(Set.of(), d2.getWormholes());
	}

}
