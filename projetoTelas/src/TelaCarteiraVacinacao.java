import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.TextField;

public class TelaCarteiraVacinacao extends JFrame {

	private JPanel contentPane;
	private JComboBox nomevacField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCarteiraVacinacao frame = new TelaCarteiraVacinacao();
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
	public TelaCarteiraVacinacao() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Carteira c = new Carteira();

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
		
		JTextField Teste = new JFormattedTextField(mascaraSus);
		Teste.setBounds(375, 172, 127, 28);
		contentPane.add(Teste);

		JLabel lblNomeDaVacina = new JLabel("Nome da vacina");
		lblNomeDaVacina.setForeground(new Color(47, 79, 79));
		lblNomeDaVacina.setBackground(Color.WHITE);
		lblNomeDaVacina.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNomeDaVacina.setBounds(206, 223, 111, 19);
		contentPane.add(lblNomeDaVacina);

		JLabel lblNumerodeDoses = new JLabel("N\u00FAmero de doses");
		lblNumerodeDoses.setForeground(new Color(47, 79, 79));
		lblNumerodeDoses.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNumerodeDoses.setBounds(206, 262, 124, 19);
		contentPane.add(lblNumerodeDoses);

		JTextField primeiraDoseF = new JFormattedTextField(mascaraData);
		primeiraDoseF.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
		primeiraDoseF.setBounds(231, 333, 80, 26);
		primeiraDoseF.setColumns(10);
		contentPane.add(primeiraDoseF);

		JTextField segundaDoseF = new JFormattedTextField(mascaraData2);
		segundaDoseF.setBounds(398, 333, 80, 26);
		segundaDoseF.setColumns(10);
		segundaDoseF.setEnabled(false);
		contentPane.add(segundaDoseF);

		JComboBox doseField = new JComboBox();
		doseField.setModel(new DefaultComboBoxModel(new String[] {"1", "2"}));
		doseField.setBounds(385, 260, 39, 28);
		contentPane.add(doseField);

		JLabel lblDataProximaDose = new JLabel("Data da 2\u00BA dose");
		lblDataProximaDose.setForeground(new Color(47, 79, 79));
		lblDataProximaDose.setFont(new Font("Dialog", Font.BOLD, 14));
		lblDataProximaDose.setBounds(375, 302, 108, 19);
		contentPane.add(lblDataProximaDose);

		// int comp= Integer.parseInt(doseField.getSelectedItem().toString());
		// System.out.println(comp);
		// if(comp > 1) {
		// segundadosSpinner.setEnabled(true);
		// }else {
		// segundadosSpinner.setEnabled(false);
		// }

		nomevacField = new JComboBox();
		nomevacField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String vacina = nomevacField.getSelectedItem().toString();
				c.setVacina(vacina);
				c.pesquisarVacina();

			}
		});
		nomevacField.setModel(new javax.swing.DefaultComboBoxModel<>(new Vacina().listarVacinas()));
		nomevacField.setBounds(375, 221, 127, 28);
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
		btnNewButton.setBounds(382, 360, 115, 19);
		contentPane.add(btnNewButton);

		JButton btnCadastrarVacina = new JButton("Cadastrar");
		btnCadastrarVacina.setForeground(new Color(47, 79, 79));
		btnCadastrarVacina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int qtdDoses = Integer.parseInt((String) doseField.getSelectedItem());
				if (qtdDoses == 1) {

					if (Teste.getText().equals("               ")) {
						JOptionPane.showMessageDialog(null, "Campo SUS não" + " preenchido");
					} else if (nomevacField.getSelectedItem().toString().equals("")) {
						JOptionPane.showMessageDialog(null, "Campo vacina não" + " preenchido");
					} else if (qtdDoses == 0) {
						JOptionPane.showMessageDialog(null, "Campo doses não" + " preenchido");
					} else if (primeiraDoseF.getText().equals("  /  /    ")) {
						JOptionPane.showMessageDialog(null, "Campo primeira dose não" + " preenchido");
					} else {
						String sus = Teste.getText();
						String vacina1 = String.valueOf(nomevacField.getSelectedItem());
						String primeiraDose = primeiraDoseF.getText();
						c.setSus(sus);
						try {
							c.pesquisarCidadao();
							c.setVacina(vacina1);
							c.setQtdDoses(qtdDoses);
							c.setPrimeiraDose(primeiraDose);
							c.pesquisarVacina();
							c.inserirPrimeiraDos();
							dispose();
							TelaCarteiraVacinacao vacina = new TelaCarteiraVacinacao();
							vacina.setVisible(true);
							vacina.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						} catch (NullPointerException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} else {
					if (Teste.getText().equals("               ")) {
						JOptionPane.showMessageDialog(null, "Campo SUS não" + " preenchido");
					} else if (nomevacField.getSelectedItem().toString().equals("")) {
						JOptionPane.showMessageDialog(null, "Campo vacina não" + " preenchido");
					} else if (qtdDoses == 0) {
						JOptionPane.showMessageDialog(null, "Campo doses não" + " preenchido");
					} else if (primeiraDoseF.getText().equals("  /  /    ")) {
						JOptionPane.showMessageDialog(null, "Campo primeira dose não" + " preenchido");
					} else if (segundaDoseF.getText().equals("  /  /    ")) {
						JOptionPane.showMessageDialog(null, "Campo segunda dose não" + " preenchido");
					} else {
						String sus = Teste.getText();
						String vacina1 = String.valueOf(nomevacField.getSelectedItem());
						String primeiraDose = primeiraDoseF.getText();
						String segundaDose = segundaDoseF.getText();
						c.setSus(sus);
						try {
							c.pesquisarCidadao();
							c.setVacina(vacina1);
							c.setQtdDoses(qtdDoses);
							c.setPrimeiraDose(primeiraDose);
							c.setSegundaDose(segundaDose);
							c.pesquisarVacina();
							c.inserir();
							dispose();
							TelaCarteiraVacinacao vacina = new TelaCarteiraVacinacao();
							vacina.setVisible(true);
							vacina.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						} catch (NullPointerException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				}
			}
		});
		
		btnCadastrarVacina.setBackground(SystemColor.textHighlightText);
		btnCadastrarVacina.setFont(new Font("Dialog", Font.BOLD, 14));
		btnCadastrarVacina.setBounds(361, 395, 115, 29);
		contentPane.add(btnCadastrarVacina);

		JButton bntSair = new JButton("Sair");
		bntSair.setForeground(new Color(47, 79, 79));
		bntSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCarteiraVacinacao.this.dispose();
				MenuCarteira frame = new MenuCarteira();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		bntSair.setBackground(SystemColor.textHighlightText);
		bntSair.setFont(new Font("Dialog", Font.BOLD, 14));
		bntSair.setBounds(223, 395, 115, 29);
		contentPane.add(bntSair);

		JLabel lblDataDeVacinacao = new JLabel("Data da  1\u00BA dose");
		lblDataDeVacinacao.setForeground(new Color(47, 79, 79));
		lblDataDeVacinacao.setFont(new Font("Dialog", Font.BOLD, 14));
		lblDataDeVacinacao.setBounds(206, 302, 112, 19);
		contentPane.add(lblDataDeVacinacao);

		JLabel Fundo = new JLabel("fundo");
		Fundo.setIcon(new ImageIcon(TelaCarteiraVacinacao.class.getResource("/icons/TelaCadastroCarteira.png")));
		Fundo.setBounds(1, -1, 683, 463);
		contentPane.add(Fundo);

	}
}
