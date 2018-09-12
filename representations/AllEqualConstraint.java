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
    for (Map.Entry<Variable,String> criterion : voiture.entrySet()) {
      if (this.scope.contains(criterion.getKey())) {
				try {
					final String test_value = criterion.getValue();
				} catch(Exception e) {
					System.out.println(e);
				}

        if (!(criterion.getValue().equals(test_value))) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public Set<Variable> getScope() {
		return this.scope;
  }
}
