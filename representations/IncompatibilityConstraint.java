package representations;

import java.util.*;

public class IncompatibilityConstraint extends Rule {


  /**
    * Constructeur de la classe IncompatibilityConstraint.
    * @param premisse , qui est un Map de Variable et de String.
    *
    */
  public IncompatibilityConstraint(Map<Variable,String> premisse) {
		super(premisse,null);
  }

  /**
    * Surcharge de la méthode isSatisfiedBy().
    * @param test , qui est un Map de Variable et de String.
    * @return true , si c'est satisfait. Sinon retourne false.
    */
  @Override
  public boolean isSatisfiedBy(Map<Variable,String> test) {
		for (Variable v : this.premisse.keySet()) {
			if (!(premisse.get(v).equals(test.get(v))) || test.get(v).equals("")) {
				return true;
			}
		}
		return false;
  }

  /**
    *
    *
    *
    */
  @Override
  public boolean filter(Map<Variable,String> voiture, Map<Variable,Set<String>> domaines) {
    //return false;/*
    boolean tmp = false;
    for (Variable v : this.premisse.keySet()) {
      if (voiture.get(v).equals(this.premisse.get(v))){
        for (Variable d : this.premisse.keySet()){
          if (domaines.containsKey(d) && domaines.get(d).contains(voiture.get(v)) && !(d.equals(v))){
            domaines.get(d).remove(voiture.get(v));
            tmp=true;
          }
        }
        return tmp;
      }
    }
    return tmp;
  }

  /**
    * Surcharge de la méthode toString().
    * @return String , qui sera la premisse plus une phrase.
    *
    */
  @Override
  public String toString(){
    return premisse + "\n IncompatibilityConstraint";
  }
}
