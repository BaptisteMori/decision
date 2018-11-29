package representations;

import java.util.*;

/** Classe fille de Rule où une seule affectation de la conclusion est suffisante pour satisfaire la contrainte.*/
public class Disjunction extends Rule {

  /**
    * Constructeur de la classe Disjunction.
    * @param premisse , qui est un Map de Variable et de String.
    * @param conclusion , qui est un Map de Variable et de String.
    */
  public Disjunction(Map<Variable,String> premisse, Map<Variable,String> conclusion) {
		super(premisse,conclusion);
  }


  /**
    * Surcharge de la méthode premisse qui permet de vérifier l'état d'une premisse.
    * @param test , qui est un Map de Variable et de String.
    * @return true
    */
  @Override
  public boolean premisse(Map<Variable,String> test) {
    if (!(this.premisse == null)) {
      for (Variable v : this.premisse.keySet()) {
        if (!(premisse.get(v).equals(test.get(v))) && !(test.get(v).equals(""))) {
          return false;
        }
      }
      return true;
    }
    return false;
  }

  /**
    * Surcharge de la méthode isSatisfiedBy() , qui regarde si une premisse et une conclusion sont satisfaites.
    * @param test , qui est un Map de Variable et de String.
    * @return boolean.
    */
	@Override
	public boolean isSatisfiedBy(Map<Variable,String> test) {
    boolean p = premisse(test);
    boolean c = conclusion(test);
    return p && c;
  }

  /**
    * Surcharge de la méthode toString().
    * @return String.
    *
    */
  @Override
  public String toString(){
    return " Disjunction : " + premisse + " " + conclusion;
  }
}
