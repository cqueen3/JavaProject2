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