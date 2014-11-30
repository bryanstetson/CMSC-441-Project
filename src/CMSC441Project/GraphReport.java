/* Author: Bryan Stetson and Raymond Chan
 * Date: 11/13/14
 * Class: CMSC 441 Algorithms 
 * Graph Algorithm Comparison Project 
 */
package CMSC441Project;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import org.jgrapht.Graph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import java.util.*;

import org.jgrapht.*;

public class GraphReport {
    //Allocation variables

    private double allocation_time_start;
    private double allocation_time_total;
    private double allocation_mem_start;
    private double allocation_mem_total;
    //Single Node Bellman-Ford
    private double SNBF_time_start;
    private double SNBF_time_total;
    private double SNBF_mem_start;
    private double SNBF_mem_total;
    //Multi Node Bellman-Ford
    private double MNBF_time_start;
    private double MNBF_time_total;
    private double MNBF_mem_start;
    private double MNBF_mem_total;

    GraphReport(long refTime) {
        double time = (System.currentTimeMillis() - refTime) / 1000.0;
        double mem = usedMemory() / (1024.0 * 1024.0);     //Convert from byte to MB
        mem = Math.round(mem * 100) / 100.0;
        System.out.println("Starting at " + " (" + time + " sec, " + mem + "MB)");
    }

    private static long usedMemory() {
        Runtime rt = Runtime.getRuntime();
        return rt.totalMemory() - rt.freeMemory();
    }

    public void startGraphAllocation() {
        allocation_time_start = System.currentTimeMillis();
        allocation_mem_start = usedMemory() / (1024.0 * 1024.0);
        allocation_mem_start = Math.round(allocation_mem_start * 100) / 100.0;
    }

    public void endGraphAllocation() {
        allocation_time_total = (System.currentTimeMillis() - allocation_time_start) / 1000.0;
        allocation_mem_total = usedMemory() / (1024.0 * 1024.0);
        allocation_mem_total = Math.round(allocation_mem_total * 100) / 100.0;
    }

    public void startSNBF() {
        SNBF_time_start = System.currentTimeMillis();
        SNBF_mem_start = usedMemory() / (1024.0 * 1024.0);
        SNBF_mem_start = Math.round(SNBF_mem_start * 100) / 100.0;
    }

    public void endSNBF() {
        SNBF_time_total = (System.currentTimeMillis() - SNBF_time_start) / 1000.0;
        SNBF_mem_total = usedMemory() / (1024.0 * 1024.0);
        SNBF_mem_total = Math.round(SNBF_mem_total * 100) / 100.0;
    }
}