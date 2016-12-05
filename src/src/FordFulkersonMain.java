/**
 * Created by patrickert on 12/4/16.
 */
public class FordFulkersonMain {

    public static void main(String[] args) {
        //FordFulkersonGUI ff = new FordFulkersonGUI();

        WeightedDigraph wd = new WeightedDigraph(3);
        wd.addEdge(0,1,5);
        wd.addEdge(1,2,3);
        wd.addEdge(0,2,1);

        System.out.println(wd.fordFulkerson(0,2));
    }
}
