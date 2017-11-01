package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public abstract class SearchMethod {
	/**
	 * A boolean type variable to check if the search succeed.
	 */
	public static boolean find = false;
	public static Node front;
	public static Node back;

	/**
	 * Check if node has been explored.
	 * 
	 * @param check
	 *            A list of explored nodes
	 * @param node
	 *            The node to check
	 * @return Return true if the node has not been explored
	 */
	public static boolean notExplored(ArrayList<Node> check, Node node) {
		for (int i = 0; i < check.size(); i++) {
			Node n = check.get(i);
			if (Arrays.equals(n.getLocation(), node.getLocation())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Check if node has been given to a parent.
	 * 
	 * @param check
	 *            A list of nodes that have been given to a parent
	 * @param node
	 *            The node to check
	 * @return Return true if the node has not been given to a parent
	 */
	public static boolean existChild(ArrayList<Node> check, Node node) {
		for (int i = 0; i < check.size(); i++) {
			Node n = check.get(i);
			if (Arrays.equals(n.getLocation(), node.getLocation())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Method to define child nodes for current node.
	 * 
	 * @param current
	 *            Current node
	 * @param map
	 *            Map to search
	 * @param childs
	 *            Array list that stored nodes who has already become a child
	 * @param explored
	 *            Array list that stored nodes who has already been explored
	 */
	public static void findChild(Node current, char[][] map, ArrayList<Node> childs, ArrayList<Node> explored) {
		// Get child nodes and record is path cost
		// Give the potential child nodes
		// Check if the potential child node is the parent node or has
		// been explored
		// If so, get rid of that child node
		if (current.getX() - 1 >= 0 && map[current.getX() - 1][current.getY()] != 'X') {
			current.uChild = new Node(current.getX() - 1, current.getY(), map[current.getX() - 1][current.getY()]);
			if (Arrays.equals(current.uChild.getLocation(), current.parent.getLocation())
					|| !existChild(childs, current.uChild) || !notExplored(explored, current.uChild)) {
				current.uChild = null;
			} else {
				current.uChild.parent = current;
			}
		}
		if (current.getX() + 1 < 10 && map[current.getX() + 1][current.getY()] != 'X') {
			current.dChild = new Node(current.getX() + 1, current.getY(), map[current.getX() + 1][current.getY()]);
			if (Arrays.equals(current.dChild.getLocation(), current.parent.getLocation())
					|| !existChild(childs, current.dChild) || !notExplored(explored, current.dChild)) {
				current.dChild = null;
			} else {
				current.dChild.parent = current;
			}
		}
		if (current.getY() - 1 >= 0 && map[current.getX()][current.getY() - 1] != 'X') {
			current.lChild = new Node(current.getX(), current.getY() - 1, map[current.getX()][current.getY() - 1]);
			if (Arrays.equals(current.lChild.getLocation(), current.parent.getLocation())
					|| !existChild(childs, current.lChild) || !notExplored(explored, current.lChild)) {
				current.lChild = null;
			} else {
				current.lChild.parent = current;
			}
		}
		if (current.getY() + 1 < 10 && map[current.getX()][current.getY() + 1] != 'X') {
			current.rChild = new Node(current.getX(), current.getY() + 1, map[current.getX()][current.getY() + 1]);
			if (Arrays.equals(current.rChild.getLocation(), current.parent.getLocation())
					|| !existChild(childs, current.rChild) || !notExplored(explored, current.rChild)) {
				current.rChild = null;
			} else {
				current.rChild.parent = current;
			}
		}
	}

	/**
	 * Method to print output (path and explored position) of search.
	 * 
	 * @param current
	 *            Current node
	 * @param start
	 *            Start node
	 * @param goal
	 *            Target node
	 * @param explored
	 *            Array list that stored all explored nodes
	 */
	public static void printOutput(Node current, Node back, Node start, Node goal, ArrayList<Node> explored,
			ArrayList<Node> back_explored) {
		Node path = current;
		Node backpath = back;
		ArrayList<int[]> Path = new ArrayList<>();
		Path.add(path.getLocation()); // Get goal position
		// Put path of coordinates in to an array list of output
		while (path.getParent() != null) {
			Path.add(path.parent.getLocation());
			path = path.parent;
			if (Arrays.equals(path.getLocation(), start.getLocation())) {
				break;
			}
		}
		Collections.reverse(Path);
		while (backpath.getParent() != null) {
			Path.add(backpath.parent.getLocation());
			backpath = backpath.parent;
			if (Arrays.equals(backpath.getLocation(), goal.getLocation())) {
				break;
			}
		}
		// Print the path of coordinates
		System.out.println("Find path from " + start.getValue() + " to " + goal.getValue());
		for (int i = 0; i < Path.size(); i++) {
			System.out.print(Arrays.toString(Path.get(i)));
		}
		System.out.println("\nPath length: " + (Path.size() - 1));
		explored.remove(0); // Get rid of first node as it is a
							// redundancy
		// Show explored times
		System.out.println("Explored " + explored.size() + " position");
		for (int j = 0; j < explored.size(); j++) {
			System.out.print(Arrays.toString(explored.get(j).getLocation()));
		}
		System.out.println();
		back_explored.remove(0);
		System.out.println("Back explored " + back_explored.size() + " position");
		for (int j = 0; j < back_explored.size(); j++) {
			System.out.print(Arrays.toString(back_explored.get(j).getLocation()));
		}
		System.out.println("\n");
	}

	/**
	 * Method to print the frontier for breath-first search.
	 * 
	 * @param frontier
	 *            A queue of frontier to print
	 */
	public static void showBFSFrontier(Queue<Node> frontier) {
		StringBuffer str = new StringBuffer();
		str.append("Frontier: {");
		for (Node n : frontier) {
			str.append(Arrays.toString(n.getLocation()) + ", ");
		}
		str.replace(str.length() - 2, str.length(), "}");
		System.out.println(str);
	}

	/**
	 * Method to print the frontier for depth-first search.
	 * 
	 * @param frontier
	 *            A queue of frontier to print
	 */
	public static void showDFSFrontier(Stack<Node> frontier) {
		StringBuffer str = new StringBuffer();
		str.append("Frontier: {");
		for (Node n : frontier) {
			str.append(Arrays.toString(n.getLocation()) + ", ");
		}
		str.replace(str.length() - 2, str.length(), "}");
		System.out.println(str);
	}

	/**
	 * Method to print frontiers.
	 * 
	 * @param frontier
	 *            A priority queue of frontiers to print
	 */
	public static void showFrontier(PriorityQueue<Node> frontier) {
		StringBuffer str = new StringBuffer();
		str.append("Frontier: {");
		for (Node n : frontier) {
			str.append(Arrays.toString(n.getLocation()) + ", ");
		}
		str.replace(str.length() - 2, str.length(), "}");
		System.out.println(str);
	}

	/**
	 * Method to show explored node.
	 * 
	 * @param explored
	 *            The array list of explored to print
	 */
	public static void showExplored(ArrayList<Node> explored) {
		StringBuffer str = new StringBuffer();
		str.append("Explored: {");
		for (int i = 1; i < explored.size(); i++) {
			str.append(Arrays.toString(explored.get(i).getLocation()) + ", ");
		}
		str.replace(str.length() - 2, str.length(), "}");
		System.out.println(str);
	}

	/**
	 * Method for Breath-first search to check if new generated child nodes have
	 * been explored by the other direction of search.
	 * 
	 * @param frontier
	 *            Front direction search frontiers
	 * @param back_frontier
	 *            Back direction search frontiers
	 * @param explored
	 *            Array List of explored nodes of front direction
	 * @param back_explored
	 *            Array List of explored nodes of back direction
	 * @return Return true if new generated node have been explored by the other
	 *         direction
	 */
	public static boolean bidirectionalCheck(Queue<Node> frontier, Queue<Node> back_frontier, ArrayList<Node> explored,
			ArrayList<Node> back_explored) {
		// Set array list to store location of explored nodes
		ArrayList<int[]> fexplored = new ArrayList<>();
		ArrayList<int[]> bexplored = new ArrayList<>();
		for (Node fe : explored) {
			fexplored.add(fe.getLocation());
		}
		for (Node be : back_explored) {
			bexplored.add(be.getLocation());
		}
		// Check if nodes in front direction frontier has been explored by back
		// direction
		for (Node node : frontier) {
			for (int i = 0; i < bexplored.size(); i++) {
				if (Arrays.equals(node.getLocation(), bexplored.get(i))) {
					front = node;
					for (Node bn : back_explored) {
						if (Arrays.equals(front.getLocation(), bn.getLocation())) {
							back = bn;
						}
					}
					return true;
				}
			}
		}
		// Check if nodes in back direction frontier has been explored by front
		// direction
		for (Node node : back_frontier) {
			for (int j = 0; j < fexplored.size(); j++) {
				if (Arrays.equals(node.getLocation(), fexplored.get(j))) {
					back = node;
					for (Node fn : explored) {
						if (Arrays.equals(back.getLocation(), fn.getLocation())) {
							front = fn;
						}
					}
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Method for Depth-first search to check if new generated child nodes have
	 * been explored by the other direction of search.
	 * 
	 * @param frontier
	 *            Front direction search frontiers
	 * @param back_frontier
	 *            Back direction search frontiers
	 * @param explored
	 *            Array List of explored nodes of front direction
	 * @param back_explored
	 *            Array List of explored nodes of back direction
	 * @return Return true if new generated node have been explored by the other
	 *         direction
	 */
	public static boolean bidirectionalDCheck(Stack<Node> frontier, Stack<Node> back_frontier, ArrayList<Node> explored,
			ArrayList<Node> back_explored) {
		// Set array list to store location of explored nodes and frontiers
		ArrayList<int[]> fexplored = new ArrayList<>();
		ArrayList<int[]> bexplored = new ArrayList<>();
		ArrayList<int[]> fft = new ArrayList<>();
		ArrayList<int[]> bft = new ArrayList<>();
		for (Node fe : explored) {
			fexplored.add(fe.getLocation());
		}
		for (Node be : back_explored) {
			bexplored.add(be.getLocation());
		}
		for (Node ft : frontier) {
			fft.add(ft.getLocation());
		}
		for (Node bf : back_frontier) {
			bft.add(bf.getLocation());
		}
		// Check if nodes in front direction frontier has been explored by back
		// direction
		for (Node node : frontier) {
			for (int i = 0; i < bexplored.size(); i++) {
				if (Arrays.equals(node.getLocation(), bexplored.get(i))) {
					front = node;
					for (Node bn : back_explored) {
						if (Arrays.equals(front.getLocation(), bn.getLocation())) {
							back = bn;
						}
					}
					return true;
				}
			}
		}
		// Check if nodes in back direction frontier has been explored by front
		// direction
		for (Node node : back_frontier) {
			for (int j = 0; j < fexplored.size(); j++) {
				if (Arrays.equals(node.getLocation(), fexplored.get(j))) {
					back = node;
					for (Node fn : explored) {
						if (Arrays.equals(back.getLocation(), fn.getLocation())) {
							front = fn;
						}
					}
					return true;
				}
			}
		}
		// Check if node in both frontiers has a match
		for(Node node: frontier){
			for(int k = 0; k < bft.size(); k++){
				if(Arrays.equals(node.getLocation(), bft.get(k))){
					front = node;
					for(Node bn: back_frontier){
						if (Arrays.equals(front.getLocation(), bn.getLocation())) {
							back = bn;
						}
					}
					return true;
				}
			}
		}
		return false;
	}
}
