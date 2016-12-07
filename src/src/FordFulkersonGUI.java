import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

/**
 * Created by patrickert on 12/4/16.
 */
public class FordFulkersonGUI {

    private FlowNetwork flowNetwork;
    private JPanel p;
    private JFrame frame;
    private final static int HEIGHT = 800;
    private final static int WIDTH = 600;

    public FordFulkersonGUI(){

        frame = new JFrame();
        frame.setVisible(true);
        frame.setTitle("Ford Fulkerson");
        frame.setSize(WIDTH, HEIGHT);

        initialize();
    }

    private void initialize(){

        //Initialize Panel, text field, button and set Layout
        p = new JPanel();
        p.setLayout(new GridLayout(3, 1));
        frame.add(p);
        JLabel sizeLabel = new JLabel("Vertices amount: ");
        JTextField size = new JTextField();
        JButton initialize = new JButton("Create Digraph");

        //Add components to Panel
        p.add(sizeLabel);
        p.add(size);
        p.add(initialize);

        //Button creates a new FlowNetwork with desired edges amount
        initialize.addActionListener((ActionEvent) -> {
            flowNetwork = new FlowNetwork(parseInt(size.getText()));
            addEdgeWindow();
        });

        p.setVisible(true);

        //Set all components visible
        Arrays.asList(p.getComponents()).forEach(c -> c.setVisible(true));

        frame.revalidate();
        frame.repaint();

    }

    private void addEdgeWindow() {

        //Reset Frame and create new Panel with GridLayout (8 rows, 1 col)
        frame.getContentPane().removeAll();
        p = new JPanel();
        frame.add(p);
        p.setLayout(new GridLayout(8, 1));

        //Create labels and text fields to add Weighted Vertice to the Graph
        JLabel originLabel = new JLabel("Origin Vertex");
        JTextField origin = new JTextField();
        JLabel destinationLabel = new JLabel("Destination Vertex");
        JTextField destination = new JTextField();
        JLabel flowLabel = new JLabel("Edge flow");
        JTextField flow =new JTextField();
        JButton addEdge = new JButton("Add Edge");
        JButton fordFulkerson = new JButton("Get Max Flow");


        //Button adds the desired Edge
        addEdge.addActionListener((ActionEvent) -> addEdge(parseInt(origin.getText()), parseInt(destination.getText()), parseInt(flow.getText())));

        //Buton takes user to Ford Fulkerson window
        fordFulkerson.addActionListener((ActionEvent) -> fordFulkerson());

        //Add components to panel
        p.add(originLabel);
        p.add(origin);
        p.add(destinationLabel);
        p.add(destination);
        p.add(flowLabel);
        p.add(flow);
        p.add(addEdge);
        p.add(fordFulkerson);

        //Set components' visibility
        Arrays.asList(p.getComponents()).forEach(c -> c.setVisible(true));

        //Paints new Panel into frame
        frame.revalidate();
        frame.repaint();


    }

    private void fordFulkerson() {

        //Reset Frame and create new Panel with GridLayout
        frame.getContentPane().removeAll();
        p = new JPanel();
        frame.add(p);
        p.setLayout(new GridLayout(5, 1));

        //Create labels and text fields
        JLabel originLabel = new JLabel("Origin Vertex");
        JTextField origin = new JTextField();
        JLabel destinationLabel = new JLabel("Destination Vertex");
        JTextField destination = new JTextField();
        JButton fordFulkerson = new JButton("Ford Fulkerson");

        //Button calculates max flow using Ford Fulkerson algorithm and takes to result window
        fordFulkerson.addActionListener((ActionEvent) -> calculate(parseInt(origin.getText()), parseInt(destination.getText())));

        p.add(originLabel);
        p.add(origin);
        p.add(destinationLabel);
        p.add(destination);
        p.add(fordFulkerson);

        Arrays.asList(p.getComponents()).forEach(c -> c.setVisible(true));

        frame.revalidate();
        frame.repaint();

    }

    private void calculate(int origin, int destination) {

        try {
            result(flowNetwork.fordFulkerson(origin, destination));
        }
        catch (IndexOutOfBoundsException e){
            fordFulkerson();
        }




    }

    private void result(int i) {
        //Reset Frame and create new Panel with GridBagLayout
        frame.getContentPane().removeAll();
        p = new JPanel();
        frame.add(p);
        p.setLayout(new GridBagLayout());

        JLabel result = new JLabel(Integer.toString(i));

        p.add(result);
        result.setVisible(true);

        frame.revalidate();
        frame.repaint();



    }

    private void addEdge(int origin, int destination, int weight) {
        try {
            flowNetwork.addEdge(origin, destination, weight);
            addEdgeWindow();
        }
        catch (UnsupportedOperationException e){
            addEdgeWindow();
        }
    }


}
