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



}
