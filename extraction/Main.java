package extraction;

import java.util.*;
import representations.*;
import examples.Example;

public class Main {

	public static void main(String[] args) {

		Example ex = new Example();
		DBReader csv_reader = new DBReader(new HashSet<Variable>(Arrays.asList(ex.getVariables())));
		DataBase db = csv_reader.importDB("resources/example_db.csv");
		BooleanDataBase bool_db = db.propositionalisation();
    FrequentItemSetMiner miner = new FrequentItemSetMiner(bool_db,3500);
    miner.frequentItemSets();
    System.out.println(miner.getItemSets());
		AssociationRuleMiner rule_miner = new AssociationRuleMiner(miner.getItemSets());
		System.out.println(rule_miner.frequentValidAssociation(0.4f));
	}
}
