package cgol;

import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

class FileHandlingActionListener implements ActionListener {
	private MainFrame frame;
	private GridSystem grid;
	private JFileChooser chooser;
	
	FileHandlingActionListener(MainFrame frame) {
		this.frame = frame;
		this.grid = frame.getGrid();
		
		chooser = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
		FileNameExtensionFilter filter = new FileNameExtensionFilter("cgol", "cgol");
		chooser.setFileFilter(filter);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("import")) {
			try {
				frame.stopSimulation();
				int returnVal = chooser.showOpenDialog(grid);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					File f = chooser.getSelectedFile();
					if(!hasCGOLExtension(f)) {
						f = new File(f.getAbsolutePath()+".cgol");
					}
					if (f.isFile()) {
							grid.load(f);
					}
					else {
						 JOptionPane.showMessageDialog(null, "A megadott fájl nem létezik!", "Sikertelen betöltés", JOptionPane.INFORMATION_MESSAGE);				
					}
				}
			} catch (Exception e) {
				System.out.println("nem importaltuk jol");
			}
		}
		else if (ae.getActionCommand().equals("export")) {
			try {
				frame.stopSimulation();
				int returnVal = chooser.showSaveDialog(grid);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					File f = chooser.getSelectedFile();
					if(!hasCGOLExtension(f)) {
						f = new File(f.getAbsolutePath()+".cgol");
					}
					if (!f.isFile()) {
						f.createNewFile();
						grid.save(f);
					} else {
						int dialogResult = JOptionPane.showConfirmDialog(null, "Ez a fájl már létezik. Biztos, hogy újra akarod írni?", "Figyelmeztetés", JOptionPane.YES_NO_OPTION);
						if(dialogResult == JOptionPane.YES_OPTION)
							grid.save(f);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private boolean hasCGOLExtension(File file) {
		 String extension = "";
		 
       try {
           if (file != null) {
               String name = file.getName();
               extension = name.substring(name.lastIndexOf("."));
           }
       } catch (Exception e) {
           extension = "";
       }
       
       return (extension.equals(".cgol"));
	}
}
