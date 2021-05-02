package pacman.wormholes;

import java.util.HashSet;
import java.util.Set;

import pacman.Square;

public class ArrivalPortal {
	
	final Square square;
	final Set<Wormhole> wormholes = new HashSet<>();
	
	public Square getSquare() { return square; }
	
	public Set<Wormhole> getWormholes() { return Set.copyOf(wormholes); }
	
	public ArrivalPortal(Square square) { this.square = square; }

}
