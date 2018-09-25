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

  @Overrride
  public boolean filter(Map<Variable,String> voiture, Map<Variable, Set<String>> domaines){

  }

}
