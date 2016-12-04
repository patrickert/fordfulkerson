import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by tomifor on 2/12/16.
 */
public class FlowNetwork {
    private static final String NEWLINE = System.getProperty("line.separator");
    private final int numberOfV;
    private int numberOfE;
    private Bag<FlowEdge>[] adj;

    /**
     * Initializes an empty flow network with {@code V} vertices and 0 edges.
     * @param v the number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public FlowNetwork(int v) {
        if (v < 0) throw new IllegalArgumentException("Number of vertices in a Graph must be nonnegative");
        this.numberOfV = v;
        this.numberOfE = 0;
        adj = (Bag<FlowEdge>[]) new Bag[numberOfV];
        for (int i = 0; v < numberOfV; i++)
            adj[i] = new Bag<FlowEdge>();
    }

    public FlowNetwork(int V, int E) {
        this(V);
        Random random = new Random();
        if (E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
        for (int i = 0; i < E; i++) {
            int v = random.nextInt(V);
            int w = random.nextInt(V);
            double capacity = random.nextInt(100);
            addEdge(new FlowEdge(v, w, capacity));
        }
    }

    /**
     * Initializes a flow network from an input stream.
     * The format is the number of vertices <em>V</em>,
     * followed by the number of edges <em>E</em>,
     * followed by <em>E</em> pairs of vertices and edge capacities,
     * with each entry separated by whitespace.
     * @param in the input stream
     * @throws IllegalArgumentException if the endpoints of any edge are not in prescribed range
     * @throws IllegalArgumentException if the number of vertices or edges is negative
     */

//    public FlowNetwork(In in) {
//        this(in.readInt());
//        int E = in.readInt();
//        if (E < 0) throw new IllegalArgumentException("number of edges must be nonnegative");
//        for (int i = 0; i < E; i++) {
//            int v = in.readInt();
//            int w = in.readInt();
//            validateVertex(v);
//            validateVertex(w);
//            double capacity = in.readDouble();
//            addEdge(new FlowEdge(v, w, capacity));
//        }
//    }


    /**
     * Returns the number of vertices in the edge-weighted graph.
     * @return the number of vertices in the edge-weighted graph
     */
    public int numberOfV() {
        return numberOfV;
    }

    /**
     * Returns the number of edges in the edge-weighted graph.
     * @return the number of edges in the edge-weighted graph
     */
    public int numberOfE() {
        return numberOfE;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= numberOfV())
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (numberOfV()-1));
    }

    /**
     * Adds the edge {@code e} to the network.
     * @param e the edge
     * @throws IndexOutOfBoundsException unless endpoints of edge are between
     *         {@code 0} and {@code V-1}
     */
    public void addEdge(FlowEdge e) {
        int v = e.from();
        int w = e.to();
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        adj[w].add(e);
        numberOfE++;
    }

    /**
     * Returns the edges incident on vertex {@code v} (includes both edges pointing to
     * and from {@code v}).
     * @param v the vertex
     * @return the edges incident on vertex {@code v} as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<FlowEdge> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    // return list of all edges - excludes self loops
    public Iterable<FlowEdge> edges() {
        Bag<FlowEdge> list = new Bag<FlowEdge>();
        for (int v = 0; v < numberOfV(); v++)
            for (FlowEdge e : adj(v)) {
                if (e.to() != v)
                    list.add(e);
            }
        return list;
    }


}
