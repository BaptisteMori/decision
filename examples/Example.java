package representations;

import java.util.*;

public class Example {

	public Set<Constraint> getExample() {

		Set<Variable> c = new HashSet<Variable>();
		c.add(ct);
		c.add(ch);
		c.add(cc);

		Constraint equal1 = new AllEqualConstraint(c);
		System.out.println(equal1.isSatisfiedBy(v2));
    //Constraint disj1 = new Disjunction();
    //Constraint incomp1 = new IncompatibilityConstraint();
	}

	public static Set<Variable> getVariables() {
		/*Domaine couleur*/

		Set<String> domaine_couleur = new HashSet<>();
		domaine_couleur.add("blanc");
		domaine_couleur.add("rouge");
		domaine_couleur.add("noir");

		/*Domaine Booléen*/

		Set<String> domaine_boolean = new HashSet<>();
		domaine_boolean.add("True");
		domaine_boolean.add("False");

		/*Création des variables avec leurs domaines*/

		Variable ct = new Variable("couleur_toit",domaine_couleur);
		Variable cc = new Variable("couleur_capot", domaine_couleur);
		Variable ch = new Variable("couleur_hayon", domaine_couleur);
		Variable cg = new Variable("couleur_gauche", domaine_couleur);
		Variable cd = new Variable("couleur_droite", domaine_couleur);
		Variable sono = new Variable("sono",domaine_boolean);
		Variable to = new Variable("toit_ouvrant", domaine_boolean);

		Set<Variable> result = new Set<Variable>();
		result.add(ct);
		result.add(cc);
		result.add(ch);
		result.add(cg);
		result.add(cd);
		result.add(sono);
		result.add(to);
		return result;
	}

	/*Création des voitures*/
	public static Map<Variable,String> getVoiture1() {

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

	public static Map<Variable,String> getVoiture2() {

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

	public static Map<Variable,String> getVoiture3() {

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
