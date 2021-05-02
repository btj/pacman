package pacman.wormholes;

public class Wormhole {
	
	DeparturePortal start;
	ArrivalPortal end;

	public DeparturePortal getDeparturePortal() { return start; }
	
	public ArrivalPortal getArrivalPortal() { return end; }
	
	public Wormhole(DeparturePortal start, ArrivalPortal end) {
		this.start = start;
		start.wormholes.add(this);
		this.end = end;
		end.wormholes.add(this);
	}
	
	public void setDeparturePortal(DeparturePortal portal) {
		if (portal == null)
			throw new IllegalArgumentException("`portal` is null");
		start.wormholes.remove(this);
		start = portal;
		start.wormholes.add(this);
	}
	
	public void setArrivalPortal(ArrivalPortal portal) {
		if (portal == null)
			throw new IllegalArgumentException("`portal` is null");
		end.wormholes.remove(this);
		end = portal;
		end.wormholes.add(this);
	}
	
}
