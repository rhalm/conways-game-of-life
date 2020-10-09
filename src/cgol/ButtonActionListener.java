package cgol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ButtonActionListener implements ActionListener {
	private MainFrame frame;
	private GridSystem grid;
	
	ButtonActionListener(MainFrame frame) {
		this.frame = frame;
		this.grid = frame.getGrid();
	}
	
	public void actionPerformed(ActionEvent ae) {
		switch(ae.getActionCommand()) {
			case "start": 
				frame.startSimulation(); 
				break;
			case "pause":
				frame.stopSimulation(); 
				break;
			case "clear":
				frame.stopSimulation();
				grid.clear();
				break;
			case "random":
				grid.randomize();
				break;
			default: 
				break;
		}
	}
}