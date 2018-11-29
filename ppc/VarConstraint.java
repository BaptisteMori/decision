package ppc;
import representations.*;
import java.util.*;

public abstract class VarConstraint implements Heuristic{

  @Override
  public void execute(Variable[] variables, int i,Map<Variable,Set<String>> unassigned_domains, Constraint[] constraints){
    execute(variables,i,constraints);
  }

  public int count(Variable variable, Constraint[] constraints){
    int cpt = 0;
    for(Constraint c : constraints){
      for(Variable v : c.getScope()){
        if(v.equals(variable)){
          cpt+=1;
        }
      }
    }
    return cpt;
  }
}
