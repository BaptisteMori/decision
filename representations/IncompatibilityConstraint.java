package representations;

import java.util.*;

public class IncompatibilityConstraint implements Constraint {

  private Set<Variable> scope;

  public IncompatibilityConstraint() {

  }

  @Override
  public void getScope() {

  }

  @Override
  public boolean isSatisfiedBy(Map<Variable,String> test) {
    return true;
  }
}
