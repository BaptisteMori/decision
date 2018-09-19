package ppc;

import representations.*;
import examples.*;
import java.util.*;

public class Backtracking {

  private Variable[] variables;
  private Constraint[] constraints;

  public Backtracking(Variable[] variables, Constraint[] constraints) {
    this.variables = variables;
    this.constraints = constraints;
  }

	public Map<Variable,String> atomicAttribution(Map<Variable,String> map, Variable v) {

		Random r = new Random();
		String[] domaine = v.getDomaine().toArray(new String[v.getDomaine().size()]);
		String value = domaine[r.nextInt(domaine.length)];
		map.put(v,value);
		System.out.println(map);
		return map;
	}

	public boolean allConstraintsSatisfiedBy(Map<Variable,String> voiture) {
		for (int i = 0; i< this.constraints.length; i++) {
			if(!(this.constraints[i].isSatisfiedBy(voiture))) {
				return false;
			}
		}
		return true;
	}

	public Variable[] getVariables() {
		return this.variables;
	}

	public Constraint[] getConstraints() {
		return this.constraints;
	}

}
