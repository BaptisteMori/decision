package planning;

import java.util.*;
import examples.AssemblyLine;

public class Main {

	public static void main(String[] args) {

		AssemblyLine universe = new AssemblyLine();
		System.out.println(universe.getAllActions().get(0).is_applicable(universe.getInitialState()));
		State goalState = universe.generateGoalState();
		ArrayList<State> goals = new ArrayList<>();
		goals.add(goalState);
		ArrayList<Action> actions = new ArrayList<>();
		//actions.addAll(universe.getAllActions());
		PlanningProblem pb = new PlanningProblem(universe.getInitialState(), goals, actions );
		Stack<Action> plan = new Stack<>();
		ArrayList<State> closed = new ArrayList<>();
		Stack<Action> actions_dfs = pb.dfs(universe.getInitialState(), plan, closed); //OK
		System.out.println("\nDFS: "+actions_dfs);
		Stack<Action> actions_bfs = pb.bfs();
		System.out.println("\nBFS: "+actions_bfs);

	}
}
