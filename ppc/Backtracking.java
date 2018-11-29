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
  private Heuristic heuristic;

  /**
		* Constructeur de la classe Backtracking
		* @param variables , qui est un tableaux de Variable.
		* @param constraints , qui est un tableaux de Constraint.
    * @param heuristic , qui est un objet Heuristic
		*/
  public Backtracking(Variable[] variables, Constraint[] constraints,Heuristic heuristic) {
    this.variables = variables;
    this.constraints = constraints;
    this.heuristic = heuristic;
    for (Variable v : variables){
      this.unassigned_domains.put(v,new HashSet<String>(v.getDomaine()));
    }
  }

  public Backtracking(Variable[] variables, Constraint[] constraints){
    this(variables,constraints,null);
  }


/**
  * La méthode backtrack utilise l'algorithme de Backtracking.
  * @param map , qui est un Map de Variable et de String.
  * @param i , qui est un int.
  */
  public void backtrack(Map<Variable,String> map, int i) { // dans map voiture que les variables deja attribuée et dans l'autre map les variables qui n'ont pas encore de valeurs.
    if (this.heuristic!=null){
      this.heuristic.execute(this.variables,i,this.constraints);
    }
    if (this.allConstraintsSatisfiedBy(map) && map.containsValue("")) {
        Set<String> domaine = variables[i].getDomaine();
        // tout ce qui compte c'est les valeurs
        for (String valeur : domaine){
          // on assigne une des valeurs du domaine de la variable i
          map.put(variables[i],valeur);
          // si la map est complète et satisfait toutes les contraintes alors on l'ajoute
          if (!(map.containsValue("")) && this.allConstraintsSatisfiedBy(map)) {
            // pour reussir, faire une fonction qui copie map (pour pas l'écraser a chaque fois)
            Map<Variable,String> tmp = new HashMap<Variable,String>();
            tmp.putAll(map);
            this.list.add(tmp);
          } else {
            if (this.allConstraintsSatisfiedBy(map)){
              // recommence une récursion
              backtrack(map,i+1);
            }
          }
        }
        // backtrack
        map.put(variables[i],"");
    }
  }

  /**
    * La méthode backtrack utilise l'algorithme de Backtracking.
    * @param map , qui est un Map de Variable et de String.
    * @param i , qui est un int.
    * @param unassigned_domains_cop ; qui est un Map des variables non assignées et de leur domaine
    */
    public void backtrack(Map<Variable,String> map, int i,Map<Variable,Set<String>> unassigned_domains_cop) { // dans map voiture que les variables deja attribuée et dans l'autre map les variables qui n'ont pas encore de valeurs.
      if (this.allConstraintsSatisfiedBy(map) && map.containsValue("")) {

          boolean b = applyAllFilters(map,unassigned_domains_cop);
          if (this.heuristic!=null){
            this.heuristic.execute(this.variables,i,unassigned_domains_cop,this.constraints);
          }
          Set<String> domaine = new HashSet<String>(unassigned_domains_cop.get(variables[i]));

          // tout ce qui compte c'est les valeurs
          for (String valeur : domaine){
            //System.out.println(i+" Variable : "+variables[i]+" Domaine : "+variables[i].getDomaine() + " valaeur : "+valeur);
            map.put(this.variables[i],valeur);
            System.out.println("-----------------------");
            for (Variable v : this.variables){
              System.out.println(v +" : "+map.get(v)+" ; " + v.getDomaine() + " ; "+((unassigned_domains_cop.containsKey(v))? unassigned_domains_cop.get(v) : "_"));
            }
            System.out.println("-----------------------");
            // on assigne une des valeurs du domaine de la variable i
            // si la map est complète et satisfait toutes les contraintes alors on l'ajoute
            if (!(map.containsValue("")) && this.allConstraintsSatisfiedBy(map)) {
              // pour reussir, faire une fonction qui copie map (pour pas l'écraser a chaque fois)
              Map<Variable,String> tmp = new HashMap<Variable,String>();
              tmp.putAll(map);
              this.list.add(tmp);
              System.out.println("ajouté: " + tmp +"\n");

            } else {
              if (this.allConstraintsSatisfiedBy(map)){
                // recommence une récursion

                HashMap<Variable,Set<String>> udc = new HashMap<>();
                for (Variable v : unassigned_domains_cop.keySet()){
                  udc.put(v,new HashSet<String>(unassigned_domains_cop.get(v)));
                }
                backtrack(map,i+1,udc);
              }
            }
          }
          // backtrack
          map.put(variables[i],"");
      }
    }

  /**
		* Méthode permettant d'appliquer les filtres afin de réduire le champ de recherche.
		* @param voiture , qui est un Map de Variable et de String.
		* @param unassigned_domains , qui est un Map de Variable et de Set de String.
    * @return restart , qui est un boolean vrai.
		*/
  public boolean applyAllFilters(Map<Variable,String> voiture, Map<Variable, Set<String>> unassigned_domains) {

    boolean restart = false;
    // on boucle sur la liste des contraintes
    for (int i = 0; i< this.constraints.length; i++) {
      // application du filtre i et vérification si il a modifié le domaine
      boolean b = this.constraints[i].filter(voiture, unassigned_domains);
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

  /**
		* Méthode permettant de vérifier si toutes les contraintes sont vérifiées sur une "voiture"
		* @param voiture , qui est un map de Variable et de String.
		* @return true , si les contraintes sont respectées, sinon retourne false.
		*/
	public boolean allConstraintsSatisfiedBy(Map<Variable,String> voiture) {
		for (int i = 0; i< this.constraints.length; i++) {
			if(!(this.constraints[i].isSatisfiedBy(voiture))) {
				return false;
			}
		}
		return true;
	}

  /**
		* Méthode permettant de générer un nouveau Map.
		* @return map , qui est un Map de Variable et de String.
		*
		*/
  public Map<Variable,String> generateMap() {
    Map<Variable,String> map = new HashMap<Variable,String>();
    for (int i = 0; i < this.variables.length; i++) {
      map.put(this.variables[i],"");
    }
    return map;
  }

  /**
		* Méthode retournant un tableau de Variable.
		* @return this.variables , qui est un tableau de Variable.
		*
		*/
	public Variable[] getVariables() {
		return this.variables;
	}

  /**
		* Méthode retournant un tableau de Constraint.
		* @return this.constraint , qui est un tableau de Constraint.
		*
		*/
	public Constraint[] getConstraints() {
		return this.constraints;
	}

  public Map<Variable,Set<String>> getUnassignedDomains(){
    return this.unassigned_domains;
  }

  /**
		* Méthode retournant la liste des résultats du bactrack.
		* @return this.list , qui est une ArrayList de Map de Variable et de String.
		*
		*/
  public ArrayList<Map<Variable,String>> getList() {
    return this.list;
  }
}
