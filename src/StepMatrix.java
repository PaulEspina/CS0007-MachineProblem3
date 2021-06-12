public class StepMatrix
{
    private int[][] matrix; // the reduced matrix at this step
    private int[] rowMin; // the minimum of each row before reducing the matrix
    private int[] colMin; // the minimum of each col before reducing the matrix
    private int nodeCount; // the remaining nodes at this step
    private int removedRow; // the removed row at this step
    private int removedCol; // the removed col at this step

    public StepMatrix(int[][] matrix, int removedRow, int removedCol, int[]rowMin, int[] colMin)
    {
        this.matrix = matrix;
        this.removedRow = removedRow;
        this.removedCol = removedCol;
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
}
