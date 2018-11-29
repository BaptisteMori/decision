package representations;

import java.util.*;

public interface Constraint {


  /**
    * Méthode permettant de retourner le scope.
    * @return un Set d'objet Variable
    */
  public Set<Variable> getScope();

  /**
    * Méthode permettant de savoir si une contrainte est satisfaite.
    * @param map , qui est un Map de Variable et de String.
    * @return true si la règle est satisfaite par map
    */
  public boolean isSatisfiedBy(Map<Variable,String> map);

  /**
    * Méthode qui utilise les filtres afin de réduire les recherches.
    * @param map , qui est un Map de Variable et de String.
    * @param domaines , qui est un Map de Variable et de Set de String.
    * @return true si il y a eu un filtre sur les domaines des variables
    */
  public boolean filter(Map<Variable,String> map, Map<Variable, Set<String>> domaines);
}
