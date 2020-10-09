package cgol;

import java.awt.Color;
import org.junit.*;

public class ColorItemTest {
	ColorItem ci;
	
	@Before
	public void setup() {
		ci = new ColorItem(Color.MAGENTA, "magenta");
	}
	
	@Test
	public void getColor() {
		Color color = ci.getColor();
		Assert.assertEquals(Color.MAGENTA, color);
	}
	
	@Test
	public void itemToString() {
		String label = ci.toString();
		Assert.assertEquals("magenta", label);
	}

}
