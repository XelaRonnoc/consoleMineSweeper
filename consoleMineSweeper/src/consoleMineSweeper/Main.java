package consoleMineSweeper;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		boolean gameOver = false;
		
		Grid gameGrid = new Grid(100, 10);
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
				gameGrid.showBombs(selected);;
			}
			
		}

	}

}
