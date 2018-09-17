package representations;

import java.util.*;

public class IncompatibilityConstraint extends Rule {

  public IncompatibilityConstraint(Map<Variable,String> premisse, Map<Variable,String> conclusion) {
		super(premisse,null);
  }


  @Override
  public boolean isSatisfiedBy(Map<Variable,String> test) {
		for (Variable v : test.keySet()) {
			if (!(v.equals(this.premisse.get(v)))) {
				return false;
			}
		}
		return true;
  }
}
