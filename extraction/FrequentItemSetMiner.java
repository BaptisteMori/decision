package extraction;

import representations.*;
import java.util.*;

public class FrequentItemSetMiner {

  private BooleanDataBase boolean_database;
  private Map<String,String> map_variables; //SERT A RIEN

  public FrequentItemSetMiner(BooleanDataBase db) {
		this.boolean_database=db;
    //this.map_variables = new HashMap<String,String>();
    //this.map_variables.put(this.boolean_database.getVariablesList().get(0),"1");
  }

  public Map<Set<String>,Integer> frequentItemSets (int minimal_support) {
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
