public enum EquipmentType {
    JET_SKI(1, "Jet Ski", 50, 30, true),
    BARCO_PONTAO(2, "Barco Pontão", 40, 30, true),
    BARCO_REMO(3, "Barco a Remo", 15, 20, true),
    CANOA(4, "Canoa", 12, 20, true),
    CAIAQUE(5, "Caiaque", 10, 20, true),
    CADEIRA_PRAIA(6, "Cadeira de Praia", 2, 5, false),
    GUARDA_SOL(7, "Guarda-sol", 1, 5, false),
    GAZEBO(8, "Gazebo", 3, 7, false);

    public final int id;
    public final String nome;
    public final double taxaBasica;
    public final double valorHora;
    public final boolean requerAula;

    EquipmentType(int id, String nome, double taxaBasica, double valorHora, boolean requerAula) {
        this.id = id;
        this.nome = nome;
        this.taxaBasica = taxaBasica;
        this.valorHora = valorHora;
        this.requerAula = requerAula;
    }

    public static EquipmentType getById(int id) {
        for (EquipmentType e : values()) {
            if (e.id == id) return e;
        }
        return null;
    }
}
