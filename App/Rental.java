import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class Rental implements Serializable {
    private static final AtomicInteger contador = new AtomicInteger(1);

    private int numeroContrato;
    private int minutos;
    private double valorTotal;
    private Equipment equipamento;

    public Rental(int minutos, Equipment equipamento) {
        if (minutos <= 0) throw new IllegalArgumentException("Tempo de aluguel inválido!");
        this.numeroContrato = contador.getAndIncrement();
        this.minutos = minutos;
        this.equipamento = equipamento;
        this.valorTotal = equipamento.calcularCusto(minutos);
    }

    @Override
    public String toString() {
        return String.format(
            "Contrato #%d\nEquipamento: %s\nTempo: %d minutos\nTotal: $%.2f\n",
            numeroContrato, equipamento.getNome(), minutos, valorTotal
        );
    }
}
