package representations;

import java.util.*;

public class IncompatibilityConstraint extends Rule {

  public IncompatibilityConstraint(Map<Variable,String> premisse) {
		super(premisse,null);
  }

  @Override
  public boolean isSatisfiedBy(Map<Variable,String> test) {
		for (Variable v : this.premisse.keySet()) {
			if (!(premisse.get(v).equals(test.get(v))) || test.get(v).equals("")) {
				return true;
			}
		}
		return false;
  }

  @Override
  public boolean filter(Map<Variable,String> voiture, Map<Variable,Set<String>> domaines) {
    boolean tmp = false;
    for (Variable v : this.premisse.keySet()) {
      if (voiture.get(v).equals(this.premisse.get(v))){
        for (Variable d : this.premisse.keySet()){
          if (domaines.containsKey(d) && domaines.get(d).contains(voiture.get(v))){
            domaines.get(d).remove(voiture.get(v));
            tmp=true;
          }
        }
      }
    }
    return tmp;
  }

  @Override
  public String toString(){
    return premisse + "\n IncompatibilityConstraint";
  }
}
