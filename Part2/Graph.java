package Part2;

import java.util.*;

/**
 * Graph represents an adjacency matrix implementation of a graph.
 *
 * @author Java Foundations
 * @version 4.0
 */
public class Graph<T> implements GraphADT<T> {

    protected final int DEFAULT_CAPACITY = 5;
    protected int numVertices; // number of vertices in the graph
    protected boolean[][] adjMatrix; // adjacency matrix
    protected T[] vertices; // values of vertices
    protected int modCount;

    /**
     * Creates an empty graph.
     * 
     */
    @SuppressWarnings("unchecked")
    public Graph() {
        numVertices = 0;
        this.adjMatrix = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Inserts an edge between two vertices of the graph.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2));
    }

    /**
     * Returns the index of the given vertex.
     * 
     * @param vertex
     * @return index of the vertex as int
     * @throws NoSuchElementException
     */
    private int getIndex(T vertex) throws NoSuchElementException {
        for (int i = 0; i < numVertices; i++) {
            if (vertex.equals(vertices[i])) {
                return i;
            }
        }
        throw new NoSuchElementException("Vertex not found");
    }

    /**
     * Inserts an edge between two vertices of the graph.
     *
     * @param index1 the first index
     * @param index2 the second index
     */
    public void addEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = true;
            adjMatrix[index2][index1] = true;
            modCount++;
        }
    }

    /**
     * Checks if a given index is valid.
     *
     * @param index the index to check
     * @return true if the index is valid, false otherwise
     */
    private boolean indexIsValid(int index) {
        return index >= 0 && index < numVertices;
    }

    /**
     * Adds a vertex to the graph, expanding the capacity of the graph
     * if necessary. It also associates an object with the vertex.
     *
     * @param vertex the vertex to add to the graph
     */
    public void addVertex(T vertex) {
        if ((numVertices + 1) == adjMatrix.length)
            expandCapacity();
        vertices[numVertices] = vertex;
        for (int i = 0; i < numVertices; i++) {
            adjMatrix[numVertices][i] = false;
            adjMatrix[i][numVertices] = false;
        }
        numVertices++;
        modCount++;
    }

    /**
     * Creates new arrays to store the contents of the graph with
     * twice the capacity.
     */
    @SuppressWarnings("unchecked")
    protected void expandCapacity() {
        T[] largerVertices = (T[]) (new Object[vertices.length * 2]);
        boolean[][] largerAdjMatrix = new boolean[vertices.length * 2][vertices.length * 2];
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                largerAdjMatrix[i][j] = adjMatrix[i][j];
            }
            largerVertices[i] = vertices[i];
        }
        vertices = largerVertices;
        adjMatrix = largerAdjMatrix;
    }

    /**
     * removes a vertex from the list of vertices for the graph
     * 
     * @param vertex the vertex to be deleted
     * @throws NoSuchElementException if the specified vertex is not valid
     */
    @Override
    public void removeVertex(T vertex) throws NoSuchElementException {
        if (indexIsValid(getIndex(vertex)))
            throw new NoSuchElementException("Provided vertex " + vertex + " is invalid.");

        int index = getIndex(vertex);
        for (int i = 0; i < numVertices; i++) {
            if (i != index) {
                adjMatrix[i][index] = false;
                adjMatrix[index][i] = false;
            }
        }
        // shift vertices and adjMatrix up to fill the gap
        for (int i = index; i < numVertices - 1; i++) {
            vertices[i] = vertices[i + 1];
            for (int j = 0; j < numVertices; j++) {
                adjMatrix[j][i] = adjMatrix[j][i + 1];
            }
            for (int j = 0; j < numVertices; j++) {
                adjMatrix[i][j] = adjMatrix[i + 1][j];
            }
        }
        // clear out last row and column of adjMatrix
        for (int i = 0; i < numVertices; i++) {
            adjMatrix[numVertices - 1][i] = false;
            adjMatrix[i][numVertices - 1] = false;
        }
        vertices[numVertices - 1] = null;
        numVertices--;
        modCount++;
    }

    /**
     * Removes an edge, if it exists, using two vertices
     */
    @Override
    public void removeEdge(T vertex1, T vertex2) {
        removeEdge(getIndex(vertex1), getIndex(vertex2));

    }

    /**
     * removes the edge representation between two vertices using the index of the
     * vertices
     * 
     * 
     * @param index1 index of first vertex
     * @param index2 index of second vertex
     */
    public void removeEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = false;
            adjMatrix[index2][index1] = false;
            modCount++;
        }
    }

    /**
     * Returns an iterator with the Breadth First Traversal of the graph
     * 
     * @param startVertex the starting vertex from which to perform the traversal
     * @return an Iterator<T> with the results of the BFS
     * 
     */
    @Override
    public Iterator<T> iteratorBFS(T startVertex) {
        int startIndex = getIndex(startVertex);
        boolean[] visited = new boolean[numVertices];
        Queue<T> queue = new LinkedList<>();
        List<T> traversalOrder = new ArrayList<>();

        queue.offer(vertices[startIndex]);
        visited[startIndex] = true;

        while (!queue.isEmpty()) {
            T currentVertex = queue.poll();
            int currentIndex = getIndex(currentVertex);
            traversalOrder.add(currentVertex);

            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[currentIndex][i] && !visited[i]) {
                    queue.offer(vertices[i]);
                    visited[i] = true;
                }
            }
        }

        return traversalOrder.iterator();
    }

    /**
     * traverses graph using depth first search and returns iterator.
     * Uses recursive DFS
     * 
     * @param startVertex the index to start from
     * @return returns an Iterator<T>
     * @throws NoSuchElementException if the specified start vertex is not valid
     * 
     */
    @Override
    public Iterator<T> iteratorDFS(T startVertex) throws NoSuchElementException {
        if (indexIsValid(getIndex(startVertex)))
            throw new NoSuchElementException("Vertex does not exist");

        int startIndex = getIndex(startVertex);
        boolean[] visited = new boolean[numVertices];
        Stack<T> traversalOrder = new Stack<T>();

        dfs(startIndex, visited, traversalOrder);

        return traversalOrder.iterator();
    }

    private void dfs(int vertexIndex, boolean[] visited, Stack<T> traversalOrder) {
        visited[vertexIndex] = true;
        traversalOrder.push(vertices[vertexIndex]);

        for (int i = 0; i < numVertices; i++) {
            if (adjMatrix[vertexIndex][i] && !visited[i]) {
                dfs(i, visited, traversalOrder);
            }
        }
    }

    /***
     * iteratorShortestPath finds the shortest path between two specified vertices
     * using Breadth First Search.
     * returns an empty iterator if there is no path to the target vertex.
     * 
     * @param startVertex  the starting vertexc
     * @param targetVertex the ending vertex
     * @throws NoSuchElementException if either the startVertex or targetVertex are
     *                                not valid vertices
     * 
     *
     */
    @Override
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) throws NoSuchElementException {

        // checks if params are valid, throws exception if vertex is not found
        if (!indexIsValid(getIndex(targetVertex)) || !indexIsValid(getIndex(startVertex))) {
            throw new NoSuchElementException("Vertex does not exist");
        }

        int startIndex = getIndex(startVertex);
        boolean[] visited = new boolean[numVertices];
        Queue<T> queue = new LinkedList<>();
        List<T> traversalOrder = new ArrayList<>();
        int[] prev = new int[numVertices];
        queue.offer(vertices[startIndex]);
        visited[startIndex] = true;
        prev[startIndex] = -1; // starting vertex has no previous

        while (!queue.isEmpty()) {
            T currentVertex = queue.poll();
            int currentIndex = getIndex(currentVertex);
            if (currentVertex.equals(targetVertex)) {
                break;
            }

            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[currentIndex][i] && !visited[i]) {
                    queue.offer(vertices[i]);
                    visited[i] = true;
                    prev[i] = currentIndex;

                }

            }

        }

        if (!visited[getIndex(targetVertex)])
            return Collections.emptyIterator(); // no path found

        for (int vertex = getIndex(targetVertex); vertex != -1; vertex = prev[vertex]) {
            traversalOrder.add(vertices[vertex]);
        }

        Collections.reverse(traversalOrder);

        return traversalOrder.iterator();

    }

    /**
     * returns true if there are no vertices in the graph
     */

    @Override
    public boolean isEmpty() {
        return numVertices == 0;
    }

    /**
     * 
     * Returns true if all vertices are connected by edges.
     * Uses recursive Depth First Search
     * 
     */
    public boolean isConnected() {
        if (numVertices == 0 || modCount < numVertices - 1)
            return false; // empty graph is not connected & cannot be connected with fewer mods than n-1
                          // vertices

        // Mark all vertices as not visited.
        boolean[] visited = new boolean[numVertices];

        // Start DFS traversal from any vertex.
        dfs(0, visited);

        // Check if all vertices have been visited.
        for (boolean visitedVertex : visited) {
            if (!visitedVertex) {
                return false;
            }
        }

        return true;
    }

    // private DFS method for use with isConnected method
    private void dfs(int vertexIndex, boolean[] visited) {
        visited[vertexIndex] = true;

        for (int i = 0; i < numVertices; i++) {
            if (adjMatrix[vertexIndex][i] && !visited[i]) {
                dfs(i, visited);
            }
        }
    }

    /**
     * Returns the size of the graph as number of vertices.
     * 
     * @return int representing the number of vertices in the graph
     */
    @Override
    public int size() {

        return numVertices;
    }

    /**
     * Provides a string representation of all vertices and edges
     * 
     */
    @Override
    public String toString() {

        String output = "Vertices: ";

        // append the string output to add values to the string
        for (T vertex : vertices) {
            output = output + vertex + ", ";

        }

        output = output + '\n';
        output = output + "Edges: ";

        // appends the string to add edges to the string representation of the graph in
        // the form (x,y)
        for (int i = 0; i < numVertices; i++) {
            for (int x = 0; x < numVertices; x++) {
                if (adjMatrix[i][x]) {
                    output = output + "(" + i + "," + x + "), ";
                }
            }
        }

        return output;
    }
}
