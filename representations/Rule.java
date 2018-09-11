package representations;

import java.util.*;

public class Rule implements Constraint {

  private Set<Variable> scope;

  public Rule(Map<Variable,String> premisse, Map<Variable,String> conclusion) {

  }

  @Override
  public void getScope() {

  }

  @Override
  public boolean isSatisfiedBy(Map<Variable,String> test) {
    return true;
  }
}
