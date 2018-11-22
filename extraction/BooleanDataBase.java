package extraction;

import java.util.*;
import representations.*;

public class BooleanDataBase {

  private ArrayList<Variable> variables_list;
  private ArrayList<Map<Variable,String>> transactions;

  /**
    * Constructeur de la classe.
    * @param variables_list , qui est une ArrayList de Variable.
    * @param transactions , qui est une ArrayList de Map de Variable et de String.
    *
    */

  public BooleanDataBase(ArrayList<Variable> variables_list, ArrayList<Map<Variable,String>> transactions) {
    this.variables_list = variables_list;
    this.transactions = transactions;
  }

/**
  * Méthode permettant de retourner la liste des variable.
  * @return this.variables_list , qui est une ArrayList de Variable.
  *
  *
  */


  public ArrayList<Variable> getVariablesList() {
    return this.variables_list;
  }

/**
  * Méthode permettant de retourner les transactions.
  * @return this.transactions , qui est une ArrayList de Map de Variable et de String.
  *
  *
  */

  public ArrayList<Map<Variable,String>> getTransactions() {
    return this.transactions;
  }

}
