package search;
import java.util.*;

public class Disboard {
	int size;
	int numCrossAfterLastMove = 0;
	// this array is only for backtracking purposes
	// -1 represents empty;
	int[] queen; 
	// the board that contains the logical existance 
	// of THE BOARD
	Status[][] board;

	public enum Status{
		EMPTY, QUEEN, CROSS
	}

	// empty board
	public Disboard(int boardSize){
		int size = boardSize;
		queen = new int[size];
		board = new Status[size][size];
		Arrays.fill(queen, -1);
		Arrays.fill(board, Status.EMPTY);
	}

	// the final function for putting the queen into the array[][]
	// col starts at 0
	public int putQueen(int col, int row){
		if(col < size && row < size){
			queen[col] = row;
			//set the queen

			return 1;
		}
		else
			return 0;
	}

	// col starts at 0
	// predicate h function
	// time function O(n)
	public int assumeQueenAt(int col, int row){
		int count = 0;
		for(int i = col + 1; i < size; i++)
			if(board[row][i] == Status.EMPTY)
				count += 1;
		for(int i = 1; col + i < size && row + i < size; i++)
			if(board[row + i][col + i] == Status.EMPTY)
				count += 1;
		for(int i = 1; col + i < size && row - i >= 0; i++)
			if(board[row - i][col + i] == Status.EMPTY)
				count += 1;
		return count;
	}

	public int getCrossUpdate(){
		return numCrossAfterLastMove;
	}
}
