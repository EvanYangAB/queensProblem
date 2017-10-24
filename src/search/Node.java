package search;
import search.*;
import search.Disboard.Status;

import java.util.*;

public class Node {
	int depth;
	Disboard grid;

	// these two variables is for the purpose
	int queenRow;

	static final ArrayList<Node> queue = new ArrayList<>();

	public Node(int depthIn, Disboard gridIn, int rowIn){
		this(depthIn, gridIn);
		queenRow = rowIn;
	}

	public Node(int depthIn, Disboard gridIn){
		depth = depthIn + 1;
		grid = gridIn;
		queenRow = -1;
	}

	public static void operator() throws Exception{
		while(!queue.isEmpty()){
			Node current = queue.get(0);
			queue.remove(0);
			Disboard newBoard;
			if(current.queenRow >= 0)
				newBoard = current.grid.putQueen(current.queenRow, current.depth - 1);
			else
				newBoard = current.grid;
			// goal check
			if(current.depth == newBoard.size){
				System.out.println(newBoard);
				return;
			}
			for(int i = 0; i <current.grid.size; i++)
				if(newBoard.at(i, current.depth) == Status.EMPTY)
					queue.add(new Node(current.depth, newBoard, i));
			
			queue.sort(Node::depthThenCross);
		}
	}

	// sort method
	static int depthThenCross(Node n1, Node n2){
		return n1.depth - n2.depth == 0 ?
			n1.grid.assumeQueenAt(n1.queenRow, n1.depth) - 
			n2.grid.assumeQueenAt(n2.queenRow, n2.depth) :
			n1.depth - n2.depth;
	}

	public static void addToQue(Node n){
		queue.add(n);
	}
}
