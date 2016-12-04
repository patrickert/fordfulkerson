import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by tomifor on 23/11/16.
 */
public class FordFulkerson {


    private boolean[] marked;
    private FlowEdge[] edgeTo;
    private double value;
    // Is s->v path in residual graph?
    // last edge on shortest s->v path
    // current value of maxflow

    public FordFulkerson(FlowNetwork G, int s, int t) {  // Find maxflow in flow network G from s to t.
        while (hasAugmentingPath(G, s, t)) {  // While there exists an augmenting path, use it.
            // Compute bottleneck capacity.
            double bottle = Double.POSITIVE_INFINITY;
            for (int v = t; v != s; v = edgeTo[v].other(v))
                bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));
            // Augment flow.
            for (int v = t; v != s; v = edgeTo[v].other(v))
                edgeTo[v].addResidualFlowTo(v, bottle);
            value += bottle;
        }
    }

    public double value() {
        return value;
    }

    public boolean inCut(int v) {
        return marked[v];
    }


    private boolean hasAugmentingPath(FlowNetwork G, int s, int t) {
        marked = new boolean[G.numberOfV()];  // Is path to this vertex known?
        edgeTo = new FlowEdge[G.numberOfV()]; // last edge on path
        Queue<Integer> q = new PriorityQueue<>();
        marked[s] = true;             // Mark the source
        q.offer(s);                 //   and put it on the queue.
        while (!q.isEmpty()) {
            int v = q.poll();
            for (FlowEdge e : G.adj(v)) {
                int w = e.other(v);
                if (e.residualCapacityTo(w) > 0 && !marked[w]) {  // For every edge to an unmarked vertex (in residual)
                    edgeTo[w] = e;
                    marked[w] = true;
                    q.offer(w);
                    return marked[t];
                }
            }
        }
        return marked[t];
    }
}


