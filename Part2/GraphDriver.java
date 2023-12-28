package Part2;

public class GraphDriver {

    public static void main(String[] args) {

        Graph<Integer> graph = new Graph<Integer>();

        graph.addVertex(4);
        graph.addVertex(5);
        graph.addEdge(4, 5);

        System.out.println(graph.toString());
    }
}
