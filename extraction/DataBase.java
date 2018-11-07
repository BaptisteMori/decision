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
			for (String s : v.getDomaine()) {
				booleans_list.add(new Variable(v.getNom(),bool_domain));
			}
		}
	}

}
