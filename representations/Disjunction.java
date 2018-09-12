package representations;

import java.util.*;

public class Disjunction extends Rule {

  public Disjunction(Map<Variable,String> premisse, Map<Variable,String> conclusion) {
		super(premisse,conclusion);
  }

  @Override
  public boolean isSatisfiedBy(Map<Variable,String> test) {
    return true;
  }
}
