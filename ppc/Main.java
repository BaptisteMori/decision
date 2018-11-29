package ppc;

import representations.*;
import examples.*;
import java.util.*;

public class Main {


	/*
		Méthode main permettant de tout créer, et de
		générer toutes les voitures satisfaisants toutes
		les contraintes.
	*/
	public static void main(String[] args) {
		ArrayList<Heuristic> heuristics = new ArrayList<>();
		heuristics.add(new MaxDomaine());
		heuristics.add(new MinVarConstraint());
		heuristics.add(new MaxVarConstraint());
		heuristics.add(new MinDomaine());
		Example data = new Example();
		Backtracking b = new Backtracking(data.getVariables(), data.getConstraints(),null);

		for(Heuristic h : heuristics){
			ArrayList<Map<Variable,String>> l = new ArrayList<>();
			b.setList(l);
			b.setCounter(0);
			b.setHeuristic(h);
			Map<Variable,String> map = b.generateMap();
			b.backtrack(map,0,b.getUnassignedDomains());
			ArrayList<Map<Variable,String>> list = b.getList();
			System.out.println("nombre de résultats : "+list.size());
			System.out.println(" Heuristic : "+h);
			System.out.println("Counter: "+b.getCounter());
		}

		ArrayList<Map<Variable,String>> list = b.getList();
		for (Map<Variable,String> voiture : list) {
			System.out.println("v " + voiture);
		}
	}
}
