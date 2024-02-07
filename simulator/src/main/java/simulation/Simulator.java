package simulation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.oristool.analyzer.log.AnalysisLogger;
import org.oristool.models.stpn.RewardRate;
import org.oristool.models.stpn.TransientSolution;
import org.oristool.models.stpn.TransientSolutionViewer;
import org.oristool.petrinet.Marking;
import org.oristool.petrinet.MarkingCondition;
import org.oristool.petrinet.PetriNet;
import org.oristool.simulator.rewards.ContinuousRewardTime;
import org.oristool.simulator.stpn.STPNSimulatorComponentsFactory;
import org.oristool.simulator.Sequencer;
import org.oristool.simulator.TimeSeriesRewardResult;
import org.oristool.simulator.rewards.ContinuousRewardTime;
import org.oristool.simulator.rewards.RewardEvaluator;
import org.oristool.simulator.stpn.STPNSimulatorComponentsFactory;
import org.oristool.simulator.stpn.TransientMarkingConditionProbability;

import pn.*;

public class Simulator {
	private enum pn_types{
		simple,
		advanced;
	}
	
	private static pn_types type = pn_types.simple;
	private static BigDecimal timeLimit = new BigDecimal(600);
	private static BigDecimal timeStep = new BigDecimal(1);
	private static int runsNumber = 10000;
	private static Boolean viewer = false;
	
	public static void main(String[] args) {
		//Capacity simulation varying the number of couplings with arrival period of 20 min
		type = pn_types.simple;
		timeLimit = new BigDecimal(300);
		ArrayList<Double> capArray = new ArrayList<>();
		for(int i=1; i<=10; i++) {
			capArray.add(simulateCapacity(i));
		}
		
		timeLimit = new BigDecimal(600);
		capArray = new ArrayList<>();
		for(int i=1; i<=10; i++) {
			capArray.add(simulateCapacity(i));
		}
		System.out.println("Capacity array simple PN " + (timeLimit.intValue()/60) + "h: " + capArray + "\n");
		
		System.out.println("Capacity array simple PN " + (timeLimit.intValue()/60) + "h: " + capArray + "\n");
		timeLimit = new BigDecimal(900);
		capArray = new ArrayList<>();
		for(int i=1; i<=10; i++) {
			capArray.add(simulateCapacity(i));
		}
		System.out.println("Capacity array simple PN " + (timeLimit.intValue()/60) + "h: " + capArray + "\n");
		
		//Capacity simulation varying the number of couplings with arrival period of 30 min
		PN_TrainCoupling.setT_arrival1(30);
		PN_TrainCoupling.setT_arrival2(30);
		PN_NoTrainCoupling.setT_arrival1(30);
		PN_NoTrainCoupling.setT_arrival2(30);
		
		type = pn_types.simple;
		
		timeLimit = new BigDecimal(300);
		capArray = new ArrayList<>();
		for(int i=1; i<=10; i++) {
			capArray.add(simulateCapacity(i));
		}
		System.out.println("Capacity array simple PN " + (timeLimit.intValue()/60) + "h: " + capArray + "\n");
		
		timeLimit = new BigDecimal(600);
		capArray = new ArrayList<>();
		for(int i=1; i<=10; i++) {
			capArray.add(simulateCapacity(i));
		}
		System.out.println("Capacity array simple PN " + (timeLimit.intValue()/60) + "h: " + capArray + "\n");
		
		timeLimit = new BigDecimal(900);
		capArray = new ArrayList<>();
		for(int i=1; i<=10; i++) {
			capArray.add(simulateCapacity(i));
		}
		System.out.println("Capacity array simple PN " + (timeLimit.intValue()/60) + "h: " + capArray + "\n");
		
		//Optimal number of couplings varying the arrival period
		int [] arrivals = {10,20,30,40,50,60};
		ArrayList<Double> maxCapArray = new ArrayList<>();
		ArrayList<Integer> maxCapIndexArray = new ArrayList<>();
		type = pn_types.simple;
		timeLimit = new BigDecimal(300);
		for(int i=0; i<arrivals.length;i++) {
			PN_TrainCoupling.setT_arrival1(arrivals[i]);
			PN_TrainCoupling.setT_arrival2(arrivals[i]);
			PN_NoTrainCoupling.setT_arrival1(arrivals[i]);
			PN_NoTrainCoupling.setT_arrival2(arrivals[i]);
			capArray = new ArrayList<>();
			for(int j=1; j<=10; j++) {
				capArray.add(simulateCapacity(j));
			}
			Double maxCap = capArray.get(0);
			int maxCapIndex = 0;
			for(int j=1; j<capArray.size();j++) {
				if(capArray.get(j)>maxCap) {
					maxCap = capArray.get(j);
					maxCapIndex = j;
				}
			}
			maxCapArray.add(maxCap);
			maxCapIndexArray.add(maxCapIndex);
		}
		System.out.println("----------------------- \n");
		System.out.println("Max capacity in 5h varying the arrival period:");
		System.out.println("Arrival Period " + arrivals);
		System.out.println("MaxCap " + maxCapArray);
		System.out.println("MaxCapIndex " + maxCapIndexArray);
		
	}
	
	public static double simulateCapacity(int N) {
		PetriNet net = new PetriNet();
		Marking marking = new Marking();
		String rewardString = "out1+out2";
		if(N > 1) {
			PN_TrainCoupling.setN(N);
			if(type == pn_types.advanced ) {
				PN_TrainCoupling_adv.build(net, marking);
			}
			else {
				PN_TrainCoupling_simple.build(net, marking);
			}
		}
		else if(N == 1){
			if(type == pn_types.advanced ) {
				PN_NoTrainCoupling_adv.build(net, marking);
			}
			else {
				PN_NoTrainCoupling_simple.build(net, marking);
			}
		}
		double capacity;
		TransientSolution<Marking, RewardRate> solution;
		solution = simulate(net, marking, timeLimit, timeStep, rewardString, runsNumber, viewer);
		capacity = getCapBySolution(solution, rewardString);
		if(N >1) {
			System.out.println("Capacity with " + N + " couples in " + timeLimit + " minutes: " + capacity);
		}
		else {
			System.out.println("Capacity without coupling in " + timeLimit + " minutes: " + capacity);
		}	
		return capacity;
	}
	
	public static TransientSolution<Marking, RewardRate> simulate(PetriNet net, Marking initialMarking, BigDecimal timeLimit, BigDecimal timeStep, String rewardString, int runsNumber, Boolean viewer) {

    	Sequencer s = new Sequencer(net, initialMarking, new STPNSimulatorComponentsFactory(), new AnalysisLogger() {
			@Override
			public void log(String message) { }
			@Override
			public void debug(String string) { }
		});
        
    	// Derive the number of time points
    	int samplesNumber = (timeLimit.divide(timeStep)).intValue() + 1;
    	
    	// Create a reward (which is a sequencer observer)
    	TransientMarkingConditionProbability reward = new TransientMarkingConditionProbability(
        		s, new ContinuousRewardTime(timeStep), samplesNumber, MarkingCondition.fromString(rewardString));
        
    	// Create a reward evaluator (which is a reward observer)
    	RewardEvaluator rewardEvaluator = new RewardEvaluator(reward, runsNumber);
    	
    	// Run simulation
        s.simulate();
        
        // Get simulation results
        TimeSeriesRewardResult result = (TimeSeriesRewardResult)reward.evaluate();
        
        TransientSolution<Marking, RewardRate> solution = getTransientSolutionFromSimulatorResult(result, rewardString, initialMarking, timeLimit, timeStep);
        // Plot results
        if(viewer) {
            new TransientSolutionViewer(solution);
        }
   
        return solution;
	}
	
	 public static TransientSolution<Marking, RewardRate> getTransientSolutionFromSimulatorResult(
	    		TimeSeriesRewardResult result, String rewardString, Marking initialMarking, BigDecimal timeLimit, BigDecimal timeStep) {

	        RewardRate rewardRate = RewardRate.fromString(rewardString);
	    	List<Marking> regenerations = new ArrayList<>(Arrays.asList(initialMarking));
	        List<RewardRate> columnStates = new ArrayList<>();
	        columnStates.add(rewardRate);
	        
	        TransientSolution<Marking, RewardRate> solution = new TransientSolution<>(timeLimit, timeStep, regenerations, columnStates, initialMarking);
	        
	        List<Marking> mrkTmp = new ArrayList<>(result.getMarkings());
	        TransientSolution<Marking, Marking> tmpSolution = new TransientSolution<>(timeLimit, timeStep, regenerations, mrkTmp, initialMarking);
	        
	        for (int t = 0; t < tmpSolution.getSolution().length; t++) {
	            for (int i = 0; i < mrkTmp.size(); i++) {
	                tmpSolution.getSolution()[t][0][i] = result.getTimeSeries(mrkTmp.get(i))[t].doubleValue();
	            }
	        }
	        
	        // Evaluate the reward
	        TransientSolution<Marking, RewardRate> rewardTmpResult = TransientSolution.computeRewards(false, tmpSolution, rewardRate);
	        for (int t = 0; t < solution.getSolution().length; t++) {
	            solution.getSolution()[t][0][columnStates.indexOf(rewardRate)] 
	                    = rewardTmpResult.getSolution()[t][0][0];
	        }
	        return solution;
	    }
	 
	 public static double getCapBySolution(TransientSolution<Marking, RewardRate> solution, String rewardString) {
		RewardRate rewardRate = RewardRate.fromString(rewardString);
	    List<RewardRate> columnStates = new ArrayList<>();
	    columnStates.add(rewardRate);
	    return solution.getSolution()[solution.getSolution().length-1][0][columnStates.indexOf(rewardRate)];	    
	 }
	
}
