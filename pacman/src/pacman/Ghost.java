package pacman;

import java.util.Arrays;
import java.util.Random;

public class Ghost {
	
	private Square square;
	private Direction direction;

	public Square getSquare() { return square; }
	
	public Direction getDirection() { return direction; }
	
	public Ghost(Square square, Direction direction) {
		this.square = square;
		this.direction = direction;
	}
	
	public void setSquare(Square square) {
		this.square = square;
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	private static int MOVE_FORWARD_PREFERENCE = 10;
	
	public Direction chooseNextMoveDirection(Random random) {
		int moveForwardPreference = square.canMove(direction) ? MOVE_FORWARD_PREFERENCE : 0;
		Direction[] passableDirections = square.getPassableDirectionsExcept(direction.getOpposite());
		if (passableDirections.length == 0)
			return direction.getOpposite();
		int result = random.nextInt(moveForwardPreference + passableDirections.length);
		if (result < moveForwardPreference)
			return direction;
		return passableDirections[result - moveForwardPreference];
	}
	
	public void move(Random random) {
		direction = chooseNextMoveDirection(random);
		setSquare(square.getNeighbor(direction));
	}
}
