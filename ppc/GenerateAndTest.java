package ppc;

import representations.*;
import examples.*;
import java.util.*;

public class GenerateAndTest {

  private Variable[] variables;
  private Constraint[] constraints;
  private Map<Variable,String> test = new HashMap<Variable,String>();
  boolean res = false;

  public GenerateAndTest(Variable[] variables, Constraint[] constraints) {
    this.variables = variables;
    this.constraints = constraints;

    while (!res) {
      this.generate();
      this.testConstraints();
      //System.out.println("res a false");
    }
  }

  public void generate() {
    for (Variable v : variables) {
      Set<String> d = v.getDomaine();
      String[] domaine = d.toArray(new String[d.size()]);
      Random r = new Random();
      int value = 0 + r.nextInt(domaine.length-1);
      this.test.put(v,domaine[value]);
    }
  }

  public boolean testConstraints() {
    Example ex = new Example();

    Constraint disjBlanc = ex.getDisjunctionToit("blanc");
    Constraint disjNoir = ex.getDisjunctionToit("noir");
    Constraint disjRouge = ex.getDisjunctionToit("rouge");

    for (int i = 0; i < this.constraints.length; i++) {
      if (this.constraints[i].isSatisfiedBy(this.test) == false) {
        this.res = false;
        return this.res;
      } else {
        this.res = true;
      }
    }
    this.res = disjBlanc.isSatisfiedBy(this.test) && disjNoir.isSatisfiedBy(this.test) && disjRouge.isSatisfiedBy(this.test);

    if (this.res == true) {
      System.out.println(this.test);
    }
    return this.res;
  }
}
