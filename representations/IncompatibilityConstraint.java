package representations;

import java.util.*;

public class IncompatibilityConstraint implements Constraint {

  private Set<Variable> scope;

  public IncompatibilityConstraint() {

  }

	@Override
  public Set<Variable> getScope() {
		return this.scope;
  }

  @Override
  public boolean isSatisfiedBy(Map<Variable,String> test) {
    return true;
  }
}
