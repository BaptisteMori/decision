package representations;

import java.util.*;
import examples.Example;

public class Main {

  public static void main(String[] args) {

    Example ex = new Example();

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

    /*Création des voitures*/

    Map<Variable, String> v1 = ex.getVoiture1();

    Map<Variable, String> v2 = ex.getVoiture2();

    Map<Variable, String> v3 = ex.getVoiture3();

		//Set<Variable> c = ex.getExample();

		Constraint equal1 = ex.getExample();
		System.out.println(equal1.isSatisfiedBy(v2));
    //Constraint disj1 = new Disjunction();
    //Constraint incomp1 = new IncompatibilityConstraint();

    /*
    r1 = ct = "noir" -> cc = b || ch = r
    v1 = < ct = "noir", cc = b, ch=r>
    if (!r1.isSatisfiedBy(v1)) {
      System.out.println("BUG")
    }
    */
  }
}
