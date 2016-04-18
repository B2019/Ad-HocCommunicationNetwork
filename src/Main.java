/* TODO
 * Build GUI
 */

import java.util.ArrayList;

/**
 * Main class that creates some arbitrary vertices and edges and adds them to an Ad-Hoc network
 *
 * @author Francis Poole
 * @version 1.0, 18/4/13
 *
 */
public class Main {
    /**
     * Builds an Ad-Hoc network with arbitrary verticies and edges
     *
     * @param args
     */
    public static void main(String[] args) {
        Graph network;
        ArrayList<Vertex> vertexList = new ArrayList<Vertex>();
        ArrayList<Edge> edgeList = new ArrayList<Edge>();

        PC PC_A = new PC("A");
        PC PC_B = new PC("B");
        PC PC_C = new PC("C");
        Mobile Mob_A = new Mobile("A");
        Channel Chan_1 = new Channel(PC_B, PC_A, "1");
        Channel Chan_2 = new Channel(PC_B, PC_C, "2");
        Channel Chan_3 = new Channel(PC_B, Mob_A, "3");

        vertexList.add(PC_A);
        vertexList.add(PC_B);
        vertexList.add(PC_C);
        vertexList.add(Mob_A);
        edgeList.add(Chan_1);
        edgeList.add(Chan_2);
        edgeList.add(Chan_3);

        network = new Graph(vertexList, edgeList);
        network.printNetwork();
    }

}
