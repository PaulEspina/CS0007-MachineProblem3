import javax.swing.*;
import java.awt.*;

public class DescriptionLabel extends JLabel
{
    public DescriptionLabel()
    {
        setForeground(new Color(200, 200, 200));
        setFont(new Font("Arial", Font.PLAIN, 12));
        setVerticalAlignment(JLabel.TOP);
        setPreferredSize(new Dimension(300, 100));
    }

    public DescriptionLabel(String text)
    {
        this();
        setText(text);
    }
}
