package planning;

import representations.*;
import java.util.*;

/** Représente une transition entre deux états, c'est-à-dire l'ajout d'une nouvelle affectation de variable.*/
public class Action {

  private Map<Variable,String> premisse;
  private Map<Variable,String> conclusion;


/**
	* Constructeur de la Classe.
	* @param premisse , qui est un Map de Variable et de String.
	* @param conclusion , qui est un Map de Variable et de String
	*/
  public Action(Map<Variable,String> premisse, Map<Variable,String> conclusion) {
    this.premisse = premisse;
    this.conclusion = conclusion;
  }

/**
  * Regarde si un état est applicable à une "voiture".
	* @param state , qui est un State.
	* @return false, si le state est différents de la prémisse ou de la conclusion, sinon dans l'autre cas renvoie True.
	*/
  public boolean is_applicable(State state) {
    for (Variable v : premisse.keySet()) {
      if (state.getState().get(v) != premisse.get(v)) {
        return false;
      }
		}
    for (Variable v : conclusion.keySet()) {
      if (state.getState().get(v) ==conclusion.get(v)) {
        return false;
      }
    }

    return true;
  }

	/**
    * Méthode qui permet d'appliquer des états à une "voiture".
		* @param state , prend un State en paramètre.
		* @return new_state , retourne un nouvel état.
		*/
  public State apply(State state) {
    Map<Variable,String> new_map = new HashMap<>();
    new_map.putAll(state.getState());

    if (this.is_applicable(state)) {
      for (Variable v : this.conclusion.keySet()) {
        new_map.put(v,this.conclusion.get(v));
      }
    }
    State new_state = new State(new_map);
    return new_state;
  }

	/**
    * Méthode du Hash qui est redéfinie.
		* @return code , retourne le Hash.
		*/
	@Override
	public int hashCode() {
		int code=11;
		code+=59*code+this.premisse.size()+this.conclusion.size();
		return code;
	}

	/**
    * Vérifie l'égalité entre deux objets.
		* @param o , qui est un Object.
		* @return true , retourne true si les deux entités testées sont égales.
		* @return false , dans les cas ou la premisse et ou la conclusion ne sont pas égales à O.
		*/
	@Override
	public boolean equals(Object o) {
		if (o==this) {
			return true;
		}
		if (!(o instanceof Action)) {
			return false;
		}
		for (Variable v : this.premisse.keySet()) {
			if (!(this.premisse.get(v).equals(((Action)o).getPrecondition().get(v)))) {
				return false;
			}
		}
		for (Variable v : this.conclusion.keySet()) {
			if (!(this.conclusion.get(v).equals(((Action)o).getPostcondition().get(v)))) {
				return false;
			}
		}
		return true;
	}

	/**
    * Permet de retourner la prémisse.
		* @return this.premisse , qui est de type Map de Variable et de String.
		*/
	public Map<Variable,String> getPrecondition() {
		return this.premisse;
	}

	/**
    * Permet de retourner la conclusion.
		* @return this.conclusion , qui est de type Map de Variable et de String.
		*/
	public Map<Variable,String> getPostcondition() {
		return this.conclusion;
	}

	/**
    * Méthode permettant d'afficher la prémisse avec la conclusion.
		* @return une phrase avec la prémisse et la conclusion.
		*/
	public String toString() {
		return "Si " + this.premisse + " est vrai, alors faire " + this.conclusion + "\n";
	}

}
