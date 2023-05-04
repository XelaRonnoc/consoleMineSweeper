package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import consoleMineSweeper.Cell;
import consoleMineSweeper.GridSingleton;

public class CellTest {
	static Cell cell;
	static GridSingleton grid;
	
	@BeforeEach
	void setUpCell() {
		grid = GridSingleton.getGrid();
		grid.setupGrid(0, 10);
		cell = new Cell(1, 5, false);
	}
	
	@Test
	void getXLoc_ReturnsXLoc() {
		assertEquals(1, cell.getXLoc());
	}
	
	@Test
	void getYLoc_ReturnsYLoc() {
		assertEquals(5, cell.getYLoc());
	}
	
	@Test
	void getLoc_ReturnsLoc() {
		assertEquals(15, cell.getLocation());
	}
	
	@Test
	void getBomb_NoBomb_ReturnsFalse() {
		assertEquals(false, cell.getBomb());
	}
	
	@Test
	void getBomb_Bomb_ReturnsBombState() {
		cell.setBomb();
		assertEquals(true, cell.getBomb());
	}
	
	@Test
	void getBomb_Revealed_ReturnsBombState() {
		cell.setRevealed();
		assertEquals(true, cell.getRevealed());
	}
	
	
	@Test
	void getBomb_NotRevealed_ReturnsBombState() {
		assertEquals(false, cell.getRevealed());
	}
	
	@Test
	void getBomb_DefaultName_ReturnsBombState() {
		assertEquals("| 15 |", cell.getName());
	}
	
	@Test
	void getName_NameNoBomb_ReturnsBombState() {
		cell.setName();
		assertEquals("|    |", cell.getName());
	}
	
	@Test
	void getBomb_NameWThreeBombs_ReturnsBombState() {
		cell.setNear(3);
		cell.setName();
		assertEquals("| B3 |", cell.getName());
	}
	
	@Test
	void canCascade_BomblessGrid_returnsTrue() {
		Optional<Cell> cur = grid.getCell(15);
		assertEquals(true, cur.get().canCascade());
	}
	
	
	@Test
	void getNear_BomblessGrid_returnsZero() {
		Optional<Cell> cur = grid.getCell(15);
		assertEquals(0, cur.get().getNear());
	}
	
	@Test
	void getNeighbors_GridSurronded_ReturnsArrayListSize8() {
		Optional<Cell> cur = grid.getCell(15);
		assertEquals(8, cur.get().getNeighbors().size());
	}
	@Test
	void getNeighbors_GridTopLeft_ReturnsArrayListSize3() {
		Optional<Cell> cur = grid.getCell(00);
		assertEquals(3, cur.get().getNeighbors().size());
	}
	
	@Test
	void getNeighbors_GridBottomRight_ReturnsArrayListSize3() {
		Optional<Cell> cur = grid.getCell(99);
		assertEquals(3, cur.get().getNeighbors().size());
	}
	
	@Test
	void getNeighbors_GridMidLeft_ReturnsArrayListSize5() {
		Optional<Cell> cur = grid.getCell(01);
		assertEquals(5, cur.get().getNeighbors().size());
	}
	
	
	
	

}
