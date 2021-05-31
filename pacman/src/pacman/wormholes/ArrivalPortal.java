package pacman.wormholes;

import java.util.HashSet;
import java.util.Set;

import logicalcollections.LogicalSet;

import pacman.Square;

/**
 * @invar | getSquare() != null
 * @invar | getWormholes() != null
 * @invar | getWormholes().stream().allMatch(wormhole -> wormhole != null && wormhole.getArrivalPortal() == this)
 */
public class ArrivalPortal {
	
	/**
	 * @invar | square != null
	 * @invar | wormholes != null
	 * @invar | wormholes.stream().allMatch(wormhole -> wormhole != null)
	 */
	private final Square square;
	/**
	 * @representationObject
	 */
	private final Set<Wormhole> wormholes = new HashSet<>();
	
	/**
	 * @invar | getWormholesInternal().stream().allMatch(wormhole -> wormhole.getArrivalPortalInternal() == this)
	 * 
	 * @post | result != null
	 * @immutable
	 */
	Square getSquareInternal() { return square; }
	
	/**
	 * @post | result != null
	 * @post | result.stream().allMatch(wormhole -> wormhole != null)
	 * @creates | result
	 * 
	 * @peerObjects (package-level)
	 */
	Set<Wormhole> getWormholesInternal() { return Set.copyOf(wormholes); }
	
	/**
	 * @immutable
	 */
	public Square getSquare() { return getSquareInternal(); }

	/**
	 * @creates | result
	 * @peerObjects
	 */
	public Set<Wormhole> getWormholes() { return getWormholesInternal(); }
	
	/**
	 * @post | getSquare() == square
	 * @post | getWormholes().isEmpty()
	 */
	public ArrivalPortal(Square square) { this.square = square; }
	
	/**
	 * @throws IllegalArgumentException | wormhole == null
	 * @mutates | this
	 * @post | getWormholesInternal().equals(LogicalSet.plus(old(getWormholesInternal()), wormhole))
	 */
	void addWormhole(Wormhole wormhole) {
		if (wormhole == null)
			throw new IllegalArgumentException("`wormhole` is null");
		
		wormholes.add(wormhole);
	}
	
	/**
	 * @throws IllegalArgumentException | wormhole == null
	 * @mutates | this
	 * @post | getWormholesInternal().equals(LogicalSet.minus(old(getWormholesInternal()), wormhole))
	 */
	void removeWormhole(Wormhole wormhole) {
		if (wormhole == null)
			throw new IllegalArgumentException("`wormhole` is null");
		
		wormholes.remove(wormhole);
	}

}
