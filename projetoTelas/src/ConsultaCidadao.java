
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.TextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JFormattedTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class ConsultaCidadao extends JFrame {

	private JPanel contentPane;
	private JTextField nomeField;
	private JTextField telefoneField;
	private JTextField dataDeNascimentoField;
	private JTextField emailField;
	private JTextField cpfField;
	private JTextField susField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaCidadao frame = new ConsultaCidadao();
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
	public ConsultaCidadao() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		Cidadao p = new Cidadao();

		JLabel NewLabel = new JLabel("MINIMO 15 DIGITOS!");
		NewLabel.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));
		NewLabel.setForeground(Color.RED);
		NewLabel.setBounds(341, 152, 196, 32);
		NewLabel.setVisible(false);
		contentPane.add(NewLabel);
		
		MaskFormatter mascaraSus = null;
        MaskFormatter mascaraTel = null;
        MaskFormatter mascaraCpf = null;
        MaskFormatter mascaraData = null;

        try{
               mascaraSus = new MaskFormatter("###############");
               mascaraTel = new MaskFormatter("(##)####-####");
               mascaraCpf = new MaskFormatter("###.###.###-##");
               mascaraData = new MaskFormatter("##/##/####");
               mascaraTel.setPlaceholderCharacter('_');
               mascaraCpf.setPlaceholderCharacter('_');
        }
        catch(ParseException excp) {
               System.err.println("Erro na formatação: " + excp.getMessage());
               System.exit(-1);
        }

		susField = new JFormattedTextField(mascaraSus);
		susField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0987654321";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		susField.setColumns(10);
		susField.setBounds(341, 178, 161, 20);
		contentPane.add(susField);

		cpfField = new JFormattedTextField(mascaraCpf);
		cpfField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0987654321";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		cpfField.setColumns(10);
		cpfField.setBounds(341, 333, 161, 20);
		cpfField.setEnabled(false);
		contentPane.add(cpfField);

		telefoneField = new JFormattedTextField(mascaraTel);
		telefoneField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0987654321";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		telefoneField.setColumns(10);
		telefoneField.setBounds(341, 302, 161, 20);
		telefoneField.setEnabled(false);
		contentPane.add(telefoneField);

		emailField = new JTextField();
		String caracteresEmail = "*!$%&(){}[]+^`´ª><¨¬£§?:,;/|";
		emailField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				if (caracteresEmail.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		emailField.setColumns(10);
		emailField.setBounds(341, 271, 161, 20);
		emailField.setEnabled(false);
		contentPane.add(emailField);

		dataDeNascimentoField = new JFormattedTextField(mascaraData);
		dataDeNascimentoField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "*!$%&(){}-[]_+0123456789";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		dataDeNascimentoField.setFont(new Font("Arial", Font.BOLD, 12));
		dataDeNascimentoField.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
		dataDeNascimentoField.setBounds(363, 240, 100, 20);
		dataDeNascimentoField.setColumns(10);
		dataDeNascimentoField.setEnabled(false);
		contentPane.add(dataDeNascimentoField);

		nomeField = new JTextField();
		String caracteresNome = "0987654321";
		nomeField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				if (caracteresNome.contains(e.getKeyChar() + "")) {
					e.consume();
					JOptionPane.showMessageDialog(null, "Apenas letras");
				}
			}
		});
		nomeField.setColumns(10);
		nomeField.setBounds(341, 209, 161, 20);
		nomeField.setEnabled(false);
		contentPane.add(nomeField);

		JLabel lblNewLabel_4 = new JLabel("E-mail");
		lblNewLabel_4.setForeground(new Color(47, 79, 79));
		lblNewLabel_4.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_4.setBackground(Color.WHITE);
		lblNewLabel_4.setBounds(206, 272, 46, 14);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_3 = new JLabel("DDD + Telefone");
		lblNewLabel_3.setForeground(new Color(47, 79, 79));
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_3.setBackground(Color.WHITE);
		lblNewLabel_3.setBounds(206, 302, 125, 17);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_1_1 = new JLabel("CPF/RNE");
		lblNewLabel_1_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_1_1.setBackground(Color.WHITE);
		lblNewLabel_1_1.setBounds(206, 334, 63, 14);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setForeground(new Color(47, 79, 79));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(206, 210, 46, 14);
		contentPane.add(lblNewLabel);
		

		Button Atualizar = new Button("Atualizar");
		Atualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sus = susField.getText();
				String nome = nomeField.getText();
				String telefone = telefoneField.getText();
				String email = emailField.getText();
				String cpf = cpfField.getText();
				String dataDeNascimento = dataDeNascimentoField.getText();
				if (sus.equals("               ") || sus.length() != 15) {
					JOptionPane.showMessageDialog(null, "Campo SUS é obriatório e deve conter 15 dígitos!");
				} else if (nome.isEmpty() || nome.equals(caracteresNome)) {
					JOptionPane.showMessageDialog(null, "Campo nome não preenchido ou com caracteres invalidos");
				} else if (dataDeNascimento.equals("  /  /    ")) {
					JOptionPane.showMessageDialog(null, "Campo data de nascimento não preenchido");
				} else if (email.isEmpty() || email.equals(caracteresEmail)) {
					JOptionPane.showMessageDialog(null, "Campo e-mail está em branco ou possui caracteres inválidos");
				} else if (telefone.equals("(__)____-____") || telefone.length() < 13 || telefone.length() > 15) {
					JOptionPane.showMessageDialog(null, "Campo telefone é obrigatório e deve conter o DDD");
				} else if (cpf.equals("___.___.___-__") || cpf.length() != 14) {
					JOptionPane.showMessageDialog(null, "Campo CPF não preenchido ou incompleto");
				} else {

					p.setSus(sus);
					p.setNome(nome);
					p.setTelefone(telefone);
					p.setEmail(email);
					p.setCpf(cpf);
					p.setDataDeNascimento(dataDeNascimento);
					try {
						p.atualizar();
						dispose();
						ConsultaCidadao consultaCidadao = new ConsultaCidadao();
						consultaCidadao.setVisible(true);
						consultaCidadao.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Cidadão não pode ser alterado!");
						e1.printStackTrace();
					}
				}
			}
		});

		Atualizar.setForeground(new Color(47, 79, 79));
		Atualizar.setFont(new Font("Dialog", Font.BOLD, 14));
		Atualizar.setBounds(348, 413, 125, 21);
		Atualizar.setEnabled(false);
		contentPane.add(Atualizar);

		Button Apagar = new Button("Apagar");
		Apagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				try {
				p.apagar();
				dispose();
				ConsultaCidadao consultaCidadao = new ConsultaCidadao();
				consultaCidadao.setVisible(true);
				consultaCidadao.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

				}catch(SQLException e1){
					JOptionPane.showMessageDialog(null, "Cidadão não pode ser APAGADO!");
				
			}
			}
		});
		Apagar.setForeground(new Color(47, 79, 79));
		Apagar.setFont(new Font("Dialog", Font.BOLD, 14));
		Apagar.setBounds(212, 413, 125, 21);
		Apagar.setEnabled(false);
		contentPane.add(Apagar);
		

		Button button = new Button("Consultar");
		button.setForeground(new Color(47, 79, 79));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sus = susField.getText();
				if (sus.isEmpty() || sus.length() != 15) {
					JOptionPane.showMessageDialog(null, "Campo SUS é obriatório e deve conter 15 dígitos!");
				} else {
					p.setSus(sus);
					try {
						p.pesquisarIdCidadao();
						p.ConsultarCidadao();
						nomeField.setText(p.getNome());
						dataDeNascimentoField.setText(p.getDataDeNascimentoFormatado());
						emailField.setText(p.getEmail());
						telefoneField.setText(p.getTelefone());
						cpfField.setText(p.getCpf());
						nomeField.setEnabled(true);
						dataDeNascimentoField.setEnabled(true);
						emailField.setEnabled(true);
						telefoneField.setEnabled(true);
						cpfField.setEnabled(true);
						Apagar.setEnabled(true);
						Atualizar.setEnabled(true);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Não existe um cidadão para esse Nº SUS!!");
						e1.printStackTrace();
					} catch (NullPointerException e2) {
						JOptionPane.showMessageDialog(null, "DIGITE O NUMERO DO SUS  VÁLIDO!!!");
						e2.printStackTrace();
					}
				}

			}
		});
		button.setFont(new Font("Dialog", Font.BOLD, 14));
		button.setBounds(488, 413, 125, 21);
		contentPane.add(button);

		Button sair = new Button("Sair");
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaCidadao.this.dispose();
				MenuCidadao frame = new MenuCidadao();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		sair.setForeground(new Color(47, 79, 79));
		sair.setFont(new Font("Dialog", Font.BOLD, 14));
		sair.setBounds(72, 413, 125, 21);
		contentPane.add(sair);

		JLabel lblNewLabel_1 = new JLabel("N\u00FAmero do sus");
		lblNewLabel_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_1.setBounds(206, 177, 142, 22);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Data de nascimento");
		lblNewLabel_2.setForeground(new Color(47, 79, 79));
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setBounds(206, 240, 147, 23);
		contentPane.add(lblNewLabel_2);

		JLabel btnConsultar = new JLabel("consultar");
		btnConsultar.setForeground(new Color(47, 79, 79));
		btnConsultar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnConsultar.setIcon(new ImageIcon(ConsultaCidadao.class.getResource("/icons/TeleConsultaCidadao.png")));
		btnConsultar.setBounds(0, 0, 684, 461);
		contentPane.add(btnConsultar);
	}
}
