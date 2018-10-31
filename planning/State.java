package planning;

import representations.*;
import java.util.*;

public class State {

  private Map<Variable,String> state;

  /**
		* Constructeur de la classe State.
		* @param state , qui est un Map de Varaible et de String.
		*
		*/
  public State(Map<Variable,String> state) {
    this.state = state;
  }

	public State() {
		this(null);
	}


  /**
		* Surcharge de la méthode hashCode().
		* @return code , qui est un int et qui contiendra le hash.
		*
		*/
  @Override
	public int hashCode() {
		int code=7;
		code+=47*code+this.state.size();
		return code;
	}


  /**
		* Surcharge de la méthode equals(), qui permet de vérifier si deux objets sont égaux.
		* @param o , qui est un Object.
		* @return true , si les deux objets sont égaux, sinon retourne false.
		*/
  @Override
  public boolean equals(Object o) {
    if (o==this) {
			return true;
		}
    if (!(o instanceof State)) {
			return false;
		}
    for (Variable v : this.state.keySet()) {
      if (!(this.state.get(v).equals(((State)o).getState().get(v)))) {
        return false;
      }
    }
    return true;
  }

  /**
		* Méthode permettant de retourner le State.
		* @return this.state , qui est un Map de Variable et de String.
		*
		*/
  public Map<Variable,String> getState() {
    return this.state;
  }


  /**
		* Méthode permettant de mettre à jour un State.
		* @param state , qui est un Map de Variable et de String.
		*
		*/
  public void setState(Map<Variable,String> state) {
    this.state = state;
  }


  /**
		* Méthode permettant d'afficher le State.
		* @return this.state.toString() , qui sera de type String.
		*
		*/
	public String toString() {
		return this.state.toString();
	}
}
