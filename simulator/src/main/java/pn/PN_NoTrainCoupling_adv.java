package pn;

import java.math.BigDecimal;

import org.oristool.models.pn.Priority;
import org.oristool.models.stpn.MarkingExpr;
import org.oristool.models.stpn.trees.StochasticTransitionFeature;
import org.oristool.petrinet.EnablingFunction;
import org.oristool.petrinet.Marking;
import org.oristool.petrinet.PetriNet;
import org.oristool.petrinet.Place;
import org.oristool.petrinet.Transition;

public class PN_NoTrainCoupling_adv extends PN_NoTrainCoupling{
	private static double t_fS1ToE1_eft = 29.0;
	private static double t_fS1ToE1_lft = 39.0;
	private static double t_fS2ToE2_eft = t_fS1ToE1_eft;
	private static double t_fS2ToE2_lft = t_fS1ToE1_lft;
	
	public static void build(PetriNet net, Marking marking) {
	    //Generating Nodes
	    Place e1 = net.addPlace("e1");
	    Place e2 = net.addPlace("e2");
	    Place fS1 = net.addPlace("fS1");
	    Place fS2 = net.addPlace("fS2");
	    Place in1 = net.addPlace("in1");
	    Place in2 = net.addPlace("in2");
	    Place out1 = net.addPlace("out1");
	    Place out2 = net.addPlace("out2");
	    Place sS1 = net.addPlace("sS1");
	    Place sS2 = net.addPlace("sS2");
	    Place sem = net.addPlace("sem");
	    Place stopped1 = net.addPlace("stopped1");
	    Place stopped2 = net.addPlace("stopped2");
	    Transition arrivals1 = net.addTransition("arrivals1");
	    Transition arrivals2 = net.addTransition("arrivals2");
	    Transition fEnter1 = net.addTransition("fEnter1");
	    Transition fEnter2 = net.addTransition("fEnter2");
	    Transition fS1ToE1 = net.addTransition("fS1ToE1");
	    Transition fS2ToC2 = net.addTransition("fS2ToC2");
	    Transition sEnter1 = net.addTransition("sEnter1");
	    Transition sEnter2 = net.addTransition("sEnter2");
	    Transition sS1ToE1 = net.addTransition("sS1ToE1");
	    Transition sS2ToE2 = net.addTransition("sS2ToE2");
	    Transition stop1 = net.addTransition("stop1");
	    Transition stop2 = net.addTransition("stop2");
	    Transition toOut1 = net.addTransition("toOut1");
	    Transition toOut2 = net.addTransition("toOut2");

	    //Generating Connectors
	    net.addInhibitorArc(stopped2, fEnter2);
	    net.addInhibitorArc(sS1, fEnter1);
	    net.addInhibitorArc(stopped1, fEnter1);
	    net.addInhibitorArc(sS2, fEnter2);
	    net.addPrecondition(sS1, sS1ToE1);
	    net.addPrecondition(sS2, sS2ToE2);
	    net.addPrecondition(sem, fEnter2);
	    net.addPrecondition(in1, fEnter1);
	    net.addPostcondition(stop1, stopped1);
	    net.addPrecondition(sem, sEnter2);
	    net.addPostcondition(sS2ToE2, e2);
	    net.addPostcondition(toOut2, sem);
	    net.addPrecondition(stopped1, sEnter1);
	    net.addPostcondition(sEnter1, sS1);
	    net.addPostcondition(toOut1, out1);
	    net.addPostcondition(sEnter2, sS2);
	    net.addPostcondition(toOut2, out2);
	    net.addPrecondition(fS2, fS2ToC2);
	    net.addPostcondition(sS1ToE1, e1);
	    net.addPrecondition(stopped2, sEnter2);
	    net.addPrecondition(sem, sEnter1);
	    net.addPrecondition(fS1, fS1ToE1);
	    net.addPrecondition(in2, fEnter2);
	    net.addPostcondition(fEnter1, fS1);
	    net.addPostcondition(fS2ToC2, e2);
	    net.addPostcondition(fS1ToE1, e1);
	    net.addPostcondition(arrivals2, in2);
	    net.addPrecondition(e1, toOut1);
	    net.addPostcondition(stop2, stopped2);
	    net.addPrecondition(in1, stop1);
	    net.addPostcondition(fEnter2, fS2);
	    net.addPostcondition(arrivals1, in1);
	    net.addPrecondition(e2, toOut2);
	    net.addPrecondition(sem, fEnter1);
	    net.addPostcondition(toOut1, sem);
	    net.addPrecondition(in2, stop2);

	    //Generating Properties
	    marking.setTokens(e1, 0);
	    marking.setTokens(e2, 0);
	    marking.setTokens(fS1, 0);
	    marking.setTokens(fS2, 0);
	    marking.setTokens(in1, 0);
	    marking.setTokens(in2, 0);
	    marking.setTokens(out1, 0);
	    marking.setTokens(out2, 0);
	    marking.setTokens(sS1, 0);
	    marking.setTokens(sS2, 0);
	    marking.setTokens(sem, 1);
	    marking.setTokens(stopped1, 0);
	    marking.setTokens(stopped2, 0);
	    arrivals1.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal(t_arrival1), MarkingExpr.from("1", net)));
	    arrivals1.addFeature(new Priority(0));
	    arrivals2.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal(t_arrival2), MarkingExpr.from("1", net)));
	    arrivals2.addFeature(new Priority(0));
	    fEnter1.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal("0"), MarkingExpr.from("1", net)));
	    fEnter1.addFeature(new Priority(1));
	    fEnter2.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal("0"), MarkingExpr.from("1", net)));
	    fEnter2.addFeature(new Priority(1));
	    fS1ToE1.addFeature(StochasticTransitionFeature.newUniformInstance(new BigDecimal(t_fS1ToE1_eft), new BigDecimal(t_fS1ToE1_lft)));
	    fS2ToC2.addFeature(StochasticTransitionFeature.newUniformInstance(new BigDecimal(t_fS2ToE2_eft), new BigDecimal(t_fS2ToE2_lft)));
	    sEnter1.addFeature(new EnablingFunction("stopped1>=stopped2"));
	    sEnter1.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal("0"), MarkingExpr.from("1", net)));
	    sEnter1.addFeature(new Priority(0));
	    sEnter2.addFeature(new EnablingFunction("stopped2>=stopped1"));
	    sEnter2.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal("0"), MarkingExpr.from("1", net)));
	    sEnter2.addFeature(new Priority(0));
	    sS1ToE1.addFeature(StochasticTransitionFeature.newUniformInstance(new BigDecimal(t_sS1ToE1_eft), new BigDecimal(t_sS1ToE1_lft)));
	    sS2ToE2.addFeature(StochasticTransitionFeature.newUniformInstance(new BigDecimal(t_sS2ToE2_eft), new BigDecimal(t_sS2ToE2_lft)));
	    stop1.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal("0"), MarkingExpr.from("1", net)));
	    stop1.addFeature(new Priority(0));
	    stop2.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal("0"), MarkingExpr.from("1", net)));
	    stop2.addFeature(new Priority(0));
	    toOut1.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal("0"), MarkingExpr.from("1", net)));
	    toOut1.addFeature(new Priority(0));
	    toOut2.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal("0"), MarkingExpr.from("1", net)));
	    toOut2.addFeature(new Priority(0));
	  }
}
