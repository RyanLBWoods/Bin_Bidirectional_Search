package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BreathFirstSearch extends SearchMethod {
	/**
	 * Method of breath-first search.
	 * 
	 * @param map
	 *            The map to search
	 * @param start
	 *            The start node
	 * @param goal
	 *            The aim node
	 */
	public static void BFSearch(char[][] map, Node start, Node goal) {

		Queue<Node> q = new LinkedList<>(); // frontier
		// Record nodes that already become child
		ArrayList<Node> childs = new ArrayList<>();
		// Record nodes that have been explored
		ArrayList<Node> explored = new ArrayList<>();

		// Initialise process
		start.parent = start;
		q.add(start);
		// Add start to explored list to avoid null pointer exception
		explored.add(start);
		// Search
		while (!q.isEmpty()) {
			Node current = q.remove();
			System.out.println("Current node: " + Arrays.toString(current.getLocation()));
			if (current.getValue() == goal.getValue()) {
				find = true;
				printOutput(current, start, goal, explored);
				break;
			} else {
				findChild(current, map, childs, explored);
				// Insert nodes to frontier
				if (!current.getChildren().isEmpty()) {
					q.addAll(current.getChildren());
					childs.addAll(current.getChildren());
				}
				showBFSFrontier(q);
				explored.add(current);
				showExplored(explored);
				System.out.println();
			}
		}
	}
}
