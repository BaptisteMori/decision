package representations;

import java.util.*;

public class Main {

  public static void main(String[] args) {

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

    Map<Variable, String> v1 = new HashMap<>();
    v1.put(ct, "rouge");
    v1.put(cc, "rouge");
    v1.put(ch, "rouge");
    v1.put(cg, "noir");
    v1.put(cd, "rouge");
    v1.put(sono, "True");
    v1.put(to, "False");


    Map<Variable, String> v2 = new HashMap<>();
    v2.put(ct, "noir");
    v2.put(cc, "noir");
    v2.put(ch, "blanc");
    v2.put(cg, "blanc");
    v2.put(cd, "rouge");
    v2.put(sono, "True");
    v2.put(to, "True");

    Map<Variable, String> v3 = new HashMap<>();
    v3.put(ct, "noir");
    v3.put(cc, "noir");
    v3.put(ch, "noir");
    v3.put(cg, "noir");
    v3.put(cd, "noir");
    v3.put(sono, "True");
    v3.put(to, "False");

		Set<Variable> c = new HashSet<Variable>();
		c.add(ct);
		c.add(ch);
		c.add(cc);

		Constraint equal1 = new AllEqualConstraint(c);
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
