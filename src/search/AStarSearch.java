package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AStarSearch extends SearchMethod {
	/**
	 * A* Search
	 * 
	 * @param map
	 *            The map to search
	 * @param start
	 *            The start node
	 * @param goal
	 *            The target node
	 */
	public static void AStar(char[][] map, Node start, Node goal) {
		// Set comparator for priority queue
		// f(n) = Euclidian distance + path cost
		Comparator<Node> cmp = new Comparator<Node>() {
			public int compare(Node n1, Node n2) {
				if (n1.path_cost + n1.EuclidianDis(goal) > n2.path_cost + n2.EuclidianDis(goal)) {
					return 1;
				}
				if (n1.path_cost + n1.EuclidianDis(goal) < n2.path_cost + n2.EuclidianDis(goal)) {
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
		start.path_cost = 0;
		frontier.add(start);
		// Add start to explored list to avoid null pointer exception
		explored.add(start);
		// Search from frontier
		while (!frontier.isEmpty()) {
			Node current = frontier.remove();
			System.out.println("Current node: " + Arrays.toString(current.getLocation()));
			// Check if reach the goal
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
