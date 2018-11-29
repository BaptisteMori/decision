package ppc;
import representations.*;
import java.util.*;

public class MinDomaine implements Heuristic{

  @Override
  public void execute(Variable[] variables, int i, Constraint[] constraints){
    Arrays.sort(variables,i,variables.length,new Comparator<Variable>() {
                                              @Override
                                              public int compare(Variable first, Variable second) {
                                                int value_first = first.getDomaine().size();
                                                int value_second = second.getDomaine().size();
                                                if (value_first < value_second) {
                                                  return -1;
                                                } else if (value_first == value_second) {
                                                  return 0;
                                                } else {
                                                  return 1;
                                                }
                                              }
                                            });
  }

  @Override
  public void execute(Variable[] variables, int i,Map<Variable,Set<String>> unassigned_domains, Constraint[] constraints){
    Arrays.sort(variables,i,variables.length,new Comparator<Variable>() {
                                              @Override
                                              public int compare(Variable first, Variable second) {
                                                int value_first = unassigned_domains.get(first).size();
                                                int value_second = unassigned_domains.get(second).size();
                                                if (value_first < value_second) {
                                                  return -1;
                                                } else if (value_first == value_second) {
                                                  return 0;
                                                } else {
                                                  return 1;
                                                }
                                              }
                                            });
  }

  @Override
  public String toString() {
    return "MinDomaine";
  }
}
