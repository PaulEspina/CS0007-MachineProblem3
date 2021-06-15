import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CodeSimPanel extends JPanel
{
    private final JPanel contentPanel;
    private final JScrollPane scrollPane;
    private final ArrayList<JLabel>[] codePanels;
    private int currentProcessIt;

    public CodeSimPanel()
    {
        setLayout(new BorderLayout());
        currentProcessIt = 0;

        // Code Labels Setup
        codePanels = new ArrayList[4];
        codePanels[0] = new ArrayList<>();
        codePanels[1] = new ArrayList<>();
        codePanels[2] = new ArrayList<>();
        codePanels[3] = new ArrayList<>();
        // row minimization code
        codePanels[0].add(new JLabel("for(int i = 0; i < nodeCount; i++)"));
        codePanels[0].add(new JLabel("{"));
        codePanels[0].add(new JLabel("    int rowMin = Integer.MAX_VALUE;"));
        codePanels[0].add(new JLabel("    for(int j = 0; j < nodeCount; j++)"));
        codePanels[0].add(new JLabel("    {"));
        codePanels[0].add(new JLabel("        if(matrix[i][j] >= 0 && matrix[i][j] < rowMin)"));
        codePanels[0].add(new JLabel("        {"));
        codePanels[0].add(new JLabel("            rowMin = matrix[i][j];"));
        codePanels[0].add(new JLabel("        }"));
        codePanels[0].add(new JLabel("    }"));
        codePanels[0].add(new JLabel("    if(rowMin != 0)"));
        codePanels[0].add(new JLabel("    {"));
        codePanels[0].add(new JLabel("        for(int j = 0; j < nodeCount; j++)"));
        codePanels[0].add(new JLabel("        {"));
        codePanels[0].add(new JLabel("            if(matrix[i][j] >= 0)"));
        codePanels[0].add(new JLabel("            {"));
        codePanels[0].add(new JLabel("                matrix[i][j] -= rowMin;"));
        codePanels[0].add(new JLabel("            }"));
        codePanels[0].add(new JLabel("        }"));
        codePanels[0].add(new JLabel("    }"));
        codePanels[0].add(new JLabel("}"));
        // col minimization code
        codePanels[1].add(new JLabel("for(int i = 0; i < nodeCount; i++)"));
        codePanels[1].add(new JLabel("{"));
        codePanels[1].add(new JLabel("    int colMin = Integer.MAX_VALUE;"));
        codePanels[1].add(new JLabel("    for(int j = 0; j < nodeCount; j++)"));
        codePanels[1].add(new JLabel("    {"));
        codePanels[1].add(new JLabel("        if(matrix[j][i] >= 0 && matrix[j][i] < colMin)"));
        codePanels[1].add(new JLabel("        {"));
        codePanels[1].add(new JLabel("            colMin = matrix[j][i];"));
        codePanels[1].add(new JLabel("        }"));
        codePanels[1].add(new JLabel("    }"));
        codePanels[1].add(new JLabel("    if(colMin != 0)"));
        codePanels[1].add(new JLabel("    {"));
        codePanels[1].add(new JLabel("        for(int j = 0; j < nodeCount; j++)"));
        codePanels[1].add(new JLabel("        {"));
        codePanels[1].add(new JLabel("            if(matrix[j][i] >= 0)"));
        codePanels[1].add(new JLabel("            {"));
        codePanels[1].add(new JLabel("                matrix[j][i] -= colMin;"));
        codePanels[1].add(new JLabel("            }"));
        codePanels[1].add(new JLabel("        }"));
        codePanels[1].add(new JLabel("    }"));
        codePanels[1].add(new JLabel("}"));
        // penalty code
        codePanels[2].add(new JLabel("ArrayList<int[]> penalties = new ArrayList<>();"));
        codePanels[2].add(new JLabel("for(int i = 0; i < nodeCount; i++)"));
        codePanels[2].add(new JLabel("{"));
        codePanels[2].add(new JLabel("    for(int j = 0; j < nodeCount; j++)"));
        codePanels[2].add(new JLabel("    {"));
        codePanels[2].add(new JLabel("        if(matrix[i][j] == 0)"));
        codePanels[2].add(new JLabel("        {"));
        codePanels[2].add(new JLabel("            int rowMin = Integer.MAX_VALUE;"));
        codePanels[2].add(new JLabel("            int colMin = Integer.MAX_VALUE;"));
        codePanels[2].add(new JLabel("            for(int k = 0; k < nodeCount; k++)"));
        codePanels[2].add(new JLabel("            {"));
        codePanels[2].add(new JLabel("                if(k != j && matrix[i][k] >= 0 && matrix[i][k] < rowMin)"));
        codePanels[2].add(new JLabel("                {"));
        codePanels[2].add(new JLabel("                    rowMin = matrix[i][k];"));
        codePanels[2].add(new JLabel("                }"));
        codePanels[2].add(new JLabel("                if(k != i && matrix[k][j] >= 0 && matrix[k][j] < colMin)"));
        codePanels[2].add(new JLabel("                {"));
        codePanels[2].add(new JLabel("                    colMin = matrix[k][j];"));
        codePanels[2].add(new JLabel("                }"));
        codePanels[2].add(new JLabel("            }"));
        codePanels[2].add(new JLabel("            penalties.add(new int[]{i, j, rowMin + colMin});"));
        codePanels[2].add(new JLabel("        }"));
        codePanels[2].add(new JLabel("    }"));
        codePanels[2].add(new JLabel("}"));
        // find highest penalty code
        codePanels[3].add(new JLabel("int highestPenalty = 0;"));
        codePanels[3].add(new JLabel("int highPenaltyX = 0;"));
        codePanels[3].add(new JLabel("int highPenaltyY = 0;"));
        codePanels[3].add(new JLabel("for(int[] penalty : penalties)"));
        codePanels[3].add(new JLabel("{"));
        codePanels[3].add(new JLabel("    if(penalty[2] > highestPenalty)"));
        codePanels[3].add(new JLabel("    {"));
        codePanels[3].add(new JLabel("        highPenaltyY = penalty[0];"));
        codePanels[3].add(new JLabel("        highPenaltyX = penalty[1];"));
        codePanels[3].add(new JLabel("        highestPenalty = penalty[2];"));
        codePanels[3].add(new JLabel("    }"));
        codePanels[3].add(new JLabel("}"));

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.BLACK);
        scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBackground(new Color(50, 50, 50));
        updateCode();
    }

    public void clearCode()
    {
        revalidate();
        repaint();
        removeAll();
        contentPanel.removeAll();
    }

    private void updateCode()
    {
        clearCode();
        add(scrollPane);

        boolean endFlag = false;

        JLabel processTitle = new JLabel();
        processTitle.setHorizontalAlignment(JLabel.CENTER);
        processTitle.setPreferredSize(new Dimension(getWidth(), 20));
        processTitle.setBackground(new Color(50, 50, 50));
        processTitle.setForeground(new Color(200, 200, 200));
        processTitle.setOpaque(true);
        switch(currentProcessIt)
        {
            case 0:
                processTitle.setText("ROW MINIMIZATION");
                break;
            case 1:
                processTitle.setText("COLUMN MINIMIZATION");
                break;
            case 2:
                processTitle.setText("COMPUTING PENALTIES");
                break;
            case 3:
                processTitle.setText("FINDING HIGHEST PENALTY");
                break;
            case 4:
                processTitle.setText("END OF ITERATION");
                endFlag = true;
                break;
            default:
                processTitle.setText("");
        }
        add(processTitle, BorderLayout.NORTH);

        if(!endFlag)
        {
            // SETUP ROW HEADER VIEW
            JViewport rowHeaderView = new JViewport();
            JPanel lineNumberPanel = new JPanel();
            lineNumberPanel.setLayout(new BoxLayout(lineNumberPanel, BoxLayout.Y_AXIS));
            lineNumberPanel.setPreferredSize(new Dimension(25, getHeight() - 20));
            lineNumberPanel.setBackground(new Color(50, 50, 50));
            ArrayList<JLabel> codes = codePanels[currentProcessIt];
            for(int i = 0; i < codes.size(); i++)
            {
                // line number
                JLabel label = new JLabel(String.valueOf(i + 1), SwingConstants.RIGHT);
                label.setPreferredSize(new Dimension(15, 20));
                label.setForeground(Color.GRAY);
                lineNumberPanel.add(label);

                // setup lines
                JLabel line = codes.get(i);
                line.setPreferredSize(new Dimension(getWidth() - 100, 20));
                line.setForeground(new Color(200, 200, 200));
                contentPanel.add(line);
            }
            rowHeaderView.add(lineNumberPanel);
            scrollPane.setRowHeader(rowHeaderView);
        }
    }

    public void changePage(int i)
    {
        currentProcessIt = i;
        updateCode();
    }
}
