package cgol;

public class SimulationThread extends Thread {
	private static int msPerGen = 100; //ennyi ms telik el az uj generaciok kirajzolasa kozott
	static final int max = 300;
	static final int min = 20;
	static final int increment = 20;
	
	private GridSystem grid;
	private boolean end;
	
	SimulationThread(GridSystem grid) {
		this.grid = grid;
	}
	
	public static int getMsPerGen() {
		return msPerGen;
	}
	
	/* novelo es csokkento gombok megnyomasakor lefuto metodusok */
	public static void increaseMsPerGen() {
		if (msPerGen < max)
			SimulationThread.msPerGen += increment;
	}
	
	public static void decreaseMsPerGen() {
		if (msPerGen > min)
			SimulationThread.msPerGen -= increment;
	}
	
	/* thread biztonsagos megallitasa kivulrol */
	public void end() {
		this.end = true;
	}
	
	public boolean isEnded() {
		return end;
	}
	
	public void run() {
		this.end = false;
		try {
			while (!end) {
				grid.nextGeneration();
				sleep(msPerGen);
			}
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
}

