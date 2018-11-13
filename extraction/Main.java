package extraction;

import java.util.*;
import representations.*;
import examples.Example;

public class Main {

	public static void main(String[] args) {
		ArrayList<Variable> variables = new ArrayList<Variable>();
		Set<String> domaine = new HashSet<String>();
		domaine.add("1");
		domaine.add("0");
		Variable v1 = new Variable("A",domaine);
		variables.add(v1);
		Variable v2 = new Variable("B",domaine);
		variables.add(v2);
		Variable v3 = new Variable("C",domaine);
		variables.add(v3);
		Variable v4 = new Variable("D",domaine);
		variables.add(v4);
		Variable v5 = new Variable("E",domaine);
		variables.add(v5);

		Map<Variable,String> transaction1 = new HashMap<Variable,String>();
		transaction1.put(v1,"1");
		transaction1.put(v2,"1");
		transaction1.put(v3,"1");
		transaction1.put(v4,"1");
		transaction1.put(v5,"1");
		Map<Variable,String> transaction2 = new HashMap<Variable,String>();
		transaction2.put(v1,"1");
		transaction2.put(v2,"0");
		transaction2.put(v3,"1");
		transaction2.put(v4,"0");
		transaction2.put(v5,"0");
		Map<Variable,String> transaction3 = new HashMap<Variable,String>();
		transaction3.put(v1,"1");
		transaction3.put(v2,"1");
		transaction3.put(v3,"1");
		transaction3.put(v4,"1");
		transaction3.put(v5,"0");
		Map<Variable,String> transaction4 = new HashMap<Variable,String>();
		transaction4.put(v1,"0");
		transaction4.put(v2,"1");
		transaction4.put(v3,"1");
		transaction4.put(v4,"0");
		transaction4.put(v5,"0");
		Map<Variable,String> transaction5 = new HashMap<Variable,String>();
		transaction5.put(v1,"1");
		transaction5.put(v2,"1");
		transaction5.put(v3,"1");
		transaction5.put(v4,"0");
		transaction5.put(v5,"0");
		Map<Variable,String> transaction6 = new HashMap<Variable,String>();
		transaction6.put(v1,"0");
		transaction6.put(v2,"0");
		transaction6.put(v3,"0");
		transaction6.put(v4,"0");
		transaction6.put(v5,"1");

		ArrayList<Map<Variable,String>> transactions = new ArrayList<Map<Variable,String>>();
		transactions.add(transaction1);
		transactions.add(transaction2);
		transactions.add(transaction3);
		transactions.add(transaction4);
		transactions.add(transaction5);
		transactions.add(transaction6);

		BooleanDataBase data = new BooleanDataBase(variables,transactions);
		FrequentItemSetMiner naive_miner = new FrequentItemSetMiner(data);
		naive_miner.bfMiner(2, new HashSet<Variable>());
		System.out.println(naive_miner.getItemSets());

		Example ex = new Example();
		DBReader csv_reader = new DBReader(new HashSet<Variable>(Arrays.asList(ex.getVariables())));
		DataBase db = csv_reader.importDB("resources/example_db.csv");
		System.out.println(db.getVariablesList());
		db.propositionalisation();

	}
}
