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

  public void backtrack(Map<Variable,String> voiture, int nb_variables) {

    if (this.allConstraintsSatisfiedBy(voiture)) {
      if (!(voiture.containsValue(""))) {
        System.out.println(voiture);
      }
        this.atomicAttribution(voiture,this.variables[nb_variables]);
        Set<String> current_domain = this.variables[nb_variables].getDomaine();
        current_domain.remove(voiture.get(this.variables[nb_variables]));
        this.variables[nb_variables].setDomaine(current_domain);
        backtrack(voiture, nb_variables);
    } else {
      this.atomicAttribution(voiture, this.variables[nb_variables-1]);
      Set<String> current_domain = this.variables[nb_variables-1].getDomaine();
      current_domain.remove(voiture.get(this.variables[nb_variables-1]));
      this.variables[nb_variables-1].setDomaine(current_domain);
      backtrack(voiture, nb_variables-1);
    }

    // while (nb_variables < this.variables.length) {
    //   tmp = atomicAttribution(voiture, this.variables[nb_variables]);
    //   valuelist.add(tmp.get(this.variables[nb_variables]));
    //
    //     System.out.println(tmp);
    //     backtrack(tmp, nb_variables+1);
    //   } else if (valuelist.size() == this.variables[nb_variables].getDomaine().size()) {
    //     tmp = atomicAttribution(tmp, this.variables[nb_variables-1]);
    //     valuelist=new ArrayList<String>();
    //     backtrack(tmp, nb_variables+1);
    //   } else {
    //     tmp = atomicAttribution(tmp, this.variables[nb_variables]);
    //     backtrack(tmp, nb_variables+1);
    //   }
    // }
  }

	public Map<Variable,String> atomicAttribution(Map<Variable,String> map, Variable v) {

    Random r = new Random();
    String[] domaine = v.getDomaine().toArray(new String[v.getDomaine().size()]);
    System.out.println(domaine.length);
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

  public Map<Variable,String> generateMap() {
    Map<Variable,String> map = new HashMap<Variable,String>();
    for (int i = 0; i < this.variables.length; i++) {
      map.put(this.variables[i],"");
    }
    return map;
  }

	public Variable[] getVariables() {
		return this.variables;
	}

	public Constraint[] getConstraints() {
		return this.constraints;
	}
}
