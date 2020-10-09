package cgol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

public class SpeedActionListener implements ActionListener {
	private JLabel msPerGen;
	
	SpeedActionListener(JLabel msPerGen) {
		this.msPerGen = msPerGen;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand().equals("up")) 
			SimulationThread.increaseMsPerGen();
		else if(ae.getActionCommand().equals("down"))
			SimulationThread.decreaseMsPerGen();
		
		msPerGen.setText(String.valueOf(SimulationThread.getMsPerGen()));
	}
	
}
