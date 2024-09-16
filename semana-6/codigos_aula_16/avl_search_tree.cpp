#include "avl_search_tree.h"

bool AVLSearchTree::isEmpty() const
{
  return root == NULL;
}

bool AVLSearchTree::isFull() const
{
  NodeType* location;
  try
    {
      location = new NodeType;
      delete location;
      return false;
    }
  catch(std::bad_alloc exception)
    {
      return true;
    }
}

void AVLSearchTree::destroyTree(NodeType*& tree)
{
  if (tree != NULL)
    {
      destroyTree(tree->esquerda);
      destroyTree(tree->direita);
      delete tree;
    }
}


void AVLSearchTree::retrieveAluno(NodeType* tree,
				  Aluno& aluno,
				  bool& found) const {
  if (tree == NULL)
    found = false;
  else if (aluno.getRa() < tree->aluno.getRa())
    retrieveAluno(tree->esquerda, aluno, found);
  else if (aluno.getRa() > tree->aluno.getRa())
    retrieveAluno(tree->direita, aluno, found);
  else {
    aluno = tree->aluno;
    found = true;
  }
}

void AVLSearchTree::insertAluno(NodeType*& tree,
				Aluno aluno,
				bool& isTaller) { 
  if (tree == NULL) {
      tree = new NodeType;
      tree->direita  = NULL;
      tree->esquerda = NULL;
      tree->aluno    = aluno;
      tree->fatorB  = 0; // Acabamos de inserir uma folha
      isTaller = true;
      return;
  }  
  if (aluno.getRa() < tree->aluno.getRa()) {
    insertAluno(tree->esquerda, aluno, isTaller);
    if (isTaller)
      tree->fatorB = tree->fatorB - 1;    
  } else {
    insertAluno(tree->direita, aluno, isTaller);
    if (isTaller)
      tree->fatorB = tree->fatorB + 1;    
  }
  // O performRotations vai ajustar o fatorB
  performRotations(tree);
  
  // Após a rotação, a árvore não muda de tamanho
  if (isTaller && tree->fatorB == 0) {
      isTaller = false;
   }
}

void AVLSearchTree::deleteAluno(NodeType*& tree, int aluno,
				bool& isShorter) {
  if (aluno < tree->aluno.getRa()){
    deleteAluno(tree->esquerda, aluno, isShorter);
    if (isShorter)
      tree->fatorB = tree->fatorB + 1;     
  } else if (aluno > tree->aluno.getRa()) {
    deleteAluno(tree->direita, aluno, isShorter);
    if (isShorter)
      tree->fatorB = tree->fatorB - 1;
  } else if (aluno == tree->aluno.getRa()) {
    deleteNode(tree, isShorter);
  }  
  if (tree != NULL) {
    performRotations(tree);  
    if (isShorter && tree->fatorB != 0){
      isShorter = false;
    }
  }
}

void AVLSearchTree::deleteNode(NodeType*& tree, bool& isShorter) {
  Aluno data;
  NodeType* tempPtr;
  tempPtr = tree;
  if (tree->esquerda == NULL) {
    tree = tree->direita;
    isShorter = true;
    delete tempPtr; 
  } else if (tree->direita == NULL) {
    tree = tree->esquerda; 
    isShorter = true;
    delete tempPtr;
  } else {
    getSuccessor(tree, data);
    tree->aluno = data;
    deleteAluno(tree->direita, data.getRa(), isShorter);
    if (isShorter)
      tree->fatorB = tree->fatorB - 1;
  }
}

void AVLSearchTree::getSuccessor(NodeType* tree, Aluno& data) {
  tree = tree->direita;
  while (tree->esquerda != NULL)
    tree = tree->esquerda;
  data = tree->aluno;
}

void AVLSearchTree::printPreOrder(NodeType* tree) const {
  if (tree != NULL)  {
      std::cout << tree->aluno.getNome() << "[" << tree->fatorB << "] ";
      printPreOrder(tree->esquerda);
      printPreOrder(tree->direita);
    }
}

void AVLSearchTree::printInOrder(NodeType* tree) const {
  if (tree != NULL) {
    printInOrder(tree->esquerda);
    std::cout << tree->aluno.getNome() << "[" << tree->fatorB << "] ";
    printInOrder(tree->direita);
  }
}

void AVLSearchTree::printPostOrder(NodeType* tree) const {
  if (tree != NULL) {
	printPostOrder(tree->esquerda);
	printPostOrder(tree->direita);
	std::cout << tree->aluno.getNome() << "[" << tree->fatorB << "] ";
    }
}

void AVLSearchTree::rotateToLeft(
				 NodeType*& tree
				 ) const{
  NodeType* p = tree->direita;
  tree->direita = p->esquerda; 
  p->esquerda   = tree; 
  tree          = p;    
}

void AVLSearchTree::rotateToRight(
				  NodeType*& tree    
				  ) const{
  NodeType* p = tree->esquerda;
  tree->esquerda = p->direita;
  p->direita     = tree;
  tree           = p;  
}

void AVLSearchTree::rotateToLeftAndRight(
					 NodeType*& tree    
					 ) const{
  NodeType* child = tree->esquerda;
  rotateToLeft(child);
  tree->esquerda = child;
  rotateToRight(tree);
}

void AVLSearchTree::rotateToRightAndLeft(
					 NodeType*& tree
					 ) const{
  NodeType* child = tree->direita;
  rotateToRight(child);
  tree->direita = child;
  rotateToLeft (tree);
}

void AVLSearchTree::performRotations(NodeType*& tree) const {      
  NodeType* child;
  NodeType* grandChild; // Usado em rotacao dupla
  
  // Rotacionar para a direita 
  if (tree->fatorB == -2) {
    child = tree->esquerda;

    switch (child->fatorB) {
    case -1 : // Simples para a direita: Caso 1
      tree->fatorB = 0;
      child ->fatorB = 0;
      rotateToRight(tree);
      break;      
    case 0 : // Simples para a direita: Caso 2 -> Remoções
      tree->fatorB = -1;
      child ->fatorB = +1;
      rotateToRight(tree);
      break;      
    case 1 :  // Rotacao dupla
      grandChild = child->direita;

      switch(grandChild->fatorB){
      case -1 :
	tree ->fatorB = +1;
	child->fatorB =  0;      
	break;
      case 0 :
	tree ->fatorB = 0;
	child->fatorB = 0;
	break;
      case +1 :
	tree ->fatorB =  0;
	child->fatorB = -1;
	break;
      }
      grandChild->fatorB = 0; 
      rotateToLeftAndRight(tree);
    }
  }  
  // Vamos rotacionar para a esquerda
  else if (tree->fatorB == +2) {
    child = tree->direita;
    
    switch (child->fatorB) {
    case  1 : // Simples para a esquerda: Caso 1
      tree ->fatorB = 0;
      child->fatorB = 0;
      rotateToLeft(tree);
      break;      
    case 0 : // Simples para a esquerda: Caso 2
      tree ->fatorB = +1;
      child->fatorB = -1;
      rotateToLeft(tree);
      break;      
    case -1 : // Rotacao dupla
      grandChild = child->esquerda;
      
      switch(grandChild->fatorB){
      case -1 :
	tree ->fatorB =  0;
	child->fatorB = +1;      
	break;
      case 0 :
	tree ->fatorB = 0;
	child->fatorB = 0;
	break;
      case +1 :
	tree ->fatorB = -1;
	child->fatorB =  0;
	break;
      }
      grandChild->fatorB = 0; 
      rotateToRightAndLeft(tree);
    }
  }          
}


