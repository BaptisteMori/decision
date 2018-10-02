package representations;

import java.util.*;

public class Rule implements Constraint {

  private Set<Variable> scope = new HashSet<Variable>();
  Map<Variable,String> premisse = new HashMap<>();
  Map<Variable,String> conclusion = new HashMap<>();

  public Rule(Map<Variable,String> premisse, Map<Variable,String> conclusion) {
    this.premisse = premisse;
    this.conclusion = conclusion;
  }

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

  public boolean conclusion(Map<Variable,String> test){
    for (Variable v: this.conclusion.keySet()) {
      if (test.get(v).equals(conclusion.get(v)) || test.get(v).equals("")) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean isSatisfiedBy(Map<Variable,String> test) {
    boolean p = premisse(test);
    boolean c = conclusion(test);

    return !p || c;
  }

	@Override
	public boolean filter(Map<Variable,String> voiture, Map<Variable, Set<String>> unassigned_domains) {
		boolean tmp = false;
		if (this.premisse(voiture) && this.premisse != null && unassigned_domains.size() != 0 ) {
      Variable unassigned_variable = null;
      int cpt = 0;

      // cheker si unassigned_domains contient une seul variable du scope de conclusion
      // et la sauvegarde
      for (Variable v : this.conclusion.keySet()) {
        if (unassigned_domains.keySet().contains(v)){
          unassigned_variable=v;
          cpt++;
        }
        if (cpt>1){
          return false;
        }
      }

      // on recupère la valeur attendu par la conclusion
      String expected = this.conclusion.get(unassigned_variable);
      // on cherche toues les variables ayant la valeur attendu
      for (Variable va : this.conclusion.keySet()){
        if (this.conclusion.get(va)==expected && !(va.equals(unassigned_variable)) && voiture.get(va)==expected){
          // une variables du scope de la conlusion est deja affecté
          tmp = true;
        }
      }
      if (tmp){
        // on supprime la valeur attendu
        return unassigned_domains.get(unassigned_variable).remove(expected);
      }else{
        unassigned_domains.get(unassigned_variable).clear();
        return unassigned_domains.get(unassigned_variable).add(expected);
      }


		}else{
      return false;
    }
	}
}
