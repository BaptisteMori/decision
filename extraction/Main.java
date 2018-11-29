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
    int min_sup=3500;
    FrequentItemSetMiner miner = new FrequentItemSetMiner(bool_db,min_sup);
    miner.frequentItemSets();
    System.out.println("Combinaisons fréquentes (>" + min_sup + ") : " + miner.getItemSets());
		AssociationRuleMiner rule_miner = new AssociationRuleMiner(miner.getItemSets());
		System.out.println("Règles : " + rule_miner.frequentValidAssociation(0.4));
	}
}
