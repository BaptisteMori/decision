package extraction;

import java.util.*;
import representations.*;

public class AssociationRuleMiner {

  private Map<Set<String>,Integer> frequent_item_sets;

  public AssociationRuleMiner(Map<Set<String>,Integer> frequent_item_sets) {
    this.frequent_item_sets = frequent_item_sets;
  }

  public int frequentValidAssociation(int minfr, int minconf) {

    return 0;
  }

	public double confidence(Set<String> item_set, Set<String> premisse) {
		return FrequentItemSetMiner.frequencyCalcul(item_set)/FrequentItemSetMiner.frequencyCalcul(premisse);
	}
}
