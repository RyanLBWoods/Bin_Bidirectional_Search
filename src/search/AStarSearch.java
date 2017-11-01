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
		Comparator<Node> cmp2 = new Comparator<Node>() {
			public int compare(Node n1, Node n2) {
				if (n1.path_cost + n1.EuclidianDis(start) > n2.path_cost + n2.EuclidianDis(start)) {
					return 1;
				}
				if (n1.path_cost + n1.EuclidianDis(start) < n2.path_cost + n2.EuclidianDis(start)) {
					return -1;
				}
				return 0;
			}
		};

		PriorityQueue<Node> frontier = new PriorityQueue<Node>(cmp);
		PriorityQueue<Node> back_frontier = new PriorityQueue<Node>(cmp2);
		// Record nodes that have already become a child
		ArrayList<Node> childs = new ArrayList<>();
		ArrayList<Node> back_childs = new ArrayList<>();
		// Record nodes that have been explored
		ArrayList<Node> explored = new ArrayList<>();
		ArrayList<Node> back_explored = new ArrayList<>();
		// Initialise process
		start.parent = start;
		goal.parent = goal;
		frontier.add(start);
		back_frontier.add(goal);
		// Add start to explored list to avoid null pointer exception
		explored.add(start);
		back_explored.add(goal);

		while (!frontier.isEmpty() && !back_frontier.isEmpty()) {
			Node current = frontier.remove();
			Node back = back_frontier.remove();
			System.out.println("Current node: " + Arrays.toString(current.getLocation()));
			if (Arrays.equals(current.getLocation(), back.getLocation())) {
				find = true;
				System.out.println("match");
				printOutput(current, back, start, goal, explored, back_explored);
				break;
			} else {
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
				if (!back.getBackChildren().isEmpty()) {
					back_frontier.addAll(back.getBackChildren());
					back_childs.addAll(back.getBackChildren());
				}
				System.out.println("Back current node: " + Arrays.toString(back.getLocation()));
				showFrontier(back_frontier);
				back_explored.add(back);
				showExplored(back_explored);
				System.out.println();
			}
			// Check if there is any match
			if (Arrays.equals(current.getLocation(), back_frontier.peek().getLocation())) {
				find = true;
				System.out.println("Match!!!");
				printOutput(current, back_frontier.peek(), start, goal, explored, back_explored);
				break;
			}
		}
	}
}
