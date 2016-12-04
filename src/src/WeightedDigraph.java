import java.util.LinkedList;

/**
 * Created by patrickert on 12/3/16.
 */
public class WeightedDigraph {
    private final int V;
    private double[][] matrix;
    private static double INFINITY = Double.MAX_VALUE;

    public WeightedDigraph(int v) {
        V = v;
        initialize();

    }

    public void addEdge(int origin, int destination, double weight){
        if (matrix[origin][destination] != INFINITY) throw new UnsupportedOperationException("Flow already defined");
        else matrix[origin][destination] = weight;

    }

    public double getWeightAt(int origin, int destination){
        return matrix[origin][destination];
    }

    public int getV() {
        return V;
    }

    private void initialize(){
        matrix = new double[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                matrix[i][j] = INFINITY;
            }
        }
    }

    boolean bfs(double rGraph[][],
                int s, int t, int parent[]) {
        // Create a visited array and mark all vertices as not
        // visited
        boolean visited[] = new boolean[V];
        for(int i=0; i<V; ++i)
            visited[i]=false;

        // Create a queue, enqueue source vertex and mark
        // source vertex as visited
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s]=-1;

        // Standard BFS Loop
        while (queue.size()!=0)
        {
            int u = queue.poll();

            for (int v=0; v<V; v++)
            {
                if (!visited[v] && rGraph[u][v] != INFINITY)
                {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        // If we reached sink in BFS starting from source, then
        // return true, else false
        return (visited[t]);
    }

    // Returns tne maximum flow from s to t in the given graph
    int fordFulkerson(
            //int graph[][],
            int s, int t)
    {
        int u, v;

        // Create a residual graph and fill the residual graph
        // with given capacities in the original graph as
        // residual capacities in residual graph

        // Residual graph where rGraph[i][j] indicates
        // residual capacity of edge from i to j (if there
        // is an edge. If rGraph[i][j] is 0, then there is
        // not)
        double aux[][] = new double[V][V];

        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                aux[u][v] = matrix[u][v];

        // This array is filled by BFS and to store path
        int parent[] = new int[V];

        int max_flow = 0;  // There is no flow initially

        // Augment the flow while tere is path from source
        // to sink
        while (bfs(aux, s, t, parent))
        {
            // Find minimum residual capacity of the edhes
            // along the path filled by BFS. Or we can say
            // find the maximum flow through the path found.
            double path_flow = INFINITY;
            for (v=t; v!=s; v=parent[v])
            {
                u = parent[v];
                path_flow = Math.min(path_flow, aux[u][v]);
            }

            // update residual capacities of the edges and
            // reverse edges along the path
            for (v=t; v != s; v=parent[v])
            {
                u = parent[v];
                aux[u][v] -= path_flow;
                aux[v][u] += path_flow;
            }

            // Add path flow to overall flow
            max_flow += path_flow;
        }

        // Return the overall flow
        return max_flow;
    }

}
