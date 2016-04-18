/**
 * Allows for the creation and alteration of a Vertex object.
 *
 * @author Francis Poole
 * @version 1.0, 18/4/13
 *
 */
public abstract class Vertex {
    private int index;
    private String name;
    private boolean visited = false;
    private Vertex parent;

    /**
     * Creates a {@link Vertex}
     *
     * @param name		String name of edge
     */
    public Vertex(String name) {
        this.index = 0;
        this.name = name;
    }

    /**
     * Creates a Vertex with a set index.
     *
     * @param index	index of Vertex
     * @param name	String name of Vertex
     */
    public Vertex(int index, String name) {
        this.index = index;
        this.name = name;
    }

    /**
     *
     * Gets and returns Vertex index
     *
     * @return	index of Vertex
     */
    public int getIndex() {
        return index;
    }

    /**
     * Abstract method to get Vertex type
     *
     * @return	String representation of Vertex type
     */
    public abstract String getType();

    /**
     * Get and return Vertex name
     *
     * @return	String name of Vertex
     */
    public String getName() {
        return name;
    }

    /**
     * Get and return if Vertex has been visited
     *
     * @return	boolean representing if Vertex has been visited
     */
    public boolean getVisited() {
        return visited;
    }

    /**
     * Get and return Vertex parent
     *
     * @return	Vertex parent of Vertex
     */
    public Vertex getParent() {
        return parent;
    }

    /**
     * Set <code>index</code> to input
     *
     * @param index	    index to set <code>index</code> to
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Set <code>name</code> to input String
     *
     * @param name	String to set <code>name</code> to
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set <code>visited</code> to input boolean
     *
     * @param visited	boolean to set <code>visited</code> to
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * Set <code>parent</code> to {@link Vertex}
     *
     * @param parent	Vertex to set <code>parent</code> to
     */
    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    /**
     * Returns full name using <code>type</code> and <code>name</code>
     *
     * @return	String full name
     */
    public String toString() {
        return getType() + "-" + name;
    }
}