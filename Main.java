package com.company;

public class Main {


    public static void main(String[] args) {
        // create flow network with V vertices and E edges
        int vertex = 5;
        int edge = 5;
        int s = 0, t = vertex-1;
        System.out.println("HOLA");
        FlowNetwork flowNetwork = new FlowNetwork(vertex,edge);
        //System.out.println(flowNetwork);

        // compute maximum flow and minimum cut
        FordFulkerson maxflow = new FordFulkerson(flowNetwork, s, t);


        System.out.println("Max flow from " + s + " to " + t);
        for (int i = 0; i < flowNetwork.numberOfV(); i++) {
            for (FlowEdge e : flowNetwork.adj(i)) {
                if ((i == e.from()) && e.flow() > 0)
                    System.out.println("   " + e);
            }
        }

        // print min-cut
        System.out.println("Min cut: ");
        for (int v = 0; v < flowNetwork.numberOfV(); v++) {
            if (maxflow.inCut(v)) System.out.println(v + " ");
        }
        System.out.println("Max flow value = " +  maxflow.value());
    }
}
