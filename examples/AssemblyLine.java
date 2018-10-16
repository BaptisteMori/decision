package examples;

import representations.*;
import planning.*;
import java.util.*;

public class AssemblyLine {

	private Action INSTALL_CHASSIS;
	private Action INSTALL_BODY;
	private Action INSTALL_FRONT_LEFT_WHEEL;
	private Action INSTALL_FRONT_RIGHT_WHEEL;
	private Action INSTALL_REAR_LEFT_WHEEL;
	private Action INSTALL_REAR_RIGHT_WHEEL;

	private Action INSTALL_FRONT_WHEELS;
	private Action INSTALL_REAR_WHEELS;
	private Action INSTALL_LEFT_WHEELS;
	private Action INSTALL_RIGHT_WHEELS;

	private ArrayList<Action> PAINT_ROOF;
	private ArrayList<Action> PAINT_FRONT;
	private ArrayList<Action> PAINT_REAR;
	private ArrayList<Action> PAINT_LEFT;
	private ArrayList<Action> PAINT_RIGHT;

	private State initial_state;

	//Ensembles de variables et domaines associés
	private Set<String> parts_list = new HashSet(Arrays.asList(new String[] {"HAS_CHASSIS", "HAS_BODY", "HAS_FRONT_LEFT_WHEEL", "HAS_FRONT_RIGHT_WHEEL", "HAS_REAR_LEFT_WHEEL", "HAS_REAR_RIGHT_WHEEL"}));

	private Set<String> booleans_dom = new HashSet(Arrays.asList(new String[] {"TRUE", "FALSE"}));

	private Set<String> parts_color = new HashSet(Arrays.asList(new String[] {"FRONT_COLOR", "REAR_COLOR", "LEFT_COLOR", "RIGHT_COLOR", "ROOF_COLOR", "FRONT_LEFT_WHEEL_COLOR", "FRONT_RIGHT_WHEEL_COLOR", "REAR_LEFT_WHEEL_COLOR", "REAR_RIGHT_WHEEL_COLOR"}));

	private Set<String> all_colors = new HashSet(Arrays.asList(new String[] {"GRAY", "BLACK", "WHITE", "RED", "GREEN", "BLUE", "ORANGE", "YELLOW"}));

	public AssemblyLine() {
		//Création de l'état initial
		HashMap<Variable,String> starting_car = new HashMap<Variable,String>();
		for (String part : parts_list) {
			starting_car.put(new Variable(part, booleans_dom),"FALSE");
		}
		for (String paint : parts_color) {
			starting_car.put(new Variable(paint, all_colors), "GRAY");
		}
		this.initial_state = new State(starting_car);

		//Actions d'installation
		HashMap<Variable,String> outcome_chassis = new HashMap<Variable,String>();
		outcome_chassis.put(new Variable("HAS_CHASSIS",booleans_dom), "TRUE");
		HashMap<Variable,String> outcome_body = new HashMap<Variable,String>();
		outcome_body.put(new Variable("HAS_BODY",booleans_dom), "TRUE");
		HashMap<Variable,String> outcome_rrw = new HashMap<Variable,String>();
		outcome_rrw.put(new Variable("HAS_REAR_RIGHT_WHEEL",booleans_dom), "TRUE");
		HashMap<Variable,String> outcome_rlw = new HashMap<Variable,String>();
		outcome_rlw.put(new Variable("HAS_REAR_LEFT_WHEEL",booleans_dom), "TRUE");
		HashMap<Variable,String> outcome_frw = new HashMap<Variable,String>();
		outcome_frw.put(new Variable("HAS_FRONT_RIGHT_WHEEL",booleans_dom), "TRUE");
		HashMap<Variable,String> outcome_flw = new HashMap<Variable,String>();
		outcome_flw.put(new Variable("HAS_FRONT_LEFT_WHEEL",booleans_dom), "TRUE");

			//Installations de coût 1
		this.INSTALL_CHASSIS = new Action(new HashMap<Variable,String>(), outcome_chassis);
		this.INSTALL_BODY = new Action(outcome_chassis, outcome_body);
		this.INSTALL_REAR_RIGHT_WHEEL = new Action(outcome_chassis, outcome_rrw);
		this.INSTALL_REAR_LEFT_WHEEL = new Action(outcome_chassis, outcome_rlw);
		this.INSTALL_FRONT_RIGHT_WHEEL = new Action(outcome_chassis, outcome_frw);
		this.INSTALL_FRONT_LEFT_WHEEL = new Action(outcome_chassis, outcome_flw);

			//Installations de coût 2
		HashMap<Variable,String> outcome_prw = new HashMap<Variable,String>(outcome_rlw);
		outcome_prw.putAll(outcome_rrw);
		HashMap<Variable,String> outcome_pfw = new HashMap<Variable,String>(outcome_flw);
		outcome_pfw.putAll(outcome_frw);
		HashMap<Variable,String> outcome_plw = new HashMap<Variable,String>(outcome_flw);
		outcome_plw.putAll(outcome_rlw);
		HashMap<Variable,String> outcome_pdw = new HashMap<Variable,String>(outcome_frw);
		outcome_pdw.putAll(outcome_rrw);
		this.INSTALL_REAR_WHEELS = new Action(outcome_chassis, outcome_prw);
		this.INSTALL_FRONT_WHEELS = new Action(outcome_chassis, outcome_pfw);
		this.INSTALL_LEFT_WHEELS = new Action(outcome_chassis, outcome_plw);
		this.INSTALL_RIGHT_WHEELS = new Action(outcome_chassis, outcome_pdw);

		/* Actions de peinture
		Les actions sont stockées dans des listes selon la cible. Chaque liste contient autant d'actions que de couleurs dans le domaine all_color, et les actions sont triées dans le même ordre que le domaine. */

		HashMap<Variable,String> conditions_rear = new HashMap<Variable,String>();
		conditions_rear.putAll(outcome_body);
		conditions_rear.put(new Variable("HAS_REAR_LEFT_WHEEL", booleans_dom), "TRUE");
		conditions_rear.put(new Variable("HAS_REAR_RIGHT_WHEEL", booleans_dom), "TRUE");
		HashMap<Variable,String> conditions_front = new HashMap<Variable,String>();
		conditions_front.putAll(outcome_body);
		conditions_front.put(new Variable("HAS_FRONT_LEFT_WHEEL", booleans_dom), "TRUE");
		conditions_front.put(new Variable("HAS_FRONT_RIGHT_WHEEL", booleans_dom), "TRUE");
		HashMap<Variable,String> conditions_left = new HashMap<Variable,String>();
		conditions_left.putAll(outcome_body);
		conditions_left.put(new Variable("HAS_REAR_LEFT_WHEEL", booleans_dom), "TRUE");
		conditions_left.put(new Variable("HAS_FRONT_LEFT_WHEEL", booleans_dom), "TRUE");
		HashMap<Variable,String> conditions_right = new HashMap<Variable,String>();
		conditions_right.putAll(outcome_body);
		conditions_right.put(new Variable("HAS_FRONT_RIGHT_WHEEL", booleans_dom), "TRUE");
		conditions_right.put(new Variable("HAS_REAR_RIGHT_WHEEL", booleans_dom), "TRUE");

		this.PAINT_ROOF = new ArrayList<Action>();
		this.PAINT_REAR = new ArrayList<Action>();
		this.PAINT_FRONT = new ArrayList<Action>();
		this.PAINT_LEFT = new ArrayList<Action>();
		this.PAINT_RIGHT = new ArrayList<Action>();
		String[] colors_array = this.all_colors.toArray(new String[this.all_colors.size()]);

		for (String color : all_colors) {
			//Peinture précise de coût 2
			HashMap<Variable,String> outcome_roof = new HashMap<Variable,String>();
			outcome_roof.put(new Variable("ROOF_COLOR", all_colors), color);
			this.PAINT_ROOF.add(new Action(outcome_body, outcome_roof));

			//Peinture large de coût 1
			HashMap<Variable,String> outcome_rear = new HashMap<Variable,String>();
			outcome_rear.putAll(outcome_roof);
			outcome_rear.put(new Variable("REAR_COLOR", all_colors), color);
			outcome_rear.put(new Variable("REAR_LEFT_WHEEL_COLOR", all_colors), color);
			outcome_rear.put(new Variable("REAR_RIGHT_WHEEL_COLOR", all_colors), color);
			this.PAINT_REAR.add(new Action(conditions_rear, outcome_rear));
			HashMap<Variable,String> outcome_front = new HashMap<Variable,String>();
			outcome_front.putAll(outcome_roof);
			outcome_front.put(new Variable("FRONT_COLOR", all_colors), color);
			outcome_front.put(new Variable("FRONT_LEFT_WHEEL_COLOR", all_colors), color);
			outcome_front.put(new Variable("FRONT_RIGHT_WHEEL_COLOR", all_colors), color);
			this.PAINT_FRONT.add(new Action(conditions_front, outcome_front));
			HashMap<Variable,String> outcome_left = new HashMap<Variable,String>();
			outcome_left.putAll(outcome_roof);
			outcome_left.put(new Variable("LEFT_COLOR", all_colors), color);
			outcome_left.put(new Variable("REAR_LEFT_WHEEL_COLOR", all_colors), color);
			outcome_left.put(new Variable("FRONT_LEFT_WHEEL_COLOR", all_colors), color);
			this.PAINT_LEFT.add(new Action(conditions_left, outcome_left));
			HashMap<Variable,String> outcome_right = new HashMap<Variable,String>();
			outcome_right.putAll(outcome_roof);
			outcome_right.put(new Variable("RIGHT_COLOR", all_colors), color);
			outcome_right.put(new Variable("REAR_RIGHT_WHEEL_COLOR", all_colors), color);
			outcome_right.put(new Variable("FRONT_RIGHT_WHEEL_COLOR", all_colors), color);
			this.PAINT_RIGHT.add(new Action(conditions_right, outcome_right));
		}
	}

	public State getInitialState() {
		return this.initial_state;
	}

	public ArrayList<Action> getBasicInstallActions() {
		ArrayList<Action> result = new ArrayList<Action>();
		result.add(this.INSTALL_CHASSIS);
		result.add(this.INSTALL_BODY);
		result.add(this.INSTALL_REAR_LEFT_WHEEL);
		result.add(this.INSTALL_REAR_RIGHT_WHEEL);
		result.add(this.INSTALL_FRONT_LEFT_WHEEL);
		result.add(this.INSTALL_FRONT_RIGHT_WHEEL);
		return result;
	}

	public ArrayList<Action> getParallelWheelInstallActions() {
		ArrayList<Action> result = new ArrayList<Action>();
		result.add(this.INSTALL_REAR_WHEELS);
		result.add(this.INSTALL_FRONT_WHEELS);
		result.add(this.INSTALL_LEFT_WHEELS);
		result.add(this.INSTALL_RIGHT_WHEELS);
		return result;
	}

	public ArrayList<Action> getPrecisePaintActions() {
		return this.PAINT_ROOF;
	}

	public ArrayList<Action> getLargePaintActions() {
		ArrayList<Action> result = new ArrayList<Action>(this.PAINT_REAR);
		result.addAll(this.PAINT_FRONT);
		result.addAll(this.PAINT_LEFT);
		result.addAll(this.PAINT_RIGHT);
		return result;
	}

	public ArrayList<Action> getAllActions() {
		ArrayList<Action> result = new ArrayList<Action>(this.getBasicInstallActions());
		result.addAll(this.getParallelWheelInstallActions());
		result.addAll(this.getPrecisePaintActions());
		result.addAll(this.getLargePaintActions());
		return result;
	}

	public State generateGoalState() {
		String[] gettable_array = this.all_colors.toArray(new String[this.all_colors.size()]);//passage du set en array pour pouvoir récupérer une couleur
		HashMap<Variable,String> random_car = new HashMap<Variable,String>();
		for (String part : parts_list) {
			random_car.put(new Variable(part, booleans_dom),"TRUE");
		}
		for (String paint : parts_color) {
			random_car.put(new Variable(paint, all_colors), gettable_array[new Random().nextInt(gettable_array.length)]);
		}
		State random_goal = new State(random_car);
		return random_goal;
	}
}
