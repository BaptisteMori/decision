package planning;

import java.util.*;
import examples.AssemblyLine;

public class PlanningProblemWithCost extends PlanningProblem {

	private ArrayList<Action> cost1;
	private ArrayList<Action> cost2;
	private Heuristic h;

	public PlanningProblemWithCost(State init, ArrayList<State> goals, ArrayList<Action> actions) {
		super(init,goals,actions);
		AssemblyLine al = new AssemblyLine();
		this.cost1 = new ArrayList<Action>(al.getParallelWheelInstallActions());
		cost1.addAll(al.getLargePaintActions());
		this.cost2 = new ArrayList<Action>(al.getBasicInstallActions());
		cost2.addAll(al.getPrecisePaintActions());
		this.h = new SimpleHeuristic();
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

	public double heuristic(State s, State goal) {
		return h.heuristic(s, goal);
	}

	public void setHeuristic(Heuristic new_h) {
		this.h=new_h;
	}

	public State minimumCost(ArrayList<State> states, Map<State,Double> distance_map) {
		Double min=Double.POSITIVE_INFINITY;
		State min_state=new State();
		for (State s : states) {
			if (distance_map.get(s) < min) {
				min=distance_map.get(s);
				min_state=s;
			}
		}
		return min_state;
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
		int nb_nodes=0;
		while (!(open.isEmpty())) {
			nb_nodes++;
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
		System.out.println("Noeuds explorés par Dijkstra : " + nb_nodes);
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

	public State bestGoal(State s) {
		double min = Double.POSITIVE_INFINITY;
		State min_state = new State();
		for (State goal : this.goals) {
			double v = heuristic(s, goal);
			if (v < min) {
				min=v;
				min_state=goal;
			}
		}
		return min_state;
	}

	public Queue<Action> aStar(int k) {
		Map<State,Double> distance = new HashMap<>();
		Map<State,Double> value = new HashMap<>();
		Map<State,State> father = new HashMap<>();
		Map<State,Action> plan = new HashMap<>();
		PriorityQueue<State> open = new PriorityQueue<>(k, new Comparator<State>() {
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
		open.offer(this.init);
		father.put(this.init,null);
		distance.put(this.init,0.);
		value.put(this.init, heuristic(this.init, bestGoal(this.init)));
		int nb_nodes=0;
		while (!(open.isEmpty())) {
			nb_nodes++;
			State state = open.poll();
			if (this.satisfies(state)) {
				System.out.println("Noeuds explorés par A*: " + nb_nodes);
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
							value.put(next, dist + heuristic(next, bestGoal(next)));
							father.put(next,state);
							plan.put(next,a);
							/*for (State s : open) {
								if (open.comparator().compare(next, s) == 1) {
									open.remove(s);*/
									open.offer(next);/*
									break;
								}
							}*/
						}
					}
				}
			}
		}
		System.out.println("Noeuds explorés par A*: " + nb_nodes);
		return null;
	}

	public Queue<Action> iterativeBeamSearch(int step) {
		int k = 10-step;
		Queue<Action> res_astar = new LinkedList<>();
		do {
			res_astar = aStar(k+=step);
		} while (res_astar == null);
		return res_astar;
	}
}
