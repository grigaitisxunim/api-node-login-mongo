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

public class ConsultarApagarVacina extends JFrame {

	private JPanel contentPane;
	private JComboBox vacinaField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultarApagarVacina frame = new ConsultarApagarVacina();
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
	public ConsultarApagarVacina() {
		setFont(new Font("Arial", Font.BOLD, 14));
		setTitle("Consultar Vacina");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		Vacina v = new Vacina();

		JComboBox qtdDosesBox = new JComboBox();
		qtdDosesBox.setModel(new DefaultComboBoxModel(new String[] { "1", "2" }));
		qtdDosesBox.setBounds(404, 296, 66, 28);
		qtdDosesBox.setEnabled(false);
		contentPane.add(qtdDosesBox);
		Integer.parseInt((String) qtdDosesBox.getSelectedItem());

		JLabel lblPeriodo = new JLabel("Periodo entre as doses");
		lblPeriodo.setForeground(new Color(47, 79, 79));
		lblPeriodo.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPeriodo.setBounds(229, 249, 183, 20);
		contentPane.add(lblPeriodo);

		JLabel lblVacina = new JLabel("Vacina");
		lblVacina.setBackground(new Color(255, 255, 255));
		lblVacina.setForeground(new Color(47, 79, 79));
		lblVacina.setFont(new Font("Dialog", Font.BOLD, 14));
		lblVacina.setBounds(229, 198, 69, 20);
		contentPane.add(lblVacina);

		JComboBox vacinaField = new JComboBox();
		vacinaField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String vacina = vacinaField.getSelectedItem().toString();
				v.setVacina(vacina);
				v.pesquisarVacina();

			}
		});
		vacinaField.setModel(new javax.swing.DefaultComboBoxModel<>(new Vacina().listarVacinas()));
		vacinaField.setBounds(308, 200, 141, 20);
		vacinaField.setEditable(false);
		vacinaField.setEditable(false);
		contentPane.add(vacinaField);
		setLocationRelativeTo(null);

		JLabel lblQuantidadeDeDoses = new JLabel("Quantidade de Doses");
		lblQuantidadeDeDoses.setForeground(new Color(47, 79, 79));
		lblQuantidadeDeDoses.setFont(new Font("Dialog", Font.BOLD, 14));
		lblQuantidadeDeDoses.setBounds(229, 298, 158, 20);
		contentPane.add(lblQuantidadeDeDoses);

		JComboBox periodoBox = new JComboBox();
		periodoBox.setModel(new DefaultComboBoxModel(new String[] { "0", "14", "20", "28", "30", "60", "90" }));
		periodoBox.setBounds(404, 247, 66, 28);
		periodoBox.setEnabled(false);
		contentPane.add(periodoBox);

		JButton btnSair = new JButton("Sair");
		btnSair.setForeground(new Color(47, 79, 79));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultarApagarVacina.this.dispose();
				MenuVacina frame = new MenuVacina();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			}
		});
		btnSair.setFont(new Font("Dialog", Font.BOLD, 14));
		btnSair.setBackground(SystemColor.textHighlightText);
		btnSair.setBounds(142, 389, 115, 29);
		contentPane.add(btnSair);

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int doses = Integer.parseInt((String) qtdDosesBox.getSelectedItem());
				int periodo = Integer.parseInt((String) periodoBox.getSelectedItem());
				if (vacinaField.getSelectedItem().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "Campo vacina não" + " preenchido");
				} else if (doses == 0) {
					JOptionPane.showMessageDialog(null, "Campo doses não" + " preenchido");
				}/* else if (periodo == 0) {
					JOptionPane.showMessageDialog(null, "Campo período não" + " preenchido");
				}*/ else {
					String vacina = vacinaField.getSelectedItem().toString();
					v.setVacina(vacina);
					v.setPeriodo(periodo);
					v.setQtdDoses(doses);
					v.atualizar();
					v.pesquisarVacina();
					dispose();
					ConsultarApagarVacina frame = new ConsultarApagarVacina();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

				}

			}
		});
		btnAtualizar.setForeground(new Color(47, 79, 79));
		btnAtualizar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnAtualizar.setBounds(286, 389, 115, 29);
		btnAtualizar.setEnabled(false);
		contentPane.add(btnAtualizar);

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setForeground(new Color(47, 79, 79));
		btnConsultar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String vacina = vacinaField.getSelectedItem().toString();
				v.setVacina(vacina);
				v.pesquisarVacina();
				v.listar();
				vacinaField.setEnabled(true);
				vacinaField.setEditable(true);
				vacinaField.setEditable(true);
				qtdDosesBox.setEnabled(true);
				periodoBox.setEnabled(true);
				btnAtualizar.setEnabled(true);
				vacinaField.setSelectedItem(v.getVacina());
				qtdDosesBox.setSelectedItem(Integer.toString(v.getQtdDoses()));
				periodoBox.setSelectedItem(Integer.toString(v.getPeriodo()));
				// codigoVacina.setText(Integer.toString(v.getCodvacina()));
			}
		});
		btnConsultar.setBounds(429, 389, 115, 29);
		contentPane.add(btnConsultar);

		JLabel LabelFundo = new JLabel("New label");
		LabelFundo.setIcon(new ImageIcon(ConsultarApagarVacina.class.getResource("/icons/TelaConsultarVacina.png")));
		LabelFundo.setBounds(0, 0, 684, 461);
		contentPane.add(LabelFundo);

	}
}
