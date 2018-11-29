package planning;

/** Stratégie de calcul de l'heuristique utilisée par A*.*/
public interface Heuristic {

	public double heuristic(State s, State goal);
}
