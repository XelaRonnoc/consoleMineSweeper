package consoleMineSweeper;

import java.util.Scanner;

public class InputHandler {
	
	public static int selectGameBoardSize(Scanner s) {
		System.out.println("Please enter the desired board width as an int e.g. 10");
		int gameBoardSize = -1;
		while(gameBoardSize < 0) {
			try {
			gameBoardSize = s.nextInt();
			}catch (Exception e){
				s.nextLine();
				System.out.println("invalid input please enter a number greater than 0 and less than 10");
			}
		}
		return gameBoardSize;
	}
	
	public static int selectBombNumber(Scanner s) {
		System.out.println("Please enter the desired number of bombs as an int e.g. 10");
		int numberOfBombs = -1;
		while(numberOfBombs < 0 ) {
			try {
				numberOfBombs = s.nextInt();
				
			}catch (Exception e) {
				s.nextLine();
				System.out.println("invalid input please enter a number greater than 0 and less than the game area");
			}
		}
		return numberOfBombs;
	}
	
	public static void consumerNextLine(Scanner s) {
		s.nextLine();
	}
	
	public static String selectTile(Scanner s) {
		System.out.println("Enter tile number");
		String input = "";
		while(true) {
			try {
				input = s.nextLine();
			}catch (Exception e) {
				System.out.println("Invalid input Please enter a positive integer");
				continue;
			}
			break;
		}
		return input;
	}
	
	public static String menuHandler(Scanner s) {
//		s.nextLine();// consumes unconsumed next line from the nextInts
		System.out.println("enter r to continue or any other key to exit");
		String menuInput = s.nextLine();
		System.out.println(menuInput);
		return menuInput;
	}
}
