import javax.swing.*;
import java.awt.*;

public class Button extends JButton
{
    public Button()
    {
        init();
    }

    public Button(String text)
    {
        setText(text);
        init();
    }

    public Button(int width, int height)
    {
        setPreferredSize(new Dimension(width, height));
        init();
    }

    public Button(String text, int width, int height)
    {
        setText(text);
        setPreferredSize(new Dimension(width, height));
        init();
    }

    private void init()
    {
        setFocusPainted(false);
        setFocusable(false);
        setFont(new Font("Arial", Font.BOLD, 12));
        setBackground(new Color(50, 50, 50));
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setHorizontalTextPosition(this.CENTER);
        setVerticalTextPosition(this.CENTER);
    }
}
