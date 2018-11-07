package extraction;

import java.util.*;
import representations.*;

public class AssociationRuleMiner {

  private Map<Set<String>,Integer> frequent_item_sets;

  public AssociationRuleMiner(Map<Set<String>,Integer> frequent_item_sets) {
    this.frequent_item_sets = frequent_item_sets;
  }

/*  public ArrayList<Map<Set<String>,Set<String>>> generateAssociationRules() {
    ArrayList<Map<Set<String>,Set<String>>> association_list = new ArrayList<Map<Set<String>,Set<String>>>();
    Map<Set<String>,Set<String>> association = new HashMap<Set<String>,Set<String>>();
    for (int i = 0; i < variables.size(); i++) {
      Set<String> premisse = new HashSet<String>();
      Set<String> conclusion = new HashSet<String>();
      premisse.add(variables.get(i));
      for (String variable : variables) {
        if (!(variables.get(i).equals(variable))) {
          conclusion.add(variable);
        }
      }
      association.put(premisse,conclusion);
      association_list.add(association);
    }
    return association_list;
  }*/

  public Map<Set<String>,Set<String>> frequentValidAssociation(/*int minfr,*/ double minconf) {
    Map<Set<String>,Set<String>> association_map = new HashMap<Set<String>,Set<String>>();
    for (Set<String> item_set : this.frequent_item_sets.keySet()) {
      if (item_set.size() > 1) {
        for (String variable : item_set) {
          Set<String> var_set = new HashSet<String>();
          var_set.add(variable);
          double confiance = this.frequent_item_sets.get(item_set)/this.frequent_item_sets.get(var_set);
          if (confiance >= minconf) {
            Set<String> variable_set = new HashSet<String>();
            variable_set.add(variable);
            association_map.put(variable_set,item_set);
          }
        }
      }
    }
    return association_map;
  }

  public void recurviseGeneration() {
    
  }
}
