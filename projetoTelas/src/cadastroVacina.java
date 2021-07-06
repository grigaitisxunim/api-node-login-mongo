import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ComboBoxEditor;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import java.awt.Panel;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.TextField;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class cadastroVacina extends JFrame {

	private JPanel contentPane;
	private JTextField vacinaField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cadastroVacina frame = new cadastroVacina();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public cadastroVacina() {
		setFont(new Font("Arial", Font.BOLD, 14));
		setTitle("Cadastrar Vacina");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);

		JComboBox qtdDosesBox_1_1 = new JComboBox();
		qtdDosesBox_1_1.setModel(new DefaultComboBoxModel(new String[] { "1", "2" }));
		qtdDosesBox_1_1.setBounds(404, 296, 66, 28);
		contentPane.add(qtdDosesBox_1_1);
		Integer.parseInt((String) qtdDosesBox_1_1.getSelectedItem());

		JLabel lblDataDeVacinacao = new JLabel("Periodo entre as doses");
		lblDataDeVacinacao.setForeground(new Color(47, 79, 79));
		lblDataDeVacinacao.setFont(new Font("Dialog", Font.BOLD, 14));
		lblDataDeVacinacao.setBounds(229, 249, 175, 20);
		contentPane.add(lblDataDeVacinacao);

		JLabel lblVacina = new JLabel("Vacina");
		lblVacina.setBackground(new Color(255, 255, 255));
		lblVacina.setForeground(new Color(47, 79, 79));
		lblVacina.setFont(new Font("Dialog", Font.BOLD, 14));
		lblVacina.setBounds(229, 198, 69, 20);
		contentPane.add(lblVacina);

		vacinaField = new JTextField();
		vacinaField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0987654321@!#&*()+$%-=[]:><,?|^~´`¨{};'";
				if (caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		vacinaField.setColumns(10);
		vacinaField.setBounds(308, 200, 141, 20);
		contentPane.add(vacinaField);

		JLabel lblQuantidadeDeDoses = new JLabel("Quantidade de Doses");
		lblQuantidadeDeDoses.setForeground(new Color(47, 79, 79));
		lblQuantidadeDeDoses.setFont(new Font("Dialog", Font.BOLD, 14));
		lblQuantidadeDeDoses.setBounds(229, 298, 158, 20);
		contentPane.add(lblQuantidadeDeDoses);

		JComboBox periodoBox_1 = new JComboBox();
		periodoBox_1.setModel(new DefaultComboBoxModel(new String[] { "0", "14", "20", "28", "30", "60", "90" }));
		periodoBox_1.setBounds(404, 247, 66, 28);
		contentPane.add(periodoBox_1);

		JButton btnSair = new JButton("Sair");
		btnSair.setForeground(new Color(47, 79, 79));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastroVacina.this.dispose();
				MenuVacina frame = new MenuVacina();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			}
		});
		btnSair.setFont(new Font("Dialog", Font.BOLD, 14));
		btnSair.setBackground(SystemColor.textHighlightText);
		btnSair.setBounds(223, 389, 115, 29);
		contentPane.add(btnSair);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setForeground(new Color(47, 79, 79));
		btnCadastrar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int periodo = Integer.parseInt((String) periodoBox_1.getSelectedItem());
				int doses = Integer.parseInt((String) qtdDosesBox_1_1.getSelectedItem());
				if (vacinaField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Campo vacina não" + " preenchido");
				}/* else if (periodo < 0) {
					JOptionPane.showMessageDialog(null, "Campo perído não" + " preenchido");
				} */
				else if (doses == 0) {
					JOptionPane.showMessageDialog(null, "Campo doses não" + " preenchido");
				} else {
					String vacina = vacinaField.getText();
					Vacina v = new Vacina();
					v.setVacina(vacina);
					v.setPeriodo(periodo);
					v.setQtdDoses(doses);
					v.inserir();
					cadastroVacina frame = new cadastroVacina();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					dispose();

				}

			}
		});
		btnCadastrar.setBounds(361, 389, 115, 29);
		contentPane.add(btnCadastrar);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(cadastroVacina.class.getResource("/icons/TelaCadastroVacinaa.png")));
		lblNewLabel.setBounds(0, 0, 684, 461);
		contentPane.add(lblNewLabel);
	}

}
