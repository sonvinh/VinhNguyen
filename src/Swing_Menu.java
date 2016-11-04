import java.awt.Color;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.jar.JarFile;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 
 */

/**
 * @author Vinh Nguyen T153346
 * 
 */
public class Swing_Menu extends JFrame {
	JMenuBar mnbBar = new JMenuBar();
	JMenu mnuFile = new JMenu("File"), mnuFormat = new JMenu("Format");
	JMenuItem mniOpen = new JMenuItem("Open"), mniExit = new JMenuItem("Exit"),mniSave = new JMenuItem("Save"),
			mniChangeBgColor = new JMenuItem("ChangeBGColor"), mniChangeFontColor = new JMenuItem("ChangeFont"), mniSaveAs = new JMenuItem("SaveAs");
	JTextArea txtContent = new JTextArea();
	JScrollPane scrPane = new JScrollPane(txtContent);
	

	public Swing_Menu() {
		setTitle("TextEditor");
		setPreferredSize(new Dimension(600, 500));
		pack();
		setLocationRelativeTo(null);
		// add to Menu File
		mnuFile.add(mniOpen);
		mnuFile.add(mniSave);
		mnuFile.add(mniSaveAs);
		mnuFile.addSeparator();	
		mnuFile.add(mniExit);
		
		// add to Menu Format
		mnuFormat.add(mniChangeBgColor);
		mnuFormat.addSeparator();
		mnuFormat.add(mniChangeFontColor);
		// add 2 Menu into MenuBar
		mnbBar.add(mnuFile);
		mnbBar.add(mnuFormat);
		// set MenuBar
		setJMenuBar(mnbBar);

		getContentPane().add(scrPane);

		ActionListener action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == mniOpen) {
					openFile();

				} else if (e.getSource() == mniExit) {
					CloseApp();
					
				} else if (e.getSource() == mniChangeBgColor) {
					changeBGColor();
					
				} else if (e.getSource() == mniChangeFontColor) {
					changeFontColor();
					
				}else if (e.getSource() == mniSaveAs) {
					SaveAs();
					
				}else if (e.getSource() == mniSave) {
					Save();
				}
			}
		};
		mniOpen.addActionListener(action);
		mniExit.addActionListener(action);
		mniChangeBgColor.addActionListener(action);
		mniChangeFontColor.addActionListener(action);
		mniSaveAs.addActionListener(action);
		mniSave.addActionListener(action);
		
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
			CloseApp();
			}
		});
	}
	File f;
	public void openFile() {
		JFileChooser fchOpenfile = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file", "txt");
		fchOpenfile.setFileFilter(filter);
		int Result = fchOpenfile.showOpenDialog(this);
		if (Result == JFileChooser.APPROVE_OPTION) {
			try {
				Scanner scan = new Scanner(fchOpenfile.getSelectedFile());
				while (scan.hasNextLine()) {
					txtContent.append(scan.nextLine() + "\n");
					txtContent.setText("");
				}
				scan.close();
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "File is Not Found");
			}
		}
	}

	public void changeBGColor() {
		Color newColor = JColorChooser.showDialog(this, "Choose new color", txtContent.getBackground());
		txtContent.setBackground(newColor);
	}

	public void changeFontColor() {
		Color fontColor = JColorChooser.showDialog(this, "Choose font color", txtContent.getForeground());
		txtContent.setForeground(fontColor);
	}

	public void CloseApp() {
		int result = JOptionPane.showConfirmDialog(null, "Are You Want To Exit", "Comfirm",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (result == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	public void SaveAs() {
		JFileChooser fchSavefile = new JFileChooser();
		FileNameExtensionFilter filter1 = new FileNameExtensionFilter("Text file", "txt");
		fchSavefile.setFileFilter(filter1);
		int Result = fchSavefile.showSaveDialog(this);
		if (Result == JFileChooser.APPROVE_OPTION ) {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(fchSavefile.getSelectedFile()));
			writer.write(txtContent.getText());
			writer.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	public void Save() {
		if (f != null) {
			
			BufferedWriter writer1;
			try {
				
			writer1 = new BufferedWriter(new FileWriter(f));
			writer1.write(txtContent.getText());
			writer1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else { 
			SaveAs();
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Swing_Menu main = new Swing_Menu();
		main.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		main.setVisible(true);

	}

}
