import javax.swing.*;
import java.awt.*;

public class TSP {
    private final int WIDTH = 900, HEIGHT = 600;

    private JFrame mainFrame;
    private JPanel tablePanel;
    private JPanel buttonsPanel;
    private JPanel codeSimPanel;
    private JPanel descriptionPanel;

    public TSP(int rowcol) {
        // Setup MainFrame
        mainFrame = new JFrame("TSP Visualization");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(new GridBagLayout());
        mainFrame.setMinimumSize(new Dimension(WIDTH, HEIGHT));

        GridBagConstraints adjustingConstraint = constraints(GridBagConstraints.BOTH, 1, 1);
        GridBagConstraints nonAdjustingConstraint = constraints(GridBagConstraints.BOTH, 0, 1);

        // Setup Panel UI
        tablePanel = new JPanel(new GridBagLayout()); // @neil palitan mo na lang nung class mo. @kenji ung cinode mo dito nasa class na ginagawa ni neil, di ko dinelete yun, unless dedelete nya.
        buttonsPanel = new ButtonsPanel();
        codeSimPanel = new CodeSimPanel();
        descriptionPanel = new DescriptionPanel();

        // table panel
        JScrollPane scrollArea = new JScrollPane(tablePanel);
        scrollArea.setBackground(Color.PINK);
        adjustingConstraint.gridwidth = 2;
        mainFrame.add(scrollArea, adjustingConstraint);

        // code sim panel
        adjustingConstraint.gridwidth = 1;
        adjustingConstraint.gridx = 0;
        adjustingConstraint.gridy = 2;
        mainFrame.add(codeSimPanel, adjustingConstraint);
        
        // desc panel
        adjustingConstraint.gridx = 1;
        mainFrame.add(descriptionPanel, adjustingConstraint);
        
        // buttons panel
        nonAdjustingConstraint.gridy = 1;
        nonAdjustingConstraint.gridwidth = 2;
        mainFrame.add(buttonsPanel, nonAdjustingConstraint);   
        
        mainFrame.setVisible(true);
    }

    private GridBagConstraints constraints(int fill, float weightY, float weightX){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = fill;
        constraints.weighty = weightY;
        constraints.weightx = weightX;
        return constraints;
    }
}
