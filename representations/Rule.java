package representations;

import java.util.*;

public class Rule implements Constraint {

  private Set<Variable> scope = new HashSet<Variable>();
  Map<Variable,String> premisse = new HashMap<>();
  Map<Variable,String> conclusion = new HashMap<>();

  /**
		*	Constructeur de la classe.
	  * @param premisse , qui est une Map de Variable et de String.
	  * @param conclusion , qui est un Map de Variable et de String.
	  *
	  */

  public Rule(Map<Variable,String> premisse, Map<Variable,String> conclusion) {
    this.premisse = premisse;
    this.conclusion = conclusion;
  }


  /**
		* Méthode qui retourne le Scope.
	  * @return this.scope , qui retourne un Set de Variable contenant les premisses,
	  * et les conclusions.
	  *
	  */

	@Override
  public Set<Variable> getScope() {
    if (this.premisse != null) {
      this.scope.addAll(this.premisse.keySet());
    }
    if (this.conclusion != null) {
      this.scope.addAll(this.conclusion.keySet());
    }
    //System.out.println(this.scope);
		return this.scope;
  }

  /**
		*	Méthode de type boolean qui regarde l'état d'une premisse.
	  * @param test , qui est une Map de Variable et de String.
	  * @return false , si la premisse n'est pas vide et qu'elle est égale à une autre premisse.
	  * @return true , dans les autres cas.
	  */

  public boolean premisse(Map<Variable,String> test){
    if (!(this.premisse == null)) {
      for (Variable v : this.premisse.keySet()) {
        if (!(premisse.get(v).equals(test.get(v)))) {
          return false;
        }
      }
      return true;
    }
    return true;
  }


  /**
		*	Méthode de type boolean qui regarde l'état d'une conclusion.
	  * @param test , qui est une Map de Variable et de String.
	  * @return true , si la conclusion est présente ou si il n'y a rien d'autre.
	  * @return false , dans les autres cas.
	  */

  public boolean conclusion(Map<Variable,String> test){
    for (Variable v: this.conclusion.keySet()) {
      if (test.get(v).equals(conclusion.get(v)) || test.get(v).equals("")) {
        return true;
      }
    }
    return false;
  }

  /**
		*	Méthode qui renverra un boolean et qui verifie si une Variable satisfait une autre Variable.
	  * @param test , qui est une  Map de Variable et de String.
	  * @return (!p || c) , qui est la table de vérité
	  *
	  */

  @Override
  public boolean isSatisfiedBy(Map<Variable,String> test) {
    boolean p = premisse(test);
    boolean c = conclusion(test);

    return !p || c;
  }

  /**
		* Méthode qui filtre le Domaine des Variables qui sont concernées par cette Contrainte.
	  * @param voiture , qui est une Map de Variable et de String.
	  * @param unassigned_domains , qui est une Map de Variable ainsi qu'un Set de String.
	  * @return false , si elle n'a pas filtrer les Variables.
	  */


	@Override
	public boolean filter(Map<Variable,String> voiture, Map<Variable, Set<String>> unassigned_domains) {
    //return false;
    System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		boolean tmp = false;
    // si la premisse est satisfaite,
    // la premisse est différente de null,
    // et la taille du unassigned_domains est supérieur à 0
    // on continue
		if (this.premisse(voiture) && this.premisse != null && unassigned_domains.size() > 0 ) {

      Variable unassigned_variable = null;
      int cpt = 0;

      // cheker si unassigned_domains contient une seul variable du scope de conclusion
      // et la sauvegarde
      for (Variable v : this.conclusion.keySet()) {
        if (unassigned_domains.keySet().contains(v) && voiture.get(v)!="") {
          // on stock la variable
          unassigned_variable=v;
          cpt++;
        }
        // si le compteur est supérieur à 1 ça signifie qu'il reste plus d'un domaine
        if (cpt>1){
          return false;
        }
      }

      if (unassigned_variable==null){return false;}
      // on recupère la valeur attendu par la conclusion
      String expected = this.conclusion.get(unassigned_variable);
      if (!(unassigned_domains.get(unassigned_variable).contains(expected))){return false;}
      // on cherche toues les variables ayant la valeur attendu
      for (Variable va : this.conclusion.keySet()){
        if (this.conclusion.get(va)==expected && !(va.equals(unassigned_variable)) && voiture.get(va)==expected ){
          // une variables du scope de la conlusion est deja affecté
          tmp = true;
        }
      }
      if (tmp){
        // on supprime la valeur attendu
        return unassigned_domains.get(unassigned_variable).remove(expected);
      }else{
        // on supprime toutes les valeurs
        unassigned_domains.get(unassigned_variable).clear();
        // et on y ajoute celle attendue
        return unassigned_domains.get(unassigned_variable).add(expected);
      }
		}else{
      return false;
    }
	}
}
