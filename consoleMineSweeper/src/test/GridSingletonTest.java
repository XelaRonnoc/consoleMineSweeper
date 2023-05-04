package test;

	import static org.junit.Assert.assertEquals;
	import static org.junit.Assert.assertFalse;
	import static org.junit.Assert.assertThrows;
	import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;
	
	import consoleMineSweeper.Cell;
	import consoleMineSweeper.GridSingleton;
	
public class GridSingletonTest {
		static GridSingleton grid;
		
		@BeforeEach
		void setUpGrid() {
			grid = GridSingleton.getGrid();
			grid.setupGrid(10, 10);
		}
		
		@Test
		void getCell_TwoDigitInt_ReturnsCorrectCell() {
			Optional<Cell> cell = grid.getCell(15);
			assertEquals(1, cell.get().getXLoc());
			assertEquals(5, cell.get().getYLoc());
		}
		
		@Test
		void getCell_LessThan0Input_ReturnsOptionalEmpty() {
			Optional<Cell> cell = grid.getCell(-1);
			assertEquals(Optional.empty(), cell);
		}
		
		@Test
		void getCell_OutOfBoundsTwoDigitInt_ReturnsOptionalEmpty() {
			Optional<Cell> cell = grid.getCell(202);
			assertEquals(Optional.empty(), cell);
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
