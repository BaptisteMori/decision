package representations;

import java.util.Map;

public interface Constraint {

  public void getScope();

  public boolean isSatisfiedBy(Map<Variable,String> map);
}
