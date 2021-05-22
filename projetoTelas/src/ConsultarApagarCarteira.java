

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class ConsultarApagarCarteira extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultarApagarCarteira frame = new ConsultarApagarCarteira();
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
	public ConsultarApagarCarteira() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 623, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Carteira c = new Carteira();
		
		
		
		JTextField doseField = new JTextField();
		doseField.setBounds(312, 115, 39, 28);
		contentPane.add(doseField);
		
		JLabel lblNewLabel = new JLabel("N\u00DAMERO DO SUS");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(143, 30, 140, 14);
		contentPane.add(lblNewLabel);
		TextField susField = new TextField();
		susField.setBounds(312, 25, 127, 26);
		contentPane.add(susField);

		JLabel lblNomeDaVacina = new JLabel("NOME DA VACINA");
		lblNomeDaVacina.setForeground(new Color(255, 255, 255));
		lblNomeDaVacina.setBackground(Color.WHITE);
		lblNomeDaVacina.setFont(new Font("Arial", Font.BOLD, 14));
		lblNomeDaVacina.setBounds(143, 63, 127, 20);
		contentPane.add(lblNomeDaVacina);

		JLabel lblNumerodeDoses = new JLabel("N\u00DAMERO DE DOSES");
		lblNumerodeDoses.setForeground(new Color(255, 255, 255));
		lblNumerodeDoses.setFont(new Font("Arial", Font.BOLD, 14));
		lblNumerodeDoses.setBounds(143, 115, 184, 20);
		contentPane.add(lblNumerodeDoses);
		JTextField primeiradosSpinner = new JTextField();
		//JSpinner.DateEditor editor = new JSpinner.DateEditor(primeiradosSpinner, "dd/MM/yy");
		primeiradosSpinner.setBounds(190, 192, 80, 26);
		contentPane.add(primeiradosSpinner);

		JLabel lblDataProximaDose = new JLabel("Data da 2\u00BA dose");
		lblDataProximaDose.setForeground(new Color(255, 255, 255));
		lblDataProximaDose.setFont(new Font("Arial", Font.BOLD, 14));
		lblDataProximaDose.setBounds(347, 162, 140, 20);
		contentPane.add(lblDataProximaDose);

		JTextField segundadosSpinner = new JTextField();
		//JSpinner.DateEditor editor1 = new JSpinner.DateEditor(segundadosSpinner, "dd/MM/yy");
		segundadosSpinner.setBounds(358, 192, 81, 26);
		contentPane.add(segundadosSpinner);
		//System.out.println(comp);
		/*if(comp > 1) {
			segundadosSpinner.setEnabled(true);
		}else {
			segundadosSpinner.setEnabled(false);
		}*/
		

		JComboBox nomevacField = new JComboBox();
		nomevacField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String vacina = nomevacField.getSelectedItem().toString();
				c.setVacina(vacina);
				c.pesquisarVacina();

			}
		});
		nomevacField.setModel(new javax.swing.DefaultComboBoxModel<>(new Vacina().listarVacinas()));
		nomevacField.setBounds(312, 60, 127, 28);
		contentPane.add(nomevacField);
		setLocationRelativeTo(null);

		JButton btnCadastrarVacina = new JButton("Consultar");
		btnCadastrarVacina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String vacina = c.getVacina();
				int qtdDoses = c.getQtdDoses();
				String primeiraDose = c.getPrimeiraDose();
				String segundaDose = c.getSegundaDose();
				String sus = susField.getText();
				c.setSus(sus);
				c.pesquisarVacina();
				c.pesquisarCidadao();
				c.listar();
				nomevacField.setSelectedItem(c.getVacina());
				doseField.setText(Integer.toString(c.getQtdDoses()));
				primeiradosSpinner.setText(c.getPrimeiraDose());
				segundadosSpinner.setText(c.getSegundaDose());
				c.setPrimeiraDose(primeiraDose);
				c.setSegundaDose(segundaDose);
		

			}
		});
		btnCadastrarVacina.setBackground(SystemColor.textHighlightText);
		btnCadastrarVacina.setFont(new Font("Arial", Font.BOLD, 12));
		btnCadastrarVacina.setBounds(418, 265, 151, 29);
		contentPane.add(btnCadastrarVacina);
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String vacina = susField.getText();
				int qtdDoses = Integer.parseInt(doseField.getText());
				String primeiraDose = primeiradosSpinner.getText();
				String segundaDose = segundadosSpinner.getText();
				String sus = susField.getText();
				c.setSus(sus);
				c.pesquisarVacina();
				c.setVacina(vacina);
				c.setQtdDoses(qtdDoses);
				c.setPrimeiraDose(primeiraDose);
				c.setSegundaDose(segundaDose);
				c.atualizar();
				dispose();
			}
		});
		btnAtualizar.setFont(new Font("Arial", Font.BOLD, 12));
		btnAtualizar.setBackground(Color.WHITE);
		btnAtualizar.setBounds(246, 265, 151, 29);
		contentPane.add(btnAtualizar);

		JButton bntSair = new JButton("Sair");
		bntSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultarApagarCarteira.this.dispose();
			}
		});
		bntSair.setBackground(SystemColor.textHighlightText);
		bntSair.setFont(new Font("Arial", Font.BOLD, 12));
		bntSair.setBounds(100, 264, 120, 30);
		contentPane.add(bntSair);

		JLabel lblDataDeVacinacao = new JLabel("Data da  1\u00BA dose");
		lblDataDeVacinacao.setForeground(new Color(255, 255, 255));
		lblDataDeVacinacao.setFont(new Font("Arial", Font.BOLD, 14));
		lblDataDeVacinacao.setBounds(155, 161, 140, 20);
		contentPane.add(lblDataDeVacinacao);
		
		JLabel fundo = new JLabel("fundo");
		fundo.setIcon(new ImageIcon(ConsultarApagarCarteira.class.getResource("/icons/fundo-abstrato-colorido_23-2148807053.jpg")));
		fundo.setBounds(0, -5, 607, 356);
		contentPane.add(fundo);
	}

}
