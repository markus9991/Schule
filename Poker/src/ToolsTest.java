import static org.junit.Assert.*;

import org.junit.Test;


public class ToolsTest {

	@Test
	public void testIsDoubleTripleOrPoker() {
		Card[] handpair={ new Card(0,3),new Card(1,3),new Card(1,6),new Card(3,1),new Card(2,5) };
		Card[] handtriple={ new Card(0,1),new Card(1,1),new Card(2,1),new Card(1,4),new Card(2,5) };
		Card[] handpoker={ new Card(0,9),new Card(1,9),new Card(2,9),new Card(3,9),new Card(2,5) };
		
		assertTrue(Tools.isDoubleTripleOrPoker(handpair, 2));
		assertTrue(Tools.isDoubleTripleOrPoker(handtriple, 3));
		assertTrue(Tools.isDoubleTripleOrPoker(handpoker, 4));
		
	}

}
