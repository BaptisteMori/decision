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
    if (!(this.premisse == null)) {
      for (Variable v : this.premisse.keySet()) {
          if (!(premisse.get(v).equals(test.get(v)))) {
              return true;
          }
      }
    }
    // conclusion
    for (Variable v: this.conclusion.keySet()) {
      if (conclusion.get(v).equals(test.get(v))) {
        return true;
      }
    }
    return false;
  }
}
