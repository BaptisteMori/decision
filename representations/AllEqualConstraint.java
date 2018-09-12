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
		for (int i = 1; i < this.scope.size() ; i++) {
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
}
