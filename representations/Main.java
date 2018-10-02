package representations;

import java.util.*;
import examples.Example;
import ppc.*;

public class Main {

  public static void main(String[] args) {

    Example ex = new Example();

    /*Création des voitures*/

    Map<Variable, String> v1 = ex.getVoiture1();
    Map<Variable, String> v2 = ex.getVoiture2();
    Map<Variable, String> v3 = ex.getVoiture3();

		/*Test de contraintes */

		/*System.out.println("Contrainte 1");
		Constraint c1 = ex.getAllEqual();
		System.out.println(c1.isSatisfiedBy(v1));
		System.out.println(c1.isSatisfiedBy(v2));
		System.out.println(c1.isSatisfiedBy(v3));

		System.out.println("Contrainte 2");
		Constraint c2 = ex.getDisjunctionTotal();
		System.out.println(c2.isSatisfiedBy(v1));
		System.out.println(c2.isSatisfiedBy(v2));
		System.out.println(c2.isSatisfiedBy(v3));

		System.out.println("Contrainte 3");
		Constraint c3 = ex.getIncompatibilityConstraintForBlackSides();
		System.out.println(c3.isSatisfiedBy(v1));
		System.out.println(c3.isSatisfiedBy(v2));
		System.out.println(c3.isSatisfiedBy(v3));

		System.out.println("Contrainte 4");
    Constraint c4 = ex.getIncompatibilityConstraintForSono();
		System.out.println(c4.isSatisfiedBy(v1));
		System.out.println(c4.isSatisfiedBy(v2));
		System.out.println(c4.isSatisfiedBy(v3));*/

     /*Variable[] v = ex.getVariables();
     Constraint[] constraints = new Constraint[4];
     Constraint allEqual = ex.getAllEqual();
     constraints[0] = allEqual;
     Constraint incompSono = ex.getIncompatibilityConstraintForSono();
     constraints[1] = incompSono;
     Constraint incompSides = ex.getIncompatibilityConstraintForBlackSides();
     constraints[2] = incompSides;
     Constraint disj = ex.getDisjunctionTotal();
     constraints[3] = disj;
     GenerateAndTest gt = new GenerateAndTest(v,constraints);*/


     Constraint c = ex.getDisjunctionTotal();
     Map<Variable,String> v4 = ex.getVoiture3();
     Map<Variable, Set<String>> domaines = new HashMap<>();
     Set<Variable> var = v1.keySet();
     for (Variable v : var) {
       if (v3.get(v).equals("")) {
         domaines.put(v, v.getDomaine());
         System.out.println(v+" " +v.getDomaine());
       }
     }
     System.out.println(c.filter(v3, domaines));
     for (Variable v : var) {
       System.out.println(v+" " +v.getDomaine());
     }

  }
}
