package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BestFirstSearch extends SearchMethod {
	/**
	 * Best-first algorithm
	 * 
	 * @param map
	 *            The map to search
	 * @param start
	 *            The start node
	 * @param goal
	 *            The target node
	 */
	public static void BestFS(char[][] map, Node start, Node goal) {
		// Set comparator for priority queue
		// f(n) = Manhattan Distance
		Comparator<Node> cmp = new Comparator<Node>() {
			public int compare(Node n1, Node n2) {
				if (n1.ManhattanDis(goal) > n2.ManhattanDis(goal)) {
					return 1;
				} else if (n1.ManhattanDis(goal) < n2.ManhattanDis(goal)) {
					return -1;
				}
				return 0;
			}
		};
		
		Comparator<Node> cmp2 = new Comparator<Node>() {
			public int compare(Node n1, Node n2) {
				if (n1.ManhattanDis(start) > n2.ManhattanDis(start)) {
					return 1;
				} else if (n1.ManhattanDis(start) < n2.ManhattanDis(start)) {
					return -1;
				}
				return 0;
			}
		};

		PriorityQueue<Node> frontier = new PriorityQueue<Node>(cmp);
		PriorityQueue<Node> back_frontier = new PriorityQueue<Node>(cmp2);
		// Record nodes that have already become a child
		ArrayList<Node> explored = new ArrayList<>();
		ArrayList<Node> back_explored = new ArrayList<>();
		// Record nodes that have been explored
		ArrayList<Node> childs = new ArrayList<>();
		ArrayList<Node> back_childs = new ArrayList<>();
		// Initialise process
		start.parent = start;
		goal.parent = goal;
		frontier.add(start);
		back_frontier.add(goal);
		// Add start to explored list to avoid null pointer exception
		explored.add(start);
		back_explored.add(goal);
		// Search
		while (!frontier.isEmpty() && !back_frontier.isEmpty()) {
			Node current = frontier.remove();
			Node back = back_frontier.remove();
			System.out.println("Current node: " + Arrays.toString(current.getLocation()));
			findChild(current, map, childs, explored);
			findChild(back, map, back_childs, back_explored);
			// Insert nodes to frontier
			if (!current.getChildren().isEmpty()) {
				frontier.addAll(current.getChildren());
				childs.addAll(current.getChildren());
			}
			showFrontier(frontier);
			explored.add(current);
			showExplored(explored);
			if(!back.getBackChildren().isEmpty()){
				back_frontier.addAll(back.getBackChildren());
				back_childs.addAll(back.getBackChildren());
			}
			System.out.println("Back current node: " + Arrays.toString(back.getLocation()));
			showFrontier(back_frontier);
			back_explored.add(back);
			showExplored(back_explored);
			System.out.println();
			// Check if there is any match
			if (!frontier.isEmpty() && !back_frontier.isEmpty()) {
				if (Arrays.equals(current.getLocation(), back_frontier.peek().getLocation())) {
					find = true;
					System.out.println("Match!!!");
					printOutput(current, back_frontier.peek(), start, goal, explored, back_explored);
					break;
				}
				if (bidirectionalCheck(frontier, back_frontier, explored, back_explored)) {
					find = true;
					System.out.println("Match!!!");
					Node front = SearchMethod.front;
					Node b = SearchMethod.back;
					printOutput(front, b, start, goal, explored, back_explored);
					break;
				}
			} else {
				System.out.println("Search failed");
				break;
			}
		}
	}
}
