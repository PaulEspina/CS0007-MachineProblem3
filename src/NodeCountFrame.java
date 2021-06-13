//package TspF;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class NodeCountFrame extends JFrame implements ActionListener {

    JButton confirmButton;
    JLabel label;
    JTextField inputTF;

    NodeCountFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Input Number of Nodes");
        setSize(450,250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,1));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        confirmButton = new JButton("CONFIRM");
        confirmButton.setFocusable(false);
        confirmButton.addActionListener(this);

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

        panel.add(label);
        panel.add(inputTF);
        panel.add(confirmButton);
        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == confirmButton) {
                int intValue = Integer.parseInt(inputTF.getText());
                if (intValue >= 2) {
                    dispose();
                    TableFrame tableFrame = new TableFrame(intValue);
                }
                else {
                    inputTF.setText("");
                    JOptionPane.showMessageDialog(null, "Please enter a value greater than or equal to 2", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        catch (NumberFormatException NFE) {
            JOptionPane.showMessageDialog(null, "The field cannot be empty!", "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }
}
