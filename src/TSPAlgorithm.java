import java.util.LinkedList;
import java.util.ArrayList;

public class TSPAlgorithm
{
    public static void main(String[] args)
    {
//        int[][] matrix =
//        {
//            {-1, 7, 6, 8, 4},
//            {7, -1, 8, 5, 6},
//            {6, 8, -1, 9, 7},
//            {8, 5, 9, -1, 8},
//            {4, 6, 7, 8, -1}
//        };

        int[][] matrix =
        {
                {-1, 20, 23, 27, 29, 34},
                {21, -1, 19, 26, 31, 24},
                {26, 28, -1, 15, 36, 26},
                {25, 16, 25, -1, 23, 18},
                {23, 40, 13, 31, -1, 10},
                {27, 18, 12, 35, 16, -1}
        };

        TSPAlgorithm test = new TSPAlgorithm(matrix);
        test.solve();

        int[] solution = test.getPath();
        for(int j : solution)
        {
            System.out.print(j + " ");
        }
        System.out.println("\nCost: " + test.getPathCost());
    }

    private int[][] adjMatrix; // the original matrix
    private LinkedList<StepMatrix> stepMatrices; // saves the matrix at each step
    private int[] path; // the solution
    private int pathCost;

    public TSPAlgorithm(int[][] adjMatrix)
    {
        this.adjMatrix = adjMatrix;
        stepMatrices = new LinkedList<>();
        path = new int[adjMatrix.length + 1];
        pathCost = 0;
    }

    public void solve()
    {
        // copy orig matrix
        int[][] currentMatrix = new int[adjMatrix.length][];
        for(int i = 0; i < adjMatrix.length; i++)
        {
            currentMatrix[i] = adjMatrix[i].clone();
        }
        
        // create linkedlist for rows and cols, this helps track nodes
        LinkedList<Integer> rows = new LinkedList<>();
        LinkedList<Integer> cols = new LinkedList<>();
        for(int i = 0; i < adjMatrix.length; i++)
        {
            rows.add(i);
            cols.add(i);
        }

        // find all path
        for(int currentMatrixNodeCount = currentMatrix.length; currentMatrix.length > 0; currentMatrixNodeCount = currentMatrix.length)
        {
            // row minimization
            int[] allRowMin = new int[currentMatrixNodeCount];
            for(int i = 0; i < currentMatrixNodeCount; i++)
            {
                int rowMin = Integer.MAX_VALUE;
                for(int j = 0; j < currentMatrixNodeCount; j++)
                {
                    if(currentMatrix[i][j] >= 0 && currentMatrix[i][j] < rowMin) // find row min
                    {
                        rowMin = currentMatrix[i][j];
                    }
                }
                if(rowMin != 0)
                {
                    for(int j = 0; j < currentMatrixNodeCount; j++) // minimize row
                    {
                        if(currentMatrix[i][j] >= 0)
                        {
                            currentMatrix[i][j] -= rowMin;
                        }
                    }
                }
                allRowMin[i] = rowMin;
            }

            // col minimization
            int[] allColMin = new int[currentMatrixNodeCount];
            for(int i = 0; i < currentMatrixNodeCount; i++)
            {
                int colMin = Integer.MAX_VALUE;
                for(int j = 0; j < currentMatrixNodeCount; j++)
                {
                    if(currentMatrix[j][i] >= 0 && currentMatrix[j][i] < colMin) // find col min
                    {
                        colMin = currentMatrix[j][i];
                    }
                }
                if(colMin != 0)
                {
                    for(int j = 0; j < currentMatrixNodeCount; j++) // minimize col
                    {
                        if(currentMatrix[j][i] >= 0)
                        {
                            currentMatrix[j][i] -= colMin;
                        }
                    }
                }
                allColMin[i] = colMin;
            }

            // penalty
            ArrayList<int[]> penalties = new ArrayList<>();
            for(int i = 0; i < currentMatrixNodeCount; i++)
            {
                for(int j = 0; j < currentMatrixNodeCount; j++)
                {
                    if(currentMatrix[i][j] == 0) // if the weight is 0, add row min and col min
                    {
                        int rowMin = Integer.MAX_VALUE;
                        int colMin = Integer.MAX_VALUE;
                        for(int k = 0; k < currentMatrixNodeCount; k++)
                        {
                            if(k != j && currentMatrix[i][k] >= 0 && currentMatrix[i][k] < rowMin)
                            {
                                rowMin = currentMatrix[i][k];
                            }
                            if(k != i && currentMatrix[k][j] >= 0 && currentMatrix[k][j] < colMin)
                            {
                                colMin = currentMatrix[k][j];
                            }
                        }
                        penalties.add(new int[]{i, j, rowMin + colMin});
                    }
                }
            }

            // find highest penalty
            int highestPenalty = 0;
            int highPenaltyX = 0;
            int highPenaltyY = 0;
            for(int[] penalty : penalties)
            {
                if(penalty[2] > highestPenalty)
                {
                    highPenaltyY = penalty[0];
                    highPenaltyX = penalty[1];
                    highestPenalty = penalty[2];
                }
            }

            // create new matrix without the row and col of highest penalty
            int[][] newMatrix = new int[currentMatrixNodeCount - 1][currentMatrixNodeCount - 1];
            int counter = 0;
            for(int y = 0; y < currentMatrixNodeCount; y++)
            {
                if(highPenaltyY == y)
                {
                    continue;
                }
                for(int x = 0; x < currentMatrixNodeCount; x++)
                {
                    if(highPenaltyX == x)
                    {
                        continue;
                    }

                    if(cols.get(highPenaltyX).equals(rows.get(y)) && rows.get(highPenaltyY).equals(cols.get(x)))
                    {
                        newMatrix[counter / newMatrix.length][counter % newMatrix.length] = -1;
                    }
                    else
                    {
                        newMatrix[counter / newMatrix.length][counter % newMatrix.length] = currentMatrix[y][x];
                    }
                    counter++;
                }
            }

            currentMatrix = newMatrix;
            int removedCol = cols.get(highPenaltyX);
            int removedRow = rows.get(highPenaltyY);
            cols.remove(highPenaltyX);
            rows.remove(highPenaltyY);
            stepMatrices.add(new StepMatrix(newMatrix, removedRow, removedCol, allRowMin, allColMin));
        }

        // create path
        int[][] coords = new int[stepMatrices.size()][2];
        for(int i = 0; i < stepMatrices.size(); i++)
        {
            coords[i][0] = stepMatrices.get(i).getRemovedRow();
            coords[i][1] = stepMatrices.get(i).getRemovedCol();
        }

        int iPath = 0;
        int node = 0;
        for(int i = 0; i < path.length;)
        {
            for(int j = 0; j < path.length; j++)
            {
                if(coords[j][0] == node)
                {
                    path[iPath++] = node;
                    node = coords[j][1];
                    i++;
                    break;
                }
            }
        }

        //compute path cost
        for(int[] coord : coords)
        {
            pathCost += adjMatrix[coord[0]][coord[1]];
        }
    }

    public int[][] getAdjMatrix()
    {
        return adjMatrix;
    }

    public LinkedList<StepMatrix> getStepMatrices()
    {
        return stepMatrices;
    }

    public int[] getPath()
    {
        return path;
    }

    public int getPathCost()
    {
        return pathCost;
    }
}
