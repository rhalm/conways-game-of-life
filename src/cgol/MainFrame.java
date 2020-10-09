package cgol;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;

public class MainFrame extends JFrame {
	private GridSystem grid = new GridSystem();
	private SimulationThread gameThread = new SimulationThread(grid);
	
	private JButton start = new JButton("Start");
	private JButton pause = new JButton("Pause");

	MainFrame(String title) {
		super(title);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Menusor
		FileHandlingActionListener fhal = new FileHandlingActionListener(this);
		
		JMenuBar bar = new JMenuBar();
		JMenu file = new JMenu("File");
		
		JMenuItem imp = new JMenuItem("Import...");
		imp.setActionCommand("import");
		imp.addActionListener(fhal);
		
		JMenuItem exp = new JMenuItem("Export...");
		exp.setActionCommand("export");
		exp.addActionListener(fhal);
		
		file.add(imp);
		file.add(exp);
		bar.add(file);
		setJMenuBar(bar);
		
		//Felso resz
		JPanel modifiers = new JPanel();
		
		JComboBox colors = new JComboBox();
		colors.addItem(new ColorItem(Color.WHITE, "white"));
		colors.addItem(new ColorItem(Color.BLUE, "blue"));
		colors.addItem(new ColorItem(Color.RED, "red"));
		colors.addItem(new ColorItem(Color.YELLOW, "yellow"));
		colors.addActionListener(new ColorActionListener(colors, grid));
		
		JLabel msPerGen = new JLabel();
		msPerGen.setText(String.valueOf(SimulationThread.getMsPerGen()));
		BasicArrowButton msUp = new BasicArrowButton(BasicArrowButton.NORTH);
		BasicArrowButton msDown = new BasicArrowButton(BasicArrowButton.SOUTH);
		msUp.setActionCommand("up");
		msDown.setActionCommand("down");
		
		SpeedActionListener sal = new SpeedActionListener(msPerGen);
		msUp.addActionListener(sal);
		msDown.addActionListener(sal);
		
		modifiers.add(new JLabel("Speed: "));
		modifiers.add(msPerGen);
		modifiers.add(new JLabel("ms / generation"));
		modifiers.add(msUp);
		modifiers.add(msDown);
		modifiers.add(new JLabel("Cell color: "));
		modifiers.add(colors);
		
		//Also resz: gombok
		JPanel buttons = new JPanel();
		ButtonActionListener bal = new ButtonActionListener(this);
		start.setActionCommand("start");
		start.addActionListener(bal);
		
		pause.setActionCommand("pause");
		pause.addActionListener(bal);
		pause.setEnabled(false);
		
		JButton clear = new JButton("Clear");
		clear.setActionCommand("clear");
		clear.addActionListener(bal);
		
		JButton random = new JButton("Random");
		random.setActionCommand("random");
		random.addActionListener(bal);
		
		buttons.add(start);
		buttons.add(pause);
		buttons.add(clear);
		buttons.add(random);
		
		//Layout
		add(modifiers, BorderLayout.NORTH);
		add(grid, BorderLayout.CENTER);
		add(buttons, BorderLayout.SOUTH);
		
		pack();
	}
	
	public GridSystem getGrid() {
		return grid;
	}
	
	public SimulationThread getGameThread() {
		return gameThread;
	}
	
	public void startSimulation() {
		gameThread = new SimulationThread(grid);
		gameThread.start();
		start.setEnabled(false);
		pause.setEnabled(true);
	}
	
	public void stopSimulation() {
		if (gameThread.isAlive())
			gameThread.end();
		pause.setEnabled(false);
		start.setEnabled(true);
	}
	
	public static void main(String[] args) {
		MainFrame cf = new MainFrame("Conway's Game of Life");
		cf.setVisible(true);
	}
}

