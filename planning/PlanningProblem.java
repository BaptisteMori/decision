package planning;

import java.util.*;
import representations.*;

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
      for (Variable v : s.getState().keySet()) {
        if (!(state.getState().containsKey(v)) || (state.getState().get(v) != s.getState().get(v))) {
          return false;
        }
      }
    }
    return true;
  }

  public Stack<Action> dfs(State state, Stack<Action> plan, ArrayList<State> closed) {
    if (satisfies(state)) {
      return plan;
    } else {
      ArrayList<Action> tmp_actions = new ArrayList<>();
      tmp_actions.addAll(this.actions);
      for (Action act : tmp_actions) {
        if (act.is_applicable(state)) {
          State next = act.apply(state);
          this.actions.remove(act);
          if (!(closed.contains(next))) {
            plan.push(act);
            closed.add(next);
            Stack<Action> subplan = dfs(next, plan, closed);
            if (subplan != null) {
              return subplan;
            }
            plan.pop();
          }
          this.actions.add(act);
        }
      }
      return null;
    }
  }

  public Stack<Action> dfsIteratif(State state,int profondeur) {
    ArrayList<State> closed = new ArrayList<>();
    Stack<Action> plan = new Stack<>();
    State present_state = state;
    State next = null;
    Stack<Action> action_tmp = new Stack();
    for (Action a : this.actions) {
      action_tmp.push(a);
    }
    while (profondeur > 0) {
      for (Action act : action_tmp) {
        if (act.is_applicable(present_state)) {
          next = act.apply(present_state);
          if (!(closed.contains(next))) {
            plan.push(act);
            closed.add(next);
            action_tmp.pop();
          }
        }
        present_state = next;
      }
      profondeur--;
    }
    return plan;
  }

  public Stack<Action> dfsIteratif(State state) {
    Stack<State> state_plan = new Stack<State>();
    state_plan.add(state);
    ArrayList<State> closed = new ArrayList<>();
    closed.add(state);
    while (!state_plan.isEmpty()) {
      State elem = state_plan.pop();

    }
    /*ArrayList<State> closed = new ArrayList<>();
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
      present_state = state_plan.pop();

      plan.push(action_done.pop);

    }*/
    return null;
  }

  public Stack<Action> bfs() {
    Map<State,State> father = new HashMap<>();
    Map<State,Action> plan = new HashMap<>();
    ArrayList<State> closed = new ArrayList<>();
    Stack<State> open = new Stack<>();
    open.push(this.init);
    father.put(this.init,null);
    while (!(open.isEmpty())) {
      State state = open.pop();
      closed.add(state);
      for (Action act : this.actions) {
        State next = act.apply(state);
        if (!(closed.contains(next)) && (open.search(next) == -1)) {
          father.put(state, next);
          plan.put(next, act);
          if (satisfies(next)) {
            System.out.println("OK 2");
            return getBFSPlan(father, plan, next);
          } else {
            open.push(next);
          }
        }
      }
    }
		return null;//pour compiler
  }

  public Stack<Action> getBFSPlan(Map<State,State> father, Map<State,Action> actions, State goal) {
    Stack<Action> plan = new Stack<>();
    System.out.println(goal);
    while (goal != null) {
      plan.push(actions.get(goal));
      goal = father.get(goal);
    }
    Collections.reverse(plan);
    return plan;
  }
}
