package consoleMineSweeper;

import java.util.ArrayList;
import java.util.Optional;

public class Cell {

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
	
	public void addNeighbor(Cell neighbor) {
		this.neighbors.add(neighbor);
	}
	
	public ArrayList<Cell> getNeighbors(){
		return this.neighbors;
	}

	public void setCanCascade(boolean cascadable) {
		this.canCascade = cascadable;
	}
	public boolean canCascade() {
		return this.canCascade;
	}
	
	public int getNear() {
		return this.bombNear;
	}
	
	public void incrementBombNear() {
		this.bombNear++;
	}
	
	// ONLY USE FOR TESTING
	public void setNear(int bombs) {
		this.bombNear = bombs;
	}
	

}
