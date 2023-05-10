package consoleMineSweeper;

import java.util.Optional;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		
		Scanner s = new Scanner(System.in);
		int gameBoardSize = -1;
		int numberOfBombs = -1;
		System.out.println("Please enter the desired board width as an int e.g. 10");
		while(gameBoardSize < 0) {
			try {
			gameBoardSize = s.nextInt();
			}catch (Exception e){
				s.nextLine();
				System.out.println("invalid input please enter a number greater than 0 and less than 10");
			}
		}
		System.out.println("Please enter the desired number of bombs as an int e.g. 10");
		while(numberOfBombs < 0 ) {
			try {
				numberOfBombs = s.nextInt();
				
			}catch (Exception e) {
				s.nextLine();
				System.out.println("invalid input please enter a number greater than 0 and less than the game area");
			}
			s.nextLine(); // consume unused next line
		}
		boolean running = true;
		GridSingleton gameGrid = GridSingleton.getGrid();
		gameGrid.setupGrid(numberOfBombs, gameBoardSize);
		
		// make the running part be held in gameGrid and then
		// then while(gameGrid.isRunning()) can be the state
		while(running) {
			gameGrid.render();
			
			System.out.println("Enter tile number");
			String input; 
			// seperate out into an input manager class
			while(true) {
				try {
					input = s.nextLine();
				}catch (Exception e) {
					System.out.println("Invalid input Please enter a positive integer");
					continue;
				}
				break;
			}
			
			//replace with try catch as performant isn't as important
			// and you can throw more errors and they can be thrown individually	
//			try {
//				gameGrid.submit(input); // true, false
//			}catch (OutOfGridError e) {
//				//handle error
//			} catch (InvalidValue e) {
//				//handle error
//			} catch (Exception e) {
//				// handle error
//			}
			
			Cell selected = null;
			Optional<Cell> chosen = gameGrid.getCell(input);
			if(chosen.isEmpty()) {
				System.out.println("please enter a positive integer displayed on the game board");
				continue;
			}else {
				selected = chosen.get();
			}
			
			boolean bomb = selected.getBomb();
			if(bomb) {
				System.out.println("BOOOM!");
				Save.save(
						"lost",
						"" + gameGrid.getSafeSpacesLeft(),
						"" + gameGrid.getNumberOfBombs(),
						gameGrid.getGridSize() + "x" + gameGrid.getGridSize()
						);		
				if(menuHandler(s).equals("r")) {
					gameGrid.setupGrid(numberOfBombs, gameBoardSize);
					continue;
				}
				break;
				
			}else {
				gameGrid.showBombs(selected);
			}
			
			if(gameGrid.getSafeSpacesLeft() == 0) {
				System.out.println("You Won!!!");
				Save.save(
						"Won",
						"" + gameGrid.getSafeSpacesLeft(),
						"" + gameGrid.getNumberOfBombs(),
						gameGrid.getGridSize() + "x" + gameGrid.getGridSize()
						);	
				if(menuHandler(s).equals("r")) {
					gameGrid.setupGrid(numberOfBombs, gameBoardSize);
					continue;
				}
				break;
				
			}
		}
		System.out.println("exited");

	}
	
	
	public static String menuHandler(Scanner s) {
//		s.nextLine();// consumes unconsumed next line from the nextInts
		System.out.println("enter r to continue or any other key to exit");
		String menuInput = s.nextLine();
		System.out.println(menuInput);
		return menuInput;
	}

}
