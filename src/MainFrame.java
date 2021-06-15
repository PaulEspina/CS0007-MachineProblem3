import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class MainFrame extends JFrame implements ActionListener
{
    private final CodeSimPanel codeSimPanel;
    private final TableFrame tableFrame;
    private final TSPAlgorithm algorithm;
    private final ArrayList<JButton> buttons;
    private int it;
    private int methodIt;

    // DESCRIPITON PANEL
    // Labels
    JPanel leftDescPanel;
    JLabel iterationLabel;
    JLabel rowMinLabel;
    JLabel colMinLabel;
    JLabel penaltiesLabel;
    JLabel highesPenaltyLabel;
    JPanel rightDescPanel;
    JLabel rowRemoveLabel;
    JLabel colRemoveLabel;
    JLabel pathLabel;
    JLabel costLabel;

    public MainFrame(TableFrame tableFrame) {
        // CLASS SETUP
        this.tableFrame = tableFrame;
        it = 0;
        methodIt = 0;

        // FRAME SETUP
        setTitle("TSP Visualization");
        setSize(600, 700);
        setMinimumSize(new Dimension(getWidth(), getHeight()));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // SOLVE ALGORITHM
        algorithm = new TSPAlgorithm(tableFrame.matrixGetter());
        algorithm.solve();

        // DESCRIPTION PANEL
        JPanel descriptionPanel = new JPanel(new GridLayout(1, 2));
        descriptionPanel.setPreferredSize(new Dimension(getWidth(), getHeight() / 4));

        // Left Description Panel
        leftDescPanel = new JPanel();
        leftDescPanel.setLayout(new BoxLayout(leftDescPanel, BoxLayout.Y_AXIS));
        leftDescPanel.setBackground(new Color(50, 60, 70));
        iterationLabel = new DescriptionLabel("Iteration: 0");
        rowMinLabel = new DescriptionLabel("All Rows Minimum: ");
        colMinLabel = new DescriptionLabel("All Cols Minimum: ");
        penaltiesLabel = new DescriptionLabel("Penalties: ");
        leftDescPanel.add(iterationLabel);
        leftDescPanel.add(rowMinLabel);
        leftDescPanel.add(colMinLabel);
        leftDescPanel.add(penaltiesLabel);

        // Right Description Panel
        rightDescPanel = new JPanel();
        rightDescPanel.setLayout(new BoxLayout(rightDescPanel, BoxLayout.Y_AXIS));
        rightDescPanel.setBackground(new Color(50, 60, 70));
        highesPenaltyLabel = new DescriptionLabel("Highest Penalty: ");
        rowRemoveLabel = new DescriptionLabel("Row to Remove: ");
        colRemoveLabel = new DescriptionLabel("Col to Remove: ");
        pathLabel = new DescriptionLabel("Path: ");
        costLabel = new DescriptionLabel("Cost: ");
        rightDescPanel.add(highesPenaltyLabel);
        rightDescPanel.add(rowRemoveLabel);
        rightDescPanel.add(colRemoveLabel);
        rightDescPanel.add(pathLabel);
        rightDescPanel.add(costLabel);

        descriptionPanel.add(leftDescPanel);
        descriptionPanel.add(rightDescPanel);

        // CODE SIMULATION PANEL
        codeSimPanel = new CodeSimPanel();
        codeSimPanel.setBackground(Color.black);

        // BUTTONS PANEL
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(new Color(50, 60, 70));
        buttons = new ArrayList<>();
        buttons.add(new Button("NEW"));
        buttons.add(new Button("<<"));
        buttons.add(new Button("<"));
        buttons.add(new Button(">"));
        buttons.add(new Button(">>"));
        for(JButton button : buttons)
        {
            button.addActionListener(this);
            buttonsPanel.add(button);
        }

        add(descriptionPanel, BorderLayout.NORTH);
        add(codeSimPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == buttons.get(0))
        {
            tableFrame.dispose();
            dispose();
            new NodeCountFrame();
        }
        if(e.getSource() == buttons.get(1))
        {
            it = 0;
            methodIt = 0;
            codeSimPanel.changePage(0);
            tableFrame.update(algorithm.getInitialStep());
            iterationLabel.setText("Iteration: " + methodIt);
            rowMinLabel.setText("All Rows Minimum: ");
            colMinLabel.setText("All Cols Minimum: ");
            penaltiesLabel.setText("Penalties: ");
            highesPenaltyLabel.setText("Highest Penalty: ");
            rowRemoveLabel.setText("Row to Remove: ");
            colRemoveLabel.setText("Col to Remove: ");
            pathLabel.setText("Path: ");
            costLabel.setText("Cost: ");
        }
        if(e.getSource() == buttons.get(2))
        {
            if(it > 0)
            {
                codeSimPanel.changePage(--it);
                if(it == 0)
                {
                    rowMinLabel.setText("All Rows Minimum: ");
                }
                else if(it == 1)
                {
                    colMinLabel.setText("All Cols Minimum: ");
                }
                else if(it == 2)
                {
                    penaltiesLabel.setText("Penalties: ");
                }
                else if(it == 3)
                {
                    highesPenaltyLabel.setText("Highest Penalty: ");
                    rowRemoveLabel.setText("Row to Remove: ");
                    colRemoveLabel.setText("Col to Remove: ");
                }
            }
            else if(it == 0 && methodIt > 0)
            {
                it = 4;
                if(methodIt - 1 <= 0)
                {
                    --methodIt;
                    tableFrame.update(algorithm.getInitialStep());
                }
                else
                {
                    tableFrame.update(algorithm.getStepMatrix(--methodIt - 1));
                    pathLabel.setText("Path: ");
                    costLabel.setText("Cost: ");
                }
                codeSimPanel.changePage(it);
                StepMatrix matrix = algorithm.getStepMatrix(methodIt);
                rowMinLabel.setText("All Rows Minimum: " + Arrays.toString(matrix.getRowMin()));
                colMinLabel.setText("All Cols Minimum: " + Arrays.toString(matrix.getColMin()));
                penaltiesLabel.setText("Penalties: " + Arrays.toString(matrix.getPenalties()));
                highesPenaltyLabel.setText("Highest Penalty: " + matrix.getHighestPenalty()[2]);
                rowRemoveLabel.setText("Row to Remove: " + (char) (65 + matrix.getRemovedRow()));
                colRemoveLabel.setText("Col to Remove: " + (char) (65 + matrix.getRemovedCol()));
            }
            iterationLabel.setText("Iteration: " + methodIt);
        }
        if(e.getSource() == buttons.get(3))
        {
            if(it < 4 && methodIt < algorithm.getStepMatrices().size())
            {
                StepMatrix matrix = algorithm.getStepMatrix(methodIt);
                if(it == 0)
                {
                    rowMinLabel.setText("All Rows Minimum: " + Arrays.toString(matrix.getRowMin()));
                }
                else if(it == 1)
                {
                    colMinLabel.setText("All Cols Minimum: " + Arrays.toString(matrix.getColMin()));
                }
                else if(it == 2)
                {
                    penaltiesLabel.setText("Penalties: " + Arrays.toString(matrix.getPenalties()));
                }
                else if(it == 3)
                {
                    highesPenaltyLabel.setText("Highest Penalty: " + matrix.getHighestPenalty()[2]);
                    rowRemoveLabel.setText("Row to Remove: " + (char) (65 + matrix.getRemovedRow()));
                    colRemoveLabel.setText("Col to Remove: " + (char) (65 + matrix.getRemovedCol()));
                }
                codeSimPanel.changePage(++it);
            }
            else if(it == 4 && methodIt < algorithm.getStepMatrices().size() - 1)
            {
                it = 0;
                tableFrame.update(algorithm.getStepMatrix(methodIt));
                codeSimPanel.changePage(it);
                rowMinLabel.setText("All Rows Minimum: ");
                colMinLabel.setText("All Cols Minimum: ");
                penaltiesLabel.setText("Penalties: ");
                highesPenaltyLabel.setText("Highest Penalty: ");
                rowRemoveLabel.setText("Row to Remove: ");
                colRemoveLabel.setText("Col to Remove: ");
                pathLabel.setText("Path: ");
                costLabel.setText("Cost: ");
                methodIt++;
                iterationLabel.setText("Iteration: " + methodIt);
            }
            else if(it == 4 && methodIt < algorithm.getStepMatrices().size())
            {
                it = 0;
                tableFrame.update(algorithm.getInitialStep());
                codeSimPanel.clearCode();
                pathLabel.setText("Path: " + Arrays.toString(algorithm.getPath()));
                costLabel.setText("Cost: " + algorithm.getPathCost());
                methodIt++;
            }
        }
        if(e.getSource() == buttons.get(4))
        {
            it = 0;
            methodIt = algorithm.getStepMatrices().size();
            codeSimPanel.clearCode();
            StepMatrix matrix = algorithm.getStepMatrix(algorithm.getStepMatrices().size() - 1);
            tableFrame.update(algorithm.getInitialStep());
            iterationLabel.setText("Iteration: " + methodIt);
            rowMinLabel.setText("All Rows Minimum: " + Arrays.toString(matrix.getRowMin()));
            colMinLabel.setText("All Cols Minimum: " + Arrays.toString(matrix.getColMin()));
            penaltiesLabel.setText("Penalties: " + Arrays.toString(matrix.getPenalties()));
            highesPenaltyLabel.setText("Highest Penalty: " + matrix.getHighestPenalty()[2]);
            rowRemoveLabel.setText("Row to Remove: " + (char) (65 + matrix.getRemovedRow()));
            colRemoveLabel.setText("Col to Remove: " + (char) (65 + matrix.getRemovedCol()));
            pathLabel.setText("Path: " + Arrays.toString(algorithm.getPath()));
            costLabel.setText("Cost: " + algorithm.getPathCost());
        }
    }
}
