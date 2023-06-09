package consoleMineSweeper;

import java.util.ArrayList;
import java.util.Optional;



// change this to be a wrapper for grid class so that it just holds an instance of grid
public class GridSingleton {
	private static GridSingleton instance = new GridSingleton();
	private  ArrayList<Cell> cells = new ArrayList<Cell>();
	private  int numberOfBombs = 0;
	private  int unassignedBombs = 0;
	private  int gridSize = 2;
	private int gridArea = 4;
	private  int safeSpacesLeft;
	private boolean isRunning;
	
	private GridSingleton() {
		
	}
	
	public static GridSingleton getGrid() {
		return instance;
	}
	
	public void setupGrid(int size, int bombs) {
		if(size > 10) {
			size = 10;
		}
		this.gridSize = size >= 2 ? size : 2; 
		this.gridArea = (int) Math.pow(this.gridSize, 2);
		this.setUpCells();
		this.setupBombs(bombs);
		this.initialliseBombs();
		this.setUpNeighbors();
		this.safeSpacesLeft = this.gridArea-this.numberOfBombs;
		this.isRunning = true;
	}
	
	private void setUpNeighbors() {
		for(Cell cell: cells) {
			initCellNeighbors(cell); 
		}
		
	}
	
	private void initCellNeighbors(Cell cell) {
		for(int i = cell.getYLoc()-1; i <= cell.getYLoc()+1; i++) {
			for(int j = cell.getXLoc()-1; j <= cell.getXLoc()+1; j++) {
				if(i != cell.getYLoc() || j != cell.getXLoc()) {
					if(i < 0 || j < 0 || i >= this.getGridSize() || j >= this.getGridSize()) {
						continue;
					}
					Optional<Cell> neighbor = this.getCell(j,i);
					if(neighbor.isPresent()) {
						if(neighbor.get().getBomb()) {
							cell.setCanCascade(false);
							cell.incrementBombNear();
						}
						cell.addNeighbor(neighbor.get());
					}
				}
			}
		}
	}

	private void initialliseBombs() {
		while(this.unassignedBombs > 0) {
			int cellLoc = (int) Math.floor(Math.random()*this.gridArea);
			if(cells.get(cellLoc).getBomb()) {
				continue;
			}
			this.cells.get(cellLoc).setBomb();
			unassignedBombs--;
		}
		
	}

	private void setupBombs(int bombs) {
		if(bombs >= this.gridArea){
			this.numberOfBombs = this.gridArea - 1;
		}else if(bombs < 0) {
			this.numberOfBombs = 0;
		}else {
			this.numberOfBombs = bombs;
		}
		this.unassignedBombs = this.numberOfBombs;
		
	}

	private void setUpCells() {
		this.cells = new ArrayList<Cell>(this.gridArea);
		int yLoc = 0;
		int xLoc = 0;
		while(xLoc < this.gridSize && yLoc < this.gridSize) {
			if(xLoc == this.gridSize-1) { // if end of row
				cells.add(new Cell(xLoc,yLoc, true, ""+xLoc+yLoc));
				xLoc = yLoc != this.gridSize-1 ? 0 : xLoc++; // if not end of last row set to 0
				yLoc++;
			}else {
				cells.add(new Cell(xLoc,yLoc, false, ""+xLoc+yLoc));
				xLoc++;
			}
		}

	}
	
	public void submit(String input) throws OutOfGridBoundsException, InvalidInputException {
		int inputAsInt = 0;
		try {
		inputAsInt = Integer.parseInt(input);
		}catch (NumberFormatException e) {
			throw new InvalidInputException();
		}
		if(inputAsInt > Integer.parseInt(this.cells.get(cells.size()-1).getLocation()) || inputAsInt < 0) {
			throw new OutOfGridBoundsException();
		}

		Cell selected = this.getCell(input);
		if(selected.getBomb()) {
			System.out.println("BOOOM!");
			Save.save(
					"lost",
					"" + this.getSafeSpacesLeft(),
					"" + this.getNumberOfBombs(),
					this.getGridSize() + "x" + this.getGridSize()
					);
			this.isRunning = false;	
		}else {
			this.showBombs(selected);
			if(this.safeSpacesLeft == 0) {
				System.out.println("You Won!!!");
				Save.save(
						"Won",
						"" + this.getSafeSpacesLeft(),
						"" + this.getNumberOfBombs(),
						this.getGridSize() + "x" + this.getGridSize()
						);	
				this.isRunning = false;
			}
		}
	}
	
	public Cell getCell(String input) {
		Cell selected = this.cells.stream().filter(s -> s.getLocation().equals(input)).findFirst().get();
		return selected;
	}
	
	public Optional<Cell> getCell(int x, int y) {
		
		Optional<Cell> selected = this.cells.stream().filter(s -> s.getXLoc() == x && s.getYLoc() == y).findFirst();
		if(selected.isEmpty()) {
			System.out.println("no cell found");
			return Optional.empty();
		}
		return selected;
	}
	
	public void showBombs(Cell selected) {
		if(selected.getBomb()) {
			return;
		}
		selected.setRevealed();
		this.decrementSafeSpacesLeft();
		
		for(Cell neighbor : selected.getNeighbors()) {
			if(!neighbor.getRevealed()) {
				if(selected.canCascade()) {
					showBombs(neighbor);
				}	
			}
		}	
		selected.setName();
	}
	
	public boolean getIsRunning() {
		return this.isRunning;
	}
	
	public int getSafeSpacesLeft() {
		return this.safeSpacesLeft;
	}
	
	public void decrementSafeSpacesLeft() {
		this.safeSpacesLeft--;
	}
	
	public int getNumberOfBombs() {
		return this.numberOfBombs;
	}
	
	public int getGridSize() {
		return this.gridSize;
	}
	
	public int getGridArea() {
		return this.gridArea;
	}
	
	public void render() {
		for(Cell cur: this.cells) {
			cur.render();
		}
	}
}
