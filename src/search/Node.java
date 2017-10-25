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
		depth = depthIn;
		grid = gridIn;
		queenRow = 0;
	}

	public static void operator() throws Exception{
		System.out.println("starting operation");
		while(!queue.isEmpty()){
			System.out.println("moving a node from stack");
			Node current = queue.get(0);
			queue.remove(0);
			Disboard curBoard = current.grid;
			System.out.println(curBoard);
			// goal check
			if(current.depth == curBoard.size){
				System.out.println("goal found");
				System.out.println(curBoard);
				return;
			}
			System.out.println("generating next level nodes");
			System.out.println("at depth:"+ current.depth);
			for(int i = 0; i <current.grid.size; i++)
				if(curBoard.at(i, current.depth) == Status.EMPTY)
					queue.add(new Node(current.depth + 1, current.grid.putQueen(i, current.depth), i));

			queue.sort(Node::depthThenCross);
		}
	}

	// sort method
	static int depthThenCross(Node n1, Node n2){
		return n1.depth - n2.depth == 0 ?
			n1.grid.getCrossUpdate() - 
			n2.grid.getCrossUpdate() :
			n2.depth - n1.depth;
	}

	public static void addToQue(Node n){
		queue.add(n);
	}
}
