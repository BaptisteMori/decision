package diagnosis;

import examples.*;

public class Main {

	public static void main(String[] args) {
		ArrayList<Variable> vars = new ArrayList<>(Arrays.asList(ex.getVariables()));
		ArrayList<Constraint> constraints = new ArrayList<>(Arrays.asList(ex.getVariables()));
		Diagnoser diag = new Diagnoser(vars, constraints);
	}
}
