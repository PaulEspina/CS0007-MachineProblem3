public class StepMatrix
{
    private int[][] matrix; // the reduced matrix at this step
    private int[] rowLabel;
    private int[] colLabel;
    private int[] rowMin; // the minimum of each row before reducing the matrix
    private int[] colMin; // the minimum of each col before reducing the matrix
    private int[] penalties;
    private int[] highestPenalty;
    private int nodeCount; // the remaining nodes at this step
    private int removedRow; // the removed row at this step
    private int removedCol; // the removed col at this step

    public StepMatrix(int[][] matrix)
    {
        this();
        this.matrix = matrix;
    }

    public StepMatrix()
    {
        matrix = null;
        rowLabel = null;
        colLabel = null;
        rowMin = null;
        colMin = null;
        penalties = null;
        highestPenalty = null;
        nodeCount = -1;
        removedRow = -1;
        removedCol = -1;
    }
    public StepMatrix(int[][] matrix, int removedRow, int removedCol, int[]rowMin, int[] colMin, int[]rowLabel, int[]colLabel, int[] penalties, int[] highestPenalty)
    {
        this();
        this.matrix = new int[matrix.length][matrix.length];
        for(int i = 0; i < matrix.length; i++)
        {
            this.matrix[i] = matrix[i].clone();
        }
        this.removedRow = removedRow;
        this.removedCol = removedCol;
        this.rowMin = rowMin;
        this.colMin = colMin;
        this.rowLabel = rowLabel;
        this.colLabel = colLabel;
        this.penalties = penalties;
        this.highestPenalty = highestPenalty;
        nodeCount = matrix.length;
    }

    // Getters

    public int[][] getMatrix()
    {
        return matrix;
    }

    public int getNodeCount()
    {
        return nodeCount;
    }

    public int getRemovedRow()
    {
        return removedRow;
    }

    public int getRemovedCol()
    {
        return removedCol;
    }

    public int[] getRowMin()
    {
        return rowMin;
    }

    public int[] getColMin()
    {
        return colMin;
    }

    public int[] getRowLabel()
    {
        return rowLabel;
    }

    public int[] getColLabel()
    {
        return colLabel;
    }

    public int[] getPenalties()
    {
        return penalties;
    }

    public int[] getHighestPenalty()
    {
        return highestPenalty;
    }
}
