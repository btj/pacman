package pacman;

public class PowerPellet extends FoodItem {
	
	@Override
	public int getSize() {
		return 2;
	}
	
	public PowerPellet(Square square) {
		super(square);
	}
	
	@Override
	public void eatenByPacMan(Maze maze) {
		maze.pacManAtePowerPellet();
	}
}
