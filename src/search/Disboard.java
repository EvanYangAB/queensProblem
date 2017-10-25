package search;
import java.util.*;

public class Disboard {
	int size;
	int numCrossAfterLastMove = 0;

	// the board that contains the logical existance 
	// of THE BOARD
	Status[][] board;

	public enum Status{
		EMPTY, QUEEN, CROSS
	}

	// empty board
	public Disboard(int boardSize){
		size = boardSize;
		board = new Status[size][size];
		for(int i = 0; i< size; i++)
			Arrays.fill(board[i], Status.EMPTY);
	}

	// return a new disboard with a queen
	// placed at row, col
	public Disboard putQueen(int row, int col) throws Exception{
		numCrossAfterLastMove = 0;
		Disboard newBoard = new Disboard(size);
		newBoard.board = Arrays.stream(board)
						.map((Status[] rows) -> rows.clone())
						.toArray(Status[][]::new);
		if(col < size && row < size && board[row][col] == Status.EMPTY){
			// set the queen
			newBoard.board[row][col] = Status.QUEEN;
			for(int i = col + 1; i < size; i++)
				if(newBoard.board[row][i] != Status.CROSS){
					newBoard.board[row][i] = Status.CROSS;
					numCrossAfterLastMove += 1;
				}
			for(int i = 1; col + i < size && row + i < size; i++)
				if(newBoard.board[row + i][col + i] != Status.CROSS){
					newBoard.board[row + i][col + i] = Status.CROSS;
					numCrossAfterLastMove += 1;
				}
			for(int i = 1; col + i < size && row - i >= 0; i++)
				if(newBoard.board[row - i][col + i] != Status.CROSS){
					newBoard.board[row - i][col + i] = Status.CROSS;
					numCrossAfterLastMove += 1;
				}
			return newBoard;
		}
		else{
			System.out.println("size:" + size + " row:" + (row + 1) + " col:" + (col + 1));
			System.out.println(this);
			throw new Exception("wrong index number in putting queen");
		}
	}

	public Status at(int row, int col){
		return board[row][col];
	}

	// indexes starts at 0
	// predicate h function
	// time function O(n)
	// returns the number of crosses that if a queen placed at
	// 
	public int assumeQueenAt(int row, int col){
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

	public String toString(){
		String result = new String();
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++)
				switch (board[i][j]) {
					case EMPTY:
						result += "E|";
						break;
					case QUEEN:
						result += "Q|";
						break;
					case CROSS:
						result += "X|";
						break;
					default:
						result += "WTF is this?";
				}
			result += "\n";
		}
		return result;
	}
}
