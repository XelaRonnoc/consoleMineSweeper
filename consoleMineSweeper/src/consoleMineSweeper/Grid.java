package consoleMineSweeper;

public class Grid {
	
	private static Cell[][] cells = new Cell[10][10];
	private int numberOfBombs;
	private int unassignedBombs;
	private int gridSize;
	private int safeSpaces;
	private int safeSpacesLeft;
	
	public Grid(int bombs, int gridSize) {
		if(bombs <=0) {
			bombs = 1;
		}

		if(gridSize < 2) {
			gridSize = 2;
		}
		this.gridSize = gridSize<=10 ? gridSize : 10;
		cells = new Cell[this.gridSize][this.gridSize];
	    for(int i=0; i<cells.length; i++) {
	        for(int j=0; j<cells[i].length; j++) {
	        	if(j == cells[i].length-1) {
	        		cells[i][j] = new Cell(j,i,true);
	        	}else {
	        		cells[i][j] = new Cell(j,i,false);
	        	}	        	
	        }
	    }
	    
	    for(int i=0; i<cells.length; i++) {
	        for(int j=0; j<cells[i].length; j++) {
	        	cells[i][j].initialiseNeighbors();
	        }
	    }
	    
	    this.numberOfBombs = (int) ( bombs >= Math.pow(this.gridSize, 2) ? Math.pow(this.gridSize, 2)-1 : bombs);
	    this.unassignedBombs = this.numberOfBombs;
	    this.safeSpaces = (int) Math.pow(this.gridSize, 2) - this.numberOfBombs;
	    this.safeSpacesLeft = safeSpaces;
	    
	}
	
	public void initialiseBombs() {
		while(this.unassignedBombs > 0) {
			int xLoc = (int) Math.floor(Math.random()*this.gridSize);
			int yLoc = (int) Math.floor(Math.random()*this.gridSize);
			if(cells[yLoc][xLoc].getBomb()) {
				continue;
			}
			cells[yLoc][xLoc].setBomb();
			unassignedBombs--;
		}
	}
	
	public void render() {
		// replace with stream?
	    for(int i=0; i<cells.length; i++) {
	        for(int j=0; j<cells[i].length; j++) {
	          cells[i][j].render();
	        }
	      }
	}
	
	public Cell getCell(int input) throws InvalidInputException {
		if((""+input).length() > 2) {
			System.out.println((""+input));
			throw new InvalidInputException("inputs must be 2 digits or less");
		}
		int xLoc = (input/10);
		int yLoc = (input%10);
		Cell selected = cells[yLoc][xLoc];
		return selected;
	}
	
	public static Cell getCell(int x, int y) {
		if(x < 0 || x >= cells[0].length) {
			return null;
		}
		
		if(y < 0 || y >= cells.length) {
			return null;
		}
		return (cells[y][x] != null) ? cells[y][x] : null;
	}
	
	public void showBombs(Cell selected) {
		selected.setRevealed();
		decrementSafeSpacesLeft();
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
}

