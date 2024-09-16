#ifndef NODETYPE_H 
#define NODETYPE_H 

#include "vertex.h"

typedef Vertex ItemType;

struct NodeType
{
  ItemType info;
  NodeType* next;
};

#endif
