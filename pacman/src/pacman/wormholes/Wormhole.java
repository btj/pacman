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
	 * @invar | end != null
	 */
	private DeparturePortal start;
	private ArrivalPortal end;
	
	/**
	 * @invar | getDeparturePortalInternal().getWormholesInternal().contains(this)
	 * @invar | getArrivalPortalInternal().getWormholesInternal().contains(this)
	 * 
	 * @post | result != null
	 * @peerObject (package-level)
	 */
	DeparturePortal getDeparturePortalInternal() { return start; }
	/**
	 * @post | result != null
	 * @peerObject (package-level)
	 */
	ArrivalPortal getArrivalPortalInternal() { return end; }

	/**
	 * @peerObject
	 */
	public DeparturePortal getDeparturePortal() { return getDeparturePortalInternal(); }
	
	/**
	 * @peerObject
	 */
	public ArrivalPortal getArrivalPortal() { return getArrivalPortalInternal(); }

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
		start.addWormhole(this);
		this.end = end;
		end.addWormhole(this);
	}
	
	/**
	 * @throws IllegalArgumentException | portal == null
	 * 
	 * @mutates_properties | this.getDeparturePortal(), getDeparturePortal().getWormholes(), portal.getWormholes()
	 * 
	 * @post | getDeparturePortal() == portal
	 * @post | portal == old(getDeparturePortal()) ?
	 *       |     portal.getWormholes().equals(old(portal.getWormholes()))
	 *       | :
	 *       |     old(getDeparturePortal()).getWormholes().equals(LogicalSet.minus(old(getDeparturePortal().getWormholes()), this)) &&
	 *       |     portal.getWormholes().equals(LogicalSet.plus(old(portal.getWormholes()), this))
	 */
	public void setDeparturePortal(DeparturePortal portal) {
		if (portal == null)
			throw new IllegalArgumentException("`portal` is null");
		if (portal == start)
			throw new IllegalArgumentException("`portal` equals the current departure portal");
		
		start.removeWormhole(this);
		start = portal;
		start.addWormhole(this);
	}
	
	/**
	 * @throws IllegalArgumentException | portal == null
	 * 
	 * @mutates_properties | this.getArrivalPortal(), getArrivalPortal().getWormholes(), portal.getWormholes()
	 * 
	 * @post | getArrivalPortal() == portal
	 * @post | portal == old(getArrivalPortal()) ?
	 *       |     portal.getWormholes().equals(old(portal.getWormholes()))
	 *       | :
	 *       |     old(getArrivalPortal()).getWormholes().equals(LogicalSet.minus(old(getArrivalPortal().getWormholes()), this)) &&
	 *       |     portal.getWormholes().equals(LogicalSet.plus(old(portal.getWormholes()), this))
	 */
	public void setArrivalPortal(ArrivalPortal portal) {
		if (portal == null)
			throw new IllegalArgumentException("`portal` is null");
		if (portal == end)
			throw new IllegalArgumentException("`portal` equals the current arrival portal");
		
		end.removeWormhole(this);
		end = portal;
		end.addWormhole(this);
	}
	
}
