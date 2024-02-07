package pn;
import org.oristool.petrinet.Marking;
import org.oristool.petrinet.PetriNet;

public abstract class PN_TrainCoupling {
	private static int N = 4; //number of coupled trains
	
	//Time expressed in minutes
	protected static double t_arrival1 = 20.0;
	protected static double t_sS1ToC1_eft = 2.0;
	protected static double t_sS1ToC1_lft = 3.0;
	protected static double t_couple1_eft = 0.5;
	protected static double t_couple1_lft = 2.0;
	protected static double t_c1ToD1_eft = 28.0;
	protected static double t_c1ToD1_lft = 37.0;
	protected static double t_decouple1_eft = 0.5;
	protected static double t_decouple1_lft = 2.0;
	protected static double t_d1ToE1_eft = 2.0;
	protected static double t_d1ToE1_lft = 3.0;
	
	protected static double t_arrival2 = t_arrival1;
	protected static double t_sS2ToC2_eft = t_sS1ToC1_eft;
	protected static double t_sS2ToC2_lft = t_sS1ToC1_lft;
	protected static double t_couple2_eft = t_couple1_eft;
	protected static double t_couple2_lft = t_couple1_lft;
	protected static double t_c2ToD2_eft = t_c1ToD1_eft;
	protected static double t_c2ToD2_lft = t_c1ToD1_lft;
	protected static double t_decouple2_eft = t_decouple1_eft;
	protected static double t_decouple2_lft = t_decouple1_lft;
	protected static double t_d2ToE2_eft = t_d1ToE1_eft;
	protected static double t_d2ToE2_lft = t_d1ToE1_lft;
	
	public static void build(PetriNet net, Marking marking) {}

	public static int getN() {
		return N;
	}

	public static void setN(int n) {
		N = n;
	}
	
	public static double getT_arrival1() {
		return t_arrival1;
	}

	public static void setT_arrival1(double t_arrival1) {
		PN_TrainCoupling.t_arrival1 = t_arrival1;
	}

	public static double getT_arrival2() {
		return t_arrival2;
	}

	public static void setT_arrival2(double t_arrival2) {
		PN_TrainCoupling.t_arrival2 = t_arrival2;
	}
}
