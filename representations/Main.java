package representations;

import java.util.*;

public class Main {

  public static void Main(String[] args) {

    Set<String> domaine = new HashSet<>();
    domaine.add("blanc");
		domaine.add("rouge");
		domaine.add("noir");
    Variable v1 = new Variable("couleur_toit",domaine);
  }
}
