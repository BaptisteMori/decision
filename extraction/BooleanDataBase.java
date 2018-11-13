package extraction;

import java.util.*;
import representations.*;

public class BooleanDataBase {

  private ArrayList<Variable> variables_list;
  private ArrayList<Map<Variable,String>> transactions;

  public BooleanDataBase(ArrayList<Variable> variables_list, ArrayList<Map<Variable,String>> transactions) {
    this.variables_list = variables_list;
    this.transactions = transactions;
  }

  public ArrayList<Variable> getVariablesList() {
    return this.variables_list;
  }

  public ArrayList<Map<Variable,String>> getTransactions() {
    return this.transactions;
  }

}
