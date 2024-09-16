class AVLTree {

    class Node {
        int key, balanceFactor;
        Node left, right;

        public Node(int key) {
            this.key = key;
            this.balanceFactor = 0;
            left = right = null;
        }
    }

    private Node root;

    public boolean isEmpty() {
        return root == null;
    }

    public boolean isFull() {
        try {
            Node testNode = new Node(0);
            return false;
        } catch (OutOfMemoryError e) {
            return true;
        }
    }

    public void destroyTree(Node node) {
        if (node != null) {
            destroyTree(node.left);
            destroyTree(node.right);
            node = null;
        }
    }

    public Node search(Node node, int key) {
        if (node == null || node.key == key) {
            return node;
        }
        if (key < node.key) {
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }
    }

    public Node insert(Node node, int key, boolean[] isTaller) {
        if (node == null) {
            node = new Node(key);
            isTaller[0] = true;
            return node;
        }

        if (key < node.key) {
            node.left = insert(node.left, key, isTaller);
            if (isTaller[0]) {
                node.balanceFactor--;
            }
        } else {
            node.right = insert(node.right, key, isTaller);
            if (isTaller[0]) {
                node.balanceFactor++;
            }
        }

        performRotations(node);

        if (isTaller[0] && node.balanceFactor == 0) {
            isTaller[0] = false;
        }
        return node;
    }

    public Node delete(Node node, int key, boolean[] isShorter) {
        if (node == null) {
            return null;
        }

        if (key < node.key) {
            node.left = delete(node.left, key, isShorter);
            if (isShorter[0]) {
                node.balanceFactor++;
            }
        } else if (key > node.key) {
            node.right = delete(node.right, key, isShorter);
            if (isShorter[0]) {
                node.balanceFactor--;
            }
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
                isShorter[0] = true;
            } else {
                Node successor = getSuccessor(node.right);
                node.key = successor.key;
                node.right = delete(node.right, successor.key, isShorter);
                if (isShorter[0]) {
                    node.balanceFactor--;
                }
            }
        }

        if (node != null) {
            performRotations(node);
            if (isShorter[0] && node.balanceFactor != 0) {
                isShorter[0] = false;
            }
        }
        return node;
    }

    private Node getSuccessor(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private void performRotations(Node node) {
        if (node.balanceFactor == -2) {
            Node child = node.left;
            if (child.balanceFactor == -1) {
                node = rotateRight(node);
                node.balanceFactor = 0;
                child.balanceFactor = 0;
            } else if (child.balanceFactor == 1) {
                node = rotateLeftRight(node);
            }
        } else if (node.balanceFactor == 2) {
            Node child = node.right;
            if (child.balanceFactor == 1) {
                node = rotateLeft(node);
                node.balanceFactor = 0;
                child.balanceFactor = 0;
            } else if (child.balanceFactor == -1) {
                node = rotateRightLeft(node);
            }
        }
    }

    private Node rotateRight(Node node) {
        Node child = node.left;
        node.left = child.right;
        child.right = node;
        return child;
    }

    private Node rotateLeft(Node node) {
        Node child = node.right;
        node.right = child.left;
        child.left = node;
        return child;
    }

    private Node rotateLeftRight(Node node) {
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }

    private Node rotateRightLeft(Node node) {
        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }

    public void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.key + "[" + node.balanceFactor + "] ");
            printInOrder(node.right);
        }
    }

    public Node getRoot() {
        return root;
    }

    public void insert(int key) {
        boolean[] isTaller = {false};
        root = insert(root, key, isTaller);
    }

    public void delete(int key) {
        boolean[] isShorter = {false};
        root = delete(root, key, isShorter);
    }

    public void printInOrder() {
        printInOrder(root);
        System.out.println();
    }
}
