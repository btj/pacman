package pacman;

import java.util.Arrays;
import java.util.Random;

public class Maze {
	
	private Random random;
	private MazeMap map;
	private PacMan pacMan;
	private Ghost[] ghosts;
	private Dot[] dots;
	
	public MazeMap getMap() { return map; }
	
	public PacMan getPacMan() { return pacMan; }
	
	public Ghost[] getGhosts() { return ghosts.clone(); }
	
	public Dot[] getDots() { return dots.clone(); }
	
	public Maze(Random random, String description) {
		this.random = random;
		
		String[] lines = description.trim().split("\n");
		
		int height = lines.length;
		int width = lines[0].length();
		
		boolean[] passable = new boolean[height * width];
		for (int i = 0; i < passable.length; i++)
			passable[i] = true;
		
		int nbDots = 0;
		Dot[] dots = new Dot[width * height];
		
		int nbGhosts = 0;
		Ghost[] ghosts = new Ghost[width * height];
		
		for (int row = 0; row < lines.length; row++) {
			String line = lines[row];
			for (int column = 0; column < line.length(); column++) {
				char c = line.charAt(column);
				if (c == '#')
					passable[row * width + column] = false;
			}
		}
		map = new MazeMap(width, height, passable);
		
		for (int row = 0; row < lines.length; row++) {
			String line = lines[row];
			for (int column = 0; column < line.length(); column++) {
				char c = line.charAt(column);
				switch (c) {
				case ' ' -> {}
				case '#' -> {}
				case '.' -> dots[nbDots++] = new Dot(Square.of(map, row, column));
				case 'G' -> ghosts[nbGhosts++] = new Ghost(Square.of(map, row, column), Direction.values()[random.nextInt(Direction.values().length)]);
				case 'P' -> {
					if (pacMan != null)
						throw new IllegalArgumentException("Maze description contains multiple P characters");
					pacMan = new PacMan(3, Square.of(map, row, column));
				}
				default -> throw new IllegalArgumentException("Invalid character in maze description: " + c);
				}
			}
		}
		
		if (pacMan == null)
			throw new IllegalArgumentException("Maze description does not contain a P character");
		this.dots = Arrays.copyOf(dots, nbDots);
		this.ghosts = Arrays.copyOf(ghosts, nbGhosts);
	}
	
	public boolean isCompleted() {
		return dots.length == 0;
	}
	
	private void checkPacManDamage() {
		for (Ghost ghost : ghosts)
			if (ghost.getSquare().equals(pacMan.getSquare()))
				pacMan.die();
	}
	
	public void moveGhosts() {
		for (Ghost ghost : ghosts)
			ghost.move(random);
		checkPacManDamage();
	}
	
	private void removeDotAtIndex(int index) {
		Dot[] newDots = new Dot[dots.length - 1];
		System.arraycopy(dots, 0, newDots, 0, index);
		System.arraycopy(dots, index + 1, newDots, index, newDots.length - index);
		dots = newDots;
	}
	
	private void removeDotAtSquare(Square square) {
		for (int i = 0; i < dots.length; i++) {
			if (dots[i].getSquare().equals(square)) {
				removeDotAtIndex(i);
				return;
			}
		}
	}
	
	public void movePacMan(Direction direction) {
		Square newSquare = pacMan.getSquare().getNeighbor(direction);
		if (newSquare.isPassable()) {
			pacMan.setSquare(newSquare);
			removeDotAtSquare(newSquare);
			checkPacManDamage();
		}
	}
	
}
