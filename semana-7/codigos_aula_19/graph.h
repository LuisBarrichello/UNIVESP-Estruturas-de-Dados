#ifndef GRAPH_H 
#define GRAPH_H 

#include "queue.h"

class Graph {  
 private:  
  int NULL_EDGE; // Constante para aresta nula.
  int maxVertices; // Número máximo de vértices.  
  int numVertices; // Número de vértices adicionados.
  Vertex* vertices; // Array com todos os vértices.
  int** edges; // Matriz de adjacências
  bool* marks; // marks[i] marca se vertices[i] foi usado.
  int getIndex(Vertex);
 public:
  Graph(int max = 50, int null = 0);  // construtor
  ~Graph(); // destrutor

  void addVertex(Vertex);
  void addEdge(Vertex, Vertex, int);

  int getWeight(Vertex, Vertex);
  void getAdjacents(Vertex, Queue&);
  void clearMarks();
  void markVertex(Vertex);
  bool isMarked(Vertex);
  void printMatrix();
};

#endif
