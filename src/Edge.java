/**
 * Allows for the creation and alteration of an Edge object. The object contains information
 * about the {@link Vertex} objects that it connects.
 *
 * @author Francis Poole
 * @version 1.0, 18/4/13
 *
 */
public abstract class Edge {
    private Vertex v;
    private Vertex w;
    private String name;
    public boolean visited = false;
    public boolean crossEdge = false;

    /**
     * Creates an {@link Edge}
     *
     * @param v		{@link Vertex} v to join to edge
     * @param w		Vertex w to join to edge
     * @param name		String name of edge
     */
    public Edge(Vertex v, Vertex w, String name) {
        this.v = v;
        this.w = w;
        this.name = name;
    }

    /**
     * Get and return {@link Vertex} v
     *
     * @return 	Vertex v
     */
    public Vertex getV() {
        return v;
    }

    /**
     * Get and return {@link Vertex} w
     *
     * @return 	Vertex w
     */
    public Vertex getW() {
        return w;
    }

    /**
     * Abstract method to get Edge type
     *
     * @return	String representation of Edge type
     */
    public abstract String getType();

    /**
     * Get and return Edge name
     *
     * @return	String name of Edge
     */
    public String getName() {
        return name;
    }

    /**
     * Get and return if Edge has been visited
     *
     * @return	boolean representing if Edge has been visited
     */
    public boolean getVisited() {
        return visited;
    }

    /**
     * Get and return if Edge has been marked as a cross edge
     *
     * @return	boolean representing if Edge is a cross edge
     */
    public boolean getCrossEdge() {
        return crossEdge;
    }

    /**
     * Set <code>v</code> to input {@link Vertex}
     *
     * @param v	Vertex to set <code>v</code> to
     */
    public void setV(Vertex v) {
        this.v = v;
    }

    /**
     * Set <code>w</code> to input {@link Vertex}
     *
     * @param w	Vertex to set <code>w</code> to
     */
    public void setW(Vertex w) {
        this.w = w;
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
     * Set <code>crossEdge</code> to input boolean
     *
     * @param crossEdge	boolean to set <code>crossEdge</code> to
     */
    public void setCrossEdge(boolean crossEdge) {
        this.crossEdge = crossEdge;
    }

    /**
     * Returns full name using <code>type</code> and <code>name</code>
     *
     * @return	String full name
     */
    public String toString() {
        return getType() + "-" + name + ": (" + getV() + ", " + getW() + ")";
    }


}

