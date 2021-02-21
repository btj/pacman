package pacman;

public class PacMan {
	
	private int nbLives;
	private Square square;
	
	public Square getSquare() { return square; }
	
	public PacMan(int nbLives, Square square) {
		this.nbLives = nbLives;
		this.square = square;
	}
	
	public void setSquare(Square square) {
		this.square = square;
	}
	
	public void die() {
		this.nbLives--;
	}

	public int getNbLives() {
		return nbLives;
	}

}
