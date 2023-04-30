package consoleMineSweeper;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		System.out.println("Please enter the board width (<=10 >=2)");
		int gameBoardSize = s.nextInt();
		System.out.println("Please enter the number of Bombs (>=1 <=width^2)");
		int numberOfBombs = s.nextInt();
		boolean gameOver = false;
		
		Grid gameGrid = new Grid(numberOfBombs, gameBoardSize);
		gameGrid.initialiseBombs();
		
		
		
		while(!gameOver) {
			gameGrid.render();
			System.out.println("Enter tile number");
			int input = s.nextInt();
			Cell selected = gameGrid.getCell(input);
			boolean bomb = selected.getBomb();
			if(bomb) {
				System.out.println("BOOOM!");
				gameOver = true;
				break;
			}else {
				gameGrid.decrementSafeSpacesLeft();
				gameGrid.showBombs(selected);
			}
			
			if(gameGrid.getSafeSpacesLeft() == 0) {
				System.out.println("You Won!!!");
				gameOver = true;
				break;
			}
			
		}

	}

}
