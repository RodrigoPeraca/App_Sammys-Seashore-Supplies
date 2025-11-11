import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SammyAppGUI extends JFrame {
    private JComboBox<EquipmentType> comboEquipamentos;
    private JTextField campoMinutos;
    private JCheckBox checkAula;
    private JTextArea areaResultado;
    private List<Rental> alugueis = new ArrayList<>();

    public SammyAppGUI() {
        super("Sammy's Seashore Supplies");

        setLayout(new BorderLayout());
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(5, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        comboEquipamentos = new JComboBox<>(EquipmentType.values());
        campoMinutos = new JTextField();
        checkAula = new JCheckBox("Incluir aula?");
        areaResultado = new JTextArea();
        areaResultado.setEditable(false);

        JButton botaoConfirmar = new JButton("Confirmar Aluguel");

        painel.add(new JLabel("Equipamento:"));
        painel.add(comboEquipamentos);
        painel.add(new JLabel("Tempo de aluguel (min):"));
        painel.add(campoMinutos);
        painel.add(new JLabel("Aula necessária:"));
        painel.add(checkAula);
        painel.add(new JLabel());
        painel.add(botaoConfirmar);

        add(painel, BorderLayout.NORTH);
        add(new JScrollPane(areaResultado), BorderLayout.CENTER);

        comboEquipamentos.addActionListener(e -> {
            EquipmentType tipo = (EquipmentType) comboEquipamentos.getSelectedItem();
            checkAula.setVisible(tipo.requerAula);
        });

        botaoConfirmar.addActionListener(e -> confirmarAluguel());

        EquipmentType tipo = (EquipmentType) comboEquipamentos.getSelectedItem();
        checkAula.setVisible(tipo.requerAula);
    }

    private void confirmarAluguel() {
        try {
            EquipmentType tipo = (EquipmentType) comboEquipamentos.getSelectedItem();
            int minutos = Integer.parseInt(campoMinutos.getText());
            boolean incluirAula = checkAula.isVisible() && checkAula.isSelected();

            Equipment eq;

            if (tipo.requerAula) {
                if (incluirAula) {
                    eq = new EquipmentWithLesson(tipo.id, tipo.nome, tipo.taxaBasica, tipo.valorHora);
                } else {
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

            areaResultado.append(r.toString() + "\n-----------------------------\n");
            campoMinutos.setText("");
            checkAula.setSelected(false);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Digite um valor numérico válido para os minutos!", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SammyAppGUI().setVisible(true));
    }
}
