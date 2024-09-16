#include "avl_search_tree.h"
using namespace std;

const int NUM_ALUNOS = 15;

int main() {
  AVLSearchTree searchTree;

  int ras[NUM_ALUNOS] = {
    41, 27, 74,  4, 29,
    65, 90,  2,  6, 28,
    30, 60, 73, 80, 92};
  
  string nomes[NUM_ALUNOS] = {
    "Enelton", "Cristhof", "Danielle",
    "Meira", "Guilherme", "Juliana", 
    "Pedro", "Raul", "Paulo",
    "Carlos", "Lucas", "Maria",
    "Samanta", "Ulisses", "Carlos"};
  Aluno alunos[NUM_ALUNOS];

  for (int i = 0; i < NUM_ALUNOS; i++){
    Aluno aluno = Aluno(ras[i], nomes[i]);
    alunos[i] = aluno;
    searchTree.insertAluno(aluno);
  }

  cout << "Pre:  ";
  searchTree.printPreOrder();
  cout << endl;
  cout << "In:   ";
  searchTree.printInOrder();
  cout << endl;
  cout << "Post: ";
  searchTree.printPostOrder();
  cout << endl;

  // Removendo alguns alunos
  searchTree.deleteAluno(alunos[0].getRa());
  searchTree.deleteAluno(alunos[11].getRa());
  searchTree.deleteAluno(alunos[5].getRa());
  searchTree.deleteAluno(alunos[12].getRa());
  
  cout << "********" << endl;
  cout << "Pre:  ";
  searchTree.printPreOrder();
  cout << endl;
  cout << "In:   ";
  searchTree.printInOrder();
  cout << endl;
  cout << "Post: ";
  searchTree.printPostOrder();
  cout << endl;
}
