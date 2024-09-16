#ifndef QUEUE_H 
#define QUEUE_H 

#include "node_type.h"

class Queue
{ 
 public:
  Queue(); // Constructor
  ~Queue(); // Destrutor  
  bool isEmpty() const;
  bool isFull() const;
  void print() const;
  
  void enqueue(ItemType);
  ItemType dequeue();
 private:
  NodeType* front;
  NodeType* rear;
};

#endif
