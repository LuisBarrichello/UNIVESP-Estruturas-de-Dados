import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        HashTable<String, Integer> hashTable = new HashTable<>(10);

        // Inserção de elementos
        hashTable.insertItem("Alice", 30);
        hashTable.insertItem("Bob", 25);
        hashTable.insertItem("Charlie", 35);

        // Recuperação de elementos
        System.out.println("Valor de Alice: " + hashTable.retrieveItem("Alice")); // Deve imprimir 30
        System.out.println("Valor de Bob: " + hashTable.retrieveItem("Bob")); // Deve imprimir 25

        // Remoção de elementos
        hashTable.deleteItem("Bob");
        System.out.println("Valor de Bob após remoção: " + hashTable.retrieveItem("Bob")); // Deve imprimir null

        // Tamanho da tabela
        System.out.println("Tamanho da tabela: " + hashTable.size()); // Deve imprimir 2

        // Conjunto de chaves
        System.out.println("Conjunto de chaves: " + hashTable.keySet());

        // Conjunto de valores
        System.out.println("Conjunto de valores: " + hashTable.values());

        // Conjunto de entradas
        System.out.println("Conjunto de entradas: " + hashTable.entrySet());

        Graph graph = new Graph(5, -1);

        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");
        Vertex e = new Vertex("E");

        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addVertex(e);

        graph.addEdge(a, b, 2);
        graph.addEdge(a, c, 1);
        graph.addEdge(b, c, 3);
        graph.addEdge(c, e, 4);
        graph.addEdge(d, e, 5);

        graph.printMatrix();

        // Testando adjacentes
        Queue<Vertex> adjVertices = new LinkedList<>();
        graph.getAdjacents(a, adjVertices);
        System.out.println("Vértices adjacentes a A:");
        for (Vertex v : adjVertices) {
            System.out.println(v.getNoe());
        }
        
        // Depth-First Search
        graph.depthFirstSearch(a, h);

        // Breadth-First Search
        graph.breadthFirstSearch(a, h);

        // PageRank
        float[] pageRanks = new float[9];
        graph.getPageRanks(pageRanks);

        for (float rank : pageRanks) {
            System.out.print(rank + " ");
        }
        System.out.println("\nSaindo");
    }
}
