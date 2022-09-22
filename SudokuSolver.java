//@author Joseph Walker

// Sudoku is a logic game. Here we create a recursive algorithmic program
// using a two dimensional array to crack the code of the 9 X 9 puzzle


public class SudokuSolver {
	
// static constant created for our grid size (Sudoku puzzles 
// will always be 9 X 9)
	
	private static final int GRID_SIZE = 9;
	
	public static void main(String[] args) {
		
// Here we create our two-dimensional array, utilizing
// integer of '0' as a placeholder value
		
		int [][] board = {
			
				{7,0,2,0,5,0,6,0,0},
				{0,0,0,0,0,3,0,0,0},
				{1,0,0,0,0,9,5,0,0},
				{8,0,0,0,0,0,0,9,0},
				{0,4,3,0,0,0,7,5,0},
				{0,9,0,0,0,0,0,0,8},
				{0,0,9,7,0,0,0,0,5},
				{0,0,0,2,0,0,0,0,0},
				{0,0,7,0,4,0,2,0,3}
		};
		
		printBoard(board);
				
		if (solveBoard(board)) {
			System.out.println();
			System.out.println("Solved successfully!");
			System.out.println();
			
		}
		else {
			System.out.println("Unsolvable board :(");
		}
		printBoard(board);
		}

	private static void printBoard(int[][] board) 
	{ 
		for(int row = 0; row < GRID_SIZE; row++) 
		{
			if(row % 3 == 0 && row != 0) 
			{
				System.out.println("-----------");
			}
			for (int column = 0; column < GRID_SIZE; column++) {
				if(column % 3 == 0 && column != 0) {
					System.out.print("|");
				}
				System.out.print(board[row][column]);
			}
			System.out.println();
		}
	}

// We create a method which checks whether the blank space on our board
// is available within the row, column, and 3 X 3 grid square of puzzle
	
	private static boolean isNumberInRow (int[][] board, int number, int row)
	
	{
		
// If we find the number that we're checking for in the ROW, we will receive 
//	a return of true, If number is NOT found, we can return false for data entry
		
		for (int i = 0; i < GRID_SIZE; i++) 
		{
			if(board[row][i] == number) 
			{
				return true;
			}
		}
		
		return false;
		
	}
	
	private static boolean isNumberInColumn (int[][] board, int number, int column){
	

// If we find the number that we're checking for in the COLUMN, we will receive 
// a return of true. If number is NOT found, we can return false for data entry.
// This method differs in that it takes in the column as a parameter. In addition, 
// instead of looking at the board at position row/row at column i, we're looking 
// at the board at row i, column/column. 
		
		for (int i = 0; i < GRID_SIZE; i++) 
		{
			if(board[i][column] == number) {
				return true;
			}
		}
		
		return false;	
	}

// Our method for taking in both x and y coordinates of our 3 X 3 grid square.
// This method will take in BOTH row and column as a parameter
	
	private static boolean isNumberInBox (int[][] board, int number, int row, int column){
	
		
// We'll take the row passed in and take the modulus using 3 as the value. 
// We apply this to both rows and columns. This gives us the accurate location
// within our local box.
	
		int  localBoxRow = row - row % 3; 
		int  localBoxColumn = column - column % 3; 
		
// We created a "nested" for loop to find location for each of our 3 X 3 grids.
// This will loop us through the exact 3 X 3 grid we target. We add + 3 in our
// conditional statement so that iterates through the three rows (and columns) of
// our local grid
		
		for (int i = localBoxRow; i < localBoxRow + 3; i++) {
			for(int j = localBoxColumn; j < localBoxColumn + 3; j++) 
			{
				if(board[i][j] == number) {
			
			return true;
				}
			}
			
		}
			return false;
	}

// Another convenient method created that checks the row, column, and our local box.
// We can just call it to see if a certain placement is valid.

	private static boolean isValidPlacement(int [][] board, int number, int row, int column)
	{
		return !isNumberInRow(board, number, row) &&
				!isNumberInColumn(board, number, column) &&
				!isNumberInBox(board, number, row, column);
	}
	
// Method which traverses our board to fill out valid solutions to solve 
	
	private static boolean solveBoard (int [][] board) 
	{
		for (int row = 0; row < GRID_SIZE; row++) {
				for(int column = 0; column < GRID_SIZE; column++) 
				{
					
// Because the blank spots on our puzzle are filled with the value of '0', 
// we're utilizing this if statement to command the program to take action 
// when one of these blank spots (0) is encountered, which would be to try a 
// number 1 - 9 to see if it's valid
					
					if (board[row][column]==0) {
						for(int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) 
						{
							if(isValidPlacement(board, numberToTry, row, column)) {
								board[row][column] = numberToTry;
								
// Here we will recursively call our solveBoard method in order to traverse the grid
// and place viable solutions in each empty space available
								
								if (solveBoard(board))
								{
									return true;
								}
								
// If recursive solveBoard call returns false, we want to clear out the 
// last tried placement with an else statement
									
								else {
									
									board[row][column] = 0;
								}
							}
						}
						
						return false;
					}
				}
		}
						return true;
	}
}
