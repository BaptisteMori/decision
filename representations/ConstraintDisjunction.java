package representations;

import java.util.*;

public class ConstraintDisjunction {

  private Constraint[] constraints;

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
}
