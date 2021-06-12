package TspF;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Input extends JPanel implements ActionListener {
    
    JFrame frame = new JFrame("Travelling Salesman Problem Visualization");
    JButton confirmButton, randomButton;
    JLabel label;
    JTextField inputTF;
    
    Input() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450,250);
        frame.add(this);
        
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setLayout(new GridLayout(4,1));
        
        confirmButton = new JButton("CONFIRM");
        confirmButton.setFocusable(false);
        confirmButton.addActionListener(this);
        
        randomButton = new JButton("RANDOMIZE");
        randomButton.setFocusable(false);
        randomButton.addActionListener(this);
        
        label = new JLabel("Please enter the number of nodes");
        label.setFont(new Font("Comic Sans", Font.BOLD, 16));
        
        inputTF = new JTextField();
        inputTF.setPreferredSize(new Dimension(10,10));
        inputTF.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent KEvent) {
                if ((KEvent.getKeyChar() >= '0' && KEvent.getKeyChar() <= '9') || KEvent.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    inputTF.setEditable(true);
                }
                else {
                    inputTF.setEditable(false);
                }
            }
        });
        
        add(label);
        add(inputTF);
        add(confirmButton);
        add(randomButton);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == confirmButton) {
                int intValue = Integer.parseInt(inputTF.getText());
                if (intValue >= 2 && intValue <= 26) {
                    frame.setVisible(false);
                    frame.dispose();
                    TSP app = new TSP(intValue);
                }
                else {
                    inputTF.setText("");
                    JOptionPane.showMessageDialog(frame, "Please enter a value between 2-26", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            }
            else if (ae.getSource() == randomButton) {
                int min = 2, max = 5;
                int value = (int)Math.floor(Math.random()*(max-min+1)+min);
                frame.setVisible(false);
                frame.dispose();
                TSP app = new TSP(value);
            }
        }
        catch (NumberFormatException NFE) {
            JOptionPane.showMessageDialog(frame, "The field cannot be empty!", "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }
}