package cgol;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.*;

public class Cell extends JPanel implements Serializable {

	private static Color aliveColor = Color.WHITE;
	private static Color deadColor = Color.BLACK;
	
	transient ArrayList<Cell> neighbors = new ArrayList<Cell>();
	
	private transient boolean aliveInNextGen;
	private boolean alive = false;
	
	Cell() {
		setBackground(deadColor);
		setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		addMouseListener(new CellMouseListener(this));
	}
	
	public static void setAliveColor(Color c) {
		if(!c.equals(deadColor))
			aliveColor = c;
	}
	
	public static Color getAliveColor() {
		return aliveColor;
	}
	
	public static Color getDeadColor() {
		return deadColor;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public void addNeighbor(Cell c) {
		neighbors.add(c);
	}

	/* allapotvaltoztatasok es hatter frissitese */
	public void changeToDead() {
		alive = false;
		this.setBackground(deadColor);
	}
	
	public void changeToAlive() {
		alive = true;
		this.setBackground(aliveColor);
	}
	
	/* frissiti az aliveInNextGen erteket a szabalyok alapjan, es visszaadja azt */
	public boolean calculateStateInNextGen() {
		int aliveNeighbors = 0;
		for (Cell c : neighbors) 
			if (c.alive) aliveNeighbors++;
		
		if(alive && (aliveNeighbors < 2 || aliveNeighbors > 3))
			aliveInNextGen = false;
		else if(!alive && aliveNeighbors == 3) 
			aliveInNextGen = true;
		else
			aliveInNextGen = alive;
		
		return aliveInNextGen;
	}
	
	/* az aliveNextGen erteke szerint elo vagy halott cellara valtozik */
	public void updateStateToNextGen() {
		if(aliveInNextGen)
			changeToAlive();
		else
			changeToDead();
	}
	
	
	
	
}
