package representations;

import java.util.*;

public class IncompatibilityConstraint extends Rule {

  public IncompatibilityConstraint(Map<Variable,String> premisse) {
		super(premisse,null);
  }

  @Override
  public boolean isSatisfiedBy(Map<Variable,String> test) {
		int cont_cpt = 0;
		for (Variable v : this.premisse.keySet()) {
			if (!(test.containsKey(this.premisse.get(v)))) {
				cont_cpt++;
				continue;
			}
			if (!(premisse.get(v).equals(test.get(v)))) {
				return true;
			}
		}
		if (cont_cpt!=0) {
			return true;
		}
		return false;
  }
}
