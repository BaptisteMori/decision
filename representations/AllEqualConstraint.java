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
    for (int i = 1; i < voiture.size(); i++) {
      if (this.scope.contains(voiture.get(i))) {
        if (!(voiture.get(i).equals(voiture.get(0)))) {
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
