package representations;

import java.util.*;
import examples.Example;
import ppc.*;

public class Main {

  public static void main(String[] args) {

    Example ex = new Example();

    /*Création des voitures*/

    Map<Variable, String> v1 = ex.getVoiture1();
    System.out.println("Voiture 1 : " + v1);
    Map<Variable, String> v2 = ex.getVoiture2();
    System.out.println("Voiture 2 : " + v2);
    Map<Variable, String> v3 = ex.getVoiture3();
    System.out.println("Voiture 3 : " + v3);

		/*Test de contraintes */

		Constraint c1 = ex.getAllEqual();
    System.out.println("Contrainte 1 : " + c1);
		System.out.println("Voiture 1 : " + c1.isSatisfiedBy(v1));
		System.out.println("Voiture 2 : " + c1.isSatisfiedBy(v2));
		System.out.println("Voiture 3 : " + c1.isSatisfiedBy(v3));

		Constraint c2 = ex.getDisjunctionTotal();
    System.out.println("Contrainte 2 : " + c2);
		System.out.println("Voiture 1 : " + c2.isSatisfiedBy(v1));
		System.out.println("Voiture 2 : " + c2.isSatisfiedBy(v2));
		System.out.println("Voiture 3 : " + c2.isSatisfiedBy(v3));

		Constraint c3 = ex.getIncompatibilityConstraintForBlackSides();
    System.out.println("Contrainte 3 : " + c3);
		System.out.println("Voiture 1 : " + c3.isSatisfiedBy(v1));
		System.out.println("Voiture 2 : " + c3.isSatisfiedBy(v2));
		System.out.println("Voiture 3 : " + c3.isSatisfiedBy(v3));

    Constraint c4 = ex.getIncompatibilityConstraintForSono();
    System.out.println("Contrainte 4 : " + c4);
		System.out.println("Voiture 1 : " + c4.isSatisfiedBy(v1));
		System.out.println("Voiture 2 : " + c4.isSatisfiedBy(v2));
		System.out.println("Voiture 3 : " + c4.isSatisfiedBy(v3));
  }
}
