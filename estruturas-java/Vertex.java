import java.util.*;

class Vertex {
    private String nome;

    public Vertex(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vertex) {
            Vertex other = (Vertex) obj;
            return nome.equals(other.getNome());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}