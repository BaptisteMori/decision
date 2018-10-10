package planning;

import java.util.*;
import examples.AssemblyLine;

public class Main {

	public static void main(String[] args) {

		AssemblyLine universe = new AssemblyLine();
		System.out.println(universe.getInitialState());
		for (int i=0; i<6; i++) {
			System.out.println(universe.generateGoalState());
		}
		/*for (int j=0; j<4; j++) {
			System.out.println(universe.getParallelWheelInstallActions()[j]);
		}
		for (int k=0; k<5; k++) {
			System.out.println(universe.getPaintActionLists().get(k));
		}*/
	}
}
