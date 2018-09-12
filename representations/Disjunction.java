package representations;

import java.util.*;

public class Disjunction implements Constraint extends Rule {

  private Set<Variable> scope;

  public Disjunction() {

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
