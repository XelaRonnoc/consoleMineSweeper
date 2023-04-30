package consoleMineSweeper;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		System.out.println("Please enter the board width (<=10 >=2)");
		int gameBoardSize = s.nextInt();
		System.out.println("Please enter the number of Bombs (>=1 <=width^2)");
		int numberOfBombs = s.nextInt();

		boolean running = true;
		
		Grid gameGrid = new Grid(numberOfBombs, gameBoardSize);
		gameGrid.initialiseBombs();
		
		
		
		while(running) {
			gameGrid.render();
			System.out.println("Enter tile number");
			int input = s.nextInt();
			Cell selected;
			try {
			selected = gameGrid.getCell(input);
			}catch(Exception e) {
				System.out.println("please provide a valid input");
				continue;
			}
			boolean bomb = selected.getBomb();
			if(bomb) {
				System.out.println("BOOOM!");
				System.out.print("type r to retry or any key to exit");
				s.nextLine();
				String menuInput = s.nextLine();
				System.out.println(menuInput);
				if(menuInput.equals("r")) {
					gameGrid = new Grid(numberOfBombs, gameBoardSize);
					gameGrid.initialiseBombs();
					continue;
				}else {
					break;
				}
				
			}else {
				gameGrid.decrementSafeSpacesLeft();
				gameGrid.showBombs(selected);
			}
			
			if(gameGrid.getSafeSpacesLeft() == 0) {
				System.out.println("You Won!!!");
				System.out.print("type r to retry or any key to exit");
				s.nextLine();
				String menuInput = s.nextLine();
				System.out.println(menuInput);
				if(menuInput.equals("r")) {
					gameGrid = new Grid(numberOfBombs, gameBoardSize);
					gameGrid.initialiseBombs();
					continue;
				}else {
					break;
				}
			
				
		}
		
			
		}
		System.out.println("exited");

	}

}
