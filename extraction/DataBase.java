package extraction;

import java.util.*;
import representations.*;

public class DataBase {

  private List<Variable> variables_list;
  private List<Map<Variable,String>> transactions;

  public DataBase(List<Variable> variables_list, List<Map<Variable,String>> transactions) {
    this.variables_list = variables_list;
    this.transactions = transactions;
  }

  public List<Variable> getVariablesList() {
    return this.variables_list;
  }

  public List<Map<Variable,String>> getTransactions() {
    return this.transactions;
  }

	public BooleanDataBase propositionalisation() {
		Set<String> bool_domain = new HashSet<>();
		bool_domain.add("0");
		bool_domain.add("1");
		ArrayList<Variable> booleans_list = new ArrayList<Variable>();
		for (Variable v : this.variables_list) {
			if (v.getDomaine().size()>2) {
				for (String s : v.getDomaine()) {
					booleans_list.add(new Variable(v.getNom()+":"+s, bool_domain));
				}
			} else {
				booleans_list.add(new Variable(v.getNom(), bool_domain));
			}
		}
		ArrayList<Map<Variable,String>> boolean_transactions = new ArrayList<>();
		for (Map<Variable,String> m : this.transactions) {
			Map<Variable,String> bool_map = new HashMap<>();
      for (Variable bool : booleans_list) {
        System.out.println("bool " + bool);
        String[] couple = bool.getNom().split(":");
        if (couple.length>1) {
          for (Variable key : m.keySet()) {
            System.out.print("key " + key);
            if (couple[1].equals(m.get(key))) {
              System.out.println(" 1");
              bool_map.put(bool, "1");
            } else {
              System.out.println(" 0");
              bool_map.put(bool, "0");
            }
          }
        } else {
          bool_map.put(bool,m.get(bool));
        }
      }
			boolean_transactions.add(bool_map);
		}
		return new BooleanDataBase(booleans_list, boolean_transactions);
	}
}
