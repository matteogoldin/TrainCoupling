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

public class PN_NoTrainCoupling_simple extends PN_NoTrainCoupling{
	
	public static void build(PetriNet net, Marking marking) {
	    //Generating Nodes
	    Place e1 = net.addPlace("e1");
	    Place e2 = net.addPlace("e2");
	    Place in1 = net.addPlace("in1");
	    Place in2 = net.addPlace("in2");
	    Place out1 = net.addPlace("out1");
	    Place out2 = net.addPlace("out2");
	    Place s1 = net.addPlace("s1");
	    Place s2 = net.addPlace("s2");
	    Place sem = net.addPlace("sem");
	    Transition arrivals1 = net.addTransition("arrivals1");
	    Transition arrivals2 = net.addTransition("arrivals2");
	    Transition enter1 = net.addTransition("enter1");
	    Transition enter2 = net.addTransition("enter2");
	    Transition s1ToE1 = net.addTransition("s1ToE1");
	    Transition s2ToC2 = net.addTransition("s2ToC2");
	    Transition toOut1 = net.addTransition("toOut1");
	    Transition toOut2 = net.addTransition("toOut2");

	    //Generating Connectors
	    net.addPostcondition(toOut2, sem);
	    net.addPostcondition(toOut1, sem);
	    net.addPostcondition(s1ToE1, e1);
	    net.addPrecondition(e2, toOut2);
	    net.addPrecondition(s2, s2ToC2);
	    net.addPostcondition(enter2, s2);
	    net.addPrecondition(sem, enter2);
	    net.addPrecondition(in1, enter1);
	    net.addPostcondition(arrivals1, in1);
	    net.addPrecondition(sem, enter1);
	    net.addPostcondition(toOut2, out2);
	    net.addPostcondition(toOut1, out1);
	    net.addPostcondition(enter1, s1);
	    net.addPrecondition(s1, s1ToE1);
	    net.addPostcondition(s2ToC2, e2);
	    net.addPrecondition(in2, enter2);
	    net.addPrecondition(e1, toOut1);
	    net.addPostcondition(arrivals2, in2);

	    //Generating Properties
	    marking.setTokens(e1, 0);
	    marking.setTokens(e2, 0);
	    marking.setTokens(in1, 0);
	    marking.setTokens(in2, 0);
	    marking.setTokens(out1, 0);
	    marking.setTokens(out2, 0);
	    marking.setTokens(s1, 0);
	    marking.setTokens(s2, 0);
	    marking.setTokens(sem, 1);
	    arrivals1.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal(t_arrival1), MarkingExpr.from("1", net)));
	    arrivals1.addFeature(new Priority(0));
	    arrivals2.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal(t_arrival2), MarkingExpr.from("1", net)));
	    arrivals2.addFeature(new Priority(0));
	    enter1.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal("0"), MarkingExpr.from("1", net)));
	    enter1.addFeature(new Priority(0));
	    enter1.addFeature(new EnablingFunction("in1>=in2"));
	    enter2.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal("0"), MarkingExpr.from("1", net)));
	    enter2.addFeature(new Priority(0));
	    enter2.addFeature(new EnablingFunction("in2>=in1"));
	    s1ToE1.addFeature(StochasticTransitionFeature.newUniformInstance(new BigDecimal(t_sS1ToE1_eft), new BigDecimal(t_sS1ToE1_lft)));
	    s2ToC2.addFeature(StochasticTransitionFeature.newUniformInstance(new BigDecimal(t_sS2ToE2_eft), new BigDecimal(t_sS2ToE2_lft)));
	    toOut1.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal("0"), MarkingExpr.from("1", net)));
	    toOut1.addFeature(new Priority(0));
	    toOut2.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal("0"), MarkingExpr.from("1", net)));
	    toOut2.addFeature(new Priority(0));
	  }

}
