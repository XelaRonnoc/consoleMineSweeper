package consoleMineSweeper;

import java.util.ArrayList;
import java.util.Optional;

public class Cell {
	
	// should avoid this global state with this 
	private GridSingleton gridRef = GridSingleton.getGrid();
	private int xLoc;
	private int yLoc;
	private String loc;
	private boolean rowEnd;
	private boolean hasBomb = false;
	private String name;
	private boolean revealed = false;
	private ArrayList<Cell> neighbors = new ArrayList<Cell>();
	private boolean canCascade = true;
	private int bombNear = 0;
	
	
	
	public Cell(int x, int y) {
		new Cell(x,y,false);
	}
	
	public Cell(int x, int y, boolean rowEnd) {
		this.loc = ""+ x + "" + y;
		this.xLoc = x;
		this.yLoc = y;
		this.rowEnd = rowEnd;
		this.name = "| " + this.xLoc + this.yLoc + " |";
		
	}
	
	public Cell(int x, int y, boolean rowEnd, String loc) {
		this.loc = loc;
		this.xLoc = x;
		this.yLoc = y;
		this.rowEnd = rowEnd;
		this.name = "| " + this.xLoc + this.yLoc + " |";
		
	}
	
	// cell can still store it's neighbors but this info should be passed in from the grid
	public void newInitialiseNeighbors() {
		for(int i = this.yLoc-1; i <= this.yLoc+1; i++) {
			for(int j = this.xLoc-1; j <= xLoc+1; j++) {
				if(i != this.yLoc || j != this.xLoc) {
					if(i < 0 || j < 0 || i >= this.gridRef.getGridSize() || j >= this.gridRef.getGridSize()) {
						continue;
					}
					Optional<Cell> neighbor = gridRef.getCell(j,i);
					if(neighbor.isPresent()) {
						if(neighbor.get().getBomb()) {
							this.canCascade = false;
							this.bombNear++;
						}
						this.neighbors.add(neighbor.get());
					}
				}
			}
		}
	}
	
	public String getLocation() {
		return this.loc;
	}
	
	public String getName() {
		return this.name;
	}
	
	// use string format
	public void setName() {
		if(this.bombNear == 0) {
			this.name = "|    |";
		}else {
			this.name = "| " + "B" + this.bombNear + " |";
		}	
	}
	
	public int getXLoc() {
		return this.xLoc;
	}
	public int getYLoc() {
		return this.yLoc;
	}
	
	public void render() {
		System.out.print(this.name);
		if(this.rowEnd) {
			System.out.printf("\n");
		}
		
	}
	
	public void setBomb() {
		this.hasBomb = true;
	}
	
	public boolean getBomb() {
		return this.hasBomb;
	}
	
	public void setRevealed() {
		this.revealed = true;
	}
	
	public boolean getRevealed() {
		return this.revealed;
	}
	
	public ArrayList<Cell> getNeighbors(){
		return this.neighbors;
	}

	public boolean canCascade() {
		
		return this.canCascade;
	}
	
	public int getNear() {
		return this.bombNear;
	}
	
	// ONLY USE FOR TESTING
	public void setNear(int bombs) {
		this.bombNear = bombs;
	}
	

}
