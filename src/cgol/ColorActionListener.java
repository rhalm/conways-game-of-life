package cgol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

public class ColorActionListener implements ActionListener {
	private GridSystem grid;
	private JComboBox colors;
	
	ColorActionListener(JComboBox colors, GridSystem grid) {
		this.colors = colors;
		this.grid = grid;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		ColorItem color = (ColorItem) colors.getSelectedItem();
		Cell.setAliveColor(color.getColor());
		grid.redraw();
	}

}
