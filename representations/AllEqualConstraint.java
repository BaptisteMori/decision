package representations;

import java.util.*;

/** Contraite d'égalité entre toutes les valeurs d'un certain nombre de variables.*/
public class AllEqualConstraint implements Constraint {

  private Set<Variable> scope;


  /**
    * Constructeur de la classe AllEqualConstraint
    * @param scope , qui est un Set de Variable.
    *
    */
  public AllEqualConstraint(Set<Variable> scope) {
    this.scope = scope;
  }


  /**
    * Surcharge de la méthode isSatisfiedBy() qui teste si
    * la contrainte est respectée ( si toutes les variables sont égales entres elles.)
    * @param voiture
    * La voiture à tester.
    * @return true , si la contrainte est satisfaite sinon retourne false.
    */
  @Override
  public boolean isSatisfiedBy(Map<Variable,String> voiture) {
		Object[] scope_array = this.scope.toArray();
		String test_value = voiture.get(scope_array[0]);
		for (int i = 0; i <= this.scope.size() -1 ; i++) {
			if (!(voiture.containsKey(scope_array[i])) || voiture.get(scope_array[i]).equals("")) {
				return true;
			}
			if(!(voiture.get(scope_array[i]).equals(test_value))) {
				return false;
			}
		}
		return true;
  }


  /**
    * Override de la méthode getScope(), qui retourne toutes
    * les variables de la premisse ou de la conclusion.
    * @return this.scope , qui est un Set de Variable.
    */
  @Override
  public Set<Variable> getScope() {
		return this.scope;
  }


  /**
    * Méthode qui Override la méthode filter et qui permet de réduire
    * le domaine des variables non assignées en enlevant les valeurs non viables.
    * @param voiture , qui est un Map de Variable et de String.
    * @param domaines , qui est un Map de Variable et de Set de String.
    * @return tmp , un boolean
    */
  @Override
  public boolean filter(Map<Variable,String> voiture, Map<Variable, Set<String>> domaines){
    //return false;/*

    // variable pour savoir si on a modifié quelque le domaine
    boolean tmp = false;
    // variable qui est le domaine que l'on expecte
    String expected = null;
    // on cherche parmis les variables affecté par les contraintes
    for (Variable v : this.scope) {
      // la première variable qui est affecté( etant donné que c'est AllEqualConstraint)
      if (voiture.get(v)!="") {
        expected = voiture.get(v);
        break;
      }
    }

    if (expected!=null){
      for (Variable v2 : this.scope) {
        // si la variable attendu est differente de v2
        // unassigned_domains contient v2
        // et que dans le domaine du unassigned_domains de la variable v2 il y ait au moins 2 valeurs
        if (domaines.get(v2).size()!=1) {
          //on modifie le domaine
          tmp=true;
          // on créé un domaine temporaire
          //Set<String> tmp_dom = new HashSet(voiture.get(expected));
          //Variable tmp_var = new Variable(v2.getNom(), tmp_dom);
          domaines.get(v2).clear();
          domaines.get(v2).add(expected);
        }
      }
    }
    return tmp;
  }


  /**
    * Surcharge de la méthode toString()
    * @return String .
    *
    */
  @Override
  public String toString(){
    return "AllEqualConstraint : " + scope;
  }
}
