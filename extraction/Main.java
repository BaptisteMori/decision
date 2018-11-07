package extraction;

import java.util.*;
import representations.*;

public class Main {

	public static void main(String[] args) {
		ArrayList<String> variables = new ArrayList<String>();
		variables.add("A");
		variables.add("B");
		variables.add("C");
		variables.add("D");
		variables.add("E");

		Map<String,String> transaction1 = new HashMap<String,String>();
		transaction1.put("A","1");
		transaction1.put("B","1");
		transaction1.put("C","1");
		transaction1.put("D","1");
		transaction1.put("E","1");
		Map<String,String> transaction2 = new HashMap<String,String>();
		transaction2.put("A","1");
		transaction2.put("B","0");
		transaction2.put("C","1");
		transaction2.put("D","0");
		transaction2.put("E","0");
		Map<String,String> transaction3 = new HashMap<String,String>();
		transaction3.put("A","1");
		transaction3.put("B","1");
		transaction3.put("C","1");
		transaction3.put("D","1");
		transaction3.put("E","0");
		Map<String,String> transaction4 = new HashMap<String,String>();
		transaction4.put("A","0");
		transaction4.put("B","1");
		transaction4.put("C","1");
		transaction4.put("D","0");
		transaction4.put("E","0");
		Map<String,String> transaction5 = new HashMap<String,String>();
		transaction5.put("A","1");
		transaction5.put("B","1");
		transaction5.put("C","1");
		transaction5.put("D","0");
		transaction5.put("E","0");
		Map<String,String> transaction6 = new HashMap<String,String>();
		transaction6.put("A","0");
		transaction6.put("B","0");
		transaction6.put("C","0");
		transaction6.put("D","0");
		transaction6.put("E","1");

		ArrayList<Map<String,String>> transactions = new ArrayList<Map<String,String>>();
		transactions.add(transaction1);
		transactions.add(transaction2);
		transactions.add(transaction3);
		transactions.add(transaction4);
		transactions.add(transaction5);
		transactions.add(transaction6);

		BooleanDataBase data = new BooleanDataBase(variables,transactions);
		FrequentItemSetMiner naive_miner = new FrequentItemSetMiner(data);

		naive_miner.frequentItemSets(2);
	}
}
