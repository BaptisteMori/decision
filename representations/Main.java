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
    v1.add(ct, "rouge");
    v1.add(cc, "rouge");
    v1.add(ch, "rouge");
    v1.add(cg, "noir");
    v1.add(cd, "rouge");
    v1.add(sono, "True");
    v1.add(to, "False");


    Map<Variable, String> v2 = new HashMap<>();
    v2.add(ct, "noir");
    v2.add(cc, "noir");
    v2.add(ch, "noir");
    v2.add(cg, "blanc");
    v2.add(cd, "rouge");
    v2.add(sono, "True");
    v2.add(to, "True");

    Map<Variable, String> v3 = new HashMap<>();
    v2.add(ct, "noir");
    v2.add(cc, "noir");
    v2.add(ch, "noir");
    v2.add(cg, "noir");
    v2.add(cd, "noir");
    v2.add(sono, "True");
    v2.add(to, "False");

    Constraint equal1 = new AllEqualConstraint();
    Constraint disj1 = new Disjunction();
    Constraint incomp1 = new IncompatibilityConstraint();

    /*
    r1 = ct = "noir" -> cc = b || ch = r
    v1 = < ct = "noir", cc = b, ch=r>
    if (!r1.isSatisfiedBy(v1)) {
      System.out.println("BUG")
    }
    */
  }
}
