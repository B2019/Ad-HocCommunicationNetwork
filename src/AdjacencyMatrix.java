/**
 * This class allows for the creation and alteration of an adjacency matrix.
 *
 * @author Francis Poole
 * @version 1.0, 18/4/13
 */
public class AdjacencyMatrix {
    private Edge[][] matrix;
    private int matrixSize;

    /**
     * Creates a new matrix with size <code>matrixSize</code>.
     *
     * @param matrixSize	size of the matrix
     */
    public AdjacencyMatrix(int matrixSize) {
        matrix = new Edge[matrixSize][matrixSize];
        this.matrixSize = matrixSize;
    }

    /**
     * Adds an edge to the matrix.
     *
     * @param vIndex	the index of {@link Vertex} v
     * @param wIndex    the index of Vertex w
     * @param e		    {@link Edge} to add
     */
    public void addEdge(int vIndex, int wIndex, Edge e) {
        if (matrix[vIndex][wIndex] == null) {
            matrix[vIndex][wIndex] = e;
            matrix[wIndex][vIndex] = e;
        } else {
            System.out.println("Error: Edge already exists at adjacency matrix position " + vIndex + ", " + wIndex);
            System.out.println("Please remove edge " + getEdge(vIndex, wIndex).getName() + " from this position before trying again.");
        }
    }

    /**
     * Removes an {@link Edge} from the matrix
     *
     * @param vIndex	the index of {@link Vertex} v
     * @param wIndex	the index of Vertex w
     */
    public void removeEdge(int vIndex, int wIndex) {
        if (matrix[vIndex][wIndex] != null) {
            matrix[vIndex][wIndex] = null;
        } else {
            System.out.println("Error: No edge exists at adjacency matrix position " + vIndex + ", " + wIndex);
        }
    }

    /**
     * Gets edge at specified position and returns it
     *
     * @param vIndex	the index of {@link Vertex} v
     * @param wIndex	the index of Vertex w
     * @return		    {@link Edge} at specified position
     */
    public Edge getEdge(int vIndex, int wIndex) {
        return matrix[vIndex][wIndex];
    }

    /**
     * Gets matrix size and returns it.
     *
     * @return	the matrix size
     */
    public int size() {
        return matrixSize;

    }

}
