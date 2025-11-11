import java.io.Serializable;

public abstract class Equipment implements Serializable {
    protected int id;
    protected String nome;
    protected double taxaBasica;
    protected double valorHora;

    public Equipment(int id, String nome, double taxaBasica, double valorHora) {
        this.id = id;
        this.nome = nome;
        this.taxaBasica = taxaBasica;
        this.valorHora = valorHora;
    }

    public abstract double calcularCusto(int minutos);

    public int getId() { return id; }
    public String getNome() { return nome; }
    public double getTaxaBasica() { return taxaBasica; }
    public double getValorHora() { return valorHora; }

    @Override
    public String toString() {
        return String.format("[%d] %s - Taxa: $%.2f/h - Base: $%.2f", id, nome, valorHora, taxaBasica);
    }
}
