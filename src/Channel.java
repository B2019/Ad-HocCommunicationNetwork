/**
 * Subclass of {@link Edge} that states type of Edge. Currently not fully used
 * as no other edge type currently exist or have any special information associated with them.
 *
 * @author Francis Poole
 * @version 1.0, 18/4/13
 *
 */
public class Channel extends Edge {

    /**
     * Creates a Channel edge.
     *
     * @param v		{@link Vertex} v to join to edge
     * @param w		Vertex w to join to edge
     * @param name	String name of edge
     */
    public Channel(Vertex v, Vertex w, String name) {
        super(v, w, name);
    }

    /**
     * Gets type of {@list Edge} and returns it.
     *
     * @return	String representing type of Edge
     */
    public String getType() {
        return "Channel";
    }


}
