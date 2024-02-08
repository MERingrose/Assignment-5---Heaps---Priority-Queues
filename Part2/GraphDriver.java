package Part2;

import java.util.Iterator;

public class GraphDriver {

    public static void main(String[] args) {

        // create two graphs of different data types
        Graph<Integer> graph = new Graph<Integer>();
        Graph<String> strGraph = new Graph<String>();

        graph.addVertex(4);// 0
        graph.addVertex(5);// 1
        graph.addVertex(1);// 2
        graph.addVertex(7);// 3
        graph.addVertex(2);// 4
        graph.addVertex(9);// 5
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(0, 3);
        graph.addEdge(0, 5);
        graph.addEdge(2, 5);

        Iterator<Integer> sp = graph.iteratorShortestPath(4, 2);

        while (sp.hasNext()) {
            System.out.println(sp.next());
        }

        strGraph.addVertex("Butcher");
        strGraph.addVertex("Baker");
        strGraph.addVertex("Candlemaker");
        strGraph.addVertex("Carpenter");
        strGraph.addVertex("King");
        strGraph.addVertex("Queen");
        strGraph.addEdge("Butcher", "Candlemaker");
        strGraph.addEdge("Butcher", "Carpenter");
        strGraph.addEdge("Butcher", "King");
        strGraph.addEdge("Butcher", "Baker");
        strGraph.addEdge("King", "Queen");
        strGraph.addEdge("Queen", "Baker");

        System.out.println(strGraph.isConnected());

        System.out.println(strGraph.toString());

        strGraph.removeVertex("Candlemaker");
        strGraph.removeEdge("Baker", "Queen");
        strGraph.removeEdge("King", "Queen");

        System.out.println(strGraph.isConnected());

        System.out.println(graph.toString());
        System.out.println(strGraph.toString());
    }
}
