package representations;

import java.util.Set;

public class Variable {

  private String nom;
  private Set<String> domaine;

  public Variable(String nom, Set<String> domaine) {
    this.nom = nom;
    this.domaine = domaine;
  }

  public String getNom() {
    return this.nom;
  }

  public Set<String> getDomaine() {
    return this.domaine;
  }
}
