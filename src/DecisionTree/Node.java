package DecisionTree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Constraints.Clause;
import SCPsolver.Constraint;
import SCPsolver.Tuple;

public class Node {
	private String var;
	private Set domain;
	private int decisionLevel;
	private List<Tuple<Node, Constraint>> ascNodes = new ArrayList<Tuple<Node, Constraint>>();
	private List<Tuple<Node, Constraint>> descNodes = new ArrayList<Tuple<Node, Constraint>>();
	
	/**
	 * @param var
	 * @param domain
	 * @param decisionLevel
	 * @param ascNodes
	 * @param descNodes
	 */
	public Node(String var, Set domain, int decisionLevel, List<Tuple<Node, Constraint>> ascNodes,
			List<Tuple<Node, Constraint>> descNodes) {
		this.var = var;
		this.domain = domain;
		this.decisionLevel = decisionLevel;
		this.ascNodes = ascNodes;
		this.descNodes = descNodes;
	}
	
	/**
	 * @param var
	 * @param domain
	 * @param decisionLevel
	 * @param ascNodes
	 * @param descNodes
	 */
	public Node(String var, Set domain, int decisionLevel) {
		this.var = var;
		this.domain = domain;
		this.decisionLevel = decisionLevel;
	}
	
	/**
	 * @param var
	 * @param domain
	 * @param decisionLevel
	 * @param ascNodes
	 * @param descNodes
	 */
	public Node(List<Tuple<Node, Constraint>> ascNodes) {
		this.ascNodes = ascNodes;
	}
	
	/**
	 * @return the var
	 */
	public String getVar() {
		return var;
	}

	/**
	 * @param var the var to set
	 */
	public void setVar(String var) {
		this.var = var;
	}

	/**
	 * @return the domain
	 */
	public Set getDomain() {
		return domain;
	}

	/**
	 * @param domain the domain to set
	 */
	public void setDomain(Set domain) {
		this.domain = domain;
	}

	/**
	 * @return the decisionLevel
	 */
	public int getDecisionLevel() {
		return decisionLevel;
	}

	/**
	 * @param decisionLevel the decisionLevel to set
	 */
	public void setDecisionLevel(int decisionLevel) {
		this.decisionLevel = decisionLevel;
	}

	/**
	 * @return the ascNodes
	 */
	public List<Tuple<Node, Constraint>> getAscNodes() {
		return ascNodes;
	}

	/**
	 * @param ascNodes the ascNodes to set
	 */
	public void setAscNodes(List<Tuple<Node, Constraint>> ascNodes) {
		this.ascNodes = ascNodes;
	}

	/**
	 * @return the descNodes
	 */
	public List<Tuple<Node, Constraint>> getDescNodes() {
		return descNodes;
	}

	/**
	 * @param descNodes the descNodes to set
	 */
	public void setDescNodes(List<Tuple<Node, Constraint>> descNodes) {
		this.descNodes = descNodes;
	}

	public String getNodeType() {
		if (this.getAscNodes().isEmpty()) {
			return "Decision";
		} else if (this.getVar() == null) {
			return "Conflict";
		} else {
			return "Standard";
		}
	}
	
	public boolean equals(Node n) {
		return (n!=null)
				&& (n.getAscNodes().containsAll(this.getAscNodes())) && (this.getAscNodes().containsAll(n.getAscNodes()))
				&& (n.getDescNodes().containsAll(this.getDescNodes())) && (this.getDescNodes().containsAll(n.getDescNodes()))
				&& (n.getVar() == this.getVar())
				&& (n.getDecisionLevel() == this.getDecisionLevel())
				&& (n.getDomain().equals(this.getDomain()));
	}
	
	
	public void print() {
		System.out.println("    Var : " + this.getVar() + " // Decision Level : " + this.getDecisionLevel() + " // Domain : " + this.getDomain().toString());
		if(!this.getAscNodes().isEmpty()) {
			System.out.println("    	Obtained by constraint" + 
							this.getAscNodes().get(0).get2().toString() + 
							" applied to following ascending Nodes :");
			for (Tuple<Node, Constraint> t : this.getAscNodes()) {
				System.out.println("        (Var : " + t.get1().getVar() 
						+ " // Decision Level : " + t.get1().getDecisionLevel() 
						+ " // Domain : " + t.get1().getDomain().toString() 
						+ ")");
			}
		}
	}
	
//	public Clause explain() {
//		
//	}
}
