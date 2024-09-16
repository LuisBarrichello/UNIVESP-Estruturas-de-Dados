import java.util.NoSuchElementException;

public class Queue<T> {
    //   Uma fila é uma estrutura linear na qual as inserções
//     ocorrem no final e as exclusões ocorrem no inicio.
    private int front;
    private int back;
    private int size;
    private final int maxSize;
    private final T[] structure;

    public Queue(int size) {
        this.maxSize = size;
        this.front = 0;
        this.back = -1;
        this.size = 0;
        this.structure = (T[]) new Object[size];
    }

    public boolean isFull() {
        return size == maxSize;
    }

    public boolean isEmpty() {
        return front == 0;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(structure[(front + i) % maxSize] + " ");
        }
        System.out.println();
    }

    public void enqueue(T item) {
        if (isFull()) {
            throw new IllegalStateException("Queue is already full!");
        }
        back = (back + 1) % maxSize; // Mover 'back' circularmente
        structure[back] = item;
        size++;
    }

    public T dequeue()  {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty!");
        }
        T item = structure[front];
        front = (front + 1) % maxSize;
        size--;
        return item;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty!");
        }
        return structure[front];  // Retornar o item no 'front' sem removê-lo
    }

    public int getSize() {
        return size;
    }
}
