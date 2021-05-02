package pacman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import pacman.wormholes.ArrivalPortal;
import pacman.wormholes.DeparturePortal;
import pacman.wormholes.Wormhole;

public class Maze {
	
	private Random random;
	private MazeMap map;
	private PacMan pacMan;
	private Ghost[] ghosts;
	private FoodItem[] foodItems;
	private DeparturePortal[] departurePortals;
	private ArrivalPortal[] arrivalPortals;
	private List<Wormhole> wormholes = new ArrayList<>();
	
	public MazeMap getMap() { return map; }
	
	public PacMan getPacMan() { return pacMan; }
	
	public Ghost[] getGhosts() { return ghosts.clone(); }
	
	public FoodItem[] getFoodItems() { return foodItems.clone(); }
	
	public DeparturePortal[] getDeparturePortals() { return departurePortals.clone(); }
	
	public ArrivalPortal[] getArrivalPortals() { return arrivalPortals.clone(); }
	
	public Wormhole[] getWormholes() { return wormholes.toArray(n -> new Wormhole[n]); }
	
	public Maze(Random random, MazeMap map, PacMan pacMan, Ghost[] ghosts, FoodItem[] foodItems, DeparturePortal[] departurePortals, ArrivalPortal[] arrivalPortals) {
		this.random = random;
		this.map = map;
		this.pacMan = pacMan;
		this.ghosts = ghosts.clone();
		this.foodItems = foodItems.clone();
		this.departurePortals = departurePortals.clone();
		this.arrivalPortals = arrivalPortals.clone();
	}
	
	public void addWormhole(Wormhole wormhole) {
		if (!Arrays.asList(departurePortals).contains(wormhole.getDeparturePortal()))
			throw new IllegalArgumentException("`wormhole`'s departure portal is not among this maze's departure portals");
		if (!Arrays.asList(arrivalPortals).contains(wormhole.getArrivalPortal()))
			throw new IllegalArgumentException("`wormhole`'s arrival portal is not among this maze's arrival portals");
		wormholes.add(wormhole);
	}
	
	public boolean isCompleted() {
		return foodItems.length == 0;
	}
	
	private void checkPacManDamage() {
		for (Ghost ghost : ghosts)
			if (ghost.getSquare().equals(pacMan.getSquare()))
				ghost.hitBy(pacMan);
	}
	
	public void moveGhosts() {
		for (Ghost ghost : ghosts)
			ghost.move(random);
		checkPacManDamage();
	}
	
	public void pacManAtePowerPellet() {
		for (Ghost ghost : ghosts)
			ghost.pacManAtePowerPellet();
	}
	
	private void removeFoodItemsAtIndex(int index) {
		FoodItem[] newFoodItems = new FoodItem[foodItems.length - 1];
		System.arraycopy(foodItems, 0, newFoodItems, 0, index);
		System.arraycopy(foodItems, index + 1, newFoodItems, index, newFoodItems.length - index);
		foodItems = newFoodItems;
	}
	
	private void checkFoodItemCollision(Square square) {
		for (int i = 0; i < foodItems.length; i++) {
			if (foodItems[i].getSquare().equals(square)) {
				foodItems[i].eatenByPacMan(this);
				removeFoodItemsAtIndex(i);
				return;
			}
		}
	}
	
	private Square processWormholes(Square square) {
		for (DeparturePortal portal : departurePortals) {
			if (portal.getSquare().equals(square)) {
				Wormhole[] wormholes = portal.getWormholes().toArray(n -> new Wormhole[n]);
				if (wormholes.length > 0) {
					Wormhole wormhole = wormholes[random.nextInt(wormholes.length)];
					return wormhole.getArrivalPortal().getSquare();
				}
				break;
			}
		}
		return null;
	}
	
	public void movePacMan(Direction direction) {
		Square newSquare = pacMan.getSquare().getNeighbor(direction);
		while (newSquare != null && newSquare.isPassable()) {
			pacMan.setSquare(newSquare);
			checkFoodItemCollision(newSquare);
			checkPacManDamage();
			newSquare = processWormholes(newSquare);
		}
	}
	
}
