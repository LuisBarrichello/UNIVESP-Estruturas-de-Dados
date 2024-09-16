import java.util.ArrayList;
import java.util.List;

public class Stack<T> {
/*  Uma pilha é uma estrutura linear na qual inserções e remoções ocorrem no topo da pilha. */

    private int maxSize;
    private int length;
    private List<T> structure;

    public Stack(int size) {
        this.maxSize = size;
        this.length = 0;
        this.structure = new ArrayList<>(size);
    }


    public boolean isFull() {
        return length == maxSize;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public void print() {
        System.out.println(structure);
    }

    public int size() {
        return structure.size();
    }

    public void push(T item) throws Exception {
        boolean isFull = this.isFull();
        if (!isFull) {
            structure.add(item);
            length++;
        } else  {
            throw new Exception("Stack is already full!");
        }
    }

    public T pop() throws Exception {
        boolean isEmpty = this.isEmpty();
        if (!isEmpty) {
            T item = structure.remove(length - 1);
            length--;
            return item;
        } else {
            throw new Exception("Stack is Empty");
        }
    }
}
