package extraction;

import representations.*;
import java.util.*;

public class FrequentItemSetMiner {

  private BooleanDataBase boolean_database;
  private Map<Set<String>, Integer> frequentItemSets;

  public FrequentItemSetMiner(BooleanDataBase db) {
		this.boolean_database=db;
    this.frequentItemSets = new HashMap<Set<String>, Integer>();
  }

	public Map<Set<String>, Integer> getItemSets() {
		return this.frequentItemSets;
	}

	public void bfMiner(int minimal_support, Set<String> combi) {
		int f = frequencyCalcul(combi);
		if (f >= minimal_support && !(frequentItemSets.containsKey(combi))) {
			this.frequentItemSets.put(combi,f);
		}
		ArrayList<String> var_list = this.boolean_database.getVariablesList();
		for (String variable : var_list) {
			if (combi.size() < var_list.size() && !(combi.contains(variable))) {
				Set<String> new_combi = new HashSet<String>(combi);
				new_combi.add(variable);
				bfMiner(minimal_support, new_combi);
			}
		}
	}

  public Map<Set<String>,Integer> frequentItemSets (int minimal_support, Set<String> prev) {
    Set<String> item_set = new HashSet<String>();
    if (frequencyCalcul(item_set) >= minimal_support) {
      item_set.add(this.boolean_database.getVariablesList().get(0));
    }
    System.out.println(frequencyCalcul(item_set));
    return null;
  }

  public int frequencyCalcul(Set<String> combi) {
    if (combi.size() == 0) {
      return this.boolean_database.getTransactions().size();
    }
		int freq=0;
    for (Map<String,String> transaction : this.boolean_database.getTransactions()) {
      int cpt = 0;
      for (String variable : combi) {
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
