package ppc;
import representations.*;
import java.util.*;

public interface Heuristic{

  public Variable[] execute(Variable[] variables, int i);

  public Variable[] execute(Variable[] variables, int i,Map<Variable,Set<String>> unassigned_domains_cop);
}
