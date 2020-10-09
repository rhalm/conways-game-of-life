package cgol;

import java.awt.Color;
import org.junit.*;

public class CellTest {
	Cell cell;
	
	@Before
	public void setup() {
		cell = new Cell();
	}
	
	@Test
	public void setAliveColor() {
		Cell.setAliveColor(Color.MAGENTA);
		Assert.assertEquals(Color.MAGENTA, Cell.getAliveColor());
	}

	@Test
	public void changeToAlive() {
		cell.changeToAlive();
		Assert.assertEquals(true, cell.isAlive());
		Assert.assertEquals(Cell.getAliveColor(), cell.getBackground());
	}
	
	@Test
	public void changeToDead() {
		cell.changeToAlive();
		cell.changeToDead();
		Assert.assertEquals(false, cell.isAlive());
		Assert.assertEquals(Cell.getDeadColor(), cell.getBackground());
	}
	
	@Test
	public void addNeighbor() {
		Assert.assertEquals(0, cell.neighbors.size());
		cell.addNeighbor(new Cell());
		Assert.assertEquals(1, cell.neighbors.size());
	}
	
	/* Egy cella meghal, ha kevesebb, mint 2 szomszedja van */
	@Test
	public void nextStateInUnderpopulation() {
		cell.changeToAlive();
		
		boolean aliveNextGen = cell.calculateStateInNextGen(); //0 elo szomszeddal
		Assert.assertEquals(false, aliveNextGen);
		
		Cell aliveCell = new Cell();
		aliveCell.changeToAlive();
		cell.addNeighbor(aliveCell);
		aliveNextGen = cell.calculateStateInNextGen(); //1 elo szomszeddal
		Assert.assertEquals(false, aliveNextGen);
	}
	
	/* Egy cella meghal, ha több, mint 3 szomszedja van */
	@Test
	public void nextStateInOverpopulation() {
		cell.changeToAlive();
		
		for (int i = 0; i < 4; i++) { //4 elo szomszed hozzaadasa
			Cell aliveCell = new Cell();
			aliveCell.changeToAlive();
			cell.addNeighbor(aliveCell);
		}
		boolean aliveNextGen = cell.calculateStateInNextGen();
		Assert.assertEquals(false, aliveNextGen);
	}
	
	/* Egy cella elo lesz, ha pontosan 3 szomszedja van */
	@Test
	public void nextStateBecomesAlive() {
		cell.changeToDead();
		
		for (int i = 0; i < 3; i++) { //3 elo szomszed hozzaadasa
			Cell aliveCell = new Cell();
			aliveCell.changeToAlive();
			cell.addNeighbor(aliveCell);
		}
		boolean aliveNextGen = cell.calculateStateInNextGen();
		Assert.assertEquals(true, aliveNextGen);
	}
	
	/* ha aliveInNextGen igaz erteku, akkor a kovetkezo generacioban elo cella lesz belole */
	@Test
	public void updateStateFromDeadToAlive() {
		cell.changeToDead(); //halott cellarol indul
		
		for (int i = 0; i < 3; i++) { //3 elo szomszed hozzaadasa
			Cell aliveCell = new Cell();
			aliveCell.changeToAlive();
			cell.addNeighbor(aliveCell);
		}
		cell.calculateStateInNextGen(); //aliveInNextGen igaz erteku
		
		cell.updateStateToNextGen(); //elove valtozik
		Assert.assertEquals(true, cell.isAlive());
		Assert.assertEquals(Cell.getAliveColor(), cell.getBackground());
	}
	
	@Test
	public void updateStateFromAliveToDead() {
		cell.changeToAlive();
		cell.calculateStateInNextGen(); //mivel nincs szomszedja, hamis erteku lesz az aliveInNextGen
		
		cell.updateStateToNextGen(); //halottra valtozik
		Assert.assertEquals(false, cell.isAlive());
		Assert.assertEquals(Cell.getDeadColor(), cell.getBackground());
	}
}
