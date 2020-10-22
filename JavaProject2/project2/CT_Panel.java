package project2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.*;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Label;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Button;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.imageio.ImageIO;
import javax.swing.DefaultButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.ListSelectionModel;
import javax.swing.JInternalFrame;

public class CT_Panel extends JPanel implements ActionListener{
	CTList newList = new CTList("./Project2/Infected_List.txt");
	
	
	// All the individual components that I use so that I can manipulate the objects
	private JPasswordField passwordField;
    private JTextField EnterMsg;
    private JTextField DelField;
    private Label SuccessMSG;
    private Label DMLabel;
    private Label topLabel;
    private Label thint;
    private JLabel SecondaryLabel;
    private JLabel picLabel;
    private JLabel cdcPic;
    private Button PButton;
    private Button Submit;
	private Button Add;
	private Button Del;
    private JTable DataTable;
	private DefaultTableModel dtm;
	private JCheckBox close;
	private BufferedImage uca;
	private BufferedImage cdc;
	private JRadioButton hint;
	private String header[];
	private boolean passwordEntered = false;
	
	public CT_Panel() {
		setLayout(null);

		// Password taken in at beginning of project
		passwordField = new JPasswordField();
		passwordField.setBounds(412, 97, 108, 22);
		add(passwordField);
		
		// Text field
		EnterMsg = new JTextField();
		EnterMsg.setEnabled(true);
		EnterMsg.setBackground(SystemColor.menu);
		EnterMsg.setFont(new Font("Times New Roman", Font.BOLD, 14));
		EnterMsg.setText("Please enter the tracer code to begin: ");
		EnterMsg.setBounds(146, 98, 269, 20);
		EnterMsg.setEditable(false);
		add(EnterMsg);
		
		// Enter Button
		PButton = new Button("Enter");
		PButton.addActionListener(this);
		PButton.setEnabled(true);
		PButton.setBounds(526, 97, 80, 21);
		add(PButton);
		
		// Success Label (Temporary)
		SuccessMSG = new Label("Success! Your password was correct.");
		SuccessMSG.setEnabled(true);
		SuccessMSG.setFont(new Font("Dialog", Font.BOLD, 16));
		SuccessMSG.setBounds(175, 211, 518, 49);
		SuccessMSG.setVisible(false);
		add(SuccessMSG);
		
		// Data Manager Label (Temporary)
		DMLabel = new Label("The Data Manager should appear now");
		DMLabel.setEnabled(true);
		DMLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		DMLabel.setBounds(175, 266, 417, 49);
		DMLabel.setVisible(false);
		add(DMLabel);
		
		// New top label
		topLabel = new Label("Welcome to the CDC Contact Tracer!");
		topLabel.setEnabled(true);
		topLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		topLabel.setBounds(146, 25, 500, 34);
		add(topLabel);
		
		// Data table is created using a model that includes all of txt file info
		Iterator<Person> iter = newList.iterator();//Create iterator
		DataTable = new JTable();                       // Ideas from  https://stackoverflow.com/questions/22371720/how-to-add-row-dynamically-in-jtable
		DataTable.setSize(677, 324);
		DataTable.setLocation(39, 244);
		dtm = new DefaultTableModel(0,0);
		
		String header[] = new String[] {"ID", "First Name", "Last Name", "In Quarantine", "Start Date", "Live on Campus", "Close Contacts"}; // Adds column headers
		dtm.setColumnIdentifiers(header);
		dtm.addRow(header);
		DataTable.setPreferredScrollableViewportSize(DataTable.getSize());
		DataTable.setModel(dtm);
		while (iter.hasNext()) { // Uses the iterator combined with the model to input data from text file
			Person p = iter.next();
			Object add[] = {null, null, null, null, null, null, null};
			add[0] = p.getSchoolID();
			add[1] = p.getFn();
			add[2] = p.getLn();
			add[3] = p.getInQuarantine();
			add[4] = p.getQSD();
			add[5] = p.getLivesCampus();
			add[6] = p.getCloseContacts();
			dtm.addRow(add);
			
		}
		// Sets desired widths of table
		DataTable.getColumnModel().getColumn(0).setPreferredWidth(50); 
		DataTable.getColumnModel().getColumn(1).setPreferredWidth(30);
		DataTable.getColumnModel().getColumn(2).setPreferredWidth(30);
		DataTable.getColumnModel().getColumn(3).setPreferredWidth(75);
		DataTable.getColumnModel().getColumn(4).setPreferredWidth(75);
		DataTable.getColumnModel().getColumn(5).setPreferredWidth(75);
		DataTable.getColumnModel().getColumn(6).setPreferredWidth(200);
		DataTable.setVisible(false);
		add(DataTable);
		
		// Label above all buttons after password entered
		SecondaryLabel = new JLabel("Feel Free to complete any changes. Press submit when finished. ");
		SecondaryLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		SecondaryLabel.setBounds(175, 144, 398, 22);
		SecondaryLabel.setVisible(false);
		add(SecondaryLabel);
		
		//Submit button
		Submit = new Button("Submit");
		Submit.addActionListener(this);
		Submit.setFont(new Font("Times New Roman", Font.BOLD, 14));
		Submit.setBounds(338, 177, 89, 23);
		Submit.setVisible(false);
		add(Submit);
		
		//Add button
		Add = new Button("Add");
		Add.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			addrow();
    		}
    	});
		Add.setFont(new Font("Times New Roman", Font.BOLD, 14));
		Add.setBounds(210, 177, 89, 23);
		Add.setVisible(false);
		add(Add);
		
		// Delete Button
		Del = new Button("Delete");
		Del.addActionListener( new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			delRow();
    		}
    	});
		Del.setFont(new Font("Times New Roman", Font.BOLD, 14));
		Del.setBounds(538, 177, 89, 23);
		Del.setVisible(false);
		add(Del);
		
		// Field used to input data
		DelField = new JTextField();
		DelField.setBounds(488, 177, 50, 23);
		add(DelField);
		DelField.setColumns(10);
		DelField.setVisible(false);
		add(DelField);
		
		close = new JCheckBox("Final Submit", false);
		close.setBounds(338, 127, 150, 23);
		close.setVisible(false);
		add(close);
		
		hint = new JRadioButton("Hint?");
		hint.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			hintPressed();
    		}
    	});
		topLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		hint.setVisible(false);
		hint.setBounds(412, 137, 150, 30);
		add(hint);
		
		thint = new Label("Primate and 4+3");
		thint.setFont(new Font("Times New Roman", Font.BOLD, 14));
		thint.setVisible(false);
		thint.setBounds(414, 107, 150, 150);
		add(thint);
		
		
		try {
			uca = ImageIO.read(new File("./Project2/UCA.PNG"));
		} catch (IOException e1) {
			System.out.println("File Not found");
		}
		picLabel = new JLabel(new ImageIcon(uca));
		picLabel.setBounds(0, 300,200,300 );
		add(picLabel);
		
		try {
			cdc = ImageIO.read(new File("./Project2/CDC.jpg"));
		} catch (IOException e1) {
			System.out.println("File Not found");
		}
		cdcPic = new JLabel(new ImageIcon(cdc));
		cdcPic.setBounds(240, 300,600,340 );
		add(cdcPic);
	}
    
	// functionality for deleting rows
    public void delRow() { 
    	if (Integer.parseInt(DelField.getText()) > 0 && Integer.parseInt(DelField.getText()) < dtm.getRowCount())
    	dtm.removeRow(Integer.parseInt(DelField.getText()));
    	else
    		JOptionPane.showMessageDialog(null, "Entered delete value is outside the bounds. Please try again.", "Error: Entry out of bounds", JOptionPane.ERROR_MESSAGE);
    }
    
    // functionality of adding rows
    public void addrow() { 
    	Object[] addrow = new Object[] {"", "", "", "", "", "", ""};
		dtm.addRow(addrow);
		if (dtm.getRowCount() > 20) {
			DataTable.scrollRectToVisible(getBounds());
		}
    }
    // Adds password
    public boolean checkPassword(char[] password) {
    	boolean pass = false;
    	char[] myPass = {'M','o', 'n','k','e','y','7'};
    	if (password.length == myPass.length)
    		pass = Arrays.equals(password,  myPass);
    	return pass;
    }
   
    public void closeP() {
    	System.exit(0);
    }
    
    public void hintPressed(){
    	if (hint.isSelected())
    	thint.setVisible(true);
    	else if (hint.isEnabled() != true)
    		thint.setVisible(false);
    }
    
    
    
    public void actionPerformed(ActionEvent e){
    	action();
    }
    
    public void action() {
    	if (passwordEntered == false) {
            char[] input = passwordField.getPassword();
            if (checkPassword(input)) {
            	 SuccessMSG.setVisible(true);
                 DMLabel.setVisible(true);
                 passwordEntered = true;
            try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e1) {}
            
                passwordField.setVisible(false);
                EnterMsg.setVisible(false);
                PButton.setVisible(false);
                topLabel.setVisible(false);
                SuccessMSG.setVisible(false);
                DMLabel.setVisible(false);
                hint.setVisible(false);
                DataTable.setVisible(true);
                SecondaryLabel.setVisible(true);
                Submit.setVisible(true);
                Add.setVisible(true);
                Del.setVisible(true);
                DelField.setVisible(true);
                close.setVisible(true);
                thint.setVisible(false);
                hint.setVisible(false);
                picLabel.setBounds(0, 0, 200, 246);
                cdcPic.setVisible(false);
            } else {
            	JOptionPane.showMessageDialog(null, "Password is incorrect. Please try again", "Error: Password Incorrect", JOptionPane.ERROR_MESSAGE);
            	hint.setVisible(true);
            }
    	}
    	Submit.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			newList.removeAll();
    			for (int i = 1; i <= DataTable.getModel().getRowCount()-1; i++) { // Starts at one to not save the column labels
    				Person p = new Person();
    				p.setSchoolID((String) DataTable.getValueAt(i, 0));
    				p.setFn((String) DataTable.getValueAt(i, 1));
    				p.setLn((String) DataTable.getValueAt(i, 2));
	    			p.setInQuarantine((String) DataTable.getValueAt(i, 3));
	    			p.setQSD((String) DataTable.getValueAt(i, 4));
	    			p.setLivesCampus((String) DataTable.getValueAt(i, 5));
	    			p.setCloseContacts((String) DataTable.getValueAt(i, 6));
    			newList.add(p);
    		}
    			newList.writeFile();
    			if (close.isSelected()) {
    				closeP();
    			}
    		}
    	});
    	
        
    }
}