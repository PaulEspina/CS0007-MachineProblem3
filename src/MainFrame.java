import com.sun.org.apache.xerces.internal.impl.xpath.XPath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainFrame extends JFrame implements ActionListener
{
    private final CodeSimPanel codeSimPanel;
    private final JPanel descriptionPanel;
    private final TableFrame tableFrame;
    private final TSPAlgorithm algorithm;
    private final ArrayList<JButton> buttons;
    private int it;

    public MainFrame(TableFrame tableFrame) {
        // CLASS SETUP
        this.tableFrame = tableFrame;
        setTitle("TSP Visualization");
        setSize(600, 900);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // SOLVE ALGORITHM
        algorithm = new TSPAlgorithm(tableFrame.matrixGetter());
        algorithm.solve();

        // DESCRIPTION PANEL
        descriptionPanel = new JPanel();
        descriptionPanel.setPreferredSize(new Dimension(getWidth(), getHeight() / 3));
        descriptionPanel.setBackground(new Color(50, 60, 70));

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
            codeSimPanel.changePage(0);
            tableFrame.update(algorithm.begin());
        }
        if(e.getSource() == buttons.get(2))
        {
            if(it > 0 && algorithm.hasNext())
            {
                codeSimPanel.changePage(--it);
            }
            else if(it == 0)
            {
                boolean flag = algorithm.hasPrev();
                StepMatrix matrix = algorithm.prev();
                tableFrame.update(matrix);
                if(flag)
                {
                    it = 3;
                    codeSimPanel.changePage(it);
                }
            }
        }
        if(e.getSource() == buttons.get(3))
        {
            if(it < 3 && algorithm.hasNext())
            {
                codeSimPanel.changePage(++it);
            }
            else if(it == 3)
            {
                it = 0;
                StepMatrix matrix = algorithm.next();
                tableFrame.update(matrix);
                if(algorithm.hasNext())
                {
                    codeSimPanel.changePage(it);
                }
                else
                {
                    codeSimPanel.clearCode();
                }
            }
        }
        if(e.getSource() == buttons.get(4))
        {
            it = 0;
            codeSimPanel.clearCode();
            tableFrame.update(algorithm.end());
        }
    }
}
