package extraction;

import java.util.*;
import representations.*;

public class BooleanDataBase {

  private ArrayList<String> variables_list;
  private ArrayList<Map<String,String>> transactions;

  public BooleanDataBase(ArrayList<String> variables_list, ArrayList<Map<String,String>> transactions) {
    this.variables_list = variables_list;
    this.transactions = transactions;
  }
}
