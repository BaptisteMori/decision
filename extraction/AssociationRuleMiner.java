package extraction;

import java.util.*;
import representations.*;

public class AssociationRuleMiner {

  private Map<Set<Variable>,Integer> frequent_item_sets;

/**
  * Le constructeur de la classe
  *@param frequent_item_set un Map des sets d'item fréquents
  */
  public AssociationRuleMiner(Map<Set<Variable>,Integer> frequent_item_sets) {
    this.frequent_item_sets = frequent_item_sets;
  }

/**
  * Méthode qui détermine si des associations sont valides et fréquentes et en retourne la liste
  *@param minconf le seuil de confiance minimum
  *@return un Map<Set<Variable>,Set<Variable>> correspondant aux associations
  */
  public Map<Set<Variable>,Set<Variable>> frequentValidAssociation(double minconf) {
    Map<Set<Variable>,Set<Variable>> association_map = new HashMap<Set<Variable>,Set<Variable>>();
    for (Set<Variable> item_set : this.frequent_item_sets.keySet()) {
      if (item_set.size() > 1) {
        for (Variable variable : item_set) {
          Set<Variable> var_set = new HashSet<Variable>();
          var_set.add(variable);
          double confiance = ((double)this.frequent_item_sets.get(item_set))/((double)this.frequent_item_sets.get(var_set));
          if (confiance >= minconf) {
            Set<Variable> variable_set = new HashSet<Variable>();
            variable_set.add(variable);
            association_map.put(variable_set,item_set);
          }
        }
      }
    }
    return association_map;
  }
}
