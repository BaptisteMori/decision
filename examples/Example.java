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

	public Constraint getExample() {

		Set<Variable> c = new HashSet<Variable>();
		c.add(this.ct);
		c.add(this.ch);
		c.add(this.cc);

		Constraint equal1 = new AllEqualConstraint(c);

		return equal1;
	}

	public Set<Variable> getVariables() {
		Set<Variable> result = new HashSet<Variable>();
		result.add(this.ct);
		result.add(this.cc);
		result.add(this.ch);
		result.add(this.cg);
		result.add(this.cd);
		result.add(this.sono);
		result.add(this.to);
		return result;
	}

	/*Création des voitures*/
	public Map<Variable,String> getVoiture1() {

		Set<Variable> vars = this.getVariables();
		Object[] vars_array = vars.toArray();

		Map<Variable, String> v1 = new HashMap<>();
		v1.put(vars_array[0], "rouge");
		v1.put(vars_array[1], "rouge");
		v1.put(vars_array[2], "rouge");
		v1.put(vars_array[3], "noir");
		v1.put(vars_array[4], "rouge");
		v1.put(vars_array[5], "True");
		v1.put(vars_array[6], "False");
		return v1;
	}

	public Map<Variable,String> getVoiture2() {

		Set<Variable> vars = this.getVariables();
		Object[] vars_array = vars.toArray();

		Map<Variable, String> v2 = new HashMap<>();
		v2.put(vars_array[0], "noir");
		v2.put(vars_array[1], "noir");
		v2.put(vars_array[2], "blanc");
		v2.put(vars_array[3], "blanc");
		v2.put(vars_array[4], "rouge");
		v2.put(vars_array[5], "True");
		v2.put(vars_array[6], "True");
		return v2;
	}

	public Map<Variable,String> getVoiture3() {

		Set<Variable> vars = this.getVariables();
		Object[] vars_array = vars.toArray();

		Map<Variable, String> v3 = new HashMap<>();
		v3.put(vars_array[0], "noir");
		v3.put(vars_array[1], "noir");
		v3.put(vars_array[2], "noir");
		v3.put(vars_array[3], "noir");
		v3.put(vars_array[4], "noir");
		v3.put(vars_array[5], "False");
		v3.put(vars_array[6], "True");
		return v3;
	}
}
