package ppc;
import representations.*;
import java.util.*;

public class MaxVarConstraint extends VarConstraint implements Heuristic{

  @Override
  public void execute(Variable[] variables, int i, Constraint[] constraints){
    Map<Variable,Integer> variableCount = new HashMap<>();
    for(Variable v : variables){
      variableCount.put(v,count(v,constraints));
    }
    Arrays.sort(variables,i,variables.length,new Comparator<Variable>() {
                                              @Override
                                              public int compare(Variable first, Variable second) {
                                                int value_first = variableCount.get(first);
                                                int value_second = variableCount.get(second);
                                                if (value_first > value_second) {
                                                  return -1;
                                                } else if (value_first == value_second) {
                                                  return 0;
                                                } else {
                                                  return 1;
                                                }
                                              }
                                            });
  }

}
