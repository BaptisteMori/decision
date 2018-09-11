package representations;

import java.util.*;

public class Main {

  public static void Main(String[] args) {

    Set<String> domaine = new Set<>();
    String blanc = "blanc";
    String rouge = "rouge";
    String noir = "noir";
    domaine.addAll(blanc,rouge,noir);
    Variable v1 = new Variable("couleur_toit",domaine);
  }
}
