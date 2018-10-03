package examples;

import representations.*;
import planning.*;
import java.util.*;

public class AssemblyLine {

	private boolean HAS_CHASSIS;
	private boolean HAS_BODY;
	private boolean HAS_FRONT_LEFT_WHEEL;
	private boolean HAS_FRONT_RIGHT_WHEEL;
	private boolean HAS_REAR_LEFT_WHEEL;
	private boolean HAS_REAR_RIGHT_WHEEL;

	private Variable FRONT_LEFT_WHEEL_COLOR;
	private Variable FRONT_RIGHT_WHEEL_COLOR;
	private Variable REAR_LEFT_WHEEL_COLOR;
	private Variable REAR_RIGHT_WHEEL_COLOR;

	private Variable FRONT_COLOR;
	private Variable REAR_COLOR;
	private Variable LEFT_COLOR;
	private Variable RIGHT_COLOR;
	private Variable ROOF_COLOR;

	private Set<String> ALL_COLORS;

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

		this.HAS_CHASSIS=false;
		this.HAS_BODY=false;
		this.HAS_FRONT_LEFT_WHEEL=false;
		this.HAS_FRONT_RIGHT_WHEEL=false;
		this.HAS_REAR_LEFT_WHEEL=false;
		this.HAS_REAR_RIGHT_WHEEL=false;

		this.ALL_COLORS = new HashSet(Arrays.asList(new String[] {"GRAY", "BLACK", "WHITE", "RED", "GREEN", "BLUE", "ORANGE", "YELLOW"}));

		this.FRONT_LEFT_WHEEL_COLOR = new Variable("couleur roue avant gauche", this.ALL_COLORS);
		this.FRONT_RIGHT_WHEEL_COLOR = new Variable("couleur roue avant droite", this.ALL_COLORS);
		this.REAR_LEFT_WHEEL_COLOR = new Variable("couleur roue arrière gauche", this.ALL_COLORS);
		this.REAR_RIGHT_WHEEL_COLOR = new Variable("couleur roue arrière droite", this.ALL_COLORS);
		this.FRONT_COLOR = new Variable("couleur capot", this.ALL_COLORS);
		this.REAR_COLOR = new Variable("couleur hayon", this.ALL_COLORS);
		this.LEFT_COLOR = new Variable("couleur gauche", this.ALL_COLORS);
		this.RIGHT_COLOR = new Variable("couleur droite", this.ALL_COLORS);
		this.ROOF_COLOR = new Variable("couleur toit", this.ALL_COLORS);

	}

}
