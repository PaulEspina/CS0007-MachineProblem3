import javax.swing.*;
import java.awt.*;

public class ButtonsPanel extends JPanel
{
//    public ButtonsPanel()
//    {
//        setBackground(Color.cyan);
//        GridBagConstraints buttonConst = constraints(GridBagConstraints.HORIZONTAL, 1, 0.1f);
//        GridBagConstraints SeparatorConst = constraints(GridBagConstraints.HORIZONTAL, 1, 2.5f);
//
//        // Code Here
//        add(new JSeparator(SwingConstants.HORIZONTAL), SeparatorConst);
//        add(new JButton("test1"), buttonConst);
//        add(new JButton("test2"), buttonConst);
//        add(new JButton("test3"), buttonConst);
//        add(new JButton("test4"), buttonConst);
//        add(new JButton("test5"), buttonConst);
//        add(new JSeparator(SwingConstants.HORIZONTAL), SeparatorConst);
//        //
//    }
//
//    private GridBagConstraints constraints(int fill, float weightY, float weightX) {
//        GridBagConstraints constraints = new GridBagConstraints();
//        constraints.fill = fill;
//        constraints.weighty = weightY;
//        constraints.weightx = weightX;
//        return constraints;
//    }
    public ButtonsPanel()
    {
        setBackground(Color.cyan);
        setLayout(new FlowLayout());
        add(new JButton("test1"));
        add(new JButton("test2"));
        add(new JButton("test3"));
        add(new JButton("test4"));
        add(new JButton("test5"));
        //
    }
}
