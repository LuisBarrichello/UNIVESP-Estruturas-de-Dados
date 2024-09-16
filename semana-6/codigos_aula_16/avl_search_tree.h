#include <cstddef>
#include <iostream>
#include "aluno.h"

/*
  Estrutura usada para 
  guardar a informação, 
  os endereços das
  subárvores e o fator
  de balanceamento.
 */
struct NodeType
{
  Aluno aluno;
  int      fatorB;
  NodeType*    esquerda;
  NodeType*    direita;
};

class AVLSearchTree {
 public:  
  AVLSearchTree() { root = NULL; }    
  ~AVLSearchTree(){ destroyTree(root); }
  bool isEmpty() const;
  bool isFull() const;
  void retrieveAluno(Aluno& item, bool& found) const{ 
    retrieveAluno(root, item, found);
  }
  void insertAluno(Aluno item){
    bool isTaller;
    insertAluno(root, item, isTaller);
  }  
  void deleteAluno(int item){
    bool isShorter;
    deleteAluno(root, item, isShorter);
  }
  
  void printPreOrder()  const { printPreOrder(root); }
  void printInOrder()   const { printInOrder(root);  }
  void printPostOrder() const { printPostOrder(root);}

  
 private:
  void destroyTree(NodeType*& tree); 
  void retrieveAluno(NodeType* tree,
		     Aluno& item,
		     bool& found) const;
  void insertAluno(NodeType*& tree, Aluno item, bool& isTaller);  
  void deleteAluno(NodeType*& tree, int item, bool& isShorter);
  void deleteNode(NodeType*& tree, bool& isShorter);
  void getSuccessor(NodeType* tree, Aluno& data);
  void printTree(NodeType *tree) const;    
  void printPreOrder(NodeType* tree)  const;
  void printInOrder(NodeType* tree)   const;
  void printPostOrder(NodeType* tree) const;

  void rotateToLeft(NodeType*& tree) const;
  void rotateToRight(NodeType*& tree) const;  
  void rotateToLeftAndRight(NodeType*& tree) const;
  void rotateToRightAndLeft(NodeType*& tree) const;

  void performRotations(NodeType*& tree) const;

  // Nó raiz da árvore.
  NodeType* root;
};
