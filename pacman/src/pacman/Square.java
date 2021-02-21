package pacman;

import java.util.Arrays;

/**
 * Each instance of this class represents a position in a maze, specified by a row index and a column index.
 * The top row and the leftmost column have index 0.
 */
public class Square {
	
	private MazeMap mazeMap;
	private int rowIndex;
	private int columnIndex;
	
	public MazeMap getMazeMap() { return mazeMap; }
	
	public int getRowIndex() { return rowIndex; }
	
	public int getColumnIndex() { return columnIndex; }
	
	public boolean isPassable() { return mazeMap.isPassable(rowIndex, columnIndex); }
	
	private Square(MazeMap mazeMap, int rowIndex, int columnIndex) {
		if (mazeMap == null)
			throw new IllegalArgumentException("mazeMap must not be null");
		this.mazeMap = mazeMap;
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
	}

	public static Square of(MazeMap mazeMap, int rowIndex, int columnIndex) {
		return new Square(mazeMap, rowIndex, columnIndex);
	}
	
	public Square getNeighbor(Direction direction) {
		int rowIndex = this.rowIndex;
		int columnIndex = this.columnIndex;
		switch (direction) {
		case RIGHT -> columnIndex = (columnIndex + 1) % mazeMap.getWidth();
		case LEFT -> columnIndex = Math.floorMod(columnIndex - 1, mazeMap.getWidth());
		case DOWN -> rowIndex = (rowIndex + 1) % mazeMap.getHeight();
		case UP -> rowIndex = Math.floorMod(rowIndex - 1, mazeMap.getHeight());
		}
		return Square.of(mazeMap, rowIndex, columnIndex);
	}
	
	public boolean canMove(Direction direction) {
		return getNeighbor(direction).isPassable();
	}
	
	public Direction[] getPassableDirectionsExcept(Direction excludedDirection) {
		Direction[] result = new Direction[4];
		int nbDirections = 0;
		for (Direction direction : Direction.values())
			if (direction != excludedDirection && canMove(direction))
				result[nbDirections++] = direction;
		return Arrays.copyOf(result, nbDirections);
	}
	
	public boolean equals(Square other) {
		return mazeMap == other.mazeMap && rowIndex == other.rowIndex && columnIndex == other.columnIndex;
	}
	
}
