package cgol;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CellMouseListener implements MouseListener {
	private static boolean mouseIsPressed;
	private Cell cell;
	
	CellMouseListener(Cell cell) {
		this.cell = cell;
	}
	
	@Override
	public void mouseClicked(MouseEvent me) { }

	@Override
	public void mouseEntered(MouseEvent me) {
		if (mouseIsPressed)
			if (cell.isAlive()) 
				cell.changeToDead();
			else 
				cell.changeToAlive();
			
	}

	@Override
	public void mouseExited(MouseEvent me) { }

	@Override
	public void mousePressed(MouseEvent me) {
		if (cell.isAlive()) 
			cell.changeToDead();
		else 
			cell.changeToAlive();
		
		mouseIsPressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		mouseIsPressed = false;
	}
	
}