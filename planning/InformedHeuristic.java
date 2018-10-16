package planning;

import representations.*;

public class InformedHeuristic implements Heuristic {

	@Override
	public double heuristic(State s, State goal) {
		double heuristic_value = 0.;
		for (Variable v : s.getState().keySet()) {
			if(!(s.get(v).equals(goal.get(v)) {
				heuristic_value++;
			}
		}
		return heuristic_value;
	}
}
