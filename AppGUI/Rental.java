import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        registrarLog(); // <-- novo
    }

    private void registrarLog() {
        String data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String linha = String.format("[%s] Contrato #%d - %s - %d min - Total: $%.2f%n",
                data, numeroContrato, equipamento.getNome(), minutos, valorTotal);

        try (FileWriter fw = new FileWriter("log_saida.txt", true)) {
            fw.write(linha);
        } catch (IOException e) {
            System.err.println("Erro ao escrever log: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return String.format(
            "Contrato #%d\nEquipamento: %s\nTempo: %d minutos\nTotal: $%.2f\n",
            numeroContrato, equipamento.getNome(), minutos, valorTotal
        );
    }
}
