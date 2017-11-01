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

		PriorityQueue<Node> frontier = new PriorityQueue<Node>(cmp);
		// Record nodes that have already become a child
		ArrayList<Node> explored = new ArrayList<>();
		// Record nodes that have been explored
		ArrayList<Node> childs = new ArrayList<>();
		// Initialise process
		start.parent = start;
		frontier.add(start);
		// Add start to explored list to avoid null pointer exception
		explored.add(start);

		while (!frontier.isEmpty()) {
			Node current = frontier.remove();
			System.out.println("Current node: " + Arrays.toString(current.getLocation()));
			if (current.getValue() == goal.getValue()) {
				find = true;
				printOutput(current, start, goal, explored);
				break;
			} else {
				findChild(current, map, childs, explored);
				// Insert nodes to frontier
				if (!current.getChildren().isEmpty()) {
					frontier.addAll(current.getChildren());
					childs.addAll(current.getChildren());
				}
				showFrontier(frontier);
				explored.add(current);
				showExplored(explored);
				System.out.println();
			}
		}
	}
}
