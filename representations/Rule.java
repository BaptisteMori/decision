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
		int cont_cpt = 0;
    // premisse
    if (!(this.premisse == null)) {
      for (Variable v : this.premisse.keySet()) {
				if (!(test.containsKey(this.premisse.get(v)))) {
					cont_cpt++;
					continue;
				}
        if (!(premisse.get(v).equals(test.get(v)))) {
          return true;
        }
      }
    }
    // conclusion
    for (Variable v: this.conclusion.keySet()) {
			if (!(test.containsKey(this.conclusion.get(v)))) {
				cont_cpt++;
				continue;
			}
      if (conclusion.get(v).equals(test.get(v))) {
        return true;
      }
    }
		if (cont_cpt!=0) {
			return true;
		}
    return false;
  }
}
