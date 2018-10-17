package planning;

import representations.*;
import examples.AssemblyLine;

public class SimpleHeuristic implements Heuristic {

	@Override
	public double heuristic(State s, State goal) {
		double heuristic_value = 0.;
		for (Variable v : s.getState().keySet()) {
			if((AssemblyLine.all_colors.contains(s.getState().get(v))) && (!(s.getState().get(v).equals(goal.getState().get(v))))) {
				heuristic_value++;
			}
		}
		return heuristic_value;
	}
}
