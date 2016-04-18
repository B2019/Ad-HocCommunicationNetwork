import java.util.ArrayList;

/**
 * This class allows for the creation and alteration of an Ad-Hoc Network graph. The graph is made up of {@link Vertex} and
 * {@link Edge} objects, both containing data about themselves. The graph takes the form of an adjacency matrix structure which
 * allows for the graph to be navigated quickly as well as for vertices to be connected easily.
 *
 * @author Francis Poole
 * @version 1.0, 18/4/13
 */
public class Graph {
    private ArrayList<Vertex> vertexList;
    private ArrayList<Edge> edgeList;
    private AdjacencyMatrix matrix;

    /**
     * Creates an empty Graph.
     */
    public Graph() {
    }

    /**
     * Creates a Graph based on lists of {@link Vertex} and {@link Edge} objects.
     *
     * @param vertexList	ArrayList of Vertex objects to add to the graph
     * @param edgeList		ArrayList of Edge objects to add to the graph
     */
    public Graph(ArrayList<Vertex> vertexList, ArrayList<Edge> edgeList) {
        this.vertexList = vertexList;
        this.edgeList = edgeList;

        for (int i = 0; i < vertexList.size(); i++) {
            vertexList.get(i).setIndex(i);
        }

        initMatrix();
    }

    /**
     * Initializes the {@link AdjacencyMatrix} that shows which {@link Vertex} connects to which {@link Edge}
     */
    private void initMatrix() {
        this.matrix = new AdjacencyMatrix(vertexList.size());
        for (Edge edge : edgeList) {
            matrix.addEdge(edge.getV().getIndex(), edge.getW().getIndex(), edge);
        }
    }

    /**
     * Creates and inserts an {@link Edge} into the graph with specified {@link Vertex} endpoints as well as a specified type and name.
     *
     * @param v		Vertex v
     * @param w		Vertex w
     * @param type	String naming the type of Edge to create
     * @param name	String of numbers naming the Edge
     */
    public void insertEdge(Vertex v, Vertex w, String type, String name) {
        Edge edge;

        if (type.equals("Channel")) {
            edge = new Channel(v, w, name);
        } else {
            System.out
                    .println("Error: Edge type '" + type + "' not recognised");
            return;
        }
        edgeList.add(edge);
        matrix.addEdge(v.getIndex(), w.getIndex(), edge);
    }

    /**
     * Removes an {@link Edge} from the graph
     *
     * @param e		Edge to remove from graph
     */
    public void removeEdge(Edge e) {
        edgeList.remove(e);
        matrix.removeEdge(e.getV().getIndex(), e.getW().getIndex());
    }

    /**
     * Creates and inserts an {@link Vertex} into the graph with a specified type and name. The {@link AdjacencyMatrix} is then re-initialized
     * so incorporate the new Vertex.
     *
     * @param type	String naming the type of Vertex to create
     * @param name	String of numbers naming the Vertex
     */
    public void insertVertex(String type, String name) {
        Vertex vertex;

        if (type.equals("PC")) {
            vertex = new PC(vertexList.size(), name);
        } else if (type.equals("Mobile")) {
            vertex = new Mobile(vertexList.size(), name);
        } else {
            System.out.println("Error: Vertex type '" + type
                    + "' not recognised");
            return;
        }

        vertexList.add(vertex);
        initMatrix();
    }

    /**
     * Removes a {@link Vertex} from the graph. The {@link AdjacencyMatrix} is then re-initialized to remove the removed Vertex.
     *
     * @param v		Vertex to be removed
     */
    public void removeVertex(Vertex v) {
        int index = v.getIndex();
        vertexList.remove(v);

        for (int i = 0; i < matrix.size(); i++) {
            edgeList.remove(matrix.getEdge(index, i));
            vertexList.get(i).setIndex(i);
        }

        initMatrix();
    }

    /**
     * Uses {@link AdjacenecyMatrix} to check if {@link Vertex} inputs are adjacent, returns result as a boolean.
     *
     * @param v		Vertex v
     * @param w		Vertex w
     * @return		boolean regarding whether the Vertices are adjacent or not
     */
    public boolean areAdjacent(Vertex v, Vertex w) {
        return matrix.getEdge(v.getIndex(), w.getIndex()) != null;
    }

    /**
     * Gets a list of all incident {@link Edge} objects to a {@link vertex} and returns them.
     *
     * @param v		Vertex to check
     * @return		ArrayList of Edge objects
     */
    public ArrayList<Edge> incidentEdges(Vertex v) {
        ArrayList<Edge> incidentEdges = new ArrayList<Edge>();

        for (int wIndex = 0; wIndex < vertexList.size(); wIndex++) {
            if (matrix.getEdge(v.getIndex(), wIndex) != null) {
                incidentEdges.add(matrix.getEdge(v.getIndex(), wIndex));
            }
        }

        return incidentEdges;
    }

    /**
     * Renames a {@link Vertex}.
     *
     * @param v		Vertex to rename
     * @param name	String to use as name
     */
    public void rename(Vertex v, String name) {
        v.setName(name);
    }

    /**
     * Renames an {@link Edge}.
     *
     * @param e		Edge to rename
     * @param name	String to use as name
     */
    public void rename(Edge e, String name) {
        e.setName(name);
    }

    /**
     * Uses <code>#searchAllVertices()</code> to search all vertices and print them out when they are found.
     */
    public void printNetwork() {
        searchAllVertices(true);
        searchReset();
    }

    /**
     * Checks if there is a cycle in the graph and returns a boolean representing the answer.
     *
     * @return	boolean representing if there is a cycle in the graph
     */
    public boolean reportCycle() {
        searchAllVertices(false);
        for (Edge e : edgeList) {
            if (e.getCrossEdge()) {
                searchReset();
                return true;
            }
        }
        searchReset();
        return false;
    }

    /**
     * Gets a spanning tree from the Graph starting at a {@link Vertex}.
     *
     * @param start	Vertex to start spanning tree from
     * @return		Graph of spanning tree objects
     */
    public Graph spanningTree(Vertex start) {
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        ArrayList<Edge> edges = new ArrayList<Edge>();

        searchBreadthFirst(start, null, false);
        for (Vertex v : vertexList) {
            if (v.getVisited()) {
                vertices.add(v);
                v.setVisited(false);
            }
        }
        for (Edge e : edgeList) {
            if (e.getVisited()) {
                edges.add(e);
                e.setVisited(false);
            } else {
                e.setCrossEdge(false);
            }
        }
        return new Graph(vertices, edges);

    }

    /**
     * Gets the shortest path between a <code>start</code> {@link Vertex} and a <code>finish</code> Vertex.
     *
     * @param start     Vertex to start path at
     * @param finish	Vertex to finish path at
     * @return		    Graph containing path objects
     */
    public Graph path(Vertex start, Vertex finish) {
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        ArrayList<Edge> edges = new ArrayList<Edge>();
        searchBreadthFirst(finish, start, false);

        if (!start.getVisited()) {
            return null;
        }

        for (Vertex child = start; child != finish; child = child.getParent()) {
            vertices.add(child);
            edges.add(matrix.getEdge(child.getIndex(), child.getParent().getIndex()));
        }
        vertices.add(finish);

        System.out.println(vertices);
        System.out.println(edges);
        Graph path = new Graph(vertices, edges);
        return path;
    }

    /**
     * Gets and returns the {@link Vertex} with the required <code>type</code> and <code>name</code>
     *
     * @param type	String of the type of Vertex
     * @param name	String name of the Vertex
     * @return		Vertex with the specified type and name
     */
    public Vertex getVertex(String type, String name) {
        for (Vertex v : vertexList) {
            if (v.getType().equals(type) && v.getName().equals(name)) {
                return v;
            }
        }
        System.out.println("Error: " + type + "-" + name
                + " could not be found");
        return null;
    }

    /**
     * Gets and returns the {@link Edge} with the required <code>type</code> and <code>name</code>
     *
     * @param type	String of the type of Edge
     * @param name	String name of the Edge
     * @return		Vertex with the specified type and name
     */
    public Edge getEdge(String type, String name) {

        for (Edge e : edgeList) {
            if (e.getType().equals(type) && e.getName().equals(name)) {
                return e;
            }
        }
        System.out.println("Error: " + type + "-" + name
                + " could not be found");
        return null;
    }

    /**
     * Searches through all vertices using a breadth first search and making sure each {@link Vertex}
     * has been visited, printing out all visited objects depending on <code>print</code> state.
     *
     * @param print	boolean stating whether or not to print objects that are visited in search
     */
    private void searchAllVertices(boolean print) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (!vertexList.get(i).getVisited()) {
                searchBreadthFirst(vertexList.get(i), null, print);
            }
        }
    }

    /**
     * Do a breadth search of the graph from <code>start</code> to <code>finish</code>, printing out
     * all visited objects depending on <code>print</code> state. Objects are marked as visited once
     * the search visits them.
     *
     * @param start	    Vertex to start search from
     * @param finish	Vertex to finish search at
     * @param print	    boolean stating whether or not to print objects that are visited in search
     */
    private void searchBreadthFirst(Vertex start, Vertex finish, boolean print) {
        ArrayList<ArrayList<Vertex>> level = new ArrayList<ArrayList<Vertex>>();
        level.add(new ArrayList<Vertex>());

        level.get(0).add(start);
        start.setVisited(true);
        if (print) {
            System.out.println("Start: " + start);
        }
        int i = 0;

        while (level.get(i).size() != 0) {

            for (Vertex v : level.get(i)) {
                level.add(new ArrayList<Vertex>());
                for (Edge e : incidentEdges(v)) {
                    if (!e.getVisited()) {
                        if (!e.getV().getVisited()) {
                            e.setVisited(true);
                            e.getV().setVisited(true);
                            e.getV().setParent(e.getW());
                            level.get(i + 1).add(e.getV());
                            if (print) {
                                System.out.println("Visit: " + e);
                            }
                            if (print) {
                                System.out.println("Visit: " + e.getV());
                            }
                        } else if (!e.getW().getVisited()) {
                            e.setVisited(true);
                            e.getW().setVisited(true);
                            e.getW().setParent(e.getV());
                            level.get(i + 1).add(e.getW());
                            if (print) {
                                System.out.println("Visit: " + e);
                            }
                            if (print) {
                                System.out.println("Visit: " + e.getW());
                            }
                        } else {
                            e.setCrossEdge(true);
                            if (print) {
                                System.out.println("Cross Edge: " + e);
                            }
                        }
                        if (finish != null && (e.getV() == finish || e.getW() == finish)) {
                            return;
                        }
                    } else {
                        if (print) {
                            System.out.println("Visited: " + e);
                        }
                    }
                }
            }
            i += 1;
        }
    }

    /**
     * Sets all objects to not visited.
     */
    private void searchReset() {
        for (Vertex v : vertexList) {
            v.setVisited(false);
        }
        for (Edge e : edgeList) {
            e.setVisited(false);
            e.setCrossEdge(false);
        }
    }
}
