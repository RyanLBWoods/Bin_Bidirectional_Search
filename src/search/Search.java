package search;

/**
 * CS5011-A2: Search-Rescue Simulation.
 * 
 * @author bl41
 *
 */
public class Search {

	public static Node start = null;
	public static Node bob = null;
	public static Node goal = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			int map_num = Integer.valueOf(args[0]);
			char[][] map = Map.getMap(map_num);
			String search_algorithm = args[1];
			// Get map information
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					if (map[i][j] == 'I') {
						start = new Node(i, j, map[i][j]);
					}
					if (map[i][j] == 'B') {
						bob = new Node(i, j, map[i][j]);
					}
					if (map[i][j] == 'G') {
						goal = new Node(i, j, map[i][j]);
					}
				}
			}
			// Search
			switch(search_algorithm){
				default:
					System.out.println("Usage: java -jar Search1.jar map_number(1 - 6) search_Algorithm(BFS or DFS)");
					break;
				case "BFS":
					// Find Bob
					BreathFirstSearch.BFSearch(map, start, bob);
					if(SearchMethod.find == false){
						System.out.println("Can not find path to Bob");
						System.exit(0);
					}
					// Find Goal
					SearchMethod.find = false;
					BreathFirstSearch.BFSearch(map, bob, goal);
					if(SearchMethod.find == false){
						System.out.println("Can not find path to Goal");
						System.exit(0);
					}
					break;
				case "DFS":
					// Find Bob
					DepthFirstSearch.DFSearch(map, start, bob);
					if(SearchMethod.find == false){
						System.out.println("Can not find path to Bob");
						System.exit(0);
					}
					// Find Goal
					SearchMethod.find = false;
					DepthFirstSearch.DFSearch(map, bob, goal);
					if(SearchMethod.find == false){
						System.out.println("Can not find path to Goal");
						System.exit(0);
					}
					break;
			}
		} catch (Exception e) {
			System.out.println("Usage: java -jar Search1.jar map_number(1 - 6) search_Algorithm(BFS or DFS)");
		}
	}

}
