package pacman.wormholes;

import logicalcollections.LogicalSet;

/**
 * @invar | getDeparturePortal() != null
 * @invar | getDeparturePortal().getWormholes().contains(this)
 * 
 * @invar | getArrivalPortal() != null
 * @invar | getArrivalPortal().getWormholes().contains(this)
 */
public class Wormhole {
	
	/**
	 * @invar | start != null
	 * @invar | start.wormholes.contains(this)
	 * 
	 * @peerObject
	 */
	DeparturePortal start;
	/**
	 * @invar | end != null
	 * @invar | end.wormholes.contains(this)
	 * 
	 * @peerObject
	 */
	ArrivalPortal end;

	/**
	 * @basic
	 * @peerObject
	 */
	public DeparturePortal getDeparturePortal() { return start; }
	
	/**
	 * @basic
	 * @peerObject
	 */
	public ArrivalPortal getArrivalPortal() { return end; }

	/**
	 * @throws IllegalArgumentException | start == null
	 * @throws IllegalArgumentException | end == null
	 * 
	 * @mutates_properties | start.getWormholes(), end.getWormholes()
	 * 
	 * @post | getDeparturePortal() == start
	 * @post | getArrivalPortal() == end
	 * @post | start.getWormholes().equals(LogicalSet.plus(old(start.getWormholes()), this))
	 * @post | end.getWormholes().equals(LogicalSet.plus(old(end.getWormholes()), this))
	 */
	public Wormhole(DeparturePortal start, ArrivalPortal end) {
		if (start == null)
			throw new IllegalArgumentException("`start` is null");
		if (end == null)
			throw new IllegalArgumentException("`end` is null");
		this.start = start;
		start.wormholes.add(this);
		this.end = end;
		end.wormholes.add(this);
	}
	
	/**
	 * @throws IllegalArgumentException | portal == null
	 * @throws IllegalArgumentException | portal == getDeparturePortal()
	 * 
	 * @mutates_properties | this.getDeparturePortal(), getDeparturePortal().getWormholes(), portal.getWormholes()
	 * 
	 * @post | getDeparturePortal() == portal
	 * @post | old(getDeparturePortal()).getWormholes().equals(LogicalSet.minus(old(getDeparturePortal().getWormholes()), this))
	 * @post | portal.getWormholes().equals(LogicalSet.plus(old(portal.getWormholes()), this))
	 */
	public void setDeparturePortal(DeparturePortal portal) {
		if (portal == null)
			throw new IllegalArgumentException("`portal` is null");
		if (portal == start)
			throw new IllegalArgumentException("`portal` equals the current departure portal");
		
		start.wormholes.remove(this);
		start = portal;
		start.wormholes.add(this);
	}
	
	/**
	 * @throws IllegalArgumentException | portal == null
	 * @throws IllegalArgumentException | portal == getArrivalPortal()
	 * 
	 * @mutates_properties | this.getArrivalPortal(), getArrivalPortal().getWormholes(), portal.getWormholes()
	 * 
	 * @post | getArrivalPortal() == portal
	 * @post | old(getArrivalPortal()).getWormholes().equals(LogicalSet.minus(old(getArrivalPortal().getWormholes()), this))
	 * @post | portal.getWormholes().equals(LogicalSet.plus(old(portal.getWormholes()), this))
	 */
	public void setArrivalPortal(ArrivalPortal portal) {
		if (portal == null)
			throw new IllegalArgumentException("`portal` is null");
		if (portal == end)
			throw new IllegalArgumentException("`portal` equals the current arrival portal");
		
		end.wormholes.remove(this);
		end = portal;
		end.wormholes.add(this);
	}
	
}
