package ppc;
import representations.*;
import java.util.*;

public interface Heuristic{

  public void execute(Variable[] variables, int i, Constraint[] constraints);

  public void execute(Variable[] variables, int i,Map<Variable,Set<String>> unassigned_domains_cop, Constraint[] constraints);
}
