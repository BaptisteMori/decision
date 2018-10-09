package planning;

import java.util.*;

public class PlanningProblem {

  State init;
  ArrayList<State> goals;
  ArrayList<Action> actions;

  public PlanningProblem(State init, ArrayList<State> goals, ArrayList<Action> actions) {
    this.init = init;
    this.goals = goals;
    this.actions = actions;
  }

  public boolean satisfies(State state) {
    for (State s : goals) {
      if (s.getState().equals(state.getState())) {
        return true;
      }
    }
    return false;
  }

  public Stack<Action> dfs(State state, Stack<Action> plan, ArrayList<State> closed) {
    if (satisfies(state)) {
      return plan;
    } else {
      for (Action act : this.actions) {
        if (act.is_applicable(state)) {
          State next = act.apply(state);
          this.actions.remove(act);
          if (!(closed.contains(next))) {
            plan.push(act);
            closed.add(next);
            Stack subplan = dfs(next, plan, closed);
            if (sublan != null) {
              return subplan;
            }
            plan.pop;
          }
          this.actions.add(act);
        }
      }
      return null;
    }
  }

/*  public Stack<Action> dfsIteratif(State state) {
    ArrayList<State> closed = new ArrayList<>();
    Stack<Action> plan = new Stack();
    Stack<State> state_plan = new Stack();
    Stack<Action> action_done = new Stack();
    State present_state = state;
    while (!(satisfies(present_state))) {
      for (Action act : this.actions) {
        State next = act.apply(present_state);
        state_plan.push(next);
        action_done.push(act);
      }
      present_state = state_plan.pop;

      plan.push(action_done.pop);

    }
  }*/

  public Stack<Action> bfs() {
    Map<State,State> father = new HashMap<>();
    Map<State,Action> plan = new HashMap<>();
    ArrayList<State> closed = new ArrayList<>();
    State open = this.init;
    father.put(this.init,null);

    while (open != null) {
      
    }
  }
}
