package ppc;
import representations.*;
import java.util.*;

public class MaxDomaine implements Heuristic{

  public Variable[] execute(Variable[] variables, int i){
    Arrays.sort(variables,i,variables.length,new Comparator<Variable>() {
                                              @Override
                                              public int compare(Variable first, Variable second) {
                                                int value_first = first.getDomaine().size();
                                                int value_second = second.getDomaine().size();
                                                if (value_first > value_second) {
                                                  return -1;
                                                } else if (value_first == value_second) {
                                                  return 0;
                                                } else {
                                                  return 1;
                                                }
                                              }
                                            });
    return variables;
  }

  public Variable[] execute(Variable[] variables, int i,Map<Variable,Set<String>> unassigned_domains){
    Arrays.sort(variables,i,variables.length,new Comparator<Variable>() {
                                              @Override
                                              public int compare(Variable first, Variable second) {
                                                int value_first = unassigned_domains.get(first).size();
                                                int value_second = unassigned_domains.get(second).size();
                                                if (value_first > value_second) {
                                                  return -1;
                                                } else if (value_first == value_second) {
                                                  return 0;
                                                } else {
                                                  return 1;
                                                }
                                              }
                                            });
    return variables;
  }

}
