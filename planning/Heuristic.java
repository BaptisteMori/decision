package planning;

public interface Heuristic {

	/**
		* Constructeur de l'interface
		* @param s , qui est un State.
		* @param goal , qui est un State.
		*/
	public double heuristic(State s, State goal);
}
