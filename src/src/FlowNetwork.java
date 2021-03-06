import java.util.LinkedList;

/**
 * Created by patrickert on 12/3/16.
 */
public class FlowNetwork {
    private final int V;
    private int[][] matrix;
    private static int INFINITY = Integer.MAX_VALUE;

    public FlowNetwork(int v) {
        V = v;
        matrix = new int[V][V];

    }

    public void addEdge(int origin, int destination, int weight) throws UnsupportedOperationException{
        if (checkVertex(origin,destination)) throw new IllegalArgumentException("Vertex must be > 0 and < "+V);
        matrix[origin][destination] = weight;
    }

    private boolean checkVertex(int origin, int destination){
     return (origin>=V || origin<0 && destination<0 || destination>=V);
    }



    private boolean bfs(int rGraph[][], int s, int t, int parent[]) {
        // Create a visited array and mark all vertices as not visited
        boolean visited[] = new boolean[V];


        // Create a queue, enqueue source vertex and mark source vertex as visited
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        parent[s]=-1;

        // Standard BFS Loop
        while (queue.size()!=0) {
            int u = queue.poll();
            for (int v=0; v<V; v++) {
                if (!visited[v] && rGraph[u][v] > 0) {
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
    int fordFulkerson(int s, int t) throws IndexOutOfBoundsException {

        if(s<0 || s>=V || t<0 || t>=V) throw new IndexOutOfBoundsException();

        int u, v;

        // Create a residual graph and fill the residual graph
        // with given capacities in the original graph as
        // residual capacities in residual graph

        // Residual graph where rGraph[i][j] indicates
        // residual capacity of edge from i to j (if there
        // is an edge. If rGraph[i][j] is 0, then there is
        // not)
        int aux[][] = new int[V][V];
        for (u = 0; u < V; u++) {
            for (v = 0; v < V; v++) {
                aux[u][v] = matrix[u][v];
            }
        }


        // This array is filled by BFS and to store path
        int parent[] = new int[V];

        int maxFlow = 0;  // There is no flow initially

        // Augment the flow while there is path from source to sink
        while (bfs(aux, s, t, parent)) {
            /**
             * Find minimum residual capacity of the edges
             *along the path filled by BFS. Or we can say
             *find the maximum flow through the path found.
             */

            double path_flow = INFINITY;
            for (v=t; v!=s; v=parent[v]) {
                u = parent[v];
                path_flow = Math.min(path_flow, aux[u][v]);
            }

            /**
             * update residual capacities of the edges and reverse edges along the path.
             * First line:
             * This is necessary to for the BFS to check if there is a Path available between s and t.
             * When aux[u][v] is equal to zero, there is no path.
             * Second line:
             * This is the reverse flow which is equal to the residual capacity.
             */

            for (v=t; v != s; v=parent[v]) {
                u = parent[v];
                aux[u][v] -= path_flow;
                aux[v][u] += path_flow;
            }

            // Add path flow to overall flow
            maxFlow += path_flow;
        }

        // Return the overall flow
        return maxFlow;
    }



}
