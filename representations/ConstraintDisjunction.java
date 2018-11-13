package representations;

import java.util.*;

public class ConstraintDisjunction implements Constraint {

  private Constraint[] constraints;
  private Set<Variable> scope = new HashSet<Variable>();

  /**
    * Constructeur de la classe ConstraintDisjunction.
    * @param constraints , qui est un tableau de Constraint.
    *
    */
  public ConstraintDisjunction(Constraint[] constraints) {
		this.constraints=constraints;
  }

  /**
    * Méthode qui verifie si une contrainte est satisfaite.
    * @param voiture , qui est un Map de Variable et de String.
    * @return union_result , qui est un boolean.
    */
	public boolean isSatisfiedBy(Map<Variable,String> voiture) {
		boolean union_result = false;
		for (int i=0; i<this.constraints.length; i++) {
			union_result = union_result || this.constraints[i].isSatisfiedBy(voiture);
		}
		return union_result;
	}

  /**
    * Surcharge de la méthode getScope(), qui retourne le scope.
    * @return this.scope , qui est un Set de Variable.
    *
    */
  @Override
  public Set<Variable> getScope() {
    for (int i = 0; i < this.constraints.length; i++) {
      this.scope.addAll(this.constraints[i].getScope());
    }
    return this.scope;
  }

  /**
    *
    *
    *
    */
  @Override
  public boolean filter(Map<Variable,String> voiture, Map<Variable, Set<String>> domaines){
    //return false;/*
    boolean tmp = false;
    for (Constraint c : this.constraints) {
      tmp = ((Disjunction)c).filter(voiture, domaines);
      if (tmp) {
        return true;
      }
    }
    return false;
  }

  /**
    * Surcharge de la méthode toString(), qui permet d'afficher.
    * @return ch , qui est un String.
    *
    */
  @Override
  public String toString(){
    String ch = "";
    for(Constraint d : constraints){
      ch+=((Disjunction)d).toString();
    }
    return ch;
  }
}
