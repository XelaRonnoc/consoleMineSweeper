package consoleMineSweeper;

import java.util.ArrayList;

public class Cell {
	private int xLoc;
	private int yLoc;
	private int loc;
	private boolean rowEnd;
	private boolean hasBomb = false;
	private String name;
	private boolean revealed = false;
	private ArrayList<Cell> neighbors = new ArrayList<Cell>();
	
	
	public Cell(int x, int y) {
		new Cell(x,y,false);
	}
	
	public Cell(int x, int y, boolean rowEnd) {
		this.xLoc = x;
		this.yLoc = y;
		this.rowEnd = rowEnd;
		this.name = "| " + this.xLoc + this.yLoc + " |";
		
	}
	
	public Cell(int loc, boolean rowEnd) {
		this.loc = loc;
		this.xLoc = loc/10;
		this.yLoc = loc%10;
		this.rowEnd = rowEnd;
		this.name = "| " + this.xLoc + this.yLoc + " |";
		
	}
	
	public void initialiseNeighbors() {
		for(int i = this.yLoc-1; i <= this.yLoc+1; i++) {
			for(int j = this.xLoc-1; j <= xLoc+1; j++) {
					if(i != this.yLoc || j != this.xLoc) {
						if(Grid.getCell(j,i) != null) {
							this.neighbors.add(Grid.getCell(j, i));
						}
					}
				}
				
			}
	}
	
	public int getLocation() {
		return this.loc;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(int numBombs) {
		if(numBombs == 0) {
			this.name = "|    |";
		}else {
			this.name = "| " + "B" + numBombs + " |";
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
	

	

}
