package representations;

import java.util.*;

public class Rule implements Constraint {

  private Set<Variable> scope;
  Map<Variable,String> premisse = new HashMap<>();
  Map<Variable,String> conclusion = new HashMap<>();

  public Rule(Map<Variable,String> premisse, Map<Variable,String> conclusion) {
    this.premisse = premisse;
    this.conclusion = conclusion;
  }

	@Override
  public Set<Variable> getScope() {
		return this.scope;
  }

  @Override
  public boolean isSatisfiedBy(Map<Variable,String> test) {
    ArrayList<Variable> p = this.premisse.getKeys();
    ArrayList<Variable> c = this.conclusion.getKeys();
    ArrayList<Variable> t = test.getKeys();


    for (Variable v : p) {
        if (!(t.get(v).equals(p.get(v)))) {
            return true;
          }
    }
    for (Variable v: c) {
      if (t.get(v).equals(c.get(v))) {
        return true;
      }
    }
    return false;
  }
}
