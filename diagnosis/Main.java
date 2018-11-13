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

		Map<Variable,String> instance = new HashMap<Variable,String>();
		String value = "noir";
		for (Variable var : vars) {
			instance.put(var,value);
		}
		Variable var = new Variable("couleur_toit", vars.get(0).getDomaine());
		System.out.println(diag.getDiagnosis(instance, var, value));
	}
}
