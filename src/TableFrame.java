import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.*;

public class TableFrame extends JFrame implements ActionListener{
    private int width, height;
    private JPanel gridPanel;
    private final JButton confirmButton, randomButton;
    private JScrollPane scrollPane;
    private final int nodeCount;
    private final int[][] parsedData;
    private final JTextField[][] matrixTextField;

    TableFrame(int nodeCount) {
        this.nodeCount = nodeCount;
        parsedData = new int[nodeCount][nodeCount];
        matrixTextField = new JTextField[nodeCount+1][nodeCount+1];

        width = ((nodeCount + 1) * 50) + 50;
        height = ((nodeCount + 1) * 25) + 87;
        setSize(width, height);
        if(width > 1200)
        {
            Dimension size = getSize();
            size.width = 1200 + 25;
            setSize(size);
        }
        if(height > 800)
        {
            Dimension size = getSize();
            size.height = 800 + 75;
            setSize(size);
        }
        setTitle("Input Matrix");
        setBackground(Color.BLACK);
        setMinimumSize(new Dimension(400, 300));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gridPanel = new JPanel(new GridBagLayout());
        gridPanel.setBackground(Color.BLACK);
        scrollPane = new JScrollPane(gridPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBackground(Color.BLACK);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.BLACK);
        add(buttonsPanel, BorderLayout.SOUTH);

        confirmButton = new Button("CONFIRM", 100, 25);
        confirmButton.addActionListener(this);
        randomButton = new Button("RANDOM", 100, 25);
        randomButton.addActionListener(this);
        buttonsPanel.add(confirmButton);
        buttonsPanel.add(randomButton);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;

        // row header
        char letter = 65;
        JViewport rowHeaderView = new JViewport();
        JPanel rowHeaderPanel = new JPanel(new GridBagLayout());
        rowHeaderPanel.setBackground(Color.BLACK);
        for (int i = 0; i < nodeCount; i++) {
            JLabel label = new JLabel(String.valueOf((char) (letter + i)), SwingConstants.CENTER);
            label.setPreferredSize(new Dimension(50,25));
            label.setBackground(Color.BLACK);
            label.setForeground(Color.WHITE);
            gbc.gridy = i;
            gbc.gridx = 0;
            rowHeaderPanel.add(label, gbc);
        }
        rowHeaderView.add(rowHeaderPanel);
        scrollPane.setRowHeader(rowHeaderView);

        // col header
        JViewport colHeaderView = new JViewport();
        JPanel colHeaderPanel = new JPanel(new GridBagLayout());
        colHeaderPanel.setBackground(Color.BLACK);
        for (int i = 0; i < nodeCount; i++) {
            JLabel label = new JLabel(String.valueOf((char) (letter + i)), SwingConstants.CENTER);
            label.setPreferredSize(new Dimension(50,25));
            label.setBackground(Color.BLACK);
            label.setForeground(Color.WHITE);
            gbc.gridy = 0;
            gbc.gridx = i;
            colHeaderPanel.add(label, gbc);
        }
        colHeaderView.add(colHeaderPanel);
        scrollPane.setColumnHeader(colHeaderView);

        // grid
        for(int row = 0; row < nodeCount; row++) {
            for(int col = 0; col < nodeCount; col++) {
                JTextField data = new JTextField();
                data.setPreferredSize(new Dimension(50,25));
                data.setOpaque(true);
                data.setBackground(new Color(50, 50, 50));
                data.setForeground(new Color(175, 175, 175));
                if (row == col) {
                    data.setText("-");
                    data.setEnabled(false);
                    data.setFont(new Font("Arial", Font.BOLD, 20));
                    data.setForeground(Color.RED);
                }
                data.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent KEvent) {
                        data.setEditable((KEvent.getKeyChar() >= '0' && KEvent.getKeyChar() <= '9') || KEvent.getKeyChar() == KeyEvent.VK_BACK_SPACE || KEvent.getKeyChar() == '-');
                    }
                });
                gbc.gridy = row;
                gbc.gridx = col;
                gridPanel.add(data, gbc);
                matrixTextField[row][col] = data;
            }
        }
        add(scrollPane);
        setVisible(true);
    }

    public void update(StepMatrix stepMatrix)
    {
        int[][] matrix = stepMatrix.getMatrix();
        getContentPane().revalidate();
        getContentPane().repaint();
        getContentPane().removeAll();

        if(matrix.length > 0)
        {
            width = ((matrix.length + 1) * 50) + 50;
            height = ((matrix.length + 1) * 25) + 87;
            setSize(width, height);
            if(width > 1200)
            {
                Dimension size = getSize();
                size.width = 1200 + 25;
                setSize(size);
            }
            if(height > 800)
            {
                Dimension size = getSize();
                size.height = 800 + 75;
                setSize(size);
            }

            gridPanel = new JPanel(new GridBagLayout());
            gridPanel.setBackground(Color.BLACK);
            scrollPane = new JScrollPane(gridPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setBackground(Color.BLACK);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.weighty = 1;
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.BOTH;

            // row header
            char letter = 65;
            JViewport rowHeaderView = new JViewport();
            JPanel rowHeaderPanel = new JPanel(new GridBagLayout());
            rowHeaderPanel.setBackground(Color.BLACK);
            for(int i = 0; i < matrix.length; i++)
            {
                char charLabel = (char) (letter + i);
                if(stepMatrix.getColLabel() != null)
                {
                    charLabel = (char) (letter + stepMatrix.getRowLabel()[i]);
                }
                JLabel label = new JLabel(String.valueOf(charLabel), SwingConstants.CENTER);
                label.setOpaque(true);
                label.setBackground(Color.BLACK);
                label.setForeground(Color.WHITE);
                label.setPreferredSize(new Dimension(50, 25));
                gbc.gridy = i;
                gbc.gridx = 0;
                rowHeaderPanel.add(label, gbc);
            }
            rowHeaderView.add(rowHeaderPanel);
            scrollPane.setRowHeader(rowHeaderView);

            // col header
            letter = 65;
            JViewport colHeaderView = new JViewport();
            JPanel colHeaderPanel = new JPanel(new GridBagLayout());
            colHeaderPanel.setBackground(Color.BLACK);
            for(int i = 0; i < matrix.length; i++)
            {
                char charLabel = (char) (letter + i);
                if(stepMatrix.getColLabel() != null)
                {
                    charLabel = (char) (letter + stepMatrix.getRowLabel()[i]);
                }
                JLabel label = new JLabel(String.valueOf(charLabel), SwingConstants.CENTER);
                label.setOpaque(true);
                label.setBackground(Color.BLACK);
                label.setForeground(Color.WHITE);
                label.setPreferredSize(new Dimension(50, 25));
                gbc.gridy = 0;
                gbc.gridx = i;
                colHeaderPanel.add(label, gbc);
            }
            colHeaderView.add(colHeaderPanel);
            scrollPane.setColumnHeader(colHeaderView);

            // grid
            for(int row = 0; row < matrix.length; row++)
            {
                for(int col = 0; col < matrix.length; col++)
                {
                    JLabel data = new JLabel(String.valueOf(matrix[row][col]), SwingConstants.CENTER);
                    data.setPreferredSize(new Dimension(50, 25));
                    data.setBorder(BorderFactory.createLineBorder(Color.black));
                    data.setOpaque(true);
                    data.setBackground(new Color(50, 50, 50));
                    data.setForeground(new Color(175, 175, 175));
                    gbc.gridy = row;
                    gbc.gridx = col;
                    gridPanel.add(data, gbc);
                }
            }
            add(scrollPane);
        }
        else
        {
            add(new JLabel("DONE"));
        }
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == confirmButton) {
                for (int row = 0; row < nodeCount; row++) {
                    for (int col = 0; col < nodeCount; col++) {
                        if (matrixTextField[row][col].getText().contains("-") || matrixTextField[row][col].getText().equals("0")) {
                            parsedData[row][col] = -1;
                        }
                        parsedData[row][col] = Integer.parseInt(matrixTextField[row][col].getText());
                    }
                }
                StepMatrix step = new StepMatrix(parsedData, 0, 0, null, null, null, null);
                update(step);
                new MainFrame(this);
            }
            else if (ae.getSource() == randomButton) {
                for (int row = 0; row < nodeCount; row++) {
                    for (int col = 0; col < nodeCount; col++) {
                        if (row == col) {
                            parsedData[row][col] = -1;
                        }
                        else {
                            parsedData[row][col] = new Random().nextInt(99);
                        }
                    }
                }
                StepMatrix step = new StepMatrix(parsedData, 0, 0, null, null, null, null);
                update(step);
                new MainFrame(this);
            }

        }
        catch (NumberFormatException NFE) {
            JOptionPane.showMessageDialog(this, "Please fill up all the textfield!", "Alert", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int[][] matrixGetter () {
        return parsedData;
    }
}
