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
		Variable toit = new Variable("x1",new HashSet<String>(domaine));
		Variable capot = new Variable("x2",new HashSet<String>(domaine));
		Variable droite = new Variable("x3",new HashSet<String>(domaine));
		Variable gauche = new Variable("x4",new HashSet<String>(domaine));

		Map<Variable,String> incomp = new HashMap<Variable,String>();
		incomp.put(droite,"false");
		incomp.put(gauche,"false");
		ArrayList<Constraint> constraints = new ArrayList<Constraint>();
		Constraint c1 = new IncompatibilityConstraint(incomp);
		constraints.add(c1);

		Set<Variable> allequal_set = new HashSet<Variable>();
		allequal_set.add(toit);
		allequal_set.add(capot);
		allequal_set.add(gauche);
		Constraint c2 = new AllEqualConstraint(allequal_set);
		constraints.add(c2);

		vars.add(toit);
		vars.add(capot);
		vars.add(droite);
		vars.add(gauche);
		Diagnoser diag = new Diagnoser(vars, constraints);

		diag.add(toit,"true");
		diag.add(capot,"true");
		diag.add(droite,"false");
		System.out.println(diag.getDiagnosis(gauche, "false"));
	}
}
