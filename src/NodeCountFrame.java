//package TspF;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class NodeCountFrame extends JFrame implements ActionListener {

    JButton confirmButton;
    JButton closeButton;
    JLabel label;
    JTextField inputTF;

    NodeCountFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Input Number of Nodes");
        setSize(400,175);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel();
        inputPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 5, 0));
        inputPanel.setBackground(Color.BLACK);

        label = new JLabel("Enter Node Count:");
        label.setFont(new Font("Comic Sans", Font.BOLD, 16));
        label.setForeground(Color.WHITE);
        label.setPreferredSize(new Dimension(150, 25));

        inputTF = new JTextField();
        inputTF.setPreferredSize(new Dimension(150,25));
        inputTF.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent KEvent) {
                inputTF.setEditable((KEvent.getKeyChar() >= '0' && KEvent.getKeyChar() <= '9') || KEvent.getKeyChar() == KeyEvent.VK_BACK_SPACE);
            }
        });

        inputPanel.add(label);
        inputPanel.add(inputTF);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 10, 0));

        closeButton = new Button("CLOSE", 100, 25);
        closeButton.addActionListener(this);

        confirmButton = new Button("SUBMIT", 100,25);
        confirmButton.addActionListener(this);

        buttonPanel.add(closeButton);
        buttonPanel.add(confirmButton);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton) {
            int intValue = Integer.parseInt(inputTF.getText());
            if (intValue >= 2) {
                dispose();
                new TableFrame(intValue);
            }
            else {
                inputTF.setText("");
                JOptionPane.showMessageDialog(null, "Please enter a value greater than or equal to 2", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        }
        if(e.getSource() == closeButton)
        {
            dispose();
        }
    }
}
