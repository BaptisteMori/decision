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
    Set<Variable> scope = premisse.keySet();
    for (Variable v : scope) {
      if (voiture.get(v) != "") {
        System.out.println("pas vraiment ok");
        for (Variable v2 : scope) {
          if (!(v.equals(v2))) {
            System.out.println("presque ok");
            Set<String> dom = domaines.get(v2);
            Set<String> tmp_dom = new HashSet<>();
            tmp_dom.addAll(dom);
            for (String s : tmp_dom) {
              if (dom.contains(s) && s == voiture.get(v)) {
                dom.remove(s);
                System.out.println("ok");
                tmp = true;
              }
            }
            domaines.put(v2,dom);
          }
        }
      }
    }
    return tmp;
  }
}
