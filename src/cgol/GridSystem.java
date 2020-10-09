package cgol;


import java.awt.GridLayout;
import java.io.*;
import java.util.Random;
import javax.swing.JPanel;

public class GridSystem extends JPanel {
	private int rows = 50;
	private int cols = 50;
	private Cell[][] cells;
	
	GridSystem() {
		this.setLayout(new GridLayout(rows, cols));
		
		cells = new Cell[rows][cols];
		for (int r = 0; r < rows; r++)
			for (int c = 0; c < cols; c++) { 
				cells[r][c] = new Cell();
				this.add(cells[r][c]);
			}
		for (int r = 0; r < rows; r++)
			for (int c = 0; c < cols; c++) {
				Cell cell = cells[r][c];
				int[] num = {0, 1, -1};
				//az osszes lehetseges szomszed megkeresese
				for (int i : num)
					if (r+i >= 0 && r+i < rows)
						for (int j : num)
							if (c+j >= 0 && c+j < cols)
								if (!(i == 0 && j == 0)) 
									cell.addNeighbor(cells[r+i][c+j]);
			}
	}
	
	public void nextGeneration() {		
		for (int r = 0; r < rows; r++)
			for (int c = 0; c < cols; c++) 
				cells[r][c].calculateStateInNextGen();
			
		for (int r = 0; r < rows; r++)
			for (int c = 0; c < cols; c++) 
				cells[r][c].updateStateToNextGen();
	}
	
	public void randomize() {
		Random rand = new Random();
		for (int r = 0; r < rows; r++)
			for (int c = 0; c < cols; c++)
				if(rand.nextBoolean()) 
					cells[r][c].changeToDead();
				else 
					cells[r][c].changeToAlive();
	}
	
	public void clear() {
		for (int r = 0; r < rows; r++)
			for (int c = 0; c < cols; c++) 
				cells[r][c].changeToDead();
	}
	
	public void redraw()  {
		for (int r = 0; r < rows; r++)
			for (int c = 0; c < cols; c++) {  
				if (cells[r][c].isAlive()) 
					cells[r][c].changeToAlive();
				else 
					cells[r][c].changeToDead();
			}
	}
	
	
	private void redrawByImport(Cell[][] newCells)  {
		for (int r = 0; r < rows; r++)
			for (int c = 0; c < cols; c++) {  
				if (newCells[r][c].isAlive()) 
					cells[r][c].changeToAlive();
				else 
					cells[r][c].changeToDead();
			}
	}
	
	
	
	public void load(File filename) throws IOException, ClassNotFoundException {
			FileInputStream f = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream (f);
			Cell[][] newCells = (Cell[][]) ois.readObject();
			ois.close();
			redrawByImport(newCells);
	}
	
	public void save(File filename) throws FileNotFoundException, IOException {
		FileOutputStream f = new FileOutputStream(filename);
		ObjectOutputStream oos = new ObjectOutputStream (f);
		oos.writeObject(this.cells);
		oos.close();
	}
}
