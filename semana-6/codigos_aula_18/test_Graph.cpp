#include "graph.h"
#include <iostream>

int main() {
  Graph graph;
  Vertex a = Vertex("A"); graph.addVertex(a);
  Vertex b = Vertex("B"); graph.addVertex(b);
  Vertex c = Vertex("C"); graph.addVertex(c);
  Vertex d = Vertex("D"); graph.addVertex(d);
  Vertex e = Vertex("E"); graph.addVertex(e);
  
  graph.addEdge(a, b, 2);  graph.addEdge(a, c, 1);
  graph.addEdge(b, c, 3);  graph.addEdge(c, e, 4);
  graph.addEdge(d, e, 5);

  graph.printMatrix(); 
  std::cout << std::endl;
}
