package search;
/**
 * CS5011-A2: Search-Rescue Simulation.
 * 
 * @author bl41
 *
 */

import java.util.LinkedList;

public class Node {
	private int x;
	private int y;
	public char value;
	public int path_cost;
	Node uChild;
	Node dChild;
	Node lChild;
	Node rChild;
	Node parent;

	/**
	 * Node constructor.
	 * 
	 * @param x
	 *            X coordinate
	 * @param y
	 *            Y coordinate
	 * @param value
	 *            Node value
	 */
	public Node(int x, int y, char value) {
		this.x = x;
		this.y = y;
		this.value = value;
	}

	/**
	 * Get value of node.
	 * 
	 * @return Return value
	 */
	public char getValue() {
		return value;
	}

	/**
	 * Get x coordinate of node.
	 * 
	 * @return Return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Get y coordinate of node
	 * 
	 * @return Return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Get Parent node.
	 * 
	 * @return Return parent node
	 */
	public Node getParent() {
		return parent;
	}

	/**
	 * Get location of node.
	 * 
	 * @return Return location consist of x and y
	 */
	public int[] getLocation() {
		int[] location = { x, y };
		return location;
	}

	/**
	 * Get the child nodes, make an order of down, right, up and left.
	 */
	public LinkedList<Node> getChildren() {
		LinkedList<Node> childNodes = new LinkedList<>();
		if (this.dChild != null) {
			childNodes.add(dChild);
		}
		if (this.rChild != null) {
			childNodes.add(rChild);
		}
		if (this.uChild != null) {
			childNodes.add(uChild);
		}
		if (this.lChild != null) {
			childNodes.add(lChild);
		}
		return childNodes;
	}

	/**
	 * Get the child nodes, make an order reverse the front search order to
	 * ensure they can meet
	 */
	public LinkedList<Node> getBackChildren() {
		LinkedList<Node> childNodes = new LinkedList<>();
		if (this.lChild != null) {
			childNodes.add(lChild);
		}
		if (this.uChild != null) {
			childNodes.add(uChild);
		}
		if (this.rChild != null) {
			childNodes.add(rChild);
		}
		if (this.dChild != null) {
			childNodes.add(dChild);
		}
		return childNodes;
	}

	/**
	 * Get Manhattan Distance between current node and goal node.
	 * 
	 * @param goal
	 *            The goal node
	 * @return Return Manhattan distance
	 */
	public int ManhattanDis(Node goal) {
		return Math.abs(goal.getX() - this.x) + Math.abs(goal.getY() - this.y);
	}

	/**
	 * Get Euclidian distance between current node and goal node.
	 * 
	 * @param goal
	 *            The goal node
	 * @return Return Euclidian distance
	 */
	public double EuclidianDis(Node goal) {
		return Math.sqrt(Math.pow((goal.getX() - this.x), 2) + Math.pow((goal.getY() - this.y), 2));
	}
}
