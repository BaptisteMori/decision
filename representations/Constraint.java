package representations;

import java.util.*;

public interface Constraint {


  /**
    * Méthode permettant de retourner le scope.
    *
    *
    */
  public Set<Variable> getScope();

  /**
    * Méthode permettant de savoir si une contrainte est satisfaite.
    * @param map , qui est un Map de Variable et de String.
    *
    */
  public boolean isSatisfiedBy(Map<Variable,String> map);

  /**
    * Méthode qui utilise les filtres afin de réduire les recherches.
    * @param map , qui est un Map de Variable et de String.
    * @param domaines , qui est un Map de Variable et de Set de String.
    */
  public boolean filter(Map<Variable,String> map, Map<Variable, Set<String>> domaines);
}
