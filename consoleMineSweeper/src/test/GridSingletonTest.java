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
	import consoleMineSweeper.OutOfGridBoundsException;
	import consoleMineSweeper.InvalidInputException;
	
public class GridSingletonTest {
		static GridSingleton grid;
		
		@BeforeEach
		void setUpGrid() {
			grid = GridSingleton.getGrid();
			grid.setupGrid(10, 10);
		}
		
		@Test
		void getCell_XYDigitInt_ReturnsCorrectCell() {
			Optional<Cell> cell = grid.getCell(1,5);
			assertEquals(1, cell.get().getXLoc());
			assertEquals(5, cell.get().getYLoc());
		}
		
		@Test
		void getCell_invalidXY_ReturnsOptionalEmpty() {
			Optional<Cell> cell = grid.getCell(-1,0);
			assertEquals(Optional.empty(), cell);
		}
		
		@Test
		void getCell_OutOfBoundsXY_ReturnsOptionalEmpty() {
			Optional<Cell> cell = grid.getCell(20,2);
			assertEquals(Optional.empty(), cell);
		}
		
		@Test
		void submit_OutOfBoundsInput_ThrowsOutOfGridBoundsException(){
			assertThrows(OutOfGridBoundsException.class,() -> {
				grid.submit("1000");
				});
		}
		
		@Test
		void submit_InvalidInput_ThrowsOutOfGridBoundsException(){
			assertThrows(InvalidInputException.class,() -> {
				grid.submit("alex");
				});
		}
		
		@Test
		void getCell_StringLoc_ReturnsCorrectCell() {
			Cell cell = grid.getCell("15");
			assertEquals(1, cell.getXLoc());
			assertEquals(5, cell.getYLoc());
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
		
		@Test 
		void getGridArea_ReturnsGridArea(){
			assertEquals(100, grid.getGridArea());
		}
		
		@Test 
		void getIsRunning_ReturnsGameStateRunning_ReturnTrue(){
			assertTrue(grid.getIsRunning());
		}
		
		@Test 
		void getIsRunning_ReturnsGameStateNotRunningAfterHittingBomb_ReturnsFalse(){
			grid.setupGrid(2, 4); // full field of bombs
			try {
				grid.submit("00");
			} catch (OutOfGridBoundsException e) {
			} catch (InvalidInputException e) {
			}
			assertFalse(grid.getIsRunning());
		}
		
		@Test 
		void getIsRunning_ReturnsGameStateNotRunningNoBombsInstaWin_ReturnsFalse(){
			grid.setupGrid(2, 4); // full field of bombs
			try {
				grid.submit("00");
			} catch (OutOfGridBoundsException e) {
			} catch (InvalidInputException e) {
			}
			assertFalse(grid.getIsRunning());
		}
		
		

}
