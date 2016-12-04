/**
 * Created by patrickert on 12/3/16.
 */
public class Main {
    public static void main(String[] args) {
        WeightedDigraph wdg = new WeightedDigraph(6);
        wdg.addEdge(0, 1, 16);
        wdg.addEdge(0, 2, 13);
        wdg.addEdge(1, 2, 10);
        wdg.addEdge(1, 3, 12);
        wdg.addEdge(2, 1, 4);
        wdg.addEdge(2, 4, 14);
        wdg.addEdge(3, 2, 9);
        wdg.addEdge(3, 5, 20);
        wdg.addEdge(4, 3, 7);
        wdg.addEdge(4, 5, 4);

        System.out.println("Max flow = " + wdg.fordFulkerson(0, 5));
    }
}
