package extraction;

import representations.*;
import java.util.*;

public class FrequentItemSetMiner {

  private BooleanDataBase boolean_database;
  private Map<Set<Variable>, Integer> frequentItemSetsList;
  private ArrayList<Variable> singletons;
  private int minimal_support;

/**
  * Constructeur de la Classe.
  * @param db , qui est de type BooleanDataBase.
  */

  public FrequentItemSetMiner(BooleanDataBase db, int minimal_support ) {
    this.boolean_database=db;
    this.frequentItemSetsList = new HashMap<Set<Variable>, Integer>();
    this.singletons= new ArrayList<Variable>();
    this.minimal_support = minimal_support;
  }

/**
  * Méthode qui permet de retourner l'item d'un Set.
  * @return this.frequentItemSets , retournant un Map de Set de Variable et un integer.
  *
  */
  public Map<Set<Variable>, Integer> getItemSets() {
    return this.frequentItemSetsList;
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
    this.frequentItemSetsList.putAll(res);
    Collections.sort(this.singletons,new Comparator<Variable>() {
                                              @Override
                                              public int compare(Variable first, Variable second) {
                                                Set<Variable> vf = new HashSet<>();
                                                vf.add(first);
                                                Set<Variable> vs = new HashSet<>();
                                                vs.add(second);
                                                int value_first = frequentItemSetsList.get(vf);
                                                int value_second = frequentItemSetsList.get(vs);
                                                if (value_first < value_second) {
                                                  return -1;
                                                } else if (value_first == value_second) {
                                                  return 0;
                                                } else {
                                                  return 1;
                                                }
                                              }
                                            });

  }

/**
  *
  * @param minimal_support , qui est de type int.
  * @param combi , qui est de type Set de Variable.
  */
  public void bfMiner(int i, Set<Variable> combi) {
    if (i<singletons.size()){
      for (int j=i ; j<singletons.size() ; j++) {
        Set<Variable> combis = new HashSet<Variable>(combi);
        System.out.println("singletons "+singletons.get(j));
        System.out.println("i "+i);
        combis.add(singletons.get(j));
        int f = frequencyCalcul(combis);

        if (f >= this.minimal_support /*&& !(frequentItemSets.containsKey(combi))*/) {
          System.out.println("combi "+combis +"f "+f);
          this.frequentItemSetsList.put(combis,f);
          bfMiner(i+1, combis);
        }
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

  public ArrayList<Variable> getSingletons(){
    return this.singletons;
  }

}
