import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

class HashTable<K, V> {
    private final int capacity;
    private final List<LinkedList<Entry<K, V>>> table;
    private int size;

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.table = new ArrayList<>(capacity);
        this.size = 0;

        // Inicializa cada "bucket" com uma lista encadeada vazia
        for (int i = 0; i < capacity; i++) {
            table.add(new LinkedList<>());
        }
    }

    // Função de Hash
    private int hash(K key) {
        return Math.abs(key.hashCode() % capacity);
    }

    // Inserção
    public void insertItem(K key, V value) {
        int hash = hash(key);
        LinkedList<Entry<K, V>> bucket = table.get(hash);

        // Verifica se a chave já existe e atualiza o valor
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }

        // Caso a chave não exista, adiciona nova entrada
        bucket.add(new Entry<>(key, value));
        size++;
    }

    // Recuperação
    public V retrieveItem(K key) {
        int hash = hash(key);
        LinkedList<Entry<K, V>> bucket = table.get(hash);

        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null; // Retorna nulo se a chave não for encontrada
    }

    // Remoção
    public void deleteItem(K key) {
        int hash = hash(key);
        LinkedList<Entry<K, V>> bucket = table.get(hash);

        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                bucket.remove(entry);
                size--;
                return;
            }
        }
    }

    // Retorna o número de entradas na tabela
    public int size() {
        return size;
    }

    // Retorna um conjunto de todas as chaves
    public List<K> keySet() {
        List<K> keys = new ArrayList<>();
        for (LinkedList<Entry<K, V>> bucket : table) {
            for (Entry<K, V> entry : bucket) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    // Retorna uma coleção de todos os valores
    public List<V> values() {
        List<V> values = new ArrayList<>();
        for (LinkedList<Entry<K, V>> bucket : table) {
            for (Entry<K, V> entry : bucket) {
                values.add(entry.getValue());
            }
        }
        return values;
    }

    // Retorna um conjunto de todas as entradas (pares chave-valor)
    public List<Entry<K, V>> entrySet() {
        List<Entry<K, V>> entries = new ArrayList<>();
        for (LinkedList<Entry<K, V>> bucket : table) {
            entries.addAll(bucket);
        }
        return entries;
    }
}