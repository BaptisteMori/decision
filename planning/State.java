package planning;

import representations.*;
import java.util.*;

public class State {

  private Map<Variable,String> state;

  public State(Map<Variable,String> state) {
    this.state = state;
  }

  @Override
	public int hashCode() {
		int code=7;
		code+=47*code+this.state.size();
		return code;
	}

  @Override
  public boolean equals(Object o) {
    if (o==this) {
			return true;
		}
    if (!(o instanceof State)) {
			return false;
		}
    ArrayList<String> this_string = new ArrayList<>();
    for (Variable v : this.state.keySet()) {
      if (!(this.state.get(v).equals(((State)o).getState().get(v)))) {
        return false;
      }
    }
    return true;
  }

  public Map<Variable,String> getState() {
    return this.state;
  }

  public void setState(Map<Variable,String> state) {
    this.state = state;
  }
}
