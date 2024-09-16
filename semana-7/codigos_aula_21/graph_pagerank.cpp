#include <iostream>
#include "graph.h"
#include "queue.h"

using namespace std;
int main() {
  Graph graph;
  Vertex a = Vertex("a"); Vertex b = Vertex("b");
  Vertex c = Vertex("c"); Vertex d = Vertex("d");   
  graph.addVertex(a); graph.addVertex(b);
  graph.addVertex(c); graph.addVertex(d);  
  graph.addEdge(a, c, 1); graph.addEdge(a, b, 1);  
  graph.addEdge(b, d, 1); graph.addEdge(c, a, 1);
  graph.addEdge(c, b, 1); graph.addEdge(c, d, 1);    
  graph.addEdge(d, c, 1);
  float *pageRanks = new float[4];
  graph.getPageRanks(pageRanks);

  for (int i = 0; i < 4; i++){
    std::cout << pageRanks[i] << " , ";
  }
  std::cout << std:: endl;
  std::cout << "Saindo" << std::endl;
}
