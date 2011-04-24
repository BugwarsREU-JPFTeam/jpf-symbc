package gov.nasa.jpf.symbc.strings;

import javax.print.attribute.IntegerSyntax;

import junit.framework.Assert;
import gov.nasa.jpf.Config;
import gov.nasa.jpf.symbc.SymbolicInstructionFactory;
import gov.nasa.jpf.symbc.numeric.Comparator;
import gov.nasa.jpf.symbc.numeric.IntegerConstant;
import gov.nasa.jpf.symbc.numeric.PathCondition;
import gov.nasa.jpf.symbc.numeric.SymbolicInteger;
import gov.nasa.jpf.symbc.string.StringComparator;
import gov.nasa.jpf.symbc.string.StringConstant;
import gov.nasa.jpf.symbc.string.StringExpression;
import gov.nasa.jpf.symbc.string.StringPathCondition;
import gov.nasa.jpf.symbc.string.StringSymbolic;
import gov.nasa.jpf.symbc.string.SymbolicStringConstraintsGeneral;

import org.junit.Test;


public class TestSymString {

	@Test
	public void Test1 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		for (String solver: solvers) {
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			StringPathCondition stringCurrentPC = new StringPathCondition(new PathCondition());
			StringSymbolic var1 = new StringSymbolic("var1");
			StringExpression pre = var1;
			StringExpression var = pre._subString(3, 2);
			StringExpression constant1 = new StringConstant("test");
			StringExpression constant2 = new StringConstant("s");
			stringCurrentPC._addDet(StringComparator.NOTEQUALS, constant1, pre);
			stringCurrentPC._addDet(StringComparator.EQUALS, constant2, var);		
			boolean result = stringCurrentPC.simplify();
			Assert.assertTrue(result);
			Assert.assertTrue(!var1.solution().equals("test"));
			Assert.assertTrue(var1.solution().substring(2,3).equals("s"));
		}
	}
	
	@Test
	public void Test2_1 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		for (String solver: solvers) {
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			StringPathCondition stringCurrentPC = new StringPathCondition(new PathCondition());
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			stringCurrentPC._addDet(StringComparator.STARTSWITH, new StringConstant("a"), var1);
			stringCurrentPC._addDet(StringComparator.STARTSWITH, new StringConstant("b"), var2);
			boolean result = stringCurrentPC.simplify();
			Assert.assertTrue(result);
			Assert.assertTrue(var1.solution().startsWith("a"));
			Assert.assertTrue(var2.solution().startsWith("b"));
		}
	}
	
	@Test
	public void Test2_2 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		for (String solver: solvers) {
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			StringPathCondition stringCurrentPC = new StringPathCondition(new PathCondition());
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			stringCurrentPC._addDet(StringComparator.STARTSWITH, new StringConstant("a"), var1);
			stringCurrentPC._addDet(StringComparator.NOTSTARTSWITH, new StringConstant("b"), var2);
			boolean result = stringCurrentPC.simplify();
			Assert.assertTrue(result);
			Assert.assertTrue(var1.solution().startsWith("a"));
			Assert.assertTrue(!var2.solution().startsWith("b"));
		}
	}

	@Test
	public void Test2_3 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		for (String solver: solvers) {
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			StringPathCondition stringCurrentPC = new StringPathCondition(new PathCondition());
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			stringCurrentPC._addDet(StringComparator.NOTSTARTSWITH, new StringConstant("a"), var1);
			stringCurrentPC._addDet(StringComparator.STARTSWITH, new StringConstant("b"), var2);
			boolean result = stringCurrentPC.simplify();
			Assert.assertTrue(result);
			Assert.assertTrue(!var1.solution().startsWith("a"));
			Assert.assertTrue(var2.solution().startsWith("b"));
		}
	}
	
	@Test
	public void Test2_4 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		for (String solver: solvers) {
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			StringPathCondition stringCurrentPC = new StringPathCondition(new PathCondition());
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			stringCurrentPC._addDet(StringComparator.NOTSTARTSWITH, new StringConstant("a"), var1);
			stringCurrentPC._addDet(StringComparator.NOTSTARTSWITH, new StringConstant("b"), var2);
			boolean result = stringCurrentPC.simplify();
			Assert.assertTrue(result);
			Assert.assertTrue(!var1.solution().startsWith("a"));
			Assert.assertTrue(!var2.solution().startsWith("b"));
		}
	}
	
	@Test
	public void Test3_1 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		for (String solver: solvers) {
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			StringPathCondition stringCurrentPC = new StringPathCondition(new PathCondition());
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			stringCurrentPC._addDet(StringComparator.ENDSWITH, new StringConstant("a"), var1);
			stringCurrentPC._addDet(StringComparator.ENDSWITH, new StringConstant("b"), var2);
			boolean result = stringCurrentPC.simplify();
			Assert.assertTrue(result);
			Assert.assertTrue(var1.solution().startsWith("a"));
			Assert.assertTrue(var2.solution().startsWith("b"));
		}
	}
	
	@Test
	public void Test3_2 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		for (String solver: solvers) {
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			StringPathCondition stringCurrentPC = new StringPathCondition(new PathCondition());
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			stringCurrentPC._addDet(StringComparator.ENDSWITH, new StringConstant("a"), var1);
			stringCurrentPC._addDet(StringComparator.NOTENDSWITH, new StringConstant("b"), var2);
			boolean result = stringCurrentPC.simplify();
			Assert.assertTrue(result);
			Assert.assertTrue(var1.solution().startsWith("a"));
			Assert.assertTrue(!var2.solution().startsWith("b"));
		}
	}

	@Test
	public void Test3_3 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		for (String solver: solvers) {
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			StringPathCondition stringCurrentPC = new StringPathCondition(new PathCondition());
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			stringCurrentPC._addDet(StringComparator.NOTENDSWITH, new StringConstant("a"), var1);
			stringCurrentPC._addDet(StringComparator.ENDSWITH, new StringConstant("b"), var2);
			boolean result = stringCurrentPC.simplify();
			Assert.assertTrue(result);
			Assert.assertTrue(!var1.solution().startsWith("a"));
			Assert.assertTrue(var2.solution().startsWith("b"));
		}
	}
	
	@Test
	public void Test3_4 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		for (String solver: solvers) {
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			StringPathCondition stringCurrentPC = new StringPathCondition(new PathCondition());
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			stringCurrentPC._addDet(StringComparator.NOTENDSWITH, new StringConstant("a"), var1);
			stringCurrentPC._addDet(StringComparator.NOTENDSWITH, new StringConstant("b"), var2);
			boolean result = stringCurrentPC.simplify();
			Assert.assertTrue(result);
			Assert.assertTrue(!var1.solution().startsWith("a"));
			Assert.assertTrue(!var2.solution().startsWith("b"));
		}
	}
	
	@Test
	public void Test4_1 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		for (String solver: solvers) {
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			StringPathCondition stringCurrentPC = new StringPathCondition(new PathCondition());
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			stringCurrentPC._addDet(StringComparator.EQUALS, new StringConstant("a"), var1);
			stringCurrentPC._addDet(StringComparator.EQUALS, new StringConstant("b"), var2);
			boolean result = stringCurrentPC.simplify();
			Assert.assertTrue(result);
			Assert.assertTrue(var1.solution().equals("a"));
			Assert.assertTrue(var2.solution().equals("b"));
		}
	}
	
	@Test
	public void Test4_2 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		for (String solver: solvers) {
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			StringPathCondition stringCurrentPC = new StringPathCondition(new PathCondition());
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			stringCurrentPC._addDet(StringComparator.EQUALS, new StringConstant("a"), var1);
			stringCurrentPC._addDet(StringComparator.NOTEQUALS, new StringConstant("b"), var2);
			boolean result = stringCurrentPC.simplify();
			System.out.println(SymbolicStringConstraintsGeneral.getSolution());
			Assert.assertTrue(result);
			Assert.assertTrue(var1.solution().equals("a"));
			Assert.assertTrue(var2.solution() == null || !var2.solution().equals("b"));
		}
	}

	@Test
	public void Test4_3 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		for (String solver: solvers) {
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			StringPathCondition stringCurrentPC = new StringPathCondition(new PathCondition());
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			stringCurrentPC._addDet(StringComparator.NOTEQUALS, new StringConstant("a"), var1);
			stringCurrentPC._addDet(StringComparator.EQUALS, new StringConstant("b"), var2);
			boolean result = stringCurrentPC.simplify();
			Assert.assertTrue(result);
			Assert.assertTrue(!var1.solution().equals("a"));
			Assert.assertTrue(var2.solution().equals("b"));
		}
	}
	
	@Test
	public void Test4_4 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		for (String solver: solvers) {
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			StringPathCondition stringCurrentPC = new StringPathCondition(new PathCondition());
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			stringCurrentPC._addDet(StringComparator.NOTEQUALS, new StringConstant("a"), var1);
			stringCurrentPC._addDet(StringComparator.NOTEQUALS, new StringConstant("b"), var2);
			boolean result = stringCurrentPC.simplify();
			Assert.assertTrue(result);
			Assert.assertTrue(!var1.solution().equals("a"));
			Assert.assertTrue(!var2.solution().equals("b"));
		}
	}
	
	@Test
	public void Test5_1 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		for (String solver: solvers) {
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			StringPathCondition stringCurrentPC = new StringPathCondition(new PathCondition());
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			stringCurrentPC._addDet(StringComparator.CONTAINS, new StringConstant("a"), var1);
			stringCurrentPC._addDet(StringComparator.CONTAINS, new StringConstant("b"), var2);
			boolean result = stringCurrentPC.simplify();
			Assert.assertTrue(result);
			Assert.assertTrue(var1.solution().contains("a"));
			Assert.assertTrue(var2.solution().contains("b"));
		}
	}
	
	@Test
	public void Test5_2 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		for (String solver: solvers) {
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			StringPathCondition stringCurrentPC = new StringPathCondition(new PathCondition());
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			stringCurrentPC._addDet(StringComparator.CONTAINS, new StringConstant("a"), var1);
			stringCurrentPC._addDet(StringComparator.NOTCONTAINS, new StringConstant("b"), var2);
			boolean result = stringCurrentPC.simplify();
			System.out.println(SymbolicStringConstraintsGeneral.getSolution());
			Assert.assertTrue(result);
			Assert.assertTrue(var1.solution().contains("a"));
			Assert.assertTrue(!var2.solution().contains("b"));
		}
	}

	@Test
	public void Test5_3 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		for (String solver: solvers) {
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			StringPathCondition stringCurrentPC = new StringPathCondition(new PathCondition());
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			stringCurrentPC._addDet(StringComparator.NOTCONTAINS, new StringConstant("a"), var1);
			stringCurrentPC._addDet(StringComparator.CONTAINS, new StringConstant("b"), var2);
			boolean result = stringCurrentPC.simplify();
			Assert.assertTrue(result);
			Assert.assertTrue(!var1.solution().contains("a"));
			Assert.assertTrue(var2.solution().contains("b"));
		}
	}
	
	@Test
	public void Test5_4 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		for (String solver: solvers) {
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			StringPathCondition stringCurrentPC = new StringPathCondition(new PathCondition());
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			stringCurrentPC._addDet(StringComparator.NOTCONTAINS, new StringConstant("a"), var1);
			stringCurrentPC._addDet(StringComparator.NOTCONTAINS, new StringConstant("b"), var2);
			boolean result = stringCurrentPC.simplify();
			Assert.assertTrue(result);
			Assert.assertTrue(!var1.solution().contains("a"));
			Assert.assertTrue(!var2.solution().contains("b"));
		}
	}
	
	@Test
	public void Test6_1 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		for (String solver: solvers) {
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			stringCurrentPC._addDet(StringComparator.CONTAINS, new StringConstant("a"), var1);
			stringCurrentPC._addDet(StringComparator.CONTAINS, new StringConstant("b"), var2);
			StringExpression var3 = var1._concat(var2);
			pc._addDet(Comparator.LE, var3._length(), 10);
			boolean result = stringCurrentPC.simplify();
			Assert.assertTrue(result);
			Assert.assertTrue(var1.solution().contains("a"));
			Assert.assertTrue(var2.solution().contains("b"));
			Assert.assertTrue(var1.solution().concat(var2.solution()).equals(var3.solution()));
			Assert.assertTrue(var3.solution().length() < 10);
		}
	}
	
	@Test
	public void Test6_2 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		for (String solver: solvers) {
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			stringCurrentPC._addDet(StringComparator.NOTCONTAINS, new StringConstant("a"), var1);
			stringCurrentPC._addDet(StringComparator.CONTAINS, new StringConstant("b"), var2);
			StringExpression var3 = var1._concat(var2);
			pc._addDet(Comparator.LE, var3._length(), 10);
			boolean result = stringCurrentPC.simplify();
			Assert.assertTrue(result);
			Assert.assertTrue(!var1.solution().contains("a"));
			Assert.assertTrue(var2.solution().contains("b"));
			Assert.assertTrue(var1.solution().concat(var2.solution()).equals(var3.solution()));
			Assert.assertTrue(var3.solution().length() < 10);
		}
	}
	
	@Test
	public void Test6_3 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		for (String solver: solvers) {
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			stringCurrentPC._addDet(StringComparator.CONTAINS, new StringConstant("a"), var1);
			stringCurrentPC._addDet(StringComparator.NOTCONTAINS, new StringConstant("b"), var2);
			StringExpression var3 = var1._concat(var2);
			pc._addDet(Comparator.LE, var3._length(), 10);
			boolean result = stringCurrentPC.simplify();
			Assert.assertTrue(result);
			Assert.assertTrue(var1.solution().contains("a"));
			Assert.assertTrue(!var2.solution().contains("b"));
			Assert.assertTrue(var1.solution().concat(var2.solution()).equals(var3.solution()));
			Assert.assertTrue(var3.solution().length() < 10);
		}
	}
	
	@Test
	public void Test6_4 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		for (String solver: solvers) {
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			stringCurrentPC._addDet(StringComparator.NOTCONTAINS, new StringConstant("a"), var1);
			stringCurrentPC._addDet(StringComparator.NOTCONTAINS, new StringConstant("b"), var2);
			StringExpression var3 = var1._concat(var2);
			pc._addDet(Comparator.LE, var3._length(), 10);
			boolean result = stringCurrentPC.simplify();
			Assert.assertTrue(result);
			Assert.assertTrue(!var1.solution().contains("a"));
			Assert.assertTrue(!var2.solution().contains("b"));
			Assert.assertTrue(var1.solution().concat(var2.solution()).equals(var3.solution()));
			Assert.assertTrue(var3.solution().length() < 10);
		}
	}

	@Test
	public void Test6_5 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		for (String solver: solvers) {
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			stringCurrentPC._addDet(StringComparator.CONTAINS, new StringConstant("a"), var1);
			stringCurrentPC._addDet(StringComparator.CONTAINS, new StringConstant("b"), var2);
			StringExpression var3 = var1._concat(var2);
			pc._addDet(Comparator.GT, var3._length(), 10);
			boolean result = stringCurrentPC.simplify();
			Assert.assertTrue(result);
			Assert.assertTrue(var1.solution().contains("a"));
			Assert.assertTrue(var2.solution().contains("b"));
			Assert.assertTrue(var1.solution().concat(var2.solution()).equals(var3.solution()));
			Assert.assertTrue(var3.solution().length() > 10);
		}
	}
	
	@Test
	public void Test6_6 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		for (String solver: solvers) {
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			stringCurrentPC._addDet(StringComparator.NOTCONTAINS, new StringConstant("a"), var1);
			stringCurrentPC._addDet(StringComparator.CONTAINS, new StringConstant("b"), var2);
			StringExpression var3 = var1._concat(var2);
			pc._addDet(Comparator.GT, var3._length(), 10);
			boolean result = stringCurrentPC.simplify();
			Assert.assertTrue(result);
			Assert.assertTrue(!var1.solution().contains("a"));
			Assert.assertTrue(var2.solution().contains("b"));
			Assert.assertTrue(var1.solution().concat(var2.solution()).equals(var3.solution()));
			Assert.assertTrue(var3.solution().length() > 10);
		}
	}
	
	@Test
	public void Test6_7 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		for (String solver: solvers) {
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			stringCurrentPC._addDet(StringComparator.CONTAINS, new StringConstant("a"), var1);
			stringCurrentPC._addDet(StringComparator.NOTCONTAINS, new StringConstant("b"), var2);
			StringExpression var3 = var1._concat(var2);
			pc._addDet(Comparator.GT, var3._length(), 10);
			boolean result = stringCurrentPC.simplify();
			Assert.assertTrue(result);
			Assert.assertTrue(var1.solution().contains("a"));
			Assert.assertTrue(!var2.solution().contains("b"));
			Assert.assertTrue(var1.solution().concat(var2.solution()).equals(var3.solution()));
			Assert.assertTrue(var3.solution().length() > 10);
		}
	}
	
	@Test
	public void Test6_8 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		for (String solver: solvers) {
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			stringCurrentPC._addDet(StringComparator.NOTCONTAINS, new StringConstant("a"), var1);
			stringCurrentPC._addDet(StringComparator.NOTCONTAINS, new StringConstant("b"), var2);
			StringExpression var3 = var1._concat(var2);
			pc._addDet(Comparator.GT, var3._length(), 10);
			boolean result = stringCurrentPC.simplify();
			Assert.assertTrue(result);
			Assert.assertTrue(!var1.solution().contains("a"));
			Assert.assertTrue(!var2.solution().contains("b"));
			Assert.assertTrue(var1.solution().concat(var2.solution()).equals(var3.solution()));
			Assert.assertTrue(var3.solution().length() > 10);
		}
	}
	
	@Test
	public void Test7_1 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		for (String solver: solvers) {
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			StringExpression var2 = var1._trim();
			stringCurrentPC._addDet(StringComparator.EQUALS, new StringConstant("cc"), var2);
			boolean result = stringCurrentPC.simplify();
			Assert.assertTrue(result);
			Assert.assertTrue(var2.solution().equals("cc"));
			Assert.assertTrue(var1.solution().trim().equals("cc"));
		}
	}
	
	@Test
	public void Test7_2 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		for (String solver: solvers) {
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			StringExpression var2 = var1._trim();
			stringCurrentPC._addDet(StringComparator.NOTEQUALS, new StringConstant("cc"), var2);
			boolean result = stringCurrentPC.simplify();
			Assert.assertTrue(result);
			Assert.assertTrue(!var2.solution().equals("cc"));
			Assert.assertTrue(!var1.solution().trim().equals("cc"));
		}
	}
	
	@Test
	//TODO: Could do with a speedup
	public void Test8_1 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		//String[] solvers = new String[]{"automata"};
		for (String solver: solvers) {
			System.out.println("Solver: " + solver);
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			stringCurrentPC._addDet(StringComparator.STARTSWITH, new StringConstant("aa"), var1);
			pc._addDet(Comparator.LE, var1._length(), 10);
			pc._addDet(Comparator.GT, var1._indexOf(new StringConstant("a")), 0);
			boolean result = stringCurrentPC.simplify();
			//System.out.printf("var1: '%s'\n", var1.solution());
			Assert.assertTrue("Solver " + solver + " failed",  !result);
			/*Assert.assertTrue(var1.solution().startsWith("aa"));
			Assert.assertTrue(var1.solution().indexOf("a") > 0);*/
			
		}
	}
	
	@Test
	public void Test8_2 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		//String[] solvers = new String[]{"automata", "z3_inc"};
		for (String solver: solvers) {
			System.out.println("Solver: " + solver);
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			stringCurrentPC._addDet(StringComparator.STARTSWITH, new StringConstant("bb"), var1);
			pc._addDet(Comparator.GT, var1._indexOf(new StringConstant("a")), 0);
			boolean result = stringCurrentPC.simplify();
			//System.out.printf("var1: '%s'\n", var1.solution());
			Assert.assertTrue(result);
			Assert.assertTrue(var1.solution().startsWith("bb"));
			Assert.assertTrue(var1.solution().indexOf("a") > 0);
			
		}
	}
	
	@Test
	//TODO: Could do with a speedup
	public void Test9_1 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		//String[] solvers = new String[]{"automata"};
		for (String solver: solvers) {
			System.out.println("Solver: " + solver);
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			stringCurrentPC._addDet(StringComparator.STARTSWITH, new StringConstant("aa"), var1);
			pc._addDet(Comparator.LE, var1._length(), 10);
			pc._addDet(Comparator.GT, var1._indexOf(new IntegerConstant((int) 'a')), 0);
			boolean result = stringCurrentPC.simplify();
			//System.out.printf("var1: '%s'\n", var1.solution());
			Assert.assertTrue("Solver " + solver + " failed",  !result);
			/*Assert.assertTrue(var1.solution().startsWith("aa"));
			Assert.assertTrue(var1.solution().indexOf("a") > 0);*/
			
		}
	}
	
	@Test
	public void Test9_2 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		//String[] solvers = new String[]{"automata", "z3_inc"};
		for (String solver: solvers) {
			System.out.println("Solver: " + solver);
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			stringCurrentPC._addDet(StringComparator.STARTSWITH, new StringConstant("bb"), var1);
			pc._addDet(Comparator.GT, var1._indexOf(new IntegerConstant((int) 'a')), 0);
			boolean result = stringCurrentPC.simplify();
			//System.out.printf("var1: '%s'\n", var1.solution());
			Assert.assertTrue(result);
			Assert.assertTrue(var1.solution().startsWith("bb"));
			Assert.assertTrue(var1.solution().indexOf("a") > 0);
			
		}
	}
	
	@Test
	//TODO: Could do with a speedup
	public void Test10_1 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		//String[] solvers = new String[]{"automata"};
		for (String solver: solvers) {
			System.out.println("Solver: " + solver);
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			stringCurrentPC._addDet(StringComparator.STARTSWITH, new StringConstant("aa"), var1);
			SymbolicInteger si = new SymbolicInteger("int1");
			pc._addDet(Comparator.LE, var1._length(), 10);
			pc._addDet(Comparator.EQ, var1._indexOf(new StringConstant("a"), new IntegerConstant(5)), si);
			boolean result = stringCurrentPC.simplify();
			//System.out.printf("var1: '%s'\n", var1.solution());
			Assert.assertTrue("Solver " + solver + " failed",  result);
			Assert.assertTrue(var1.solution().startsWith("aa"));
			System.out.printf("var1.solution() '%s'\n", var1.solution());
			System.out.printf("si.solution() '%s'\n", si.solution());
			Assert.assertTrue(var1.solution().indexOf("a", 5) == si.solution());
			
		}
	}
	
	@Test
	public void Test10_2 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		//String[] solvers = new String[]{"automata", "z3_inc"};
		for (String solver: solvers) {
			System.out.println("Solver: " + solver);
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			stringCurrentPC._addDet(StringComparator.STARTSWITH, new StringConstant("bb"), var1);
			SymbolicInteger si = new SymbolicInteger("int1");
			pc._addDet(Comparator.EQ, var1._indexOf(new StringConstant("a"), new IntegerConstant(5)), si);
			boolean result = stringCurrentPC.simplify();
			//System.out.printf("var1: '%s'\n", var1.solution());
			Assert.assertTrue(result);
			Assert.assertTrue(var1.solution().startsWith("bb"));
			Assert.assertTrue(var1.solution().indexOf("a", 5) == si.solution());
			
		}
	}
	
	@Test
	public void Test11_1 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		//String[] solvers = new String[]{"automata", "z3_inc"};
		for (String solver: solvers) {
			System.out.println("Solver: " + solver);
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			stringCurrentPC._addDet(StringComparator.STARTSWITH, new StringConstant("aa"), var1);
			stringCurrentPC._addDet(StringComparator.ENDSWITH, new StringConstant("bb"), var2);
			pc._addDet(Comparator.GT, var1._indexOf(var2), 0);
			boolean result = stringCurrentPC.simplify();
			//System.out.printf("var1: '%s'\n", var1.solution());
			Assert.assertTrue(result);
			Assert.assertTrue(var1.solution().startsWith("aa"));
			Assert.assertTrue(var2.solution().endsWith("bb"));
			Assert.assertTrue(var1.solution().indexOf(var2.solution()) > 0);
			
		}
	}
	
	@Test
	public void Test12_1 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		//String[] solvers = new String[]{"automata", "z3_inc"};
		for (String solver: solvers) {
			System.out.println("Solver: " + solver);
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			pc._addDet(Comparator.EQ, var1._charAt(new IntegerConstant(1)), new IntegerConstant((int) 'a'));
			boolean result = stringCurrentPC.simplify();
			//System.out.printf("var1: '%s'\n", var1.solution());
			Assert.assertTrue(result);
			Assert.assertTrue(var1.solution().charAt(1) == 'a');
			
		}
	}
	
	@Test
	public void Test12_2 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		//String[] solvers = new String[]{"automata", "z3_inc"};
		for (String solver: solvers) {
			System.out.println("Solver: " + solver);
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			pc._addDet(Comparator.NE, var1._charAt(new IntegerConstant(1)), new IntegerConstant((int) 'a'));
			boolean result = stringCurrentPC.simplify();
			//System.out.printf("var1: '%s'\n", var1.solution());
			Assert.assertTrue(result);
			Assert.assertTrue(var1.solution().charAt(1) != 'a');
		}
	}
	
	@Test
	public void Test13_1 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		//String[] solvers = new String[]{"automata", "z3_inc"};
		for (String solver: solvers) {
			System.out.println("Solver: " + solver);
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			SymbolicInteger si = new SymbolicInteger("si1");
			pc._addDet(Comparator.EQ, var1._charAt(new IntegerConstant(1)), si);
			boolean result = stringCurrentPC.simplify();
			//System.out.printf("var1: '%s'\n", var1.solution());
			Assert.assertTrue(result);
			Assert.assertTrue(var1.solution().charAt(1) == si.solution());
			
		}
	}
	
	@Test
	public void Test14_1 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		//String[] solvers = new String[]{"automata", "z3_inc"};
		for (String solver: solvers) {
			System.out.println("Solver: " + solver);
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			stringCurrentPC._addDet(StringComparator.STARTSWITH, var1, new StringConstant("abc"));
			stringCurrentPC._addDet(StringComparator.STARTSWITH, var2, new StringConstant("b"));
			stringCurrentPC._addDet(StringComparator.EQUALS, var1, var2);
			boolean result = stringCurrentPC.simplify();
			//System.out.printf("var1: '%s'\n", var1.solution());
			Assert.assertTrue(!result);
		}
	}
	
	@Test
	public void Test14_2 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		//String[] solvers = new String[]{"automata", "z3_inc"};
		for (String solver: solvers) {
			System.out.println("Solver: " + solver);
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			stringCurrentPC._addDet(StringComparator.STARTSWITH, var1, new StringConstant("abc"));
			stringCurrentPC._addDet(StringComparator.STARTSWITH, var2, new StringConstant("b"));
			stringCurrentPC._addDet(StringComparator.NOTEQUALS, var1, var2);
			boolean result = stringCurrentPC.simplify();
			//System.out.printf("var1: '%s'\n", var1.solution());
			Assert.assertTrue(result);
			Assert.assertTrue(!var1.solution().equals(var2.solution()));
		}
	}
	
	@Test
	public void Test15_1 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		//String[] solvers = new String[]{"automata", "z3_inc"};
		for (String solver: solvers) {
			System.out.println("Solver: " + solver);
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			stringCurrentPC._addDet(StringComparator.NOTEQUALS, var1, var2);
			boolean result = stringCurrentPC.simplify();
			//System.out.printf("var1: '%s'\n", var1.solution());
			Assert.assertTrue(result);
			Assert.assertTrue(!var1.solution().equals(var2.solution()));
		}
	}
	
	@Test
	public void Test15_2 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		//String[] solvers = new String[]{"automata", "z3_inc"};
		for (String solver: solvers) {
			System.out.println("Solver: " + solver);
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			stringCurrentPC._addDet(StringComparator.EQUALS, var1, var2);
			boolean result = stringCurrentPC.simplify();
			//System.out.printf("var1: '%s'\n", var1.solution());
			Assert.assertTrue(result);
			Assert.assertTrue(var1.solution().equals(var2.solution()));
		}
	}
	
	@Test
	public void Test16 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		//String[] solvers = new String[]{"automata", "z3_inc"};
		for (String solver: solvers) {
			System.out.println("Solver: " + solver);
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			StringExpression var2 = var1._trim();
			stringCurrentPC._addDet(StringComparator.EQUALS, var2, new StringConstant("ab"));
			boolean result = stringCurrentPC.simplify();
			//System.out.printf("var1: '%s'\n", var1.solution());
			Assert.assertTrue(result);
			Assert.assertTrue(var1.solution().trim().equals("ab"));
		}
	}
	
	@Test
	public void Test17 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		//String[] solvers = new String[]{"automata", "z3_inc"};
		for (String solver: solvers) {
			System.out.println("Solver: " + solver);
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			stringCurrentPC._addDet(StringComparator.STARTSWITH, new StringConstant(" "), var1);
			StringExpression var2 = var1._trim();
			stringCurrentPC._addDet(StringComparator.EQUALS, new StringConstant("ab"), var2 );
			//System.out.println(stringCurrentPC);
			boolean result = stringCurrentPC.simplify();
			//System.out.printf("var1: '%s'\n", var1.solution());
			Assert.assertTrue(solver + " failed", result);
			Assert.assertTrue(var1.solution().trim().equals("ab"));
			Assert.assertTrue(var1.solution().startsWith(" "));
		}
	}
	
	@Test
	public void Test18 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		//String[] solvers = new String[]{"automata", "z3_inc"};
		for (String solver: solvers) {
			System.out.println("Solver: " + solver);
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			StringExpression var1ss = var1._subString(1);
			stringCurrentPC._addDet(StringComparator.EQUALS, var1ss, var2 );
			//System.out.println(stringCurrentPC);
			boolean result = stringCurrentPC.simplify();
			//System.out.printf("var1: '%s'\n", var1.solution());
			Assert.assertTrue(solver + " failed", result);
			Assert.assertTrue(var1.solution().substring(1).equals(var2.solution()));
		}
	}
	
	@Test
	public void Test19_1 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		//String[] solvers = new String[]{"automata", "z3_inc"};
		for (String solver: solvers) {
			System.out.println("Solver: " + solver);
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			stringCurrentPC._addDet(StringComparator.ENDSWITH, new StringConstant("a"), var2 );
			stringCurrentPC._addDet(StringComparator.STARTSWITH, var2, var1 );
			System.out.println(stringCurrentPC);
			boolean result = stringCurrentPC.simplify();
			//System.out.printf("var1: '%s'\n", var1.solution());
			Assert.assertTrue(solver + " failed", result);
			Assert.assertTrue(var2.solution().endsWith("a"));
			Assert.assertTrue(var1.solution().startsWith(var2.solution()));
		}
	}
	
	@Test
	public void Test19_2 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		//String[] solvers = new String[]{"automata", "z3_inc"};
		for (String solver: solvers) {
			System.out.println("Solver: " + solver);
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			stringCurrentPC._addDet(StringComparator.NOTENDSWITH, new StringConstant("a"), var2 );
			stringCurrentPC._addDet(StringComparator.STARTSWITH, var2, var1 );
			System.out.println(stringCurrentPC);
			boolean result = stringCurrentPC.simplify();
			//System.out.printf("var1: '%s'\n", var1.solution());
			Assert.assertTrue(solver + " failed", result);
			Assert.assertTrue(!var2.solution().endsWith("a"));
			Assert.assertTrue(var1.solution().startsWith(var2.solution()));
		}
	}
	
	@Test
	public void Test19_3 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		//String[] solvers = new String[]{"automata", "z3_inc"};
		for (String solver: solvers) {
			System.out.println("Solver: " + solver);
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			stringCurrentPC._addDet(StringComparator.ENDSWITH, new StringConstant("a"), var2 );
			stringCurrentPC._addDet(StringComparator.NOTSTARTSWITH, var2, var1 );
			System.out.println(stringCurrentPC);
			boolean result = stringCurrentPC.simplify();
			//System.out.printf("var1: '%s'\n", var1.solution());
			Assert.assertTrue(solver + " failed", result);
			Assert.assertTrue(var2.solution().endsWith("a"));
			Assert.assertTrue(!var1.solution().startsWith(var2.solution()));
		}
	}
	
	@Test
	public void Test19_4 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		//String[] solvers = new String[]{"automata", "z3_inc"};
		for (String solver: solvers) {
			System.out.println("Solver: " + solver);
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			stringCurrentPC._addDet(StringComparator.NOTENDSWITH, new StringConstant("a"), var2 );
			stringCurrentPC._addDet(StringComparator.NOTSTARTSWITH, var2, var1 );
			System.out.println(stringCurrentPC);
			boolean result = stringCurrentPC.simplify();
			//System.out.printf("var1: '%s'\n", var1.solution());
			Assert.assertTrue(solver + " failed", result);
			Assert.assertTrue(!var2.solution().endsWith("a"));
			Assert.assertTrue(!var1.solution().startsWith(var2.solution()));
		}
	}
	
	@Test
	public void Test20_1 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		//String[] solvers = new String[]{"automata", "z3_inc"};
		for (String solver: solvers) {
			System.out.println("Solver: " + solver);
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			StringSymbolic var3 = new StringSymbolic("var3");
			stringCurrentPC._addDet(StringComparator.EQUALS, var1, var2 );
			stringCurrentPC._addDet(StringComparator.EQUALS, var2, var3 );
			System.out.println(stringCurrentPC);
			boolean result = stringCurrentPC.simplify();
			//System.out.printf("var1: '%s'\n", var1.solution());
			Assert.assertTrue(solver + " failed", result);
			Assert.assertTrue(var2.solution().equals(var1.solution()));
			Assert.assertTrue(var2.solution().equals(var3.solution()));
		}
	}
	
	@Test
	public void Test20_2 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		//String[] solvers = new String[]{"automata", "z3_inc"};
		for (String solver: solvers) {
			System.out.println("Solver: " + solver);
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			StringSymbolic var3 = new StringSymbolic("var3");
			stringCurrentPC._addDet(StringComparator.NOTEQUALS, var1, var2 );
			stringCurrentPC._addDet(StringComparator.EQUALS, var2, var3 );
			System.out.println(stringCurrentPC);
			boolean result = stringCurrentPC.simplify();
			//System.out.printf("var1: '%s'\n", var1.solution());
			Assert.assertTrue(solver + " failed", result);
			Assert.assertTrue(!var2.solution().equals(var1.solution()));
			Assert.assertTrue(var2.solution().equals(var3.solution()));
		}
	}
	
	@Test
	public void Test20_3 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		//String[] solvers = new String[]{"automata", "z3_inc"};
		for (String solver: solvers) {
			System.out.println("Solver: " + solver);
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			StringSymbolic var3 = new StringSymbolic("var3");
			stringCurrentPC._addDet(StringComparator.EQUALS, var1, var2 );
			stringCurrentPC._addDet(StringComparator.NOTEQUALS, var2, var3 );
			System.out.println(stringCurrentPC);
			boolean result = stringCurrentPC.simplify();
			//System.out.printf("var1: '%s'\n", var1.solution());
			Assert.assertTrue(solver + " failed", result);
			Assert.assertTrue(var2.solution().equals(var1.solution()));
			Assert.assertTrue(!var2.solution().equals(var3.solution()));
		}
	}
	
	@Test
	public void Test20_4 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		//String[] solvers = new String[]{"automata", "z3_inc"};
		for (String solver: solvers) {
			System.out.println("Solver: " + solver);
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			StringSymbolic var3 = new StringSymbolic("var3");
			stringCurrentPC._addDet(StringComparator.NOTEQUALS, var1, var2 );
			stringCurrentPC._addDet(StringComparator.NOTEQUALS, var2, var3 );
			System.out.println(stringCurrentPC);
			boolean result = stringCurrentPC.simplify();
			System.out.printf("var1: '%s'\n", var1.solution());
			System.out.printf("var2: '%s'\n", var2.solution());
			System.out.printf("var3: '%s'\n", var3.solution());
			Assert.assertTrue(solver + " failed", result);
			Assert.assertTrue(!var2.solution().equals(var1.solution()));
			Assert.assertTrue(!var2.solution().equals(var3.solution()));
		}
	}
	
	@Test
	public void Test21_1 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		//String[] solvers = new String[]{"automata", "z3_inc"};
		for (String solver: solvers) {
			System.out.println("Solver: " + solver);
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			StringSymbolic var3 = new StringSymbolic("var3");
			StringSymbolic var4 = new StringSymbolic("var4");
			stringCurrentPC._addDet(StringComparator.EQUALS, var1, var2 );
			stringCurrentPC._addDet(StringComparator.EQUALS, var2, var3 );
			stringCurrentPC._addDet(StringComparator.EQUALS, var3, var4 );
			System.out.println(stringCurrentPC);
			boolean result = stringCurrentPC.simplify();
			//System.out.printf("var1: '%s'\n", var1.solution());
			Assert.assertTrue(solver + " failed", result);
			Assert.assertTrue(var2.solution().equals(var1.solution()));
			Assert.assertTrue(var2.solution().equals(var3.solution()));
			Assert.assertTrue(var3.solution().equals(var4.solution()));
		}
	}
	
	@Test
	public void Test21_2 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		//String[] solvers = new String[]{"automata", "z3_inc"};
		for (String solver: solvers) {
			System.out.println("Solver: " + solver);
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			StringSymbolic var3 = new StringSymbolic("var3");
			StringSymbolic var4 = new StringSymbolic("var4");
			stringCurrentPC._addDet(StringComparator.NOTEQUALS, var1, var2 );
			stringCurrentPC._addDet(StringComparator.EQUALS, var2, var3 );
			stringCurrentPC._addDet(StringComparator.EQUALS, var3, var4 );
			System.out.println(stringCurrentPC);
			boolean result = stringCurrentPC.simplify();
			//System.out.printf("var1: '%s'\n", var1.solution());
			Assert.assertTrue(solver + " failed", result);
			Assert.assertTrue(!var2.solution().equals(var1.solution()));
			Assert.assertTrue(var2.solution().equals(var3.solution()));
			Assert.assertTrue(var3.solution().equals(var4.solution()));
		}
	}
	
	@Test
	public void Test21_3 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		//String[] solvers = new String[]{"automata", "z3_inc"};
		for (String solver: solvers) {
			System.out.println("Solver: " + solver);
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			StringSymbolic var3 = new StringSymbolic("var3");
			StringSymbolic var4 = new StringSymbolic("var4");
			stringCurrentPC._addDet(StringComparator.EQUALS, var1, var2 );
			stringCurrentPC._addDet(StringComparator.NOTEQUALS, var2, var3 );
			stringCurrentPC._addDet(StringComparator.EQUALS, var3, var4 );
			System.out.println(stringCurrentPC);
			boolean result = stringCurrentPC.simplify();
			//System.out.printf("var1: '%s'\n", var1.solution());
			Assert.assertTrue(solver + " failed", result);
			Assert.assertTrue(var2.solution().equals(var1.solution()));
			Assert.assertTrue(!var2.solution().equals(var3.solution()));
			Assert.assertTrue(var3.solution().equals(var4.solution()));
		}
	}
	
	@Test
	public void Test21_4 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		//String[] solvers = new String[]{"automata", "z3_inc"};
		for (String solver: solvers) {
			System.out.println("Solver: " + solver);
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			StringSymbolic var3 = new StringSymbolic("var3");
			StringSymbolic var4 = new StringSymbolic("var4");
			stringCurrentPC._addDet(StringComparator.NOTEQUALS, var1, var2 );
			stringCurrentPC._addDet(StringComparator.NOTEQUALS, var2, var3 );
			stringCurrentPC._addDet(StringComparator.EQUALS, var3, var4 );
			System.out.println(stringCurrentPC);
			boolean result = stringCurrentPC.simplify();
			//System.out.printf("var1: '%s'\n", var1.solution());
			Assert.assertTrue(solver + " failed", result);
			Assert.assertTrue(!var2.solution().equals(var1.solution()));
			Assert.assertTrue(!var2.solution().equals(var3.solution()));
			Assert.assertTrue(var3.solution().equals(var4.solution()));
		}
	}
	
	@Test
	public void Test21_5 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		//String[] solvers = new String[]{"automata", "z3_inc"};
		for (String solver: solvers) {
			System.out.println("Solver: " + solver);
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			StringSymbolic var3 = new StringSymbolic("var3");
			StringSymbolic var4 = new StringSymbolic("var4");
			stringCurrentPC._addDet(StringComparator.EQUALS, var1, var2 );
			stringCurrentPC._addDet(StringComparator.EQUALS, var2, var3 );
			stringCurrentPC._addDet(StringComparator.NOTEQUALS, var3, var4 );
			System.out.println(stringCurrentPC);
			boolean result = stringCurrentPC.simplify();
			//System.out.printf("var1: '%s'\n", var1.solution());
			Assert.assertTrue(solver + " failed", result);
			Assert.assertTrue(var2.solution().equals(var1.solution()));
			Assert.assertTrue(var2.solution().equals(var3.solution()));
			Assert.assertTrue(!var3.solution().equals(var4.solution()));
		}
	}
	
	@Test
	public void Test21_6 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		//String[] solvers = new String[]{"automata", "z3_inc"};
		for (String solver: solvers) {
			System.out.println("Solver: " + solver);
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			StringSymbolic var3 = new StringSymbolic("var3");
			StringSymbolic var4 = new StringSymbolic("var4");
			stringCurrentPC._addDet(StringComparator.NOTEQUALS, var1, var2 );
			stringCurrentPC._addDet(StringComparator.EQUALS, var2, var3 );
			stringCurrentPC._addDet(StringComparator.NOTEQUALS, var3, var4 );
			System.out.println(stringCurrentPC);
			boolean result = stringCurrentPC.simplify();
			//System.out.printf("var1: '%s'\n", var1.solution());
			Assert.assertTrue(solver + " failed", result);
			Assert.assertTrue(!var2.solution().equals(var1.solution()));
			Assert.assertTrue(var2.solution().equals(var3.solution()));
			Assert.assertTrue(!var3.solution().equals(var4.solution()));
		}
	}
	
	@Test
	public void Test21_7 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		//String[] solvers = new String[]{"automata", "z3_inc"};
		for (String solver: solvers) {
			System.out.println("Solver: " + solver);
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			StringSymbolic var3 = new StringSymbolic("var3");
			StringSymbolic var4 = new StringSymbolic("var4");
			stringCurrentPC._addDet(StringComparator.EQUALS, var1, var2 );
			stringCurrentPC._addDet(StringComparator.NOTEQUALS, var2, var3 );
			stringCurrentPC._addDet(StringComparator.NOTEQUALS, var3, var4 );
			System.out.println(stringCurrentPC);
			boolean result = stringCurrentPC.simplify();
			//System.out.printf("var1: '%s'\n", var1.solution());
			Assert.assertTrue(solver + " failed", result);
			Assert.assertTrue(var2.solution().equals(var1.solution()));
			Assert.assertTrue(!var2.solution().equals(var3.solution()));
			Assert.assertTrue(!var3.solution().equals(var4.solution()));
		}
	}
	
	@Test
	public void Test21_8 () {
		String[] solvers = new String[]{"z3", "automata", "z3_inc"};
		//String[] solvers = new String[]{"automata", "z3_inc"};
		for (String solver: solvers) {
			System.out.println("Solver: " + solver);
			String[] options = {"+symbolic.dp=choco",
					"+symbolic.string_dp=" + solver,
					"+symbolic.string_dp_timeout_ms=0"};
			Config cfg = new Config(options);
			new SymbolicInstructionFactory(cfg);
			PathCondition pc = new PathCondition();
			StringPathCondition stringCurrentPC = new StringPathCondition(pc);
			StringSymbolic var1 = new StringSymbolic("var1");
			StringSymbolic var2 = new StringSymbolic("var2");
			StringSymbolic var3 = new StringSymbolic("var3");
			StringSymbolic var4 = new StringSymbolic("var4");
			stringCurrentPC._addDet(StringComparator.NOTEQUALS, var1, var2 );
			stringCurrentPC._addDet(StringComparator.NOTEQUALS, var2, var3 );
			stringCurrentPC._addDet(StringComparator.NOTEQUALS, var3, var4 );
			System.out.println(stringCurrentPC);
			boolean result = stringCurrentPC.simplify();
			//System.out.printf("var1: '%s'\n", var1.solution());
			Assert.assertTrue(solver + " failed", result);
			Assert.assertTrue(!var2.solution().equals(var1.solution()));
			Assert.assertTrue(!var2.solution().equals(var3.solution()));
			Assert.assertTrue(!var3.solution().equals(var4.solution()));
		}
	}
}
