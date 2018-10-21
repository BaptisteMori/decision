package representations;

import java.util.*;

public class AllEqualConstraint implements Constraint {

  private Set<Variable> scope;

  public AllEqualConstraint(Set<Variable> scope) {
    this.scope = scope;
  }

  /*test si la constrainte est respectée (toutes les variables égales entre elles)*/
  @Override
  public boolean isSatisfiedBy(Map<Variable,String> voiture) {
		Object[] scope_array = this.scope.toArray();
		String test_value = voiture.get(scope_array[0]);
		for (int i = 0; i <= this.scope.size() -1 ; i++) {
			if (voiture.get(scope_array[i]).equals("")) {
				return true;
			}
			if(!(voiture.get(scope_array[i]).equals(test_value))) {
				return false;
			}
		}
		return true;
  }

  /*
    Méthode Override qui retourne toutes les
    variables de la prémice ou de la conclusion.
  */

  @Override
  public Set<Variable> getScope() {
		return this.scope;
  }

  /*
    Méthode qui Override la méthode filter et qui
    permet de réduire le domaine des variables non
    assignées en enlevant les valeurs non viables.
  */

  @Override
  public boolean filter(Map<Variable,String> voiture, Map<Variable, Set<String>> domaines){
    //return false;/*
    boolean tmp = false;
    Variable test = new Variable();
    for (Variable v : this.scope) {
      if (voiture.get(v)!="") {
        test = v;
        break;
      }
    }

    for (Variable v2 : this.scope) {
      if (!(test.equals(v2)) && domaines.containsKey(v2) && domaines.get(v2).size()!=1) {
        tmp=true;
        domaines.get(v2).clear();
        domaines.get(v2).add(voiture.get(test));
      }
    }
    return tmp;
  }

  @Override
  public String toString(){
    return "AllEqualConstraint";
  }
}
