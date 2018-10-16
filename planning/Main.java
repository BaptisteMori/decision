package planning;

import java.util.*;
import examples.AssemblyLine;

public class Main {

	public static void main(String[] args) {

		AssemblyLine universe = new AssemblyLine();
		State goalState = universe.generateGoalState();
		ArrayList<State> goals = new ArrayList<>();
		goals.add(goalState);
		PlanningProblemWithCost pb = new PlanningProblemWithCost(universe.getInitialState(), goals, universe.getAllActions() );
		Stack<Action> plan = new Stack<>();
		ArrayList<State> closed = new ArrayList<>();
		Stack<Action> actions_dfs = pb.dfs(universe.getInitialState(), plan, closed);
		System.out.println("\nDFS: "+actions_dfs);
		Stack<Action> actions_bfs = pb.bfs();
		System.out.println("\nBFS: "+actions_bfs);
		Stack<Action> actions_dijkstra = pb.dijkstra();
		System.out.println("\nDijkstra: "+actions_dijkstra);
		//Stack<Action> actions_astar = pb.aStar();
		//System.out.println("\nA*: "+actions_astar);
	}
}
