
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

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
		setFont(new Font("Arial", Font.BOLD, 14));
		setTitle("Consultar Carteira de Vacina\u00E7\u00E3o");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);

		Carteira c = new Carteira();

		JComboBox doseField = new JComboBox();
		doseField.setMaximumRowCount(2);
		doseField.setModel(new DefaultComboBoxModel(new String[] {"1.1", "1", "2"}));
		doseField.setSelectedIndex(1);
		doseField.setBounds(385, 260, 39, 28);
		doseField.setEnabled(false);
		
		contentPane.add(doseField);

		JLabel lblNewLabel = new JLabel("N\u00FAmero do sus");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(47, 79, 79));
		lblNewLabel.setBounds(206, 183, 106, 19);
		contentPane.add(lblNewLabel);
		
		MaskFormatter mascaraSus = null;
        MaskFormatter mascaraData = null;
        MaskFormatter mascaraData2 = null;

        try{
               mascaraSus = new MaskFormatter("###############");
               mascaraData2 = new MaskFormatter("##/##/####");
               mascaraData = new MaskFormatter("##/##/####");
        }
        catch(ParseException excp) {
               System.err.println("Erro na formatação: " + excp.getMessage());
               System.exit(-1);
        }
		
		JTextField susField = new JFormattedTextField(mascaraSus);
		susField.setBounds(375, 172, 127, 28);
		contentPane.add(susField);

		JLabel lblNomeDaVacina = new JLabel("Nome da vacina");
		lblNomeDaVacina.setForeground(new Color(47, 79, 79));
		lblNomeDaVacina.setBackground(Color.WHITE);
		lblNomeDaVacina.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNomeDaVacina.setBounds(206, 223, 127, 20);
		contentPane.add(lblNomeDaVacina);

		JLabel lblNumerodeDoses = new JLabel("N\u00FAmero de doses");
		lblNumerodeDoses.setForeground(new Color(47, 79, 79));
		lblNumerodeDoses.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNumerodeDoses.setBounds(206, 262, 124, 19);
		contentPane.add(lblNumerodeDoses);

		JTextField primeiraDoseF = new JFormattedTextField(mascaraData);
		primeiraDoseF.setBounds(231, 333, 80, 26);
		primeiraDoseF.setColumns(10);
		primeiraDoseF.setEnabled(false);
		contentPane.add(primeiraDoseF);

		JTextField segundaDoseF = new JFormattedTextField(mascaraData2);
		segundaDoseF.setBounds(398, 333, 80, 26);
		segundaDoseF.setColumns(10);
		segundaDoseF.setEnabled(false);
		contentPane.add(segundaDoseF);

		JLabel lblDataProximaDose = new JLabel("Data da 2\u00BA dose");
		lblDataProximaDose.setForeground(new Color(47, 79, 79));
		lblDataProximaDose.setFont(new Font("Dialog", Font.BOLD, 14));
		lblDataProximaDose.setBounds(375, 302, 108, 19);
		contentPane.add(lblDataProximaDose);

		JComboBox nomevacField = new JComboBox();
		nomevacField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String vacina = nomevacField.getSelectedItem().toString();
				c.setVacina(vacina);
				c.pesquisarVacina();

			}
		});
		nomevacField.setModel(new javax.swing.DefaultComboBoxModel<>(new Vacina().listarVacinas()));
		nomevacField.setBounds(375, 221, 127, 28);
		nomevacField.setEnabled(false);
		contentPane.add(nomevacField);
		setLocationRelativeTo(null);
		JButton btnNewButton = new JButton("Selecionar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int teste = 2;
				if (teste == Integer.parseInt((String) doseField.getSelectedItem())) {
					segundaDoseF.setEnabled(true);
				} else {
					segundaDoseF.setEnabled(false);
				}
			}
		});
		btnNewButton.setBounds(385, 359, 115, 19);
		contentPane.add(btnNewButton);

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setForeground(new Color(47, 79, 79));
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nomevacField.getSelectedItem().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "Campo vacina não" + " preenchido");

				} else if (primeiraDoseF.getText().equals("  /  /    ")) {
					JOptionPane.showMessageDialog(null, "Campo primeira dose não" + " preenchido");
				} else if (segundaDoseF.getText().equals("  /  /    ")) {
					JOptionPane.showMessageDialog(null, "Campo segunda dose não" + " preenchido");
				} else {
					JOptionPane.showMessageDialog(null, "Carteira de vacina atualizada com sucesso!!");
					String vacina = nomevacField.getSelectedItem().toString();
					int qtdDoses = doseField.getSelectedIndex();
					String primeiraDose = primeiraDoseF.getText();
					String segundaDose = segundaDoseF.getText();
					c.pesquisarVacina();
					try {
						c.pesquisarCarteira();
						c.setVacina(vacina);
						c.setQtdDoses(qtdDoses);
						c.setPrimeiraDose(primeiraDose);
						c.setSegundaDose(segundaDose);
						c.atualizar();
						dispose();
						ConsultarApagarCarteira frame = new ConsultarApagarCarteira();
						frame.setVisible(true);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});

		btnAtualizar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnAtualizar.setBackground(Color.WHITE);
		btnAtualizar.setBounds(348, 413, 127, 29);
		btnAtualizar.setEnabled(false);
		contentPane.add(btnAtualizar);

		JButton bntSair = new JButton("Sair");
		bntSair.setForeground(new Color(47, 79, 79));
		bntSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultarApagarCarteira.this.dispose();
				MenuCarteira frame = new MenuCarteira();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		bntSair.setBackground(SystemColor.textHighlightText);
		bntSair.setFont(new Font("Dialog", Font.BOLD, 14));
		bntSair.setBounds(72, 413, 120, 30);
		contentPane.add(bntSair);

		JLabel lblDataDeVacinacao = new JLabel("Data da  1\u00BA dose");
		lblDataDeVacinacao.setForeground(new Color(47, 79, 79));
		lblDataDeVacinacao.setFont(new Font("Dialog", Font.BOLD, 14));
		lblDataDeVacinacao.setBounds(206, 302, 112, 19);
		contentPane.add(lblDataDeVacinacao);

		JButton btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					c.pesquisarCarteira();
					c.apagar();
				} catch (SQLException e1) {

					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Não foi possível apagar carteira");
				}
			}
		});

		btnApagar.setForeground(new Color(47, 79, 79));
		btnApagar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnApagar.setBackground(Color.WHITE);
		btnApagar.setBounds(212, 413, 127, 29);
		btnApagar.setEnabled(false);
		contentPane.add(btnApagar);

		JButton btnCadastrarVacina = new JButton("Consultar");
		btnCadastrarVacina.setForeground(new Color(47, 79, 79));
		btnCadastrarVacina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sus = susField.getText();
				c.setSus(sus);
				try {
					c.pesquisarVacina();
					c.pesquisarCidadao();
					c.listar();
					susField.setEditable(false);
					nomevacField.setEnabled(true);
					doseField.setEnabled(true);
					primeiraDoseF.setEnabled(true);
					btnAtualizar.setEnabled(true);
					btnApagar.setEnabled(true);
					nomevacField.setSelectedItem(c.getVacina());
					doseField.setSelectedIndex(c.getQtdDoses());
					System.out.println(doseField.getSelectedItem());	
					primeiraDoseF.setText(c.getPrimeiraDoseFormatado());
					if(c.getQtdDoses() == 2) {
					segundaDoseF.setText(c.getSegundaDoseFormatado());
					segundaDoseF.setEnabled(true);
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Não existe um cidadão para esse Nº SUS!!");
					e1.printStackTrace();
				} catch (NullPointerException e2) {
					JOptionPane.showMessageDialog(null, "DIGITE O NUMERO DO SUS!!");
					e2.printStackTrace();
				}
			}
		});
		btnCadastrarVacina.setBackground(SystemColor.textHighlightText);
		btnCadastrarVacina.setFont(new Font("Dialog", Font.BOLD, 14));
		btnCadastrarVacina.setBounds(488, 413, 127, 29);
		contentPane.add(btnCadastrarVacina);

		JLabel fundo = new JLabel("fundo");
		fundo.setIcon(new ImageIcon(ConsultarApagarCarteira.class.getResource("/icons/TelaConsultaCarteira.png")));
		fundo.setBounds(-1, -2, 685, 463);
		contentPane.add(fundo);
	}
}
