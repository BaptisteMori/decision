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

  public int frequencyCalcul() {
    for (Map<String,String> transaction : this.boolean_database.getTransactions()) {
      for (String variable : this.map_variables.keySet()) {
        if (this.map_variables.get(variable).equals("1")){
          if (!(transaction.contains(variable))){
            break;
          }
        }
      }
    }
  }
}
