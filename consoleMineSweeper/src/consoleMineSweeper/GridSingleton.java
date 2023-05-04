package consoleMineSweeper;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public class GridSingleton {
	private static GridSingleton instance = new GridSingleton();
	private  ArrayList<Cell> cells = new ArrayList<Cell>();
	private  int numberOfBombs = 0;
	private  int unassignedBombs = 0;
	private  int gridSize = 2;
	private int gridArea = 4;
	private  int safeSpacesLeft;
	
	private GridSingleton() {
		
	}
	
	public static GridSingleton getGrid() {
		return instance;
	}
	
	public void setupGrid(int bombs, int size) {
		this.gridSize = size >= 2 ? size : 2; 
		this.gridArea = (int) Math.pow(this.gridSize, 2);
		this.setupBombs(bombs);
		this.setUpCells();
		this.initialliseBombs();

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
		}else if(bombs < 1) {
			this.numberOfBombs = 1;
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
				cells.add(new Cell(xLoc,yLoc, true));
				xLoc = yLoc != this.gridSize-1 ? 0 : xLoc++; // if not end of last row set to 0
				yLoc++;
			}else {
				cells.add(new Cell(xLoc,yLoc, true));
				xLoc++;
			}
			
		}
		
		for(Cell cell: cells) {
			cell.initialiseNeighbors(); // in current form won't work need new initialise Neighbors
		}
	}
	
	
	public Cell getCell(int input) throws InvalidInputException{
		if((""+input).length() > 2) {
			System.out.println(input);
			throw new InvalidInputException("inputs must be 2 digits or less");
		}
		
		Optional<Cell> selected = this.cells.stream().filter(s -> s.getLocation() == input).findFirst();
		
		if(selected.isEmpty()) {
			throw new InvalidInputException("cannot find cell at: " + input);
		}
		
		return selected.get();
	}
	
	public void showBombs(Cell selected) {
		selected.setRevealed();
		this.decrementSafeSpacesLeft();
		int bombCount = 0;
		
		
		for(Cell neighbor : selected.getNeighbors()) {
			if(!neighbor.getRevealed()) {
				if(neighbor.getBomb()) {
					bombCount++;
				}
			}
		}	

		if(bombCount == 0) {
			for(Cell neighbor : selected.getNeighbors()) {
				if(!neighbor.getRevealed()) {
					showBombs(neighbor);
				}
			}
		}
		selected.setName(bombCount);
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
