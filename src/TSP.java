import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TSP implements ActionListener
{
    private final int WIDTH = 600, HEIGHT = 900;

    private JFrame mainFrame;
    private ButtonsPanel buttonsPanel;
    private JPanel codeSimPanel;
    private JPanel descriptionPanel;

    public TSP(TableFrame tableFrame) {
        // Setup MainFrame
        mainFrame = new JFrame("TSP Visualization");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setMinimumSize(new Dimension(WIDTH, HEIGHT));

        TSPAlgorithm algorithm = new TSPAlgorithm(tableFrame.matrixGetter());
        algorithm.solve();

        // Setup Panel UI
        descriptionPanel = new DescriptionPanel();
        descriptionPanel.setPreferredSize(new Dimension(mainFrame.getWidth(), mainFrame.getHeight() / 4));
        codeSimPanel = new CodeSimPanel();
        buttonsPanel = new ButtonsPanel();
        buttonsPanel.addActionListener(this);

        mainFrame.add(descriptionPanel, BorderLayout.NORTH);
        mainFrame.add(codeSimPanel, BorderLayout.CENTER);
        mainFrame.add(buttonsPanel, BorderLayout.SOUTH);
        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if
    }
}
