package planning;

import representations.*;
import java.util.*;

public class Action {

  private Map<Variable,String> premisse;
  private Map<Variable,String> conclusion;

  public Action(Map<Variable,String> premisse, Map<Variable,String> conclusion) {
    this.premisse = premisse;
    this.conclusion = conclusion;
  }

  public boolean is_applicable(State state) {
    for (Variable v : premisse.keySet()) {
      if (state.getState().get(v) != premisse.get(v)) {
        return false;
      }
    }
    return true;
  }

  public State apply(State state) {
    Map<Variable,String> new_map = new HashMap<>();
    new_map.putAll(state.getState());

    if (this.is_applicable(state)) {
      for (Variable v : this.conclusion.keySet()) {
        new_map.put(v,this.conclusion.get(v));
      }
    }
    State new_state = new State(new_map);
    return new_state;
  }
}
