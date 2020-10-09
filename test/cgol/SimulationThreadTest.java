package cgol;

import org.junit.*;

public class SimulationThreadTest {
	int defaultMs;
	int max;
	int min;
	int increment;
	
	@Before
	public void setup() {
		defaultMs = SimulationThread.getMsPerGen();
		max = SimulationThread.max;
		min = SimulationThread.min;
		increment = SimulationThread.increment;
	}
	
	@Test
	public void increase() {
		SimulationThread.increaseMsPerGen();
		int actual = SimulationThread.getMsPerGen();
		Assert.assertEquals(defaultMs+increment, actual);
	}
	
	@Test
	public void increaseTooMuch() {
		for (int i = 0; i < max/increment; i++)
			SimulationThread.increaseMsPerGen();
		int actual = SimulationThread.getMsPerGen();
		Assert.assertEquals(max, actual);
	}
	
	@Test
	public void decrease() {
		SimulationThread.decreaseMsPerGen();
		int actual = SimulationThread.getMsPerGen();
		Assert.assertEquals(defaultMs-increment, actual);
	}
	
	@Test
	public void decreaseTooMuch() {
		for (int i = 0; i < max/increment; i++)
			SimulationThread.decreaseMsPerGen();
		int actual = SimulationThread.getMsPerGen();
		Assert.assertEquals(min, actual);
	}

}
