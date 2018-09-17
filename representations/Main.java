package representations;

import java.util.*;
import examples.Example;

public class Main {

  public static void main(String[] args) {

    Example ex = new Example();

    /*Création des voitures*/

    Map<Variable, String> v1 = ex.getVoiture1();

    Map<Variable, String> v2 = ex.getVoiture2();

    Map<Variable, String> v3 = ex.getVoiture3();

		/*Constraint c1 = ex.getAllEqual();
		System.out.println(c1.isSatisfiedBy(v1));
		System.out.println(c1.isSatisfiedBy(v2));
		System.out.println(c1.isSatisfiedBy(v3));
		Constraint c3 = ex.getIncompatibilityConstraintForBlackSides();
		System.out.println(c3.isSatisfiedBy(v1));
		System.out.println(c3.isSatisfiedBy(v2));
		System.out.println(c3.isSatisfiedBy(v3));
    Constraint c4 = ex.getIncompatibilityConstraintForSono();
		System.out.println(c4.isSatisfiedBy(v1));
		System.out.println(c4.isSatisfiedBy(v2));
		System.out.println(c4.isSatisfiedBy(v3));*/
    Constraint c2a = ex.getDisjunctionBlanc();
    Constraint c2b = ex.getDisjunctionNoir();
    Constraint c2c = ex.getDisjunctionRouge();
    System.out.println(!(c2a.isSatisfiedBy(v1) && c2b.isSatisfiedBy(v1) && c2c.isSatisfiedBy(v1)));
    System.out.println(!(c2a.isSatisfiedBy(v2) && c2b.isSatisfiedBy(v2) && c2c.isSatisfiedBy(v2)));
    System.out.println(!(c2a.isSatisfiedBy(v3) && c2b.isSatisfiedBy(v3) && c2c.isSatisfiedBy(v3)));

    /*
    r1 = ct = "noir" -> cc = b || ch = r
    v1 = < ct = "noir", cc = b, ch=r>
    if (!r1.isSatisfiedBy(v1)) {
      System.out.println("BUG")
    }
    */
  }
}
