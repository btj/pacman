package pacman;

public class MazeMap {

	private int width;
	private int height;
	/**
	 * Stores this maze's squares (in row-major order).
	 */
	private boolean[] passable;
	
	/**
	 * Returns the width (i.e. the number of columns) of this maze map.
	 */
	public int getWidth() { return width; }
	
	/**
	 * Returns the height (i.e. the number of rows) of this maze map.
	 */
	public int getHeight() { return height; }
	
	/**
	 * Returns whether the square in this maze at row index {@code row} and column index {@code column} is passable.
	 * The square in the top-left corner of the maze has row index 0 and column index 0.
	 */
	public boolean isPassable(int rowIndex, int columnIndex) { return passable[rowIndex * width + columnIndex]; }
	
	public MazeMap(int width, int height, boolean[] passable) {
		this.width = width;
		this.height = height;
		this.passable = passable.clone();
	}
}
