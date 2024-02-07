package pn;
import java.math.BigDecimal;
import org.oristool.models.pn.PostUpdater;
import org.oristool.models.pn.Priority;
import org.oristool.models.stpn.MarkingExpr;
import org.oristool.models.stpn.trees.StochasticTransitionFeature;
import org.oristool.petrinet.EnablingFunction;
import org.oristool.petrinet.Marking;
import org.oristool.petrinet.PetriNet;
import org.oristool.petrinet.Place;
import org.oristool.petrinet.Transition;

public class PN_TrainCoupling_adv extends PN_TrainCoupling{
	
	private static double t_fS1ToC1_eft = 1.0;
	private static double t_fS1ToC1_lft = 2.0;
	private static double t_fS2ToC2_eft = t_fS1ToC1_eft;
	private static double t_fS2ToC2_lft = t_fS1ToC1_lft;
	
	public static void main(String[] args) {
		PetriNet net = new PetriNet();
		Marking marking = new Marking();
		build(net, marking);
		System.out.println("Ok");
	}
	
	public static void build(PetriNet net, Marking marking) {
		//Generating Nodes
	    Place c1 = net.addPlace("c1");
	    Place c2 = net.addPlace("c2");
	    Place cWait1 = net.addPlace("cWait1");
	    Place cWait2 = net.addPlace("cWait2");
	    Place couples1 = net.addPlace("couples1");
	    Place couples2 = net.addPlace("couples2");
	    Place d1 = net.addPlace("d1");
	    Place d2 = net.addPlace("d2");
	    Place dWait1 = net.addPlace("dWait1");
	    Place dWait2 = net.addPlace("dWait2");
	    Place decoupled1 = net.addPlace("decoupled1");
	    Place decoupled2 = net.addPlace("decoupled2");
	    Place decouples1 = net.addPlace("decouples1");
	    Place decouples2 = net.addPlace("decouples2");
	    Place e1 = net.addPlace("e1");
	    Place e1_dec = net.addPlace("e1_dec");
	    Place e2 = net.addPlace("e2");
	    Place e2_dec = net.addPlace("e2_dec");
	    Place fS1 = net.addPlace("fS1");
	    Place fS2 = net.addPlace("fS2");
	    Place in1 = net.addPlace("in1");
	    Place in2 = net.addPlace("in2");
	    Place out1 = net.addPlace("out1");
	    Place out2 = net.addPlace("out2");
	    Place preC1 = net.addPlace("preC1");
	    Place preC2 = net.addPlace("preC2");
	    Place ready1 = net.addPlace("ready1");
	    Place ready2 = net.addPlace("ready2");
	    Place sS1 = net.addPlace("sS1");
	    Place sS2 = net.addPlace("sS2");
	    Place sem = net.addPlace("sem");
	    Place slaves1 = net.addPlace("slaves1");
	    Place slaves2 = net.addPlace("slaves2");
	    Place stopped1 = net.addPlace("stopped1");
	    Place stopped2 = net.addPlace("stopped2");
	    Transition arrivals1 = net.addTransition("arrivals1");
	    Transition arrivals2 = net.addTransition("arrivals2");
	    Transition c1ToD1 = net.addTransition("c1ToD1");
	    Transition c2ToD2 = net.addTransition("c2ToD2");
	    Transition couple1 = net.addTransition("couple1");
	    Transition couple2 = net.addTransition("couple2");
	    Transition d1ToE1 = net.addTransition("d1ToE1");
	    Transition d1ToE1_dec = net.addTransition("d1ToE1_dec");
	    Transition d2ToE2 = net.addTransition("d2ToE2");
	    Transition d2ToE2_dec = net.addTransition("d2ToE2_dec");
	    Transition decouple1 = net.addTransition("decouple1");
	    Transition decouple2 = net.addTransition("decouple2");
	    Transition fMasterEnter1 = net.addTransition("fMasterEnter1");
	    Transition fMasterEnter2 = net.addTransition("fMasterEnter2");
	    Transition fS1ToC1 = net.addTransition("fS1ToC1");
	    Transition fS2ToC2 = net.addTransition("fS2ToC2");
	    Transition fSlaveEnter1 = net.addTransition("fSlaveEnter1");
	    Transition fSlaveEnter2 = net.addTransition("fSlaveEnter2");
	    Transition preCouple1 = net.addTransition("preCouple1");
	    Transition preCouple2 = net.addTransition("preCouple2");
	    Transition sMasterEnter1 = net.addTransition("sMasterEnter1");
	    Transition sMasterEnter2 = net.addTransition("sMasterEnter2");
	    Transition sS1toC1 = net.addTransition("sS1toC1");
	    Transition sS2ToC2 = net.addTransition("sS2ToC2");
	    Transition sSlaveEnter1 = net.addTransition("sSlaveEnter1");
	    Transition sSlaveEnter2 = net.addTransition("sSlaveEnter2");
	    Transition stop1 = net.addTransition("stop1");
	    Transition stop2 = net.addTransition("stop2");
	    Transition toC1 = net.addTransition("toC1");
	    Transition toC2 = net.addTransition("toC2");
	    Transition toD1 = net.addTransition("toD1");
	    Transition toD2 = net.addTransition("toD2");
	    Transition toOut1 = net.addTransition("toOut1");
	    Transition toOut1_dec = net.addTransition("toOut1_dec");
	    Transition toOut2 = net.addTransition("toOut2");
	    Transition toOut2_dec = net.addTransition("toOut2_dec");
	    Transition toReady1 = net.addTransition("toReady1");
	    Transition toReady2 = net.addTransition("toReady2");

	    //Generating Connectors
	    net.addInhibitorArc(sS2, fSlaveEnter2);
	    net.addInhibitorArc(stopped2, fMasterEnter2);
	    net.addInhibitorArc(decoupled1, d1ToE1);
	    net.addInhibitorArc(stopped2, fSlaveEnter2);
	    net.addInhibitorArc(sS2, fMasterEnter2);
	    net.addInhibitorArc(stopped1, fMasterEnter1);
	    net.addInhibitorArc(decoupled2, d2ToE2);
	    net.addInhibitorArc(stopped1, fSlaveEnter1);
	    net.addInhibitorArc(sS1, fSlaveEnter1);
	    net.addInhibitorArc(sS1, fMasterEnter1);
	    net.addPostcondition(toD2, d2);
	    net.addPostcondition(arrivals2, in2);
	    net.addPrecondition(slaves2, fSlaveEnter2);
	    net.addPrecondition(in1, fMasterEnter1);
	    net.addPrecondition(in2, fSlaveEnter2);
	    net.addPrecondition(dWait1, d1ToE1);
	    net.addPostcondition(couple1, cWait1);
	    net.addPrecondition(dWait1, toD1);
	    net.addPostcondition(toD1, d1);
	    net.addPrecondition(decouples2, toD2);
	    net.addPrecondition(sS1, sS1toC1);
	    net.addPrecondition(e1, toOut1);
	    net.addPostcondition(d2ToE2_dec, e2_dec);
	    net.addPostcondition(sMasterEnter1, sS1);
	    net.addPostcondition(fS2ToC2, preC2);
	    net.addPrecondition(decoupled1, d1ToE1_dec);
	    net.addPostcondition(decouple1, decoupled1);
	    net.addPrecondition(in2, fMasterEnter2);
	    net.addPostcondition(toOut2, sem);
	    net.addPrecondition(preC1, preCouple1);
	    net.addPrecondition(in1, fSlaveEnter1);
	    net.addPostcondition(toC2, c2);
	    net.addPostcondition(d1ToE1, e1);
	    net.addPostcondition(preCouple1, c1);
	    net.addPrecondition(slaves1, sSlaveEnter1);
	    net.addPrecondition(slaves2, sSlaveEnter2);
	    net.addPostcondition(toOut2, out2);
	    net.addPostcondition(d1ToE1_dec, e1_dec);
	    net.addPostcondition(c2ToD2, d2);
	    net.addPrecondition(e1_dec, toOut1_dec);
	    net.addPrecondition(ready1, c1ToD1);
	    net.addPrecondition(cWait1, toC1);
	    net.addPostcondition(stop2, stopped2);
	    net.addPrecondition(stopped2, sSlaveEnter2);
	    net.addPostcondition(decouple1, dWait1);
	    net.addPrecondition(preC2, preCouple2);
	    net.addPostcondition(c1ToD1, d1);
	    net.addPostcondition(arrivals1, in1);
	    net.addPostcondition(toReady2, ready2);
	    net.addPostcondition(decouple2, decoupled2);
	    net.addPrecondition(slaves1, fSlaveEnter1);
	    net.addPostcondition(sS2ToC2, preC2);
	    net.addPostcondition(d2ToE2, e2);
	    net.addPrecondition(stopped2, sMasterEnter2);
	    net.addPrecondition(e2_dec, toOut2_dec);
	    net.addPrecondition(dWait2, toD2);
	    net.addPostcondition(couple2, cWait2);
	    net.addPostcondition(toOut1, out1);
	    net.addPostcondition(toReady1, ready1);
	    net.addPostcondition(decouple2, dWait2);
	    net.addPrecondition(decoupled2, d2ToE2_dec);
	    net.addPrecondition(sem, fMasterEnter1);
	    net.addPrecondition(sem, sMasterEnter2);
	    net.addPostcondition(fMasterEnter2, fS2);
	    net.addPrecondition(couples2, toC2);
	    net.addPrecondition(fS2, fS2ToC2);
	    net.addPrecondition(fS1, fS1ToC1);
	    net.addPrecondition(cWait2, toReady2);
	    net.addPrecondition(c2, couple2);
	    net.addPrecondition(sem, fMasterEnter2);
	    net.addPostcondition(sMasterEnter2, sS2);
	    net.addPostcondition(fSlaveEnter2, fS2);
	    net.addPostcondition(toOut1_dec, out1);
	    net.addPostcondition(toC1, c1);
	    net.addPrecondition(dWait2, d2ToE2);
	    net.addPostcondition(fS1ToC1, preC1);
	    net.addPrecondition(stopped1, sMasterEnter1);
	    net.addPrecondition(c1, couple1);
	    net.addPostcondition(sSlaveEnter1, sS1);
	    net.addPostcondition(sSlaveEnter2, sS2);
	    net.addPrecondition(stopped1, sSlaveEnter1);
	    net.addPostcondition(fMasterEnter1, fS1);
	    net.addPrecondition(e2, toOut2);
	    net.addPostcondition(toOut1, sem);
	    net.addPrecondition(ready2, c2ToD2);
	    net.addPrecondition(couples1, toC1);
	    net.addPrecondition(in1, stop1);
	    net.addPrecondition(cWait1, toReady1);
	    net.addPrecondition(sem, sMasterEnter1);
	    net.addPostcondition(toOut2_dec, out2);
	    net.addPostcondition(sS1toC1, preC1);
	    net.addPrecondition(d2, decouple2);
	    net.addPostcondition(stop1, stopped1);
	    net.addPostcondition(preCouple2, c2);
	    net.addPrecondition(in2, stop2);
	    net.addPrecondition(d1, decouple1);
	    net.addPrecondition(cWait2, toC2);
	    net.addPostcondition(fSlaveEnter1, fS1);
	    net.addPrecondition(sS2, sS2ToC2);
	    net.addPrecondition(decouples1, toD1);

	    //Generating Properties
	    marking.setTokens(c1, 0);
	    marking.setTokens(c2, 0);
	    marking.setTokens(cWait1, 0);
	    marking.setTokens(cWait2, 0);
	    marking.setTokens(couples1, 0);
	    marking.setTokens(couples2, 0);
	    marking.setTokens(d1, 0);
	    marking.setTokens(d2, 0);
	    marking.setTokens(dWait1, 0);
	    marking.setTokens(dWait2, 0);
	    marking.setTokens(decoupled1, 0);
	    marking.setTokens(decoupled2, 0);
	    marking.setTokens(decouples1, 0);
	    marking.setTokens(decouples2, 0);
	    marking.setTokens(e1, 0);
	    marking.setTokens(e1_dec, 0);
	    marking.setTokens(e2, 0);
	    marking.setTokens(e2_dec, 0);
	    marking.setTokens(fS1, 0);
	    marking.setTokens(fS2, 0);
	    marking.setTokens(in1, 0);
	    marking.setTokens(in2, 0);
	    marking.setTokens(out1, 0);
	    marking.setTokens(out2, 0);
	    marking.setTokens(preC1, 0);
	    marking.setTokens(preC2, 0);
	    marking.setTokens(ready1, 0);
	    marking.setTokens(ready2, 0);
	    marking.setTokens(sS1, 0);
	    marking.setTokens(sS2, 0);
	    marking.setTokens(sem, 1);
	    marking.setTokens(slaves1, 0);
	    marking.setTokens(slaves2, 0);
	    marking.setTokens(stopped1, 0);
	    marking.setTokens(stopped2, 0);
	    arrivals1.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal(t_arrival1), MarkingExpr.from("1", net)));
	    arrivals1.addFeature(new Priority(0));
	    arrivals2.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal(t_arrival2), MarkingExpr.from("1", net)));
	    arrivals2.addFeature(new Priority(0));
	    c1ToD1.addFeature(StochasticTransitionFeature.newUniformInstance(new BigDecimal(t_c1ToD1_eft), new BigDecimal(t_c1ToD1_lft)));
	    c2ToD2.addFeature(StochasticTransitionFeature.newUniformInstance(new BigDecimal(t_c2ToD2_eft), new BigDecimal(t_c2ToD2_lft)));
	    couple1.addFeature(new EnablingFunction("c1>=2"));
	    couple1.addFeature(new PostUpdater("c1=0", net));
	    couple1.addFeature(StochasticTransitionFeature.newUniformInstance(new BigDecimal(t_couple1_eft), new BigDecimal(t_couple1_lft)));
	    couple2.addFeature(new EnablingFunction("c2>=2"));
	    couple2.addFeature(new PostUpdater("c2=0", net));
	    couple2.addFeature(StochasticTransitionFeature.newUniformInstance(new BigDecimal(t_couple2_eft), new BigDecimal(t_couple2_lft)));
	    d1ToE1.addFeature(StochasticTransitionFeature.newUniformInstance(new BigDecimal(t_d1ToE1_eft), new BigDecimal(t_d1ToE1_lft)));
	    d2ToE2.addFeature(StochasticTransitionFeature.newUniformInstance(new BigDecimal(t_d2ToE2_eft), new BigDecimal(t_d2ToE2_lft)));
	    d1ToE1_dec.addFeature(StochasticTransitionFeature.newUniformInstance(new BigDecimal(t_d1ToE1_eft), new BigDecimal(t_d1ToE1_lft)));
	    d2ToE2_dec.addFeature(StochasticTransitionFeature.newUniformInstance(new BigDecimal(t_d2ToE2_eft), new BigDecimal(t_d2ToE2_lft)));
	    decouple1.addFeature(StochasticTransitionFeature.newUniformInstance(new BigDecimal(t_decouple1_eft), new BigDecimal(t_decouple1_lft)));
	    decouple2.addFeature(StochasticTransitionFeature.newUniformInstance(new BigDecimal(t_decouple2_eft), new BigDecimal(t_decouple2_lft)));
	    fMasterEnter1.addFeature(new PostUpdater("slaves1=" + (getN()-1) + ",couples1=" + (getN()-2) + ",decouples1=" + (getN()-2), net));
	    fMasterEnter1.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal("0"), MarkingExpr.from("1", net)));
	    fMasterEnter1.addFeature(new Priority(1));
	    fMasterEnter2.addFeature(new PostUpdater("slaves2=" + (getN()-1) + ",couples2=" + (getN()-2) + ",decouples2=" + (getN()-2), net));
	    fMasterEnter2.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal("0"), MarkingExpr.from("1", net)));
	    fMasterEnter2.addFeature(new Priority(1));
	    fS1ToC1.addFeature(StochasticTransitionFeature.newUniformInstance(new BigDecimal(t_fS1ToC1_eft), new BigDecimal(t_fS1ToC1_lft)));
	    fS2ToC2.addFeature(StochasticTransitionFeature.newUniformInstance(new BigDecimal(t_fS2ToC2_eft), new BigDecimal(t_fS2ToC2_lft)));
	    fSlaveEnter1.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal("0"), MarkingExpr.from("1", net)));
	    fSlaveEnter1.addFeature(new Priority(1));
	    fSlaveEnter2.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal("0"), MarkingExpr.from("1", net)));
	    fSlaveEnter2.addFeature(new Priority(1));
	    preCouple1.addFeature(new EnablingFunction("c1+cWait1<2"));
	    preCouple1.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal("0"), MarkingExpr.from("1", net)));
	    preCouple1.addFeature(new Priority(0));
	    preCouple2.addFeature(new EnablingFunction("c2+cWait2<2"));
	    preCouple2.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal("0"), MarkingExpr.from("1", net)));
	    preCouple2.addFeature(new Priority(0));
	    sMasterEnter1.addFeature(new EnablingFunction("stopped1>=stopped2"));
	    sMasterEnter1.addFeature(new PostUpdater("slaves1=" + (getN()-1) + ",couples1=" + (getN()-2) + ",decouples1=" + (getN()-2), net));
	    sMasterEnter1.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal("0"), MarkingExpr.from("1", net)));
	    sMasterEnter1.addFeature(new Priority(0));
	    sMasterEnter2.addFeature(new EnablingFunction("stopped2>=stopped1"));
	    sMasterEnter2.addFeature(new PostUpdater("slaves2=" + (getN()-1) + ",couples2=" + (getN()-2) + ",decouples2=" + (getN()-2), net));
	    sMasterEnter2.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal("0"), MarkingExpr.from("1", net)));
	    sMasterEnter2.addFeature(new Priority(0));
	    sS1toC1.addFeature(StochasticTransitionFeature.newUniformInstance(new BigDecimal(t_sS1ToC1_eft), new BigDecimal(t_sS1ToC1_lft)));
	    sS2ToC2.addFeature(StochasticTransitionFeature.newUniformInstance(new BigDecimal(t_sS2ToC2_eft), new BigDecimal(t_sS2ToC2_lft)));
	    sSlaveEnter1.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal("0"), MarkingExpr.from("1", net)));
	    sSlaveEnter1.addFeature(new Priority(0));
	    sSlaveEnter2.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal("0"), MarkingExpr.from("1", net)));
	    sSlaveEnter2.addFeature(new Priority(0));
	    stop1.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal("0"), MarkingExpr.from("1", net)));
	    stop1.addFeature(new Priority(0));
	    stop2.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal("0"), MarkingExpr.from("1", net)));
	    stop2.addFeature(new Priority(0));
	    toC1.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal("0"), MarkingExpr.from("1", net)));
	    toC1.addFeature(new Priority(1));
	    toC2.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal("0"), MarkingExpr.from("1", net)));
	    toC2.addFeature(new Priority(1));
	    toD1.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal("0"), MarkingExpr.from("1", net)));
	    toD1.addFeature(new Priority(1));
	    toD2.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal("0"), MarkingExpr.from("1", net)));
	    toD2.addFeature(new Priority(0));
	    toOut1.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal("0"), MarkingExpr.from("1", net)));
	    toOut1.addFeature(new Priority(0));
	    toOut1_dec.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal("0"), MarkingExpr.from("1", net)));
	    toOut1_dec.addFeature(new Priority(0));
	    toOut2.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal("0"), MarkingExpr.from("1", net)));
	    toOut2.addFeature(new Priority(0));
	    toOut2_dec.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal("0"), MarkingExpr.from("1", net)));
	    toOut2_dec.addFeature(new Priority(0));
	    toReady1.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal("0"), MarkingExpr.from("1", net)));
	    toReady1.addFeature(new Priority(0));
	    toReady2.addFeature(StochasticTransitionFeature.newDeterministicInstance(new BigDecimal("0"), MarkingExpr.from("1", net)));
	    toReady2.addFeature(new Priority(0));
	}

}