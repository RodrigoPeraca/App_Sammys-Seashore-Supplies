public class EquipmentWithLesson extends Equipment {
    private static final double TAXA_AULA = 20.0;

    public EquipmentWithLesson(int id, String nome, double taxaBasica, double valorHora) {
        super(id, nome, taxaBasica, valorHora);
        if (id < 1 || id > 5) {
            throw new IllegalArgumentException("Equipamento não requer aula: ID inválido para EquipmentWithLesson");
        }
    }

    @Override
    public double calcularCusto(int minutos) {
        double horas = Math.ceil(minutos / 60.0);
        return taxaBasica + valorHora * horas + TAXA_AULA;
    }
}
