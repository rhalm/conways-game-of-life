package cgol;

import org.junit.*;

public class MainFrameTest {
	MainFrame frame;
	
	@Before
	public void setup() {
		frame = new MainFrame("cgol");
	}
	
	@Test
	public void startSimulationWithNewThread() {
		frame.startSimulation();
		SimulationThread thread = frame.getGameThread();
		Assert.assertEquals(true, thread.isAlive());
		Assert.assertEquals(false, thread.isEnded());
	}
	
	@Test
	public void startSimulationAfterStopping() {
		frame.startSimulation();
		frame.stopSimulation();
		frame.startSimulation(); //uj threadet indit el
		SimulationThread thread = frame.getGameThread();
		Assert.assertEquals(true, thread.isAlive());
		Assert.assertEquals(false, thread.isEnded());
	}
	
	
	@Test
	public void stopSimulationWithoutStarting() {
		frame.stopSimulation();
		SimulationThread thread = frame.getGameThread();
		Assert.assertEquals(false, thread.isEnded()); //mivel meg nem volt elinditva, nem kell leallitani
	}
	
	@Test
	public void stopSimulationWhenRunning() {
		frame.startSimulation();
		frame.stopSimulation();
		SimulationThread thread = frame.getGameThread();
		Assert.assertEquals(true, thread.isEnded()); //mar elindult, az end-et at kell allitani, hogy megalljon
	}

}
