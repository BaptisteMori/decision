package representations;

import java.util.*;

public interface Constraint {

  public Set<Variable> getScope();

  public boolean isSatisfiedBy(Map<Variable,String> map);

  public boolean filter(Map<Variable,String> map, Map<Variable, Set<String>> domaines);
}
