package representations;

import java.util.*;

public class Disjunction extends Rule {

  public Disjunction(Map<Variable,String> premisse, Map<Variable,String> conclusion) {
		super(premisse,conclusion);
  }


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

	@Override
	public boolean isSatisfiedBy(Map<Variable,String> test) {
    boolean p = premisse(test);
    boolean c = conclusion(test);
    System.out.println("conc : " + c);

    return p && c;
  }

  @Override
  public String toString(){
    return "disjunction\n";
  }
}
