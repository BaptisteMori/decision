package planning;

import representations.*;

public class SimpleHeuristic implements Heuristic {

	@Override
	public double heuristic(State s, State goal) {
		double heuristic_value = 0.;
		for (Variable v : s.getState().keySet()) {
			if(!(s.getState().get(v).equals(goal.getState().get(v)))) {
				heuristic_value++;
			}
		}
		return heuristic_value;
	}
}
