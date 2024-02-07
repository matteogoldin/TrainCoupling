package pn;

import org.oristool.petrinet.Marking;
import org.oristool.petrinet.PetriNet;

public abstract class PN_NoTrainCoupling {
	protected static double t_arrival1 = 20.0;
	protected static double t_sS1ToE1_eft = 30;
	protected static double t_sS1ToE1_lft = 40;
	protected static double t_arrival2 = t_arrival1;
	protected static double t_sS2ToE2_eft = t_sS1ToE1_eft;
	protected static double t_sS2ToE2_lft = t_sS1ToE1_lft;
	
	public static void build(PetriNet pn, Marking marking) {}

	public static double getT_arrival1() {
		return t_arrival1;
	}

	public static void setT_arrival1(double t_arrival1) {
		PN_NoTrainCoupling.t_arrival1 = t_arrival1;
	}

	public static double getT_arrival2() {
		return t_arrival2;
	}

	public static void setT_arrival2(double t_arrival2) {
		PN_NoTrainCoupling.t_arrival2 = t_arrival2;
	}
	
}
