package pacman.wormholes;

import java.util.HashSet;
import java.util.Set;

import pacman.Square;

/**
 * @invar | getSquare() != null
 * @invar | getWormholes() != null
 * @invar | getWormholes().stream().allMatch(wormhole -> wormhole.getDeparturePortal() == this)
 */
public class DeparturePortal {
	
	/**
	 * @invar | square != null
	 * @invar | wormholes != null
	 * @invar | wormholes.stream().allMatch(wormhole -> wormhole.start == this)
	 */
	final Square square;
	/**
	 * @representationObject
	 * @peerObjects
	 */
	final Set<Wormhole> wormholes = new HashSet<>();

	/**
	 * @basic
	 * @immutable
	 */
	public Square getSquare() { return square; }

	/**
	 * @basic
	 */
	public Set<Wormhole> getWormholes() { return Set.copyOf(wormholes); }

	/**
	 * @post | getSquare() == square
	 * @post | getWormholes().isEmpty()
	 */
	public DeparturePortal(Square square) { this.square = square; }
	
}
