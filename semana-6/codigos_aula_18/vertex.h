#ifndef VERTEX_H 
#define VERTEX_H 

#include <iostream>

class Vertex {
 public:
  Vertex(){
    this->nome = "";
  }
  Vertex(std::string nome){
    this->nome  = nome;
  }
  std::string getNome() const {
    return nome;    
  }  
 private :
  std::string nome;
};

#endif
