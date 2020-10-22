package project2;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class CT_Frame{

    public static void main(String[] args) {

        JFrame frame = new JFrame("CDC Contact Tracing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        CT_Panel ctPanel = new CT_Panel();
        frame.add(ctPanel, BorderLayout.CENTER);

        frame.setSize(800, 700);
        frame.setVisible(true);
    }
}

/*-----------------------------------------------------------Testing Plan----------------------------------------------------------*/
/*
	Since you cannot set up a tester file like in the last one, I will give a set of instructions as how to test everything the program has to offer
	1. Enter a random combination, get error message
	2. Use radar button to recieve hint on password
	3. Enter "Monkey7" as the password, see success message pop up
	4. Use add button to add another entry
	5. Use delete, but enter a negative number or a number above 100. Close out of error message
	6. Delete row 1
	7. Press submit (note, you will have to click off of last entered data point for it to save correctly.
	8. make a couple more additions or subtractions or edits
	9. Check the box labeled "final submission"
	10. Press submit. Program should close. Verify in text file that the correct data is saved. 
	



















*/