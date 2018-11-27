package diagnosis;

import representations.*;
import java.util.*;
import ppc.*;

public class Diagnoser {

  private ArrayList<Variable> variables;
  private ArrayList<Constraint> contraintes;
  private Map<Variable,String> current_instance;
  private Backtracking bt;

/**
  * Le constructeur de la classe
  *@param variables une liste d'objet Variable
  *@see representations.Variable
  *@param contraintes une liste d'objet Constraint
  *@see representations.Constraint
  */
  public Diagnoser(ArrayList<Variable> variables, ArrayList<Constraint> contraintes) {
    this.variables = variables;
    this.contraintes = contraintes;
    this.current_instance = new HashMap<Variable,String>();
    this.bt = new Backtracking(this.variables.toArray(new Variable[this.variables.size()]), this.contraintes.toArray(new Constraint[contraintes.size()]));
  }

/**
  * Méthode qui ajoute un nouvel élément au Map current_instance
  *@param var un objet Variable
  *@param value une valeur (String) à associer à l'objet Variable
  */
  public void add(Variable var, String value){
    this.current_instance.put(var,value);
  }

/**
  * Méthoque qui teste si le Map donné en argument est une explication de pourquoi on ne
  * peut pas associer l'argument value à l'argument variable
  *@param instance un Map de Variable et leur valeur (String)
  *@param variable un objet Variable auquel on veut associer une valeur
  *@param value la valeur (String) que l'on veut associer à l'objet Variable
  *@return true si l'argument instance est une explication
  */
  public boolean isExplanation(Map<Variable,String> instance, Variable variable, String value) {
    Map<Variable,String> instance_bis;
    instance_bis = new HashMap<>(instance);
    for (Variable var : instance_bis.keySet()) {
      var.setDomaine(new HashSet<String>(Arrays.asList(instance_bis.get(var))));
    }
    variable.setDomaine(new HashSet<String>(Arrays.asList(value)));

    instance_bis.put(variable,value);
    this.bt.backtrack(instance_bis,0);
    return this.bt.getList().isEmpty();
  }


/**
  * Méthode qui permet de trouver l'explication minimale au sens de l'inclusion de pourquoi
  * on ne peut pas avoir variable = value
  *@param variable un objet Variable auquel on veut associer une valeur
  *@param value la valeur (String) que l'on veut associer à l'objet Variable
  *@return un Map qui est une explication de pourquoi on ne peut pas avoir variable = value
  */
  public Map<Variable,String> getDiagnosis(Variable variable, String value) {
    Map<Variable,String> res = new HashMap<Variable,String>();
    res.putAll(this.current_instance);
    Map<Variable,String> choices = new HashMap<Variable,String>();
    choices.putAll(this.current_instance);
    Map<Variable,String> res_bis;

    for (Variable var : choices.keySet()) {
      res_bis = new HashMap<>(res);
      if (res_bis.size() > 1) {
        res_bis.remove(var);
        if (this.isExplanation(res_bis,variable,value)) {
          res = new HashMap<>(res_bis);
        }
      }
    }
    return res;
  }
}
