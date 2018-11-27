package ppc;
import representations.*;
import java.util.*;

public class MaxDomaine implements Heuristic{

  public Variable[] execute(Variable[] variables, int i){
    for (int j = i; j<variables.length-1;j++){
      int l = variables[j].getDomaine().size();
      for (int k = j+1; k<variables.length;k++){
        if (variables[l].getDomaine().size()<=variables[k].getDomaine().size()){
          l=k;
        }
      }
      variables=switchCase(variables,j,l);
    }
    return variables;
  }

  public Variable[] execute(Variable[] variables, int i,Map<Variable,Set<String>> unassigned_domains){
    for (int j = i; j<variables.length-1;j++){
      int l = unassigned_domains.get(variables[j]).size();
      for (int k = j+1; k<variables.length;k++){
        if (unassigned_domains.get(variables[l]).size()<=unassigned_domains.get(variables[k]).size()){
          l=k;
        }
      }
      variables=switchCase(variables,j,l);
    }
    return variables;
  }

  private Variable[] switchCase(Variable[] variables, int j, int k){
    Variable tmp = variables[k];
    variables[k]=variables[j];
    variables[j]=tmp;
    return variables;
  }

}
