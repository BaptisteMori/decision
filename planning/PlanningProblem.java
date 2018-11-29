package planning;

import java.util.*;
import representations.*;

public class PlanningProblem {

  State init;
  ArrayList<State> goals;
  ArrayList<Action> actions;
	int nodes_dfs = 0;


  /**
    * Constructeur de la classe PlanningProblem.
    * @param init , qui est un State.
    * @param goals , qui est une ArrayList de State.
    * @param actions , qui est une ArrayList d'Action.
    */
  public PlanningProblem(State init, ArrayList<State> goals, ArrayList<Action> actions) {
    this.init = init;
    this.goals = goals;
    this.actions = actions;
  }

  /**
    * Méthode qui dit si un état est satisfait
    * @param state , qui est un State.
    * @return tmp , qui est faux si l'état ne contient pas
    */
  public boolean satisfies(State state) {
    boolean tmp = true;
    for (State s : goals) {
      tmp = true;
      for (Variable v : s.getState().keySet()) {
        if (!(state.getState().containsKey(v)) || (state.getState().get(v) != s.getState().get(v))) {
          tmp = false;
          break;
        }
      }
    }
    return tmp;
  }

  /**
    * Méthode pour la recherche par Deep First Search.
    * Recherche en profondeur.
    * @param state , qui est un State.
    * @param plan , qui est un Stack d'Action.
    * @param closed , qui est une ArrayList de State.
    * @param profondeur , qui est un int.
    * @return plan , qui est un Stack d'Action , si l'algorithme a trouvé un plan.
    * @return null , si il ne trouve rien.
    */
  public Stack<Action> dfs(State state, Stack<Action> plan, ArrayList<State> closed, int profondeur) {
		this.nodes_dfs++;
    if (profondeur == 0) {
      if (satisfies(state)) {
				System.out.println("Noeuds explorés par la DFS : " + this.nodes_dfs);
        return plan;
      }
      return null;
    }
    if (satisfies(state)) {
			System.out.println("Noeuds explorés par la DFS : " + this.nodes_dfs);
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
            Stack<Action> subplan = dfs(next, plan, closed, profondeur-1);
            if (subplan != null) {
              this.actions.add(act);
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

  /**
    * Méthode Deep First Search Itératif
    * Même méthode que dfs, mais écrite de manière itérative.
    * @param state , qui est un State.
    * @param plan , qui est un Stack d'Action.
    * @param closed , qui est un ArrayList de State.
    * @return res_dfs , qui est un Stack d'Action.
    */
  public Stack<Action> dfsIteratif(State state, Stack<Action> plan, ArrayList<State> closed) {
    int profondeur = 1;
    Stack<Action> res_dfs = new Stack<Action>();
    do {
      res_dfs = dfs(state, plan, closed, profondeur++);
    } while (res_dfs == null);
    return res_dfs;
  }

  /**
    * Méthode Breadth  first Search
    * Méthode de parcours en largeur.
    * @return getBFSPlan , qui est une Queue d'Action.
    * @return null , si il ne trouve rien.
    * L'algorithme peut ne pas toujours trouver un chemin, mais dès qu'il en trouve un,
    * il est plus rapide que dfs.
    */
  public Queue<Action> bfs() {
    Map<State,State> father = new HashMap<>();
    Map<State,Action> plan = new HashMap<>();
    Set<State> closed = new HashSet<>();
    Queue<State> open = new LinkedList<>();
    open.add(this.init);
    father.put(this.init,null);
		int nb_nodes=0;
    while (!(open.isEmpty())) {
			nb_nodes++;
      State state = open.remove();
      closed.add(state);
      for (Action act : this.actions) {
        State next = act.apply(state);
        if (!(closed.contains(next)) && !(open.contains(next))) {
          father.put(next, state);
          plan.put(next, act);
          if (satisfies(next)) {
						System.out.println("Noeuds explorés par la BFS : " + nb_nodes);
            return getBFSPlan(father, plan, next);
          } else {
            open.add(next);
          }
        }
      }
    }
		System.out.println("Noeuds explorés par la BFS : " + nb_nodes);
		return null;
  }

  /**
    * Méthode permettant d'obtenir le plan de recherche pour le BFS.
    * @param father , qui est un Map de State de State.
    * @param actions , qui est un Map de State et d'Action.
    * @param goal , qui est un State.
    * @return plan , qui est une Queue d'Action.
    */
  public Queue<Action> getBFSPlan(Map<State,State> father, Map<State,Action> actions, State goal) {
    Queue<Action> plan = new LinkedList<>();
    System.out.println("Goal : " + goal);
    while (goal != null) {
      plan.add(actions.get(goal));
      goal = father.get(goal);
    }
    Collections.reverse((LinkedList<Action>)plan);
    return plan;
  }
}
