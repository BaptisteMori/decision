package representations;

import java.util.*;

public class ConstraintDisjunction implements Constraint {

  private Constraint[] constraints;
  private Set<Variable> scope = new HashSet<Variable>();

  public ConstraintDisjunction(Constraint[] constraints) {
		this.constraints=constraints;
  }

	public boolean isSatisfiedBy(Map<Variable,String> voiture) {
		boolean union_result = false;
		for (int i=0; i<this.constraints.length; i++) {
			union_result = union_result || this.constraints[i].isSatisfiedBy(voiture);
		}
		return union_result;
	}

  @Override
  public Set<Variable> getScope() {
    for (int i = 0; i < this.constraints.length; i++) {
      this.scope.addAll(this.constraints[i].getScope());
    }
    return this.scope;
  }

  @Override
  public boolean filter(Map<Variable,String> voiture, Map<Variable, Set<String>> domaines){
    boolean tmp = false;
    for (Variable v : this.scope) {
      if (voiture.get(v) != "") {
        for (Variable v2 : this.scope) {
          if (!(v.equals(v2))) {
            
          }
        }
      }
    }
    return true;
  }
}
