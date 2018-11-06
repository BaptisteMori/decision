package extraction;

import java.util.*;
import representations.*;

public class BooleanDataBase {

  private ArrayList<Variable> variables_list;
  private ArrayList<Map<Variable,String>> transactions;

  public BooleanDataBase() {
    this.variables_list = new ArrayList<Variable>();
    this.transactions = new ArrayList<Map<Variable,String>>();
  }
}
