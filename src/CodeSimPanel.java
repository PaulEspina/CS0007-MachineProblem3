import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CodeSimPanel extends JPanel
{
    private final JPanel contentPanel;
    private JScrollPane scrollPane;
    private final ArrayList<JLabel>[] codeLabels;
    private int currentProcessIt;
    private int lineIt;

    public CodeSimPanel()
    {
        currentProcessIt = -1;
        lineIt = -1;
        // Code Labels Setup
        codeLabels = new ArrayList[4]; // 0 - row minimization, 1 - col minimization, 2 - penalty, 3 - find highest penalty
        codeLabels[0] = new ArrayList<>();
        codeLabels[1] = new ArrayList<>();
        codeLabels[2] = new ArrayList<>();
        codeLabels[3] = new ArrayList<>();
        // row minimization code
        codeLabels[0].add(new JLabel("for(int i = 0; i < nodeCount; i++)"));
        codeLabels[0].add(new JLabel("{"));
        codeLabels[0].add(new JLabel("   int rowMin = Integer.MAX_VALUE;"));
        codeLabels[0].add(new JLabel("   for(int j = 0; j < nodeCount; j++)"));
        codeLabels[0].add(new JLabel("   {"));
        codeLabels[0].add(new JLabel("       if(matrix[i][j] >= 0 && matrix[i][j] < rowMin)"));
        codeLabels[0].add(new JLabel("       {"));
        codeLabels[0].add(new JLabel("           rowMin = matrix[i][j];"));
        codeLabels[0].add(new JLabel("       }"));
        codeLabels[0].add(new JLabel("   }"));
        codeLabels[0].add(new JLabel("   if(rowMin != 0)"));
        codeLabels[0].add(new JLabel("   {"));
        codeLabels[0].add(new JLabel("       for(int j = 0; j < nodeCount; j++)"));
        codeLabels[0].add(new JLabel("       {"));
        codeLabels[0].add(new JLabel("           if(matrix[i][j] >= 0)"));
        codeLabels[0].add(new JLabel("           {"));
        codeLabels[0].add(new JLabel("               matrix[i][j] -= rowMin;"));
        codeLabels[0].add(new JLabel("           }"));
        codeLabels[0].add(new JLabel("       }"));
        codeLabels[0].add(new JLabel("   }"));
        codeLabels[0].add(new JLabel("}"));
        // col minimization code
        codeLabels[1].add(new JLabel("for(int i = 0; i < nodeCount; i++)"));
        codeLabels[1].add(new JLabel("{"));
        codeLabels[1].add(new JLabel("  int colMin = Integer.MAX_VALUE;"));
        codeLabels[1].add(new JLabel("  for(int j = 0; j < nodeCount; j++)"));
        codeLabels[1].add(new JLabel("  {"));
        codeLabels[1].add(new JLabel("      if(matrix[j][i] >= 0 && matrix[j][i] < colMin)"));
        codeLabels[1].add(new JLabel("      {"));
        codeLabels[1].add(new JLabel("          colMin = matrix[j][i];"));
        codeLabels[1].add(new JLabel("      }"));
        codeLabels[1].add(new JLabel("  }"));
        codeLabels[1].add(new JLabel("  if(colMin != 0)"));
        codeLabels[1].add(new JLabel("  {"));
        codeLabels[1].add(new JLabel("      for(int j = 0; j < nodeCount; j++)"));
        codeLabels[1].add(new JLabel("      {"));
        codeLabels[1].add(new JLabel("          if(matrix[j][i] >= 0)"));
        codeLabels[1].add(new JLabel("          {"));
        codeLabels[1].add(new JLabel("              matrix[j][i] -= colMin;"));
        codeLabels[1].add(new JLabel("          }"));
        codeLabels[1].add(new JLabel("      }"));
        codeLabels[1].add(new JLabel("  }"));
        codeLabels[1].add(new JLabel("}"));
        // penalty code
        codeLabels[2].add(new JLabel("ArrayList<int[]> penalties = new ArrayList<>();"));
        codeLabels[2].add(new JLabel("for(int i = 0; i < nodeCount; i++)"));
        codeLabels[2].add(new JLabel("{"));
        codeLabels[2].add(new JLabel("  for(int j = 0; j < nodeCount; j++)"));
        codeLabels[2].add(new JLabel("  {"));
        codeLabels[2].add(new JLabel("      if(matrix[i][j] == 0)"));
        codeLabels[2].add(new JLabel("      {"));
        codeLabels[2].add(new JLabel("          int rowMin = Integer.MAX_VALUE;"));
        codeLabels[2].add(new JLabel("          int colMin = Integer.MAX_VALUE;"));
        codeLabels[2].add(new JLabel("          for(int k = 0; k < nodeCount; k++)"));
        codeLabels[2].add(new JLabel("          {"));
        codeLabels[2].add(new JLabel("              if(k != j && matrix[i][k] >= 0 && matrix[i][k] < rowMin)"));
        codeLabels[2].add(new JLabel("              {"));
        codeLabels[2].add(new JLabel("                  rowMin = matrix[i][k];"));
        codeLabels[2].add(new JLabel("              }"));
        codeLabels[2].add(new JLabel("              if(k != i && matrix[k][j] >= 0 && matrix[k][j] < colMin)"));
        codeLabels[2].add(new JLabel("              {"));
        codeLabels[2].add(new JLabel("                  colMin = matrix[k][j];"));
        codeLabels[2].add(new JLabel("              }"));
        codeLabels[2].add(new JLabel("          }"));
        codeLabels[2].add(new JLabel("          penalties.add(new int[]{i, j, rowMin + colMin});"));
        codeLabels[2].add(new JLabel("      }"));
        codeLabels[2].add(new JLabel("  }"));
        codeLabels[2].add(new JLabel("}"));
        // find highest penalty code
        codeLabels[3].add(new JLabel("int highestPenalty = 0;"));
        codeLabels[3].add(new JLabel("int highPenaltyX = 0;"));
        codeLabels[3].add(new JLabel("int highPenaltyY = 0;"));
        codeLabels[3].add(new JLabel("for(int[] penalty : penalties)"));
        codeLabels[3].add(new JLabel("{"));
        codeLabels[3].add(new JLabel("  if(penalty[2] > highestPenalty)"));
        codeLabels[3].add(new JLabel("  {"));
        codeLabels[3].add(new JLabel("      highPenaltyY = penalty[0];"));
        codeLabels[3].add(new JLabel("      highPenaltyX = penalty[1];"));
        codeLabels[3].add(new JLabel("      highestPenalty = penalty[2];"));
        codeLabels[3].add(new JLabel("  }"));
        codeLabels[3].add(new JLabel("}"));

        for(int i = 0; i < 4; i++)
        {
            for(JLabel label : codeLabels[i])
            {
                label.setOpaque(true);
                label.setBackground(Color.BLACK);
            }
        }

        setLayout(new BorderLayout());
        contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(Color.BLACK);
        scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBackground(new Color(50, 50, 50));
        add(scrollPane);
    }

    private void clearCode()
    {
        revalidate();
        repaint();
        removeAll();
        contentPanel.removeAll();
        scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBackground(new Color(50, 50, 50));
        add(scrollPane);
    }

    private void updateCode()
    {
        clearCode();
        int numOfLine = codeLabels[currentProcessIt].size();

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
            default:
                processTitle.setText("");
        }
        add(processTitle, BorderLayout.NORTH);

        // SETUP ROW HEADER VIEW
        JViewport rowHeaderView = new JViewport();
        JPanel lineNumberPanel = new JPanel(new GridBagLayout());
        lineNumberPanel.setPreferredSize(new Dimension(25, getHeight() - 20));
        lineNumberPanel.setBackground(new Color(50, 50, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        for(int i = 0; i < numOfLine; i++)
        {
            gbc.gridy = i;
            gbc.weightx = 1;
            gbc.gridx = 0;
            // line number
            JLabel label = new JLabel(String.valueOf(i + 1), SwingConstants.RIGHT);
            label.setPreferredSize(new Dimension(15,20));
            label.setForeground(Color.GRAY);
            lineNumberPanel.add(label, gbc);

            // create space between line number and line
            gbc.weightx = 0;
            JPanel space = new JPanel();
            space.setPreferredSize(new Dimension(10, 20));
            space.setBackground(Color.BLACK);
            contentPanel.add(space, gbc);

            // setup lines
            gbc.weightx = 1;
            gbc.gridx = 1;
            JLabel line = (JLabel)codeLabels[currentProcessIt].get(i);
            line.setPreferredSize(new Dimension(getWidth() - 100,20));
            line.setForeground(new Color(200, 200, 200));
            contentPanel.add(line, gbc);
        }
        rowHeaderView.add(lineNumberPanel);
        scrollPane.setRowHeader(rowHeaderView);
    }

    public void nextProcess()
    {
        lineIt = 0;
        if(currentProcessIt < 4)
        {
            currentProcessIt++;
            if(currentProcessIt == 4)
            {
                clearCode();
            }
            else
            {
                updateCode();
            }
        }
    }

    public void previousProcess()
    {
        if(currentProcessIt > 0)
        {
            currentProcessIt--;
        }
        lineIt = codeLabels[currentProcessIt].size() - 1;
        updateCode();
    }

    private void updateLine()
    {
        if(currentProcessIt < 4)
        {
            if(lineIt != 0)
            {
                codeLabels[currentProcessIt].get(lineIt - 1).setBackground(Color.BLACK);
            }
            if(lineIt != codeLabels[currentProcessIt].size() - 1)
            {
                codeLabels[currentProcessIt].get(lineIt + 1).setBackground(Color.BLACK);
            }
            codeLabels[currentProcessIt].get(lineIt).setBackground(new Color(100, 150, 200));
        }
    }

    public void nextCodeLine()
    {
        if(currentProcessIt < 4)
        {
            if(currentProcessIt < 0)
            {
                nextProcess();
            }
            if(lineIt < codeLabels[currentProcessIt].size() - 1)
            {
                lineIt++;
            }
            else
            {
                nextProcess();
            }
            updateLine();
        }
    }

    public void previousCodeLine()
    {
        if(lineIt > 0)
        {
            lineIt--;
            updateLine();
        }
        else if(currentProcessIt > 0)
        {
            previousProcess();
        }
    }
}
