package representations;

import java.util.*;

public class IncompatibilityConstraint extends Rule {

  public IncompatibilityConstraint(Map<Variable,String> premisse) {
		super(premisse,null);
  }

  @Override
  public boolean isSatisfiedBy(Map<Variable,String> test) {
		for (Variable v : this.premisse.keySet()) {
			if (!(premisse.get(v).equals(test.get(v))) || test.get(v).equals("")) {

				return true;
			}
		}
		return false;
  }
}
