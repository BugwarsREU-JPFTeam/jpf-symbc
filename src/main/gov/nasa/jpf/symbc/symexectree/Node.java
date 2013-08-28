/**
 * 
 */
package gov.nasa.jpf.symbc.symexectree;

import java.util.LinkedList;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author Kasper S. Luckow <luckow@cs.aau.dk>
 *
 */
public class Node implements ISymbolicExecutionTreeElement {
	
	private final InstrContext instructionContext;
	private LinkedList<Transition> incomingTransitions;
	private LinkedList<Transition> outgoingTransitions;
	

	public Node(InstrContext instructionContext) {
		this.instructionContext = instructionContext;
		this.incomingTransitions = new LinkedList<>();
		this.outgoingTransitions = new LinkedList<>();
	}
	
	public Node(InstrContext instructionContext, SymbolicExecutionTree tree) {
		this(instructionContext);
		tree.addNode(this);
	}
	
	public void addIncomingTransition(Transition incomingTrans) {
		this.incomingTransitions.add(incomingTrans);
	}
	
	public void addOugoingTransitions(Transition outgoingTrans) {
		this.outgoingTransitions.add(outgoingTrans);
	}
	
	public void removeIncomingTransition(Transition incomingTrans) {
		this.incomingTransitions.remove(incomingTrans);
	}
	
	public void removeOutgoingTransition(Transition outgoingTrans) {
		this.outgoingTransitions.remove(outgoingTrans);
	}

	public InstrContext getInstructionContext() {
		return instructionContext;
	}

	public LinkedList<Transition> getIncomingTransitions() {
		return incomingTransitions;
	}

	public LinkedList<Transition> getOutgoingTransitions() {
		return outgoingTransitions;
	}
	
	@Override
	public void accept(SymbolicExecutionTreeVisitor visitor) {
		visitor.visit(this);
		for(Transition out : this.outgoingTransitions) {
			out.accept(visitor);
		}
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31).append(instructionContext.getInstr().getMnemonic())
										  .append(instructionContext.getInstr().getFileLocation())
										  .append(instructionContext.getFrame().hashCode())
										  .toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		/*if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		return new EqualsBuilder().append(incomingTransitions, other.incomingTransitions)
								  .append(outgoingTransitions, other.outgoingTransitions)
								  .append(instructionContext, other.instructionContext)
								  .isEquals();
								  */
		return false;
	}
}