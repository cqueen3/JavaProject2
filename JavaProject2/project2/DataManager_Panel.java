package project2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.*;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Label;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Button;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class DataManager_Panel extends JPanel implements ActionListener, KeyListener{
	CTList newList = new CTList("./Project2/Infected_List.txt");
	
	
	public DataManager_Panel() {
		setLayout(null);
				
		JLabel DM_lab = new JLabel("Data Manager");
		DM_lab.setBounds(385, 5, 132, 26);
		DM_lab.setFont(new Font("Dialog", Font.BOLD, 20));
		add(DM_lab);
		dataImplementer();
		
		
	}
    

	public void dataImplementer() {
		FileReader reader;
        try {

            reader = new FileReader("./Project2/Infected_List.txt");

            BufferedReader br = new BufferedReader(reader); 
             JTextArea area = new JTextArea();
             area.setBounds(181, 76, 605, 382);
             add(area);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	

    public void actionPerformed(ActionEvent e){
    	
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {}


    public void keyReleased(KeyEvent e) {}
}