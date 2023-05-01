package consoleMineSweeper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CellTest {
	static Cell cell;
	
	@BeforeEach
	void setUpCell() {
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
	void getBomb_NameNoBomb_ReturnsBombState() {
		cell.setName(0);
		assertEquals("|    |", cell.getName());
	}
	
	@Test
	void getBomb_NameWThreeBombs_ReturnsBombState() {
		cell.setName(3);
		assertEquals("| B3 |", cell.getName());
	}
	
	
	
	

}
