package extraction;

import representations.*;
import java.util.*;

public class FrequentItemSetMiner {

  private BooleanDataBase boolean_database;

  public FrequentItemSetMiner(BooleanDataBase db) {
		this.boolean_database=db;
  }

  public Map<Set<Variable>,Integer> frequentItemSets (int minimal_support) {
    return null;
  }
}
