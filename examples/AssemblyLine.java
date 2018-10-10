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
		HashMap<Variable,String> condition_chassis = new HashMap<Variable,String>();
		condition_chassis.put(new Variable("HAS_CHASSIS",booleans_dom), "FALSE");
		HashMap<Variable,String> outcome_chassis = new HashMap<Variable,String>();
		outcome_chassis.put(new Variable("HAS_CHASSIS",booleans_dom), "TRUE");
		HashMap<Variable,String> conditions_body = new HashMap<Variable,String>(outcome_chassis);
		conditions_body.put(new Variable("HAS_BODY",booleans_dom), "FALSE");
		HashMap<Variable,String> conditions_rrw = new HashMap<Variable,String>(outcome_chassis);
		conditions_rrw.put(new Variable("HAS_REAR_RIGHT_WHEEL",booleans_dom), "FALSE");
		HashMap<Variable,String> conditions_rlw = new HashMap<Variable,String>(outcome_chassis);
		conditions_rlw.put(new Variable("HAS_REAR_LEFT_WHEEL",booleans_dom), "FALSE");
		HashMap<Variable,String> conditions_frw = new HashMap<Variable,String>(outcome_chassis);
		conditions_frw.put(new Variable("HAS_FRONT_RIGHT_WHEEL",booleans_dom), "FALSE");
		HashMap<Variable,String> conditions_flw = new HashMap<Variable,String>(outcome_chassis);
		conditions_flw.put(new Variable("HAS_FRONT_LEFT_WHEEL",booleans_dom), "FALSE");
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

			//Installations de coût 2
		this.INSTALL_CHASSIS = new Action(condition_chassis, outcome_chassis);
		this.INSTALL_BODY = new Action(conditions_body, outcome_body);
		this.INSTALL_REAR_RIGHT_WHEEL = new Action(conditions_rrw, outcome_rrw);
		this.INSTALL_REAR_LEFT_WHEEL = new Action(conditions_rlw, outcome_rlw);
		this.INSTALL_FRONT_RIGHT_WHEEL = new Action(conditions_frw, outcome_frw);
		this.INSTALL_FRONT_LEFT_WHEEL = new Action(conditions_flw, outcome_flw);

			//Installations de coût 1

		HashMap<Variable,String> conditions_prw = new HashMap<Variable,String>(conditions_rlw);
		conditions_prw.put(new Variable("HAS_REAR_RIGHT_WHEEL",booleans_dom), "FALSE");
		HashMap<Variable,String> conditions_pfw = new HashMap<Variable,String>(conditions_frw);
		conditions_pfw.put(new Variable("HAS_FRONT_LEFT_WHEEL",booleans_dom), "FALSE");
		HashMap<Variable,String> conditions_plw = new HashMap<Variable,String>(conditions_rlw);
		conditions_plw.put(new Variable("HAS_FRONT_LEFT_WHEEL",booleans_dom), "FALSE");
		HashMap<Variable,String> conditions_pdw = new HashMap<Variable,String>(conditions_frw);
		conditions_pdw.put(new Variable("HAS_REAR_RIGHT_WHEEL",booleans_dom), "FALSE");
		HashMap<Variable,String> outcome_prw = new HashMap<Variable,String>(outcome_rlw);
		outcome_prw.putAll(outcome_rrw);
		HashMap<Variable,String> outcome_pfw = new HashMap<Variable,String>(outcome_flw);
		outcome_pfw.putAll(outcome_frw);
		HashMap<Variable,String> outcome_plw = new HashMap<Variable,String>(outcome_flw);
		outcome_plw.putAll(outcome_rlw);
		HashMap<Variable,String> outcome_pdw = new HashMap<Variable,String>(outcome_frw);
		outcome_pdw.putAll(outcome_rrw);
		this.INSTALL_REAR_WHEELS = new Action(conditions_prw, outcome_prw);
		this.INSTALL_FRONT_WHEELS = new Action(conditions_pfw, outcome_pfw);
		this.INSTALL_LEFT_WHEELS = new Action(conditions_plw, outcome_plw);
		this.INSTALL_RIGHT_WHEELS = new Action(conditions_pdw, outcome_pdw);

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

	public Action[] getBasicInstallActions() {
		return new Action[] {this.INSTALL_CHASSIS, this.INSTALL_BODY, this.INSTALL_REAR_LEFT_WHEEL, this.INSTALL_REAR_RIGHT_WHEEL, this.INSTALL_FRONT_LEFT_WHEEL, this.INSTALL_FRONT_RIGHT_WHEEL};
	}

	public Action[] getParallelWheelInstallActions() {
		return new Action[] {this.INSTALL_REAR_WHEELS, this.INSTALL_FRONT_WHEELS, this.INSTALL_LEFT_WHEELS, this.INSTALL_RIGHT_WHEELS};
	}

	public ArrayList<Action> getPrecisePaintActions() {
		return this.PAINT_ROOF;
	}

	public ArrayList<Action> getLargePaintActions() {
		ArrayList<Action> result = new ArrayList<Action>();
		result.addAll(this.PAINT_REAR);
		result.addAll(this.PAINT_FRONT);
		result.addAll(this.PAINT_LEFT);
		result.addAll(this.PAINT_RIGHT);
		return result;
	}

	public ArrayList<Action> getAllActions() {
		ArrayList<Action> result = new ArrayList<Action>();
		result.addAll(Arrays.asList(this.getBasicInstallActions()));
		result.addAll(Arrays.asList(this.getParallelWheelInstallActions()));
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
