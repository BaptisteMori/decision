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
    FrequentItemSetMiner miner = new FrequentItemSetMiner(bool_db);
    miner.generateSingletons(3500);
    miner.bfMiner(1,3500,new HashSet<Variable>());
    System.out.println(miner.getItemSets());
	}
}
