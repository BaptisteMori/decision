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

  public boolean isIncompatible(Map<Variable,String> incomplete_instance, Variable variable, String value) {
    return true;
  }

  public Map<Variable,String> getDiagnostic(Variable variable, String value) {
    return null;
  }
}
