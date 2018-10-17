package planning;

import java.util.*;
import examples.AssemblyLine;

public class Main {

	public static void main(String[] args) {

		AssemblyLine universe = new AssemblyLine();
		ArrayList<State> goals = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			State goalState = universe.generateGoalState();
			goals.add(goalState);
		}
		PlanningProblemWithCost pb = new PlanningProblemWithCost(universe.getInitialState(), goals, universe.getAllActions() );
		Stack<Action> plan = new Stack<>();
		ArrayList<State> closed = new ArrayList<>();
		/*Stack<Action> actions_dfs_iter = pb.dfsIteratif(universe.getInitialState(), plan, closed);
		System.out.println("\nDFSIteratif: "+actions_dfs_iter);
		Stack<Action> actions_dfs = pb.dfs(universe.getInitialState(), plan, closed,10);
		System.out.println("\nDFS: "+actions_dfs); //SI c'est null alors c'est qu'il a pas trouvé
		Queue<Action> actions_bfs = pb.bfs();
		System.out.println("\nBFS: "+actions_bfs);
		Stack<Action> actions_dijkstra = pb.dijkstra();
		System.out.println("\nDijkstra: "+actions_dijkstra);*/
		Queue<Action> actions_astar_simple = pb.aStar(10);
		System.out.println("\nA*: "+actions_astar_simple);
		pb.setHeuristic(new InformedHeuristic());
		Queue<Action> actions_astar_informed = pb.aStar(10);
		System.out.println("\nA*: "+actions_astar_informed);
		//Queue<Action> actions_beam = pb.iterativeBeamSearch(3);
		//System.out.println("\nIterative beam search: "+actions_beam);

	}
}
