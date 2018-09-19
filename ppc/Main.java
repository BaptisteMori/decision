package ppc;

import representations.*;
import examples.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {

		Example data = new Example();
		Backtracking b = new Backtracking(data.getVariables(), data.getConstraints());
		Variable[] e = b.getVariables();
		Map<Variable,String> map = b.generateMap();

		//Map<Variable,String> test = b.atomicAttribution(map,e[5]);
		b.backtrack(map,0);
		//System.out.println(b.allConstraintsSatisfiedBy(test));
	}
}
