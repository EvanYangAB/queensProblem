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

	public int putQueen(int col, int row){
		if(col < size && row < size){
			queen[col] = row;


			return 1;
		}
		else
			return 0;
	}

	public int getCrossUpdate(){
		return numCrossAfterLastMove;
	}
}
