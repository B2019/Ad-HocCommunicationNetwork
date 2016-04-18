/**
 * Subclass of {@link Vertex} that states type of Vertex.
 *
 * @author Francis Poole
 * @version 1.0, 18/4/13
 *
 */
public class PC extends Vertex {

    /**
     * Creates a PC {@link Vertex}.
     *
     * @param name	String name of Vertex
     */
    public PC(String name) {
        super(name);
    }

    /**
     * Creates a Mobile {@link Vertex} with a set index.
     *
     * @param index	index of Vertex
     * @param name	String name of Vertex
     */
    public PC(int index, String name) {
        super(index, name);
    }

    /**
     * Gets type of {@list Vertex} and returns it.
     *
     * @return	String representing type of Vertex
     */
    public String getType() {
        return "PC";
    }
}