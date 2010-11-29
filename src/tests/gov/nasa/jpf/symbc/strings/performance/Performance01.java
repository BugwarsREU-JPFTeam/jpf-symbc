package gov.nasa.jpf.symbc.strings.performance;

public class Performance01 {
	private static final int MAX_LENGTH = 10;
	
	public static void main (String [] args) {
		test2 ("dcba", "a", "b");
	}
	
	public static void test (String a, String b) {
		int i = 0;
		if (a.endsWith("a")) {
			System.out.println("Step 1");
			i++;
		}
		if (b.endsWith("b")) {
			System.out.println("Step 2");
			i++;
		}
		if (a.startsWith(b)) {
			System.out.println("Step 3");
			i++;
		}
		if (!a.equals(b)) {
			System.out.println("Step 4");
			i++;
		}
		if (a.trim().contains(b)) {
			System.out.println("Step 5");
			i++;
		}
		/*if (i == 5) {
			throw new RuntimeException ("look at this");
		}*/
	}
	
	public static void test2 (String a, String b, String c) {
		int i = 0;
		if (a.length () < MAX_LENGTH && b.length() < MAX_LENGTH && c.length() < MAX_LENGTH) {
			if (a.endsWith("a")) {
				System.out.println("Step 1");
				i++;
			}
			if (b.endsWith("b")) {
				System.out.println("Step 2");
				i++;
			}
			if (c.endsWith("c")) {
				System.out.println("Step 3");
				i++;
			}
			if (a.startsWith(b)) {
				System.out.println("Step 4");
				i++;
			}
			if (b.startsWith(c)) {
				System.out.println("Step 5");
				i++;
			}
			if (!a.equals(b)) {
				System.out.println("Step 6");
				i++;
			}
			if (!b.equals(c)) {
				System.out.println("Step 7");
				i++;
			}
			if (a.trim().contains(b)) {
				System.out.println("Step 8");
				i++;
			}
			if (b.trim().contains(c)) {
				System.out.println("Step 9");
				i++;
			}
			if (i == 9) {
				throw new RuntimeException ("look at this");
			}
		}
	}
	
	public static void test3 (String a, String b, String c, String d) {
		int i = 0;
		if (a.length () < MAX_LENGTH && b.length() < MAX_LENGTH && c.length() < MAX_LENGTH && d.length() < MAX_LENGTH) {
			if (a.endsWith("a")) {
				System.out.println("Step 1");
				i++;
			}	
			if (b.endsWith("b")) {
				System.out.println("Step 2");
				i++;
			}
			if (c.endsWith("c")) {
				System.out.println("Step 3");
				i++;
			}
			if (d.endsWith("d")) {
				System.out.println("Step 4");
				i++;
			}
			if (a.startsWith(b)) {
				System.out.println("Step 5");
				i++;
			}
			if (b.startsWith(c)) {
				System.out.println("Step 6");
				i++;
			}
			if (c.startsWith(d)) {
				System.out.println("Step 7");
				i++;
			}
			if (!a.equals(b)) {
				System.out.println("Step 8");
				i++;
			}
			if (!b.equals(c)) {
				System.out.println("Step 9");
				i++;
			}
			if (!c.equals(d)) {
				System.out.println("Step 10");
				i++;
			}
			if (a.trim().contains(b)) {
				System.out.println("Step 11");
				i++;
			}
			if (b.trim().contains(c)) {
				System.out.println("Step 12");
				i++;
			}
			if (c.trim().contains(d)) {
				System.out.println("Step 13");
				i++;
			}
			/*if (i == 13) {
				throw new RuntimeException ("look at this");
			}*/
		}
	}
}