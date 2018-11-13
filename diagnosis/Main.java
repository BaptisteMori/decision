package diagnosis;

import representations.*;
import examples.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Example ex = new Example();
		ArrayList<Variable> vars = new ArrayList<Variable>(Arrays.asList(ex.getVariables()));
		ArrayList<Constraint> constraints = new ArrayList<Constraint>(Arrays.asList(ex.getConstraints()));
		Diagnoser diag = new Diagnoser(vars, constraints);
	}
}
