package extraction;

import representations.*;
import java.util.*;

public class FrequentItemSetMiner {

  private BooleanDataBase boolean_database;
  private Map<Set<Variable>, Integer> frequentItemSets;


/**
  * Constructeur de la Classe.
  * @param db , qui est de type BooleanDataBase.
  *
  */

  public FrequentItemSetMiner(BooleanDataBase db) {
		this.boolean_database=db;
    this.frequentItemSets = new HashMap<Set<Variable>, Integer>();
  }

/**
  * Méthode qui permet de retourner l'item d'un Set.
  * @return this.frequentItemSets , retournant un Map de Set de Variable et un integer.
  *
  */
	public Map<Set<Variable>, Integer> getItemSets() {
		return this.frequentItemSets;
	}


/**
  *
  * @param minimal_support , qui est de type int.
  * @param combi , qui est de type Set de Variable.
  */

	public void bfMiner(int minimal_support, Set<Variable> combi) {
		int f = frequencyCalcul(combi);
		if (f >= minimal_support && !(frequentItemSets.containsKey(combi))) {
			this.frequentItemSets.put(combi,f);
		}
		ArrayList<Variable> var_list = this.boolean_database.getVariablesList();
		for (Variable variable : var_list) {
			if (combi.size() < var_list.size() && !(combi.contains(variable))) {
				Set<Variable> new_combi = new HashSet<Variable>(combi);
				new_combi.add(variable);
				bfMiner(minimal_support, new_combi);
			}
		}
	}


  /**
    * Méthode permettant d'afficher les Set d'item fréquent.
    * @param minimal_support , qui est un int.
    * @param prev , qui est un Set de Variable.
    */
  public Map<Set<Variable>,Integer> frequentItemSets (int minimal_support, Set<Variable> prev) {
    Set<Variable> item_set = new HashSet<Variable>();
    if (frequencyCalcul(item_set) >= minimal_support) {
      item_set.add(this.boolean_database.getVariablesList().get(0));
    }
    System.out.println(frequencyCalcul(item_set));
    return null;
  }


  /**
    * Méthode
    * @param combi, qui est un Set de Variable.
    * @return this.boolean_database.getTransactions().size() , qui est un entier représentant la fréquence de calcul.
    */
  public int frequencyCalcul(Set<Variable> combi) {
    if (combi.size() == 0) {
      return this.boolean_database.getTransactions().size();
    }
		int freq=0;
    for (Map<Variable,String> transaction : this.boolean_database.getTransactions()) {
      int cpt = 0;
      for (Variable variable : combi) {
        if (transaction.get(variable).equals("0")) {
            break;
        }
        cpt++;
      }
      if (cpt == combi.size()) {
        freq++;
      }
    }
		return freq;
  }
}
