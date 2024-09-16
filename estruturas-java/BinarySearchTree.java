public class BinarySearchTree {
    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    // Método de Inserção
    public void insert(int value) {
        root = insertRec(root, value);
    }

    private Node insertRec(Node root, int value) {
        if (root == null) {
            root = new Node(value);
            return root;
        }

        if (value < root.value) {
            root.left = insertRec(root.left, value);
        } else if (value > root.value) {
            root.right = insertRec(root.right, value);
        }

        return root;
    }

    // Método de Busca
    public boolean search(int value) {
        return searchRec(root, value);
    }

    private boolean searchRec(Node root, int value) {
        if (root == null) {
            return false;
        }

        if (value == root.value) {
            return true;
        }

        return value < root.value ? searchRec(root.left, value) : searchRec(root.right, value);
    }

    // Método de Remoção
    public void delete(int value) {
        root = deleteRec(root, value);
    }

    private Node deleteRec(Node root, int value) {
        if (root == null) {
            return root;
        }

        if (value < root.value) {
            root.left = deleteRec(root.left, value);
        } else if (value > root.value) {
            root.right = deleteRec(root.right, value);
        } else {
            // Nó com um filho ou nenhum filho
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Nó com dois filhos: Pegar o sucessor (menor na subárvore direita)
            root.value = minValue(root.right);

            // Remover o sucessor
            root.right = deleteRec(root.right, root.value);
        }

        return root;
    }

    private int minValue(Node root) {
        int minValue = root.value;
        while (root.left != null) {
            minValue = root.left.value;
            root = root.left;
        }
        return minValue;
    }

    // Percurso In-ordem
    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.value + " ");
            inorderRec(root.right);
        }
    }

    // Percurso Pré-ordem
    public void preorder() {
        preorderRec(root);
        System.out.println();
    }

    private void preorderRec(Node root) {
        if (root != null) {
            System.out.print(root.value + " ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }

    // Percurso Pós-ordem
    public void postorder() {
        postorderRec(root);
        System.out.println();
    }

    private void postorderRec(Node root) {
        if (root != null) {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print(root.value + " ");
        }
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        // Inserindo elementos
        bst.insert(20);
        bst.insert(10);
        bst.insert(30);
        bst.insert(5);
        bst.insert(15);
        bst.insert(25);
        bst.insert(35);

        // Percursos
        System.out.print("Percurso In-ordem: ");
        bst.inorder();

        System.out.print("Percurso Pré-ordem: ");
        bst.preorder();

        System.out.print("Percurso Pós-ordem: ");
        bst.postorder();

        // Busca
        System.out.println("Busca por 15: " + (bst.search(15) ? "Encontrado" : "Não encontrado"));
        System.out.println("Busca por 40: " + (bst.search(40) ? "Encontrado" : "Não encontrado"));

        // Remoção
        System.out.println("Removendo 20");
        bst.delete(20);
        System.out.print("Percurso In-ordem após remoção: ");
        bst.inorder();
    }
}
