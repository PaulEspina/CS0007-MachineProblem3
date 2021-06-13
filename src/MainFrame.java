import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainFrame extends JFrame implements ActionListener
{
    private JPanel buttonsPanel;
    private JPanel codeSimPanel;
    private JPanel descriptionPanel;
    private TableFrame tableFrame;
    private TSPAlgorithm algorithm;
    private ArrayList<JButton> buttons;

    public MainFrame(TableFrame tableFrame) {
        this.tableFrame = tableFrame;
        // Setup MainFrame
        setTitle("TSP Visualization");
        setSize(600, 900);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));

        algorithm = new TSPAlgorithm(tableFrame.matrixGetter());
        algorithm.solve();

        // description panel
        descriptionPanel = new JPanel();
        descriptionPanel.setPreferredSize(new Dimension(getWidth(), getHeight() / 4));

        // code sim panel
        codeSimPanel = new JPanel();

        // buttons panel
        buttonsPanel = new JPanel();
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
            tableFrame.update(algorithm.begin());
        }
        if(e.getSource() == buttons.get(2))
        {
            tableFrame.update(algorithm.prev());
        }
        if(e.getSource() == buttons.get(3))
        {
            tableFrame.update(algorithm.next());
        }
        if(e.getSource() == buttons.get(4))
        {
            tableFrame.update(algorithm.end());
        }
    }
}
