package consoleMineSweeper;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		
		Scanner s = new Scanner(System.in);
		GridSingleton gameGrid = GridSingleton.getGrid();
		gameGrid.setupGrid(InputHandler.selectGameBoardSize(s), InputHandler.selectBombNumber(s));
		InputHandler.consumerNextLine(s);
		

		while(gameGrid.getIsRunning()) {
			gameGrid.render();
			String input = InputHandler.selectTile(s); 
			try {
				gameGrid.submit(input);
			}catch (InvalidInputException e) {
				System.out.printf("%s is a non-numeric string please enter a numeric string\n", input);
				continue;
			}catch (OutOfGridBoundsException e) {
				System.out.printf("%s is out of grid bounds ensure it is positive and no larger than the highest numbered cell\n", input);
				continue;
			}catch (Exception e) {
				e.printStackTrace();
				System.out.printf("%s is an invalid input\n", input);
				continue;
			}
			
			if(!gameGrid.getIsRunning()) {
				if(InputHandler.menuHandler(s).equals("r")) {
					gameGrid.setupGrid(InputHandler.selectGameBoardSize(s),InputHandler.selectBombNumber(s));
					InputHandler.consumerNextLine(s);
					continue;
				}
			}	
		}
		System.out.println("exited");
	}
	
	


}
