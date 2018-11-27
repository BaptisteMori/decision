package extraction;

import representations.*;
import java.util.*;

public class FrequentItemSetMiner {

  private BooleanDataBase boolean_database;
  private Map<Set<Variable>, Integer> frequentItemSets;
  private ArrayList<Variable> singletons;
  private int minimal_support;

/**
  * Constructeur de la Classe.
  * @param db , qui est de type BooleanDataBase.
  */

  public FrequentItemSetMiner(BooleanDataBase db, int minimal_support ) {
    this.boolean_database=db;
    this.frequentItemSets = new HashMap<Set<Variable>, Integer>();
    this.singletons= new ArrayList<Variable>();
    this.minimal_support = minimal_support;
  }

/**
  * Méthode qui permet de retourner l'item d'un Set.
  * @return this.frequentItemSets , retournant un Map de Set de Variable et un integer.
  *
  */
  public Map<Set<Variable>, Integer> getItemSets() {
    return this.frequentItemSets;
  }

  public void frequentItemSets(){
    generateSingletons();
    Set<Variable> combi=new HashSet<Variable>();
    bfMiner(0,combi);
  }

  public void generateSingletons() {
    Map<Set<Variable>, Integer> res = new HashMap<Set<Variable>, Integer>();
    int i=0;
    for (Variable v : this.boolean_database.getVariablesList()) {
      Set<Variable> s = new HashSet<Variable>();
      s.add(v);
      int f = frequencyCalcul(s);
      if (f >= this.minimal_support) {
        this.singletons.add(v);
        res.put(s,f);
      }
      i++;
    }
    this.frequentItemSets.putAll(res);

  }

/**
  *
  * @param minimal_support , qui est de type int.
  * @param combi , qui est de type Set de Variable.
  */
  public void bfMiner(int i, Set<Variable> combi) {
    for (int j=i+1 ; i<singletons.size()-1 ; j++) {
      System.out.println("singletons "+singletons.get(j));
      combi.add(singletons.get(j));
      System.out.println(combi);
      int f = frequencyCalcul(combi);
      if (f >= this.minimal_support /*&& !(frequentItemSets.containsKey(combi))*/) {
        this.frequentItemSets.put(combi,f);
        bfMiner(i++, combi);
      }
    }
  }

  /**
    * Méthode permettant d'afficher les Set d'item fréquent.
    * @param minimal_support , qui est un int.
    * @param prev , qui est un Set de Variable.
    Set<Variable> item_set = new HashSet<Variable>();
    */



  /**
    * Méthode
    * @param combi, qui est un Set de Variable.
    * @return this.boolean_database.getTransactions().size() , qui est un entier représentant la fréquence de calcul.
    */
  public int frequencyCalcul(Set<Variable> combi) {
    if (combi.size() == 0) {
      return this.boolean_database.getTransactions().size();
    }
    int freq=0;
    for (Map<Variable,String> transaction : this.boolean_database.getTransactions()) {
      int cpt = 0;
      for (Variable variable : combi) {
        if (transaction.get(variable).equals("0")) {
            break;
        }
        cpt++;
      }
      if (cpt == combi.size()) {
        freq++;
      }
    }
  return freq;
  }
}
