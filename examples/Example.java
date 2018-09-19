package examples;

import java.util.*;
import representations.*;

public class Example {

	private HashSet<String> domaine_couleur;
	private HashSet<String> domaine_boolean;
	private Variable ct;
	private Variable cc;
	private Variable ch;
	private Variable cg;
	private Variable cd;
	private Variable sono;
	private Variable to;

	public Example(){
		/*Domaine couleur*/

		this.domaine_couleur = new HashSet<>();
		this.domaine_couleur.add("blanc");
		this.domaine_couleur.add("rouge");
		this.domaine_couleur.add("noir");

		/*Domaine Booléen*/

		this.domaine_boolean = new HashSet<>();
		this.domaine_boolean.add("True");
		this.domaine_boolean.add("False");

		/*Création des variables avec leurs domaines*/

		this.ct = new Variable("couleur_toit",this.domaine_couleur);
		this.cc = new Variable("couleur_capot", this.domaine_couleur);
		this.ch = new Variable("couleur_hayon", this.domaine_couleur);
		this.cg = new Variable("couleur_gauche", this.domaine_couleur);
		this.cd = new Variable("couleur_droite", this.domaine_couleur);
		this.sono = new Variable("sono",this.domaine_boolean);
		this.to = new Variable("toit_ouvrant", this.domaine_boolean);
	}

	public Constraint getAllEqual() {

		Set<Variable> c = new HashSet<Variable>();
		c.add(this.ct);
		c.add(this.ch);
		c.add(this.cc);

		Constraint equal1 = new AllEqualConstraint(c);
		return equal1;
	}

	public Constraint getDisjunctionToit(String coul_toit) {

		Map<Variable,String> premisse = new HashMap<Variable,String>();
		Map<Variable,String> conditions = new HashMap<Variable,String>();
		premisse.put(this.ct,coul_toit);
		conditions.put(this.cg,coul_toit);
		conditions.put(this.cd,coul_toit);

		Constraint disjunction = new Disjunction(premisse,conditions);
		return disjunction;
	}

	public Constraint getIncompatibilityConstraintForBlackSides() {

		Map<Variable,String> conditions = new HashMap<Variable,String>();
		conditions.put(this.cg, "noir");
		conditions.put(this.cd,"noir");

		Constraint inc_black = new IncompatibilityConstraint(conditions);
		return inc_black;
	}

	public Constraint getIncompatibilityConstraintForSono() {

		Map<Variable,String> conditions = new HashMap<Variable,String>();
		conditions.put(this.sono, "True");
		conditions.put(this.to,"True");

		Constraint inc_sono = new IncompatibilityConstraint(conditions);
		return inc_sono;
	}

	public Variable[] getVariables() {
		Variable[] result = new Variable[7];
		result[0]=this.ct;
		result[1]=this.cc;
		result[2]=this.ch;
		result[3]=this.cg;
		result[4]=this.cd;
		result[5]=this.sono;
		result[6]=this.to;
		return result;
	}

	public Constraint[] getConstraints() {
		Constraint[] result = new Constraint[6];
		result[0]=this.getAllEqual();
		result[1]=this.getDisjunctionToit("noir");
		result[2]=this.getDisjunctionToit("blanc");
		result[3]=this.getDisjunctionToit("rouge");
		result[4]=this.getIncompatibilityConstraintForBlackSides();
		result[5]=this.getIncompatibilityConstraintForSono();
		return result;
	}

	/*Création des voitures*/
	public Map<Variable,String> getVoiture1() {

		Map<Variable, String> v1 = new HashMap<>();
		v1.put(this.ct, "rouge");
		v1.put(this.cc, "rouge");
		v1.put(this.ch, "rouge");
		v1.put(this.cg, "noir");
		v1.put(this.cd, "rouge");
		v1.put(this.sono, "True");
		v1.put(this.to, "False");
		return v1;
	}

	public Map<Variable,String> getVoiture2() {

		Map<Variable, String> v2 = new HashMap<>();
		v2.put(this.ct, "noir");
		v2.put(this.cc, "noir");
		v2.put(this.ch, "blanc");
		v2.put(this.cg, "blanc");
		v2.put(this.cd, "rouge");
		v2.put(this.sono, "True");
		v2.put(this.to, "True");
		return v2;
	}

	public Map<Variable,String> getVoiture3() {

		Map<Variable, String> v3 = new HashMap<>();
		v3.put(this.ct, "noir");
		v3.put(this.cc, "noir");
		v3.put(this.ch, "noir");
		v3.put(this.cg, "noir");
		v3.put(this.cd, "noir");
		v3.put(this.sono, "False");
		v3.put(this.to, "True");
		return v3;
	}
}
