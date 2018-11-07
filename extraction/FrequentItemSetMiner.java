package extraction;

import representations.*;
import java.util.*;

public class FrequentItemSetMiner {

  private BooleanDataBase boolean_database;
  private Map<String,String> map_variables;

  public FrequentItemSetMiner(BooleanDataBase db) {
		this.boolean_database=db;
    this.map_variables = new HashMap<String,String>();
    this.map_variables.put(this.boolean_database.getVariablesList().get(0),"1");
  }

  public Map<Set<String>,Integer> frequentItemSets (int minimal_support) {
    Set<String> item_set = new HashSet<String>();

    return null;
  }

  public int frequencyCalcul(Set<String> combi) {
		int freq=0;
    for (Map<String,String> transaction : this.boolean_database.getTransactions()) {
      for (String variable : combi) {
        if (transaction.get(variable).equals("0"))) {
            break;
        }
				freq++;
      }
    }
		return freq;
  }
}
