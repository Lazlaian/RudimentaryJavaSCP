package SCPsolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Constraints.LessThan;
import Constraints.NotEqualPlusC;
import DecisionTree.ImplicationGraph;

public class Main {
	
	

	public static void main(String[] args) {
		//We here instantiate a simple problem to test our SCP solver
		
		//Seeting up the domains (three variables, all in {1, 2, 3})
		Map<String, Set> domains = new HashMap<String, Set>();
		
		Set<Integer> temp1 = new HashSet<Integer>();
		Set<Integer> temp2 = new HashSet<Integer>();
		Set<Integer> temp3 = new HashSet<Integer>();
		
		temp1.add(1);
		temp1.add(2);
		temp1.add(3);
		
		temp2.add(1);
		temp2.add(2);
		temp2.add(3);
		
		temp3.add(1);
		temp3.add(2);
		temp3.add(3);
		
		domains.put("x1", temp1);
		domains.put("x2", temp2);
		domains.put("x3", temp3);
		
		//Setting up the constraint
		List<Constraint> constraints = new ArrayList<Constraint>();
		LessThan lessThan1 = new LessThan("x1", "x2"); // x1 < x2
		LessThan lessThan2 = new LessThan("x2", "x3"); // x2 < x3
		LessThan lessThan3 = new LessThan("x3", "x1"); // x3 < x1
		NotEqualPlusC x1Notx2Plus0 = new NotEqualPlusC("x1", "x2", 0); // x1 != x2
		
		//ADDING CONSTRAINTS
		//constraints.add(lessThan1); // x1 < x2
		constraints.add(lessThan2); // x2 < x3
		//constraints.add(lessThan3); // x3 < x1
		constraints.add(x1Notx2Plus0); // x1 != x2
		
		Problem problem = new Problem(domains, constraints);
		
		System.out.println("Search has started");
		Triple<Integer, List<Constraint>, ImplicationGraph> solution = Problem.enumerate(problem);		
		System.out.println(solution.get1() + " solutions have been found.");	
		System.out.println("Constraints :");
		for(Constraint c : solution.get2()) {
			System.out.println(c.toString());
		}
		
		System.out.println("-------");
		System.out.println("-------");
		System.out.println("Implication Graph");
		
		solution.get3().print();
		
		//4 places need go be uncommented (1 in problem, 1 in ImplicationGraph, 1 in Clause and 1 somewhere else)
	}

}
