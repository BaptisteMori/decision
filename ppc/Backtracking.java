package ppc;

import representations.*;
import examples.*;
import java.util.*;

public class Backtracking {

  private Variable[] variables;
  private Constraint[] constraints;
  private Map<Variable,Integer> cpt = new HashMap<Variable,Integer>();
  private ArrayList<Map<Variable,String>> list = new ArrayList<>();

  public Backtracking(Variable[] variables, Constraint[] constraints) {
    this.variables = variables;
    this.constraints = constraints;
  }

  public Map<Variable, String> backtrack(Map<Variable,String> map, int i) {
    if (this.allConstraintsSatisfiedBy(map) && map.containsValue("")) {
        String[] domaine = variables[i].getDomaine().toArray(new String[variables[i].getDomaine().size()]);
        // tout ce qui compte c'est les valeurs
        for (String valeur : domaine){
          System.out.println("\n" + i + "\n");
          System.out.println(variables[i]+ " " + valeur);
          map.put(variables[i],valeur);
          // si le dictionnaire est plein
          if (!(map.containsValue("")) && this.allConstraintsSatisfiedBy(map)) {
            System.out.println("Yep1 " + map);
            // pour reussir, faire une fonction qui copie map (pour pas l'écraser a chaque fois)

            this.list.add(map);
            System.out.println("list " +this.list);
          } else {
            if (this.allConstraintsSatisfiedBy(map)){
              System.out.println("Bof\n" + map);
              backtrack(map,i+1);
            }
          }
        }
        return map;
    }
    return null;
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

  public ArrayList<Map<Variable,String>> getList() {
    return this.list;
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
