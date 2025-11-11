public class EquipmentWithoutLesson extends Equipment {
    public EquipmentWithoutLesson(int id, String nome, double taxaBasica, double valorHora) {
        super(id, nome, taxaBasica, valorHora);
        if (id < 6 || id > 8) {
            throw new IllegalArgumentException("Equipamento inválido para EquipmentWithoutLesson");
        }
    }

    @Override
    public double calcularCusto(int minutos) {
        double horas = Math.ceil(minutos / 60.0);
        return taxaBasica + valorHora * horas;
    }
}
