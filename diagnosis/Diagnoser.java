package diagnosis;

import representations.*;
import java.util.*;

public class Diagnoser {

  private ArrayList<Variable> variables;
  private ArrayList<Constraint> contraintes;
  private Map<Variable,String> current_instance;

  public Diagnoser(ArrayList<Variable> variables, ArrayList<Constraint> contraintes) {
    this.variables = variables;
    this.contraintes = contraintes;
    this.current_instance = new HashMap<Variable,String>();
  }

  public boolean isExplication(Map<Variable,String> incomplete_instance, Variable variable, String value) {

    return true;
  }

  public Map<Variable,String> getDiagnostic(Map<Variable,String> instance, Variable variable, String value) {
    Map<Variable,String> f = new HashMap<Variable,String>();
    f.putAll(this.current_instance);
    Map<Variable,String> f_bis = new HashMap<Variable,String>();
    f_bis.putAll(f);
    Map<Variable,String> current_copy = new HashMap<Variable,String>();
    current_copy.putAll(this.current_instance);
    Map<Variable,String> instance_bis = new HashMap<Variable,String>();
    while (!(f_bis.isEmpty())){
      for (Variable var : f.keySet()) {
        instance_bis.putAll(current_copy);
        instance_bis.remove(var);
        if(this.isExplication(instance_bis,variable,value)){
          current_copy = instance_bis;
        }
        f_bis.remove(var);
      }
    }
    return instance_bis;
  }
}
