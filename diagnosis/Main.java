package diagnosis;

import representations.*;
import examples.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Example ex = new Example();
		ArrayList<Variable> vars = new ArrayList<Variable>();
		Set<String> domaine = new HashSet<String>();
		domaine.add("true");
		domaine.add("false");
		//domaine.add("blanc");
		Variable x1 = new Variable("x1",new HashSet<String>(domaine));
		Variable x2 = new Variable("x2",new HashSet<String>(domaine));
		Variable x3 = new Variable("x3",new HashSet<String>(domaine));
		Variable x4 = new Variable("x4",new HashSet<String>(domaine));

		Map<Variable,String> incomp = new HashMap<Variable,String>();
		incomp.put(x3,"false");
		incomp.put(x4,"false");
		ArrayList<Constraint> constraints = new ArrayList<Constraint>();
		Constraint c1 = new IncompatibilityConstraint(incomp);
		constraints.add(c1);

		Set<Variable> allequal_set = new HashSet<Variable>();
		allequal_set.add(x1);
		allequal_set.add(x2);
		allequal_set.add(x3);
		Constraint c2 = new AllEqualConstraint(allequal_set);
		constraints.add(c2);

		vars.add(x1);
		vars.add(x2);
		vars.add(x3);
		vars.add(x4);
		Diagnoser diag = new Diagnoser(vars, constraints);

		diag.add(x1,"true");
		diag.add(x2,"true");
		diag.add(x4,"false");
		System.out.println(diag.getDiagnosis(x3, "false"));
	}
}
