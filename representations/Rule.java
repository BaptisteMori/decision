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

  @Override
  public boolean isSatisfiedBy(Map<Variable,String> test) {
    // premisse
    if (!(this.premisse == null)) {
      for (Variable v : this.premisse.keySet()) {
        if (!(premisse.get(v).equals(test.get(v)))) {
          return true;
        }
      }
    }
    // conclusion
    for (Variable v: this.conclusion.keySet()) {
      if (conclusion.get(v).equals(test.get(v))) {
        return true;
      }
    }
    return false;
  }

	@Override
	public boolean filter(Map<Variable,String> voiture, Map<Variable, Set<String>> unassigned_domains) {
		boolean tmp = false;
    /*Variable[] var_set = this.premisse.keySet().toArray(new Variable[this.premisse.keySet().size()]);
    String couleur_var = "";
    for (Variable v : var_set) {
      if (v.getNom().equals("couleur_toit")) {
        couleur_var = voiture.get(v);
        System.out.println(couleur_var);
      }
    }*/

    /*PAS FONCTIONNEL
    if (this.isSatisfiedBy(voiture) && this.premisse != null) {
      for (Variable v : this.conclusion.keySet()) {
        if (!(voiture.get(v).equals(""))) {

          Set<String> dom = unassigned_domains.get;
          Set<String> tmp_dom = new HashSet<>();
          tmp_dom.addAll(dom);
          if (this.conclusion.get(v) == voiture.get(v)){

          }*/

          /*FONCTIONNEL MAIS BIZARRE (avec le premier commentaire)
          for (Variable v2 : this.conclusion.keySet()) {
            if (!(v.equals(v2))) {
              Set<String> dom = unassigned_domains.get(v2);
              Set<String> tmp_dom = new HashSet<>();
              tmp_dom.addAll(dom);
              for (String s : tmp_dom) {
                if(!(voiture.get(v).equals(couleur_var)) && s != couleur_var) {
                  dom.remove(s);
                  tmp = true;
                }
              }
              unassigned_domains.put(v2,dom);
            }
          }
        }
      }
    }*/
    return tmp;
  }
}
