package diagnosis;

import representations.*;
import java.util.*;
import ppc.*;

public class Diagnoser {

  private ArrayList<Variable> variables;
  private ArrayList<Constraint> contraintes;
  private Map<Variable,String> current_instance;
  private Backtracking bt;


  public Diagnoser(ArrayList<Variable> variables, ArrayList<Constraint> contraintes) {
    this.variables = variables;
    this.contraintes = contraintes;
    this.current_instance = new HashMap<Variable,String>();
    this.bt = new Backtracking(this.variables.toArray(new Variable[this.variables.size()]), this.contraintes.toArray(new Constraint[contraintes.size()]));
  }

  public void add(Variable var, String value){
    this.current_instance.put(var,value);
  }

  public boolean isExplanation(Map<Variable,String> instance, Variable variable, String value) {
    Map<Variable,String> instance_bis;
    instance_bis = new HashMap<>(instance);
    for (Variable var : instance_bis.keySet()) {
      var.setDomaine(new HashSet<String>(Arrays.asList(instance_bis.get(var))));
    }
    variable.setDomaine(new HashSet<String>(Arrays.asList(value)));

    instance_bis.put(variable,value);
    this.bt.backtrack(instance_bis,0);
    System.out.println("isExplanation  "+this.bt.getList().isEmpty());
    return this.bt.getList().isEmpty();
  }

  public Map<Variable,String> getDiagnosis(Variable variable, String value) {
    Map<Variable,String> res = new HashMap<Variable,String>();
    res.putAll(this.current_instance);
    Map<Variable,String> choices = new HashMap<Variable,String>();
    choices.putAll(this.current_instance);
    Map<Variable,String> res_bis;

    for (Variable var : choices.keySet()) {
      res_bis = new HashMap<>(res);
      res_bis.remove(var);
      System.out.println("\n\nvar = "+var);
      System.out.println("e = "+res);
      System.out.println("ePrime = "+res_bis);
      if (this.isExplanation(res_bis,variable,value)) {
        res = new HashMap<>(res_bis);
      }
    }
    return res;
  }
}
