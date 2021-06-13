import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        setHorizontalTextPosition(this.CENTER);
        setVerticalTextPosition(this.CENTER);
    }
}
