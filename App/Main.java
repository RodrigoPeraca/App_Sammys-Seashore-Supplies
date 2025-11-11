import java.io.*;
import java.util.*;

public class Main {
    private static final String ARQUIVO = "alugueis.dat";
    private static List<Rental> alugueis = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        carregarArquivo();

        System.out.println("=== SAMMY'S SEASHORE SUPPLIES ===");
        boolean rodando = true;

        while (rodando) {
            try {
                System.out.println("\nEscolha um equipamento:");
                for (EquipmentType e : EquipmentType.values()) {
                    System.out.printf("%d - %s\n", e.id, e.nome);
                }
                System.out.print("ID: ");
                int id = Integer.parseInt(sc.nextLine());
                EquipmentType tipo = EquipmentType.getById(id);
                if (tipo == null) throw new IllegalArgumentException("Equipamento inválido!");

                System.out.print("Tempo de aluguel (minutos): ");
                int minutos = Integer.parseInt(sc.nextLine());

                Equipment eq;
                if (tipo.requerAula) {
                    System.out.print("Deseja incluir aula? (s/n): ");
                    String aula = sc.nextLine();
                    eq = new EquipmentWithLesson(tipo.id, tipo.nome, tipo.taxaBasica, tipo.valorHora);
                    if (aula.equalsIgnoreCase("n")) {
                        eq = new Equipment(tipo.id, tipo.nome, tipo.taxaBasica, tipo.valorHora) {
                            @Override
                            public double calcularCusto(int minutos) {
                                double horas = Math.ceil(minutos / 60.0);
                                return taxaBasica + valorHora * horas;
                            }
                        };
                    }
                } else {
                    eq = new EquipmentWithoutLesson(tipo.id, tipo.nome, tipo.taxaBasica, tipo.valorHora);
                }

                Rental r = new Rental(minutos, eq);
                alugueis.add(r);
                System.out.println("\n" + r);

            } catch (NumberFormatException e) {
                System.out.println("Erro: valor numérico inválido!");
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

            System.out.print("\nDeseja fazer outro aluguel? (s/n): ");
            rodando = sc.nextLine().equalsIgnoreCase("s");
        }

        salvarArquivo();
        System.out.println("Encerrando o sistema. Aluguéis salvos em arquivo.");
        sc.close();
    }

    @SuppressWarnings("unchecked")
    private static void carregarArquivo() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
            alugueis = (List<Rental>) ois.readObject();
            System.out.println("Aluguéis carregados com sucesso!");
        } catch (Exception e) {
            System.out.println("Nenhum aluguel anterior encontrado.");
        }
    }

    private static void salvarArquivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(alugueis);
        } catch (IOException e) {
            System.out.println("Erro ao salvar os aluguéis: " + e.getMessage());
        }
    }
}
