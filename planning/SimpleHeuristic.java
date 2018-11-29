package planning;

import representations.*;
import examples.AssemblyLine;

/** Heuristique par distance de Hamming simple : on compare les couleurs des pièces de la voiture et on compte les différences par rapport à l'état but.*/
public class SimpleHeuristic implements Heuristic {

	/**
		* Surcharge du constructeur d'Heuristic.
		* @param s , qui est un State.
		* @param goal , qui est un State.
		* @return heuristic_value , qui est un double.
		* Implémente une heuristic pls simple.
		*/
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
