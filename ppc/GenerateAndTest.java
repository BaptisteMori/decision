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

  /*
    Méthode permettant de générer toutes les configurations
    de voitures possible.
  */

  public void generate() {
    for (Variable v : variables) {
      Set<String> d = v.getDomaine();
      String[] domaine = d.toArray(new String[d.size()]);
      Random r = new Random();
      int value = 0 + r.nextInt(domaine.length-1);
      this.test.put(v,domaine[value]);
    }
  }

    /*
      Méthode qui teste sur les voitures, toutes
      les contraintes et permet de retourner les
      voitures ayant accomplies les différentes
      contraintes.
    */

  public boolean testConstraints() {
    Example ex = new Example();

    for (int i = 0; i < this.constraints.length; i++) {
      if (this.constraints[i].isSatisfiedBy(this.test) == false) {
        this.res = false;
        return this.res;
      } else {
        this.res = true;
      }
    }
    if (this.res == true) {
      System.out.println(this.test);
    }
    return this.res;
  }
}
