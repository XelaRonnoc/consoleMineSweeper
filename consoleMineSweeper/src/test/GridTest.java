package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import consoleMineSweeper.Cell;
import consoleMineSweeper.Grid;
import consoleMineSweeper.InvalidInputException;

public class GridTest {
	static Grid grid;
	
	@BeforeEach
	void setUpGrid() {
		grid = new Grid(10, 10);
	}
	
	@Test
	void getCell_TwoDigitInt_ReturnsCorrectCell() {
		Cell cell = null;
		try {
			cell = grid.getCell(15);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		assertEquals(1, cell.getXLoc());
		assertEquals(5, cell.getYLoc());
	}
	
	@Test
	void getCell_GreaterThan2DigitInt_Returnsnull() {
		assertThrows(InvalidInputException.class, () -> grid.getCell(145));
	}
	
	@Test
	void getCell_LessThan2DigitInt_Returnsnull() {
		assertThrows(InvalidInputException.class, () -> grid.getCell(1));
	}
	
	@Test
	void getSafeSpacesLeft_ReturnsNumOfCellsMinusNumOfBombs() {
		int result = grid.getSafeSpacesLeft();
		assertEquals(90, result);
	}
	
	@Test
	void decrementSafeSpacesLeft_ReducesSafeSpacesLeftBy1() {
		int initial = grid.getSafeSpacesLeft();
		grid.decrementSafeSpacesLeft();
		int outcome = grid.getSafeSpacesLeft();
		assertEquals(initial-1, outcome);
	}
	
	@Test 
	void getNumberOfBombs_ReturnsNumberOfBombs(){
		assertEquals(10, grid.getNumberOfBombs());
	}
	
	@Test 
	void getGridSize_ReturnsGridDimension(){
		assertEquals(10, grid.getGridSize());
	}
}
