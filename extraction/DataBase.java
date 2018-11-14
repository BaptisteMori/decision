package extraction;

import java.util.*;
import representations.*;

public class DataBase {

  private List<Variable> variables_list;
  private List<Map<Variable,String>> transactions;

  /**
    * Constructeur de la classe.
    * @param variables_list , qui est une List de Variable.
    * @param transactions , qui est une List de Map de Variable et un String.
    *
    */
  public DataBase(List<Variable> variables_list, List<Map<Variable,String>> transactions) {
    this.variables_list = variables_list;
    this.transactions = transactions;
  }


  /**
    * Méthode retournant la liste des Variable.
    * @return this.variables_list , qui est une List de Variable.
    *
    */
  public List<Variable> getVariablesList() {
    return this.variables_list;
  }

  /**
    * Méthode retournant une liste de transactions.
    * @return this.transactions qui est une List de Map de Variable et de String.
    *
    */
  public List<Map<Variable,String>> getTransactions() {
    return this.transactions;
  }


  /**
    *
    *
    *
    */
	public BooleanDataBase propositionalisation() {
		Set<String> bool_domain = new HashSet<>();
		bool_domain.add("0");
		bool_domain.add("1");
		ArrayList<Variable> booleans_list = new ArrayList<Variable>();
		for (Variable v : this.variables_list) {
			if (v.getDomaine().size()>2) {
				for (String s : v.getDomaine()) {
					booleans_list.add(new Variable(v.getNom()+"_"+s, bool_domain));
				}
			} else {
				booleans_list.add(new Variable(v.getNom(), bool_domain));
			}
		}
		ArrayList<Map<Variable,String>> boolean_transactions = new ArrayList<>();
		for (Map<Variable,String> m : this.transactions) {
			Map<Variable,String> t = new HashMap<>();
			for (Variable v2 : m.keySet()) {
				for (String s : v2.getDomaine()) {
					if (v2.getDomaine().size()>2) {
						if (s.equals(t.get(v2))) {
							t.put(booleans_list.get(booleans_list.indexOf(new Variable(v2.getNom()+"_"+s, bool_domain))), "1");
						} else {
							t.put(booleans_list.get(booleans_list.indexOf(new Variable(v2.getNom()+"_"+s, bool_domain))), "0");
						}
					} else {
						t.put(booleans_list.get(booleans_list.indexOf(new Variable(v2.getNom(), bool_domain))), t.get(v2));
					}
				}
			}
			boolean_transactions.add(t);
		}
		System.out.println(booleans_list);
		System.out.println(boolean_transactions);
		return null;
	}

}
