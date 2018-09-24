package ppc;

import representations.*;
import examples.*;
import java.util.*;

public class Backtracking {

  private Variable[] variables;
  private Constraint[] constraints;
  private Map<Variable,Integer> cpt = new HashMap<Variable,Integer>();

  public Backtracking(Variable[] variables, Constraint[] constraints) {
    this.variables = variables;
    this.constraints = constraints;
  }

  public void backtrack(Map<Variable,String> map, int i) {
    String value = "";
    if (this.allConstraintsSatisfiedBy(map) && map.containsValue("")) {
      value = atomicAttribution(variables[i]);
      map.put(variables[i],value);
      if (allConstraintsSatisfiedBy(map)) {
        if (!(map.containsValue(""))) {
          System.out.println(map);
        } else {
          System.out.println(map);
          backtrack(map, i+1);
        }
      } else {
        System.out.println(map);
        backtrack(map,i-1);
      }
    }
  }

  public void backtrack2(Map<Variable,String> voiture, int nb_variables) {

    if (this.allConstraintsSatisfiedBy(voiture)) {
      if (!(voiture.containsValue(""))) {
        System.out.println(voiture);
      } else {
        if (this.variables[nb_variables].getDomaine().size() > 0) {
          this.atomicAttribution2(voiture,this.variables[nb_variables]);
          Set<String> current_domain = this.variables[nb_variables].getDomaine();
          current_domain.remove(voiture.get(this.variables[nb_variables]));
          this.variables[nb_variables].setDomaine(current_domain);
          backtrack(voiture, nb_variables);
        }
      }
    } else {
      this.atomicAttribution2(voiture, this.variables[nb_variables-1]);
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

  public String atomicAttribution(Variable v) {
    Random r = new Random();
    String[] domaine = v.getDomaine().toArray(new String[v.getDomaine().size()]);
    String value = domaine[r.nextInt(domaine.length)];
    return value;
  }

	public Map<Variable,String> atomicAttribution2(Map<Variable,String> map, Variable v) {

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

  // ********************************************
  // HEURISTIC
  // ********************************************

  public void heuristic() {
    //SUPPRIMER DANS LA LISTE DES VARIABLES DES CONTRAINTES CELLES DEJA ASSIGNEES
    Variable[] scopeTMP;
    ArrayList<Variable> scope = new ArrayList<>();
    for (Constraint c : constraints) {
      scopeTMP = c.getScope().toArray(new Variable[c.getScope().size()]);
      for (Variable v : scopeTMP) {
        scope.add(v);
      }
    }
    for (int i = 0; i< scope.size(); i++) {
      int ncpt = 1;
      for(int j = 0; j< scope.size(); j++) {
        if (i != j) {
          if (scope.get(i).equals(scope.get(j))) {
            ncpt += 1;
          }
        }
      }
      if(!(this.cpt.containsKey(scope.get(i)))) {
        this.cpt.put(scope.get(i),ncpt);
      }
    }
  }

  public Variable heuristicDomaine() {
    ArrayList<Variable> vDom = new ArrayList<>();
    int tmp = 0;
    for (int v : this.cpt.values()) {
      if (v > tmp) {
        tmp = v;
      }
    }

    for (Variable v : this.cpt.keySet().toArray(new Variable[this.cpt.size()])) {
      if (this.cpt.get(v) == tmp) {
        vDom.add(v);
      }
    }

    tmp = 100;
    Variable vMinDom = null;
    for(Variable v : vDom) {
      if (v.getDomaine().size() < tmp) {
        tmp = v.getDomaine().size();
        vMinDom = v;
      }
    }
    return vMinDom;
  }


}
