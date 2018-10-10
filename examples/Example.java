package examples;

import java.util.*;
import representations.*;

public class Example {

	/*
		Déclaration des HashSet pour les domaines.
	*/

	private HashSet<String> domaine_couleur;
	private HashSet<String> domaine_boolean;
	private HashSet<String> domaine_couleur2;

	/*
		Déclaration des Varaibles.
		- ct pour couleur toit.
		- cc pour couleur capot.
		- ch pour couleur hayon.
		- cg pour couleur de la porte gauche.
		- cd pour la couleur de la porte droite.
		- sono pour la sono.
		- to pour le toit ouvrant.
	*/

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

		Set<String> tmp1 = new HashSet<>();
		tmp1.addAll(this.domaine_couleur);
		this.ct = new Variable("couleur_toit",tmp1);
		Set<String> tmp2 = new HashSet<>();
		tmp2.addAll(this.domaine_couleur);
		this.cc = new Variable("couleur_capot", tmp2);
		Set<String> tmp3 = new HashSet<>();
		tmp3.addAll(this.domaine_couleur);
		this.ch = new Variable("couleur_hayon", tmp3);
		Set<String> tmp4 = new HashSet<>();
		tmp4.addAll(this.domaine_couleur);
		this.cg = new Variable("couleur_gauche", tmp4);
		Set<String> tmp5 = new HashSet<>();
		tmp5.addAll(this.domaine_couleur);
		this.cd = new Variable("couleur_droite", tmp5);
		Set<String> tmp6 = new HashSet<>();
		tmp6.addAll(this.domaine_boolean);
		this.sono = new Variable("sono",tmp6);
		Set<String> tmp7 = new HashSet<>();
		tmp7.addAll(this.domaine_boolean);
		this.to = new Variable("toit_ouvrant", tmp7);
	}

	/*
		Méthode vérifiant si la couleur du toit,
		la couleur du hayon et la couleur du capot
		sont de la même couleur.
	*/
	public Constraint getAllEqual() {

		Set<Variable> c = new HashSet<Variable>();
		c.add(this.ct);
		c.add(this.ch);
		c.add(this.cc);

		Constraint equal1 = new AllEqualConstraint(c);
		return equal1;
	}

	public Constraint getRules(String coul_toit) {
		Map<Variable,String> premisse = new HashMap<Variable,String>();
		Map<Variable,String> conditions = new HashMap<Variable,String>();
		premisse.put(this.ct,coul_toit);
		conditions.put(this.cg,coul_toit);
		conditions.put(this.cd,coul_toit);

		Constraint rule = new Rule(premisse,conditions);
		return rule;
	}

	/*
		Méthode de type Constraint qui regarde pour
		la disjonction du toit.
	*/

	public Constraint getDisjunctionToit(String coul_toit) {

		Map<Variable,String> premisse = new HashMap<Variable,String>();
		Map<Variable,String> conditions = new HashMap<Variable,String>();
		premisse.put(this.ct,coul_toit);
		conditions.put(this.cg,coul_toit);
		conditions.put(this.cd,coul_toit);

		Constraint disjunction = new Disjunction(premisse,conditions);
		return disjunction;
	}

	public Constraint getDisjunctionTotal() {
		Constraint[] disjunction_list = new Constraint[3];
		disjunction_list[0]=getDisjunctionToit("noir");
		disjunction_list[1]=getDisjunctionToit("blanc");
		disjunction_list[2]=getDisjunctionToit("rouge");
		ConstraintDisjunction union = new ConstraintDisjunction(disjunction_list);
		return union;
	}

	/*
		Méthode de type Constraint qui regarde pour
		l'incompatibilité pour les portes droites et
		gauche qui ne peuvent pas être toutes les deux
		de la couleur noir.
	*/

	public Constraint getIncompatibilityConstraintForBlackSides() {

		Map<Variable,String> conditions = new HashMap<Variable,String>();
		conditions.put(this.cg, "noir");
		conditions.put(this.cd,"noir");

		Constraint inc_black = new IncompatibilityConstraint(conditions);
		return inc_black;
	}

	/*
		Méthode de type Constraint qui regarde pour
		l'incompatibilité pour la sono, qui ne peut pas
		être présente si le toit ouvrant est pris, et
		inversement..
	*/

	public Constraint getIncompatibilityConstraintForSono() {

		Map<Variable,String> conditions = new HashMap<Variable,String>();
		conditions.put(this.sono, "True");
		conditions.put(this.to,"True");

		Constraint inc_sono = new IncompatibilityConstraint(conditions);
		return inc_sono;
	}

	/*
		Méthode qui stocke dans result toutes
		les variables.
	*/

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
	/*
		Méthode qui stocke dans result toutes
		les contraintes.
	*/

	public Constraint[] getConstraints() {
		Constraint[] result = new Constraint[4];
		result[0]=this.getAllEqual();
		result[1]=this.getDisjunctionTotal();
		result[2]=this.getIncompatibilityConstraintForBlackSides();
		result[3]=this.getIncompatibilityConstraintForSono();
		return result;
	}

	/*Création des différentes voitures*/

	/*Voiture numéro 1*/
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

/*Voiture numéro 2*/
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

/*Voiture numéro 3*/
	public Map<Variable,String> getVoiture3() {

		Map<Variable, String> v3 = new HashMap<>();
		v3.put(this.ct, "blanc");
		v3.put(this.cc, "noir");
		v3.put(this.ch, "noir");
		v3.put(this.cg, "blanc");
		v3.put(this.cd, "noir");
		v3.put(this.sono, "False");
		v3.put(this.to, "True");
		return v3;
	}

/*Voiture numéro 4*/
	public Map<Variable,String> getVoiture4() {

		Map<Variable,String> v4 = new HashMap<>();
		v4.put(this.ct, "blanc");
		v4.put(this.cc, "blanc");
		v4.put(this.ch, "blanc");
		v4.put(this.cg, "");
		v4.put(this.cd, "noir");
		v4.put(this.sono, "True");
		v4.put(this.to, "False");
		return v4;
	}
}
