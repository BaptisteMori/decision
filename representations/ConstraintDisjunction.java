package representations;

import java.util.*;

/** Union de plusieurs contraintes de disjonction.*/
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
    * @param voiture
    * La voiture à tester.
    * @return Le résultat du test.
    */
	public boolean isSatisfiedBy(Map<Variable,String> voiture) {
		boolean union_result = false;
		for (int i=0; i<this.constraints.length; i++) {
			union_result = union_result || this.constraints[i].isSatisfiedBy(voiture);//si une seule constraite est bonne, le booléen passe à true et le reste
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
