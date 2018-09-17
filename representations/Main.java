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

		Constraint equal1 = ex.getAllEqual();
		System.out.println(equal1.isSatisfiedBy(v1));
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
