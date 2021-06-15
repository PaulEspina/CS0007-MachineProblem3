import java.util.*;

public class TSPAlgorithm
{
    private final int[][] adjMatrix; // the original matrix
    private final StepMatrix initialStep;
    private final LinkedList<StepMatrix> stepMatrices; // saves the matrix at each step
    private final int[] path; // the solution
    private int pathCost;

    public TSPAlgorithm(int[][] adjMatrix)
    {
        this.adjMatrix = adjMatrix;
        stepMatrices = new LinkedList<>();
        initialStep = new StepMatrix(adjMatrix);
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
            int[] highest = new int[] {0, 0, Integer.MIN_VALUE};
            for(int[] penalty : penalties)
            {
                if(penalty[2] > highest[2])
                {
                    highest = penalty;
                }
            }

            // create new matrix without the row and col of highest penalty
            int[][] newMatrix = new int[currentMatrixNodeCount - 1][currentMatrixNodeCount - 1];
            int counter = 0;
            for(int y = 0; y < currentMatrixNodeCount; y++)
            {
                if(highest[0] == y)
                {
                    continue;
                }
                for(int x = 0; x < currentMatrixNodeCount; x++)
                {
                    if(highest[1] == x)
                    {
                        continue;
                    }

                    if(cols.get(highest[1]).equals(rows.get(y)) && rows.get(highest[0]).equals(cols.get(x)))
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

            int[] penaltyValues = new int[penalties.size()];
            for(int i = 0; i < penalties.size(); i++)
            {
                penaltyValues[i] = penalties.get(i)[2];
            }
            currentMatrix = newMatrix;
            int removedCol = cols.get(highest[1]);
            int removedRow = rows.get(highest[0]);
            cols.remove(highest[1]);
            rows.remove(highest[0]);
            stepMatrices.add(new StepMatrix(newMatrix, removedRow, removedCol, allRowMin, allColMin, rows.stream().mapToInt(Integer::intValue).toArray(), cols.stream().mapToInt(Integer::intValue).toArray(), penaltyValues, highest));
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

    public StepMatrix getInitialStep()
    {
        return initialStep;
    }

    public StepMatrix getStepMatrix(int i)
    {
        return stepMatrices.get(i);
    }

    // Getters

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
