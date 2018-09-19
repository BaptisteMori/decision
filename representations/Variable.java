package representations;

import java.util.Set;

public class Variable {

  private String nom;
  private Set<String> domaine;

  public Variable(String nom, Set<String> domaine) {
    this.nom = nom;
    this.domaine = domaine;
  }

	@Override
	public int hashCode() {
		int code=7;
		code+=47*code+this.nom.length();
		return code;
	}

	@Override
	public boolean equals(Object o) {
		if (o==this) {
			return true;
		}
		if (!(o instanceof Variable)) {
			return false;
		}
		Variable n = (Variable)o;
		return this.nom==n.nom;
	}

	public String getNom() {
		return this.nom;
	}

	public Set<String> getDomaine() {
		return this.domaine;
	}

  public void setDomaine(Set<String> new_dom) {
    this.domaine=new_dom;
  }

  public String toString() {
    return this.nom;
  }
}
