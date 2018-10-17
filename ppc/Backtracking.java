package ppc;

import representations.*;
import examples.*;
import java.util.*;

public class Backtracking {

  private Variable[] variables;
  private Constraint[] constraints;
  private Map<Variable,Integer> cpt = new HashMap<Variable,Integer>();
  private ArrayList<Map<Variable,String>> list = new ArrayList<>();
  private Map<Variable,Set<String>> unassigned_domains = new HashMap<>();

  public Backtracking(Variable[] variables, Constraint[] constraints) {
    this.variables = variables;
    this.constraints = constraints;
  }

/*
  La méthode backtrack, utilise l'algorithme de Backtracking.
*/

  public void backtrack(Map<Variable,String> map, int i) { // dans map voiture que les variables deja attribuée et dans l'autre map les variables qui n'ont pas encore de valeurs.
    // application de tous les filtres pour réduire les domaines des variables contenue dans unassigned_domains
    applyAllFilters(map, this.unassigned_domains);
    if (this.allConstraintsSatisfiedBy(map) && map.containsValue("")) {
        String[] domaine = variables[i].getDomaine().toArray(new String[variables[i].getDomaine().size()]);
        // tout ce qui compte c'est les valeurs
        for (String valeur : domaine){
          System.out.println(i+" Variable : "+variables[i]+" Domaine : "+variables[i].getDomaine() + " valaeur : "+valeur);
          // on assigne une des valeurs du domaine de la variable i
          map.put(variables[i],valeur);


          // création du unassigned_domains
          // suppression de tous les éléments de unassigned_domains
          this.unassigned_domains.clear();
          // parcour de toutes les variables
          for (Variable v : map.keySet()) {
            // vérifié si elles n'ont pas été assigné
            if (map.get(v)==""){
              // récupérer le domaine de cette variable
              Set<String> tmp_dom = v.getDomaine();
              // assigné ce domaine à la variable dans le unassigned_domains
              this.unassigned_domains.put(v, tmp_dom);
            }
          }
          unassigned_domains.remove(variables[i]);


          for (Variable v : map.keySet()){
            System.out.println(v +" : "+map.get(v)+" ; "+((unassigned_domains.containsKey(v))? unassigned_domains.get(v) : " "));
          }

          System.out.println("---------\n");
          // si le dictionnaire est plein
          if (!(map.containsValue("")) && this.allConstraintsSatisfiedBy(map)) {
            // pour reussir, faire une fonction qui copie map (pour pas l'écraser a chaque fois)
            Map<Variable,String> tmp = new HashMap<Variable,String>();
            tmp.putAll(map);
            this.list.add(tmp);
            System.out.println("ajouté: " + tmp +"\n");
          } else {
            if (this.allConstraintsSatisfiedBy(map)){
              backtrack(map,i+1);
            }
          }
        }
        map.put(variables[i],"");
    }
  }

  public boolean applyAllFilters(Map<Variable,String> voiture, Map<Variable, Set<String>> unassigned_domains) {
    boolean restart = false;
    // on boucle sur la liste des contraintes
    for (int i = 0; i< this.constraints.length; i++) {
      // application du filtre i et vérification si il a modifié le domaine
      boolean b = this.constraints[i].filter(voiture, unassigned_domains);
      System.out.println(this.constraints[i]+ " : " + b);
      System.out.println("\n");
      // si il y a eu une modification on passe restart à true
      if(b){
        restart = true;
      }
    }
    // si restart est à true on réaplique de façon récursive les filters 
    if (restart) {
      applyAllFilters(voiture, unassigned_domains);
    }
    return restart;
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
  //                 HEURISTIC
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
