import javax.swing.*;
import java.awt.*;

public class ButtonsPanel extends JPanel
{
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
