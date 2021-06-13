package TspF;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class TablePanel extends JPanel implements ActionListener{
    JFrame frame = new JFrame("Sample Window");
    JPanel rowHeader = new JPanel();
    JPanel colHeader = new JPanel();
    JButton button;
    JScrollPane scrollPane;
    int matrix = 5;
    int[][] parsedData = new int[matrix+1][matrix+1];
    JTextField[][] matrixTextField = new JTextField[matrix+1][matrix+1];
    
    TablePanel() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900,600);
        frame.add(this);
        
        setBorder(BorderFactory.createEmptyBorder(0,20,20,20));
        setLayout(new GridLayout(matrix + 2, matrix + 1));
        setBackground(Color.GRAY);
        
        scrollPane = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.add(scrollPane);
        
        button = new JButton("Confirm");
        button.setFocusable(false);
        button.addActionListener(this);
        
        int A = 65;
        char letter;
        
        for (int i = 0; i < matrix; i++) {
            letter = (char) A++;
            JLabel label = new JLabel(String.valueOf(letter));
            label.setPreferredSize(new Dimension(30,25));
            rowHeader.add(label);
            scrollPane.setRowHeaderView(label);
        }
        
        A = 65;
        for(int row = 0; row < matrix + 1; row++) {
//            A = 65;
            if (row != 0) {
                A += row;
            }   
            for(int col = 0; col < matrix + 1; col++) {
                //++A;
                if (row == 0 && col == 0) {
                    JLabel indexZero = new JLabel("");
                    indexZero.setVisible(true);
                    add(indexZero);
                }
                else if (row == 0 && col >= 1) {
                    letter = (char) A++;
                    JLabel alphabet = new JLabel(String.valueOf(letter));
                    add(alphabet);
                }
                else if (row >= 1 && col == 0) {
                    letter = (char) A++;
                    JLabel alphabet = new JLabel(String.valueOf(letter));
                    add(alphabet);
                }
                else {
                    JTextField data = new JTextField();
                    data.setPreferredSize(new Dimension(30,25));
                    data.addKeyListener(new KeyAdapter() {
                        public void keyPressed(KeyEvent KEvent) {
                            if ((KEvent.getKeyChar() >= '0' && KEvent.getKeyChar() <= '9') || KEvent.getKeyChar() == KeyEvent.VK_BACK_SPACE || KEvent.getKeyChar() == '-') {
                                data.setEditable(true);
                            }
                            else {
                                data.setEditable(false);
                            }
                        }
                    });
                    add(data);
                    matrixTextField[row][col] = data;
                }
            }
        }
        add(button);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        TablePanel mainPanel = new TablePanel();
    }
    
    public int[][] matrixGetter () {
        return parsedData;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == button) {
                for (int row = 1; row < 6; row++) {
                    for (int col = 1; col < 6; col++) {
                        if (matrixTextField[row][col].getText().contains("-") || matrixTextField[row][col].getText().equals("0")) {
                            matrixTextField[row][col].setText("-1");
                        }
                        parsedData[row][col] = Integer.parseInt(matrixTextField[row][col].getText());
                        matrixTextField[row][col].setEnabled(false);
                        System.out.println("ParsedData[" + row + "][" + col + "] = " + parsedData[row][col]);
                    }
                }
            }
            
        }
        catch (NumberFormatException NFE) {
            JOptionPane.showMessageDialog(frame, "Please fill up all the textfield!", "Alert", JOptionPane.ERROR_MESSAGE);
        }
    }
}