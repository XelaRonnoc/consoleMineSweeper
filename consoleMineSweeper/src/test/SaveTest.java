package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import consoleMineSweeper.Save;


public class SaveTest {
	
	@Test
	void createSaveFile_NormalRunningNotInterupted() {
		assertEquals("saveFile.txt", Save.createSaveFile());
	}

}
