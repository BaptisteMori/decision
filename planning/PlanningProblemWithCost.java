package planning;

import java.util.*;
import examples.AssemblyLine;

public class PlanningProblemWithCost extends PlanningProblem {

	private ArrayList<Action> cost1;
	private ArrayList<Action> cost2;

	public PlanningProblemWithCost(State init, ArrayList<State> goals, ArrayList<Action> actions) {
		super(init,goals,actions);
		AssemblyLine al = new AssemblyLine();
		this.cost1 = new ArrayList<Action>(al.getParallelWheelInstallActions());
		cost1.addAll(al.getLargePaintActions());
		this.cost2 = new ArrayList<Action>(al.getBasicInstallActions());
		cost2.addAll(al.getPrecisePaintActions());

	}

	public double cost(Action a) {
		if (this.cost2.contains(a)) {
			return 2.;
		}
		if (this.cost1.contains(a)) {
			return 1.;
		}
		throw new IllegalArgumentException("Action non comprise dans l'exemple");
	}

	public State minimumCost(Collection<State> states, Map<State,Double> distance_map) {
		Double min=Double.POSITIVE_INFINITY;
		State minState=new State();
		for (State s : states) {
			if (distance_map.get(s) < min) {
				min=distance_map.get(s);
				minState=s;
			}
		}
		return minState;
	}

	public Stack<Action> dijkstra() {
		Map<State,Double> distance = new HashMap<>();
		Map<State,State> father = new HashMap<>();
		Map<State,Action> plan = new HashMap<>();
		ArrayList<State> goals = new ArrayList<>();
		ArrayList<State> open = new ArrayList<>();
		open.add(this.init);
		distance.put(this.init,0.);
		father.put(this.init,null);
		while (!(open.isEmpty())) {
			State state = minimumCost(open, distance);
			open.remove(state);
			if (this.satisfies(state)) {
				System.out.println("goal Dijkstra");
				goals.add(state);
			}
			for (Action a : this.actions) {
				if (a.is_applicable(state)) {
					State next = a.apply(state);
					if (!(distance.containsKey(next))) {
						distance.put(next,Double.POSITIVE_INFINITY);
					}
					double dist = distance.get(state) + this.cost(a);
					if (distance.get(next) > dist) {
						distance.put(next, dist);
						father.put(next,state);
						plan.put(next,a);
						open.add(next);
					}
				}
			}
		}
		return getDijkstraPlan(father,plan,goals,distance);
	}

	public Stack<Action> getDijkstraPlan(Map<State,State> father, Map<State,Action> actions, ArrayList<State> goals, Map<State,Double> distance) {
		Stack<Action> plan = new Stack<>();
		State goal = this.minimumCost(goals, distance);
		while (goal!=null) {
			plan.push(actions.get(goal));
			goal=father.get(goal);
		}
		Collections.reverse(plan);
		return plan;
	}

	public Stack<Action> aStar() {
		Map<State,Double> distance = new HashMap<>();
		Map<State,Double> value = new HashMap<>();
		Map<State,State> father = new HashMap<>();
		Map<State,Action> plan = new HashMap<>();
		PriorityQueue<State> open = new PriorityQueue<>(11, new Comparator<State>() {
																											@Override
																											public int compare(State first, State second) {
																												Double value_first = value.get(first);
																												Double value_second = value.get(second);
																												if (value_first < value_second) {
																													return -1;
																												} else if (value_first == value_second) {
																													return 0;
																												} else {
																													return 1;
																												}
																											}
																										});
		open.add(this.init);
		father.put(this.init,null);
		distance.put(this.init,0.);
		//value.put(this.init, heuristic(this.init));//faire heuristique
		while (!(open.isEmpty())) {
			State state = minimumCost(open, value);
			if (this.satisfies(state)) {
				return this.getBFSPlan(father,plan,state);
			} else {
				open.remove(state);
				for (Action a : this.actions) {
					if (a.is_applicable(state)) {
						State next = a.apply(state);
						if (!(distance.containsKey(next))) {
							distance.put(next,Double.POSITIVE_INFINITY);
						}
						double dist = distance.get(state) + this.cost(a);
						if (distance.get(next) > dist) {
							distance.put(next, dist);
				//			value.put(next, dist + heuristic(next));
							father.put(next,state);
							plan.put(next,a);
							open.add(next);
						}
					}
				}
			}
		}
		return null;
	}
}
