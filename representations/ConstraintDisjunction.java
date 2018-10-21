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
    return false;/*
    boolean tmp = false;
    for (Constraint c : this.constraints) {
      tmp = ((Disjunction)c).filter(voiture, domaines);
      if (tmp) {
        return true;
      }
    }
    return false;*/
  }

  @Override
  public String toString(){
    String ch = "";
    for(Constraint d : constraints){
      ch+=((Disjunction)d).toString();
    }
    return ch;
  }
}
