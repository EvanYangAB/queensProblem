package search;
import java.util.*;
import search.*;

public class initializer {
	public static void initialize() throws Exception{
		Disboard emptyBoard = new Disboard(8);
		Node newNode = new Node(0, emptyBoard);
		Node.addToQue(newNode);
		System.out.println("prep phase done");
		Node.operator();
	}

	public static void main(String[] args) throws Exception {
		initialize();	
	}
}
