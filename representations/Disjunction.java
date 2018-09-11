package representations;

import java.util.*;

public class Disjunction implements Constraint {

  private Set<Variable> scope;

  public Disjunction() {

  }

  @Override
  public void getScope() {

  }

  @Override
  public boolean isSatisfiedBy(Map<Variable,String> test) {
    return true;
  }
}
