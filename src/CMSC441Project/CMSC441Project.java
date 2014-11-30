/* Author: Bryan Stetson and Raymond Chan
 * Date: 11/13/14
 * Class: CMSC 441 Algorithms 
 * Graph Algorithm Comparison Project 
 */
package CMSC441Project;

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.*;

import org.jgraph.*;
import org.jgraph.graph.*;

import org.jgrapht.*;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.ext.*;
import org.jgrapht.generate.CompleteGraphGenerator;
import org.jgrapht.generate.SimpleWeightedGraphMatrixGenerator;
import org.jgrapht.graph.*;

// resolve ambiguity
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.DepthFirstIterator;

public class CMSC441Project extends JApplet {

    static Graph<Object, DefaultEdge> completeGraph;
    static WeightedMultigraph<Object, DefaultEdge> WeightedGraph1;
    static int size = 10; //Number of vertices
    static int weight_upper_bound = 20;
    static int weight_lower_bound = 1;
    private static final Color DEFAULT_BG_COLOR = Color.decode("#FAFBFF");
    private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);
    //Adapter used to branch jGraph
    private JGraphModelAdapter<String, org.jgrapht.graph.DefaultEdge> jgAdapter;
    
    
    
    static SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph;

    /**
     * An alternative starting point for this demo, to also allow running this applet as an application.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {
        //Sizes of v to test algorithms on 
        ArrayList<Integer> graph_size_array = new ArrayList<>();
        graph_size_array.add(5);
        graph_size_array.add(10);
        graph_size_array.add(25);
        graph_size_array.add(100);
        
        PerformanceGraph g = new PerformanceGraph(7, 6, 0, 15, true);
        g.printGraph();
        
        //Size Test
        for (Integer size : graph_size_array){
            //generate graphs 
            //do belman ford
            //do djikstras
            //store x/y graph info
        }
        
        //create the graphs
        
        
//        //Stuff for jGraph visualization
//        CMSC441Project applet = new CMSC441Project();
//        applet.init();
//
//        JFrame frame = new JFrame();
//        frame.getContentPane().add(applet);
//        frame.setTitle("JGraphT Adapter to JGraph Demo");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
        /**
         * {@inheritDoc}
         */
//    public void init() {
//        // create a visualization using JGraph, via an adapter
//        jgAdapter = new JGraphModelAdapter<String, DefaultEdge>(graph);
//    }
    }
}