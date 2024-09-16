import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {
    private int NULL_EDGE;
    private int maxVertices;
    private int numVertices;
    private Vertex[] vertices;
    private boolean[] marks;
    private int[][] edges;

    // Construtor do grafo
    public Graph(int max, int nullEdge) {
        NULL_EDGE = nullEdge;
        maxVertices = max;
        numVertices = 0;
        vertices = new Vertex[maxVertices];
        marks = new boolean[maxVertices];
        edges = new int[maxVertices][maxVertices];

        // Populando a matriz de adjacências com valor nulo
        for (int i = 0; i < maxVertices; i++) {
            for (int j = 0; j < maxVertices; j++) {
                edges[i][j] = NULL_EDGE;
            }
        }
    }

    // Adiciona um vértice ao grafo
    public void addVertex(Vertex vertex) {
        vertices[numVertices] = vertex;
        numVertices++;
    }

    // Adiciona uma aresta entre dois vértices com o peso especificado
    public void addEdge(Vertex fromVertex, Vertex toVertex, int weight) {
        int row = getIndex(fromVertex);
        int col = getIndex(toVertex);

        edges[row][col] = weight;
        // Remover se for grafo direcionado
        edges[col][row] = weight;
    }

    // Retorna o peso da aresta entre dois vértices
    public int getWeight(Vertex fromVertex, Vertex toVertex) {
        int row = getIndex(fromVertex);
        int col = getIndex(toVertex);
        return edges[row][col];
    }

    // Retorna os adjacentes de um vértice
    public void getAdjacents(Vertex vertex, Queue<Vertex> adjVertices) {
        int fromIndex = getIndex(vertex);
        for (int toIndex = 0; toIndex < numVertices; toIndex++) {
            if (edges[fromIndex][toIndex] != NULL_EDGE) {
                adjVertices.add(vertices[toIndex]);
            }
        }
    }

    // Limpa as marcações de todos os vértices
    public void clearMarks() {
        for (int i = 0; i < numVertices; i++) {
            marks[i] = false;
        }
    }

    // Marca um vértice
    public void markVertex(Vertex vertex) {
        int index = getIndex(vertex);
        marks[index] = true;
    }

    // Verifica se um vértice está marcado
    public boolean isMarked(Vertex vertex) {
        int index = getIndex(vertex);
        return marks[index];
    }

    // Imprime a matriz de adjacências
    public void printMatrix() {
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.print(edges[i][j] + ", ");
            }
            System.out.println();
        }
    }

    // Retorna o índice de um vértice
    private int getIndex(Vertex vertex) {
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].getNome().equals(vertex.getNome())) {
                return i;
            }
        }
        throw new IllegalArgumentException("Vértice não encontrado!");
    }

    public List<Vertex> getAdjacents(Vertex vertex) {
        int fromIndex = getIndex(vertex);
        List<Vertex> adjacents = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            if (edges[fromIndex][i] != NULL_EDGE) {
                adjacents.add(vertices[i]);
            }
        }
        return adjacents;
    }

    public void printMatrix() {
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.print(edges[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void depthFirstSearch(Vertex origem, Vertex destino) {
        Stack<Vertex> vertexStack = new Stack<>();
        boolean found = false;

        clearMarks();
        vertexStack.push(origem);

        while (!vertexStack.isEmpty() && !found) {
            Vertex vertex = vertexStack.pop();

            if (vertex.equals(destino)) {
                System.out.println("Encontrado: " + vertex.getNome());
                found = true;
            } else {
                if (!isMarked(vertex)) {
                    markVertex(vertex);
                    System.out.println("Visitando: " + vertex.getNome());

                    for (Vertex adjacent : getAdjacents(vertex)) {
                        if (!isMarked(adjacent)) {
                            System.out.println("Empilhando: " + adjacent.getNome());
                            vertexStack.push(adjacent);
                        }
                    }
                }
            }
        }

        if (!found) {
            System.out.println("Caminho não encontrado.");
        }
    }

    public void breadthFirstSearch(Vertex origem, Vertex destino) {
        Queue<Vertex> vertexQueue = new LinkedList<>();
        boolean found = false;

        clearMarks();
        vertexQueue.add(origem);

        while (!vertexQueue.isEmpty() && !found) {
            Vertex vertex = vertexQueue.poll();

            if (vertex.equals(destino)) {
                System.out.println("Encontrado: " + vertex.getNome());
                found = true;
            } else {
                if (!isMarked(vertex)) {
                    markVertex(vertex);
                    System.out.println("Visitando: " + vertex.getNome());

                    for (Vertex adjacent : getAdjacents(vertex)) {
                        if (!isMarked(adjacent)) {
                            System.out.println("Enfileirando: " + adjacent.getNome());
                            vertexQueue.add(adjacent);
                        }
                    }
                }
            }
        }

        if (!found) {
            System.out.println("Caminho não encontrado.");
        }
    }

    public void getPageRanks(float[] pageRanks) {
        int[] outputDegree = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            outputDegree[i] = 0;
            for (int j = 0; j < numVertices; j++) {
                if (edges[i][j] != NULL_EDGE) {
                    outputDegree[i]++;
                }
            }
        }

        float[] prPrevious = new float[numVertices];
        Arrays.fill(prPrevious, 1.0f / numVertices);
        float d = 0.85f;

        for (int iteration = 0; iteration < 100; iteration++) {
            float[] pr = new float[numVertices];

            for (int i = 0; i < numVertices; i++) {
                pr[i] = 0;

                for (int j = 0; j < numVertices; j++) {
                    if (edges[j][i] != NULL_EDGE) {
                        pr[i] += prPrevious[j] / outputDegree[j];
                    }
                }

                pr[i] = (1 - d) / numVertices + d * pr[i];
            }

            prPrevious = pr.clone();
        }

        System.arraycopy(prPrevious, 0, pageRanks, 0, numVertices);
    }
}
