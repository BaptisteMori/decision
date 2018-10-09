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
    for (Variable v : this.premisse.keySet()) {
      if (voiture.get(v) != "") {
        for (Variable v2 : this.premisse.keySet()) {
          if (!(v.equals(v2)) && domaines.containsKey(v2)) {
            domaines.get(v2).remove(voiture.get(v));
            return true;
          }
        }
      }
    }
    return false;
  }

  @Override
  public String toString(){
    return "IncompatibilityConstraint";
  }
}
