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

	private Action PAINT_ROOF;
	private Action PAINT_FRONT;
	private Action PAINT_REAR;
	private Action PAINT_LEFT;
	private Action PAINT_RIGHT;

	public AssemblyLine() {

		/* Ensembles de variables et domaines associés */
		Set<String> parts_list = new HashSet(Arrays.asList(new String[] {"HAS_CHASSIS", "HAS_BODY", "HAS_FRONT_LEFT_WHEEL", "HAS_FRONT_RIGHT_WHEEL", "HAS_REAR_LEFT_WHEEL", "HAS_REAR_RIGHT_WHEEL"}));

		Set<String> booleans_dom = new HashSet(Arrays.asList(new String[] {"TRUE", "FALSE"}));

		Set<String> colors_list = new HashSet(Arrays.asList(new String[] {"FRONT_COLOR", "REAR_COLOR", "LEFT_COLOR", "RIGHT_COLOR", "ROOF_COLOR", "FRONT_LEFT_WHEEL_COLOR", "FRONT_RIGHT_WHEEL_COLOR", "REAR_LEFT_WHEEL_COLOR", "REAR_RIGHT_WHEEL_COLOR"}));

		Set<String> ALL_COLORS = new HashSet(Arrays.asList(new String[] {"GRAY", "BLACK", "WHITE", "RED", "GREEN", "BLUE", "ORANGE", "YELLOW"}));

		/* Création de l'état initial */
		HashMap<Variable,String> starting_car = new HashMap<Variable,String>();
		for (String part : parts_list) {
			starting_car.put(new Variable(part, booleans_dom),"FALSE");
		}
		for (String paint : colors_list) {
			starting_car.put(new Variable(paint, ALL_COLORS), "GRAY");
		}

		/* Actions d'installation */
		HashMap<Variable,String> outcome_chassis = new HashMap<Variable,String>();
		outcome_chassis.put(new Variable("HAS_CHASSIS",booleans_dom), "TRUE");
		this.INSTALL_CHASSIS = new Action(new HashMap<Variable,String>(), outcome_chassis);

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

		this.INSTALL_BODY = new Action(outcome_chassis, outcome_body);
		this.INSTALL_REAR_RIGHT_WHEEL = new Action(outcome_chassis, outcome_rrw);
		this.INSTALL_REAR_LEFT_WHEEL = new Action(outcome_chassis, outcome_rlw);
		this.INSTALL_FRONT_RIGHT_WHEEL = new Action(outcome_chassis, outcome_frw);
		this.INSTALL_FRONT_LEFT_WHEEL = new Action(outcome_chassis, outcome_flw);
		this.INSTALL_REAR_WHEELS = new Action(outcome_chassis, outcome_rlw.putAll(outcome_rrw));
		this.INSTALL_FRONT_WHEELS = new Action(outcome_chassis, outcome_flw.putAll(outcome_frw));
		this.INSTALL_LEFT_WHEELS = new Action(outcome_chassis, outcome_flw.putAll(outcome_rlw));
		this.INSTALL_RIGHT_WHEELS = new Action(outcome_chassis, outcome_frw.putAll(outcome_rrw));
		
		this.PAINT_ROOF = new Action(outcome_body, );


	}

	/*
    Des actions d’installation d’une pièce unique.
  */

  public State uniqueInstall(State state){
    return state;
  }
  /*
    Des actions d’installation parallèle
  */

  public State parallelInstall(State state){
    return state;
  }

  /*
    Des actions de peinture précises
  */

  public State specificPaint(State state){
    /*
    if ( INSTALL_BODY != false) {
        PAINT_ROOF = true;
    }
  */
    return state;
  }

  /*
    Des actions de peinture à large effet.
  */

  public State largePaint(State state){
    return state;
  }




}
