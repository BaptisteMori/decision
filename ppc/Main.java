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

		Example data = new Example();
		Backtracking b = new Backtracking(data.getVariables(), data.getConstraints());
		Map<Variable,String> map = b.generateMap();

		b.backtrack(map,0,b.getUnassignedDomains());
		ArrayList<Map<Variable,String>> list = b.getList();
		System.out.println(list.size());
		for (Map<Variable,String> voiture : list) {
			System.out.println("v " + voiture);
		}
	}
}
