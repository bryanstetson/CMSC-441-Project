/* Author: Bryan Stetson and Raymond Chan
 * Date: 11/13/14
 * Class: CMSC 441 Algorithms 
 * Graph Algorithm Comparison Project 
 */
package CMSC441Project;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import org.jgrapht.VertexFactory;
import org.jgrapht.generate.*;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.ClassBasedVertexFactory;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import org.jgrapht.traverse.DepthFirstIterator;
import sun.security.provider.certpath.Vertex;

/**
 * Generates a multi-graph for use in algorithm analysis
 *
 * @param V: The number of vertices the graph will have
 * @param E: The number of edges the graph will have
 * @param max_weight: maximum weight that an edge can have
 * @param min_weight: minimum weight that an edge can have
 */
//TODO
//Create Unit Test that tests constructor initialization values
public class PerformanceGraph {

    private AbstractBaseGraph<Object, DefaultWeightedEdge> G;
    public GraphReport report;   //Where statistical data is sent for the project

    //Graph generation is done in the constructor
    public PerformanceGraph(int V, int E, int max_weight, int min_weight, boolean multigraph) {
        report = new GraphReport(System.currentTimeMillis());

        report.startGraphAllocation();
        if (multigraph) {
            //Initialize Graph
            G = new WeightedMultigraph<>(DefaultWeightedEdge.class);
            RandomGraphGenerator rgg = new RandomGraphGenerator(V, E);
            VertexFactory<Object> vFactory = new ClassBasedVertexFactory<>(Object.class);
            rgg.generateGraph(G, vFactory, null);

            //Replace all the vertices with sequential numbers so we can ID them
            Set<Object> vertices = new HashSet<>();
            vertices.addAll(G.vertexSet());
            double default_edge_weight;
            default_edge_weight = G.getEdgeWeight(null);
            Integer counter = 0;
            Set<DefaultWeightedEdge> edges = new HashSet<>();
            for (Object vertex : vertices) {
                Set<DefaultWeightedEdge> connected_edges = G.edgesOf(vertex);
                //Sets any edge weights that haven't been set yet
                for (DefaultWeightedEdge edge : connected_edges) {
                    if (!edges.contains(edge)) {
                        double random_weight = min_weight + (int) (Math.random()
                                * ((max_weight - min_weight) + 1));
                        G.setEdgeWeight(edge, random_weight);
                        edges.add(edge);
                    }
                }
                replaceVertex(vertex, (Object) counter++);
            }
        }
        report.endGraphAllocation();
    }

    //Defaults to multigraph analysis if not specified
    public PerformanceGraph(int V, int E, int max_weight, int min_weight) {
        this(V, E, max_weight, min_weight, false);
    }

    private PerformanceGraph() {
    }    // ensure non-instantiability

    //Simply calculates the time to run bellman ford on 2 random verticies
    //returns the total weight 
    public double singleNodeBellmanFord() {
        Set<Object> vertices = new HashSet<Object>();
        vertices.addAll(G.vertexSet());
        if (vertices.size() < 2) {
            return 0; //cannot find shortest path if less than 2 nodes 
        }

        //find vertices indecies
        int v1 = new Random().nextInt(vertices.size());
        int v2 = new Random().nextInt(vertices.size());
        while (v1 == v2) {
            v2 = new Random().nextInt(vertices.size());
        }
        report.startSNBF();
        
        int weight = 0; //placeholder
        //if nodes don't have a path then do report.startSNBF(); again and try again
        //loop that
        
        report.endSNBF();
        return weight;
    }

    //Calculates the shortest path using bellman ford from every node to every other node in the graph and stores it in a table
    public void multiNodeBellmanFord() {
    }

    /**
     * TODO turn this into toString()
     */
    public void printGraph() {
        System.out.println("Depth First Traversal");
        Iterator<Object> iter = new DepthFirstIterator<>(G);
        Object vertex;
        while (iter.hasNext()) {
            vertex = iter.next();
            System.out.println("Vertex: " + vertex.toString());
            for (DefaultWeightedEdge edge : G.edgesOf(vertex)) {
                System.out.println("\tEdge: " + edge.toString()
                        + "\tWeight: " + G.getEdgeWeight(edge));
            }
        }
    }

    /**
     * Replaces the oldVertex with a new one of name newVertex Note. weights transfer so output appears to be a simple vertex name change
     *
     * @param oldVertex: Old default vertex factory name
     * @param newVertex: New vertex name
     * @return
     */
    private boolean replaceVertex(Object oldVertex, Object newVertex) {
        if ((oldVertex == null) || (newVertex == null)) {
            return false;
        }
        Set<DefaultWeightedEdge> relatedEdges = G.edgesOf(oldVertex);
        G.addVertex(newVertex);

        Object sourceVertex;
        Object targetVertex;
        for (DefaultWeightedEdge e : relatedEdges) {
            double weight = G.getEdgeWeight(e);
            sourceVertex = G.getEdgeSource(e);
            targetVertex = G.getEdgeTarget(e);
            //Self Loop
            if (sourceVertex.equals(oldVertex) && targetVertex.equals(oldVertex)) {
                DefaultWeightedEdge new_edge = G.addEdge(newVertex, newVertex);
                G.setEdgeWeight(new_edge, weight);
            } else {
                if (sourceVertex.equals(oldVertex)) {
                    DefaultWeightedEdge new_edge = G.addEdge(newVertex, targetVertex);
                    G.setEdgeWeight(new_edge, weight);
                } else {
                    DefaultWeightedEdge new_edge = G.addEdge(sourceVertex, newVertex);
                    G.setEdgeWeight(new_edge, weight);
                }
            }
        }
        G.removeVertex(oldVertex);
        return true;
    }
}
