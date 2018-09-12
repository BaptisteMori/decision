package representations;

import java.util.*;

public class Disjunction extends Rule implements Constraint  {

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
