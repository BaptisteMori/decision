package representations;

import java.util.Map;

public interface Constraint {

  public Set<Variable> getScope();

  public boolean isSatisfiedBy(Map<Variable,String> map);
}
