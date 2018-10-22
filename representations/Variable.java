package representations;

import java.util.Set;

public class Variable {

  private String nom;
  private Set<String> domaine;


/**
  *
  * Constructeur qui prend en paramètre
  * @param nom qui est un string.
  * @param domaine qui est un set de String.
  *
  */
  public Variable(String nom, Set<String> domaine) {
    this.nom = nom;
    this.domaine = domaine;
  }


  /**
    *
    * Second constructeur de la classe Variable.
    *
    */

  public Variable(){
    this(null,null);
  }

  /**
    * Surcharge de la méthode hashCode.
    * @return code de type int, qui sera un hash du code fournit.
    *
    */

	@Override
	public int hashCode() {
		int code=7;
		code+=47*code+this.nom.length();
		return code;
	}


  /**
    * Surcharge de la méthode equals, qui vérifie l'égalité entre deux objets.
    * @param o qui est l'objet à tester.
    * @return true, si les deux objets sont égaux.
    * @return false, si les deux objets ne sont pas égaux.
    * @return this.nom == n.nom, donc on retourne une égalité entre les objets testés.
    *
    */

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

  /**
    *
    * Méthode permettant de retourner le nom de la variable.
    * @return this.nom, qui est le nom de la Varaible qui sera sous forme de String.
    */

	public String getNom() {
		return this.nom;
	}

  /**
    *
    * Méthode permettant de retourner le Domaine.
    * @return this.domaine, qui retournera le Domaine sous forme de Set de String.
    *
    */

	public Set<String> getDomaine() {
		return this.domaine;
	}

  /**
    * Méthode permettant de créer un domaine.
    * @param new_dom , qui est un Set de String.
    * Qui permettra d'initialiser un nouveau Domaine.
    *
    */

  public void setDomaine(Set<String> new_dom) {
    this.domaine=new_dom;
  }

  /**
    * Méthode toString qui renvoie une chaine de caractère.
    * @return this.nom , qui sera une String.
    *
    */

  public String toString() {
    return this.nom;
  }
}
