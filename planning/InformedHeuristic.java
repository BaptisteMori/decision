package planning;

import representations.*;

public class InformedHeuristic implements Heuristic {


	/**
		*  Redéfinition du constructeur de l'interface Heuristic.
		* @param s , qui est un State.
		* @param goal , qui est un State.
		* @return heuristic_value , qui est un double et qui sera l'heuristic prédit.
		*/
	@Override
	public double heuristic(State s, State goal) {
		double heuristic_value = 0.;
		for (Variable v : s.getState().keySet()) {
			if (!(s.getState().get(v).equals(goal.getState().get(v)))) {
				heuristic_value++;
			}
		}
		return heuristic_value;
	}
}
