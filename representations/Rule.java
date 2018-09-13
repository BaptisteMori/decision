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
    // premisse
    for (Variable v : test.keySet()) {
        if (!(v.equals(this.premisse.get(v)))) {
            return true;
        }
    }
    // conclusion
    for (Variable v: test.keySet()) {
      if (v.equals(this.conclusion.get(v))) {
        return true;
      }
    }
    return false;
  }
}
