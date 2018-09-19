package representations;

import java.util.*;

public class Disjunction extends Rule {

  public Disjunction(Map<Variable,String> premisse, Map<Variable,String> conclusion) {
		super(premisse,conclusion);
  }

	@Override
	public boolean isSatisfiedBy(Map<Variable,String> test) {
		// premisse
		if (!(this.premisse == null)) {
			for (Variable v : this.premisse.keySet()) {
				if (!(premisse.get(v).equals(test.get(v)))) {
					return false;
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
