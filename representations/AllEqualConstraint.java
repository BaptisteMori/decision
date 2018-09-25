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

  @Override
  public Set<Variable> getScope() {
		return this.scope;
  }

  @Override
  public boolean filter(Map<Variable,String> voiture, Map<Variable, Set<String>> domaines){
    boolean tmp = false;
    for (Variable v : this.scope) {
      if (voiture.get(v) != ""){
        for (Variable v2 : this.scope) {
          if (!(v.equals(v2))) {
            Set<String> dom = domaines.get(v2);
            Set<String> tmp_dom = new HashSet<>();
            tmp_dom.addAll(dom);
            for (String s : tmp_dom){
              if (dom.contains(s) && s!=voiture.get(v)){
                dom.remove(s);
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
