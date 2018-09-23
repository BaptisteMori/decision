package ppc;

import representations.*;
import examples.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {

		Example data = new Example();
		Backtracking b = new Backtracking(data.getVariables(), data.getConstraints());
		Map<Variable,String> map = b.generateMap();

		b.backtrack(map,0);
		//b.heuristic();
		//b.heuristicDomaine();
		//System.out.println(b.allConstraintsSatisfiedBy(test));
	}
}
