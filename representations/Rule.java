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
		if (this.isSatisfiedBy(voiture) && this.premisse != null) {
			for (Variable v : this.conclusion.keySet()) {
				if (unassigned_domains.size()==1 && voiture.get(v).equals("")) {
          for (Variable va : this.conclusion.keySet()){
            if(!(voiture.get(va).equals(""))) {
              String value = voiture.get(va);
              Set<String> dom = unassigned_domains.get(va);
              Set<String> tmp_dom = new HashSet<>();
              tmp_dom.addAll(dom);
              for (String s : tmp_dom){
                if (dom.contains(s) && s == value){
                  dom.remove(s);
                  tmp = true;
                }
              }
              unassigned_domains.put(v,dom);
            }
          }
          //voiture.put(v,this.conclusion.get(v));
					//return tmp;
				}
			}
		}
		return tmp;
	}
}
