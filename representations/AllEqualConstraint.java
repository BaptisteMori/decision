package representations;

import java.util.*;

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
    * la contrainte est respectée ( si toutes es variables sont égales entres elles.)
    * @param voiture , qui est un Map de Variable et de String.
    * @return true , si la contrainte est satisfaite sinon retourne false.
    */
  @Override
  public boolean isSatisfiedBy(Map<Variable,String> voiture) {
		Object[] scope_array = this.scope.toArray();
		String test_value = voiture.get(scope_array[0]);
		for (int i = 0; i <= this.scope.size() -1 ; i++) {
			if (voiture.get(scope_array[i]).equals("")) {
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
    boolean tmp = false;
    Variable expected = new Variable();
    for (Variable v : this.scope) {
      if (voiture.get(v)!="") {
        expected = v;
        break;
      }
    }
    System.out.println("You sck2 " + this.scope);
    for (Variable v2 : this.scope) {
      if (!(expected.equals(v2)) && domaines.containsKey(v2) && domaines.get(v2).size()!=1) {
        tmp=true;
        Set<String> tmp_dom = new HashSet(v2.getDomaine());
        Variable tmp_var = new Variable(v2.getNom(), tmp_dom);
        domaines.get(tmp_var).clear();
        System.out.println("OKOK"+v2.getDomaine());
        domaines.get(tmp_var).add(voiture.get(expected));
        System.out.println("OKO"+v2.getDomaine());
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
    return "AllEqualConstraint";
  }
}
