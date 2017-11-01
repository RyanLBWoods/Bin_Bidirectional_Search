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

		Queue<Node> frontier = new LinkedList<>();
		Queue<Node> back_frontier = new LinkedList<>();
		// Record nodes that already become child
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
			showBFSFrontier(frontier);
			explored.add(current);
			showExplored(explored);
			if (!back.getBackChildren().isEmpty()) {
				back_frontier.addAll(back.getBackChildren());
				back_childs.addAll(back.getBackChildren());
			}
			System.out.println("Back current node: " + Arrays.toString(back.getLocation()));
			showBFSFrontier(back_frontier);
			back_explored.add(back);
			showExplored(back_explored);
			System.out.println();
			// Check if there is any match
			if (bidirectionalCheck(frontier, back_frontier, explored, back_explored)) {
				find = true;
				System.out.println("Match!!!");
				Node front = SearchMethod.front;
				Node b = SearchMethod.back;
				printOutput(front, b, start, goal, explored, back_explored);
				break;
			}
		}
	}
}
