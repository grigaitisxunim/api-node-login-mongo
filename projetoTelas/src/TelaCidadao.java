import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.awt.event.KeyAdapter;

public class TelaCidadao extends JFrame {

	private JPanel contentPane;
	private JTextField nomeField;
	private JTextField cpfField;
	private JTextField telefoneField;
	private JTextField emailField;
	private JTextField susField;
	protected String title;
	private JTextField dataDeNascimentoField;
	static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCidadao frame = new TelaCidadao();
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
	public TelaCidadao() {
		setFont(new Font("Arial", Font.BOLD, 14));
		setTitle("Cadastro do cidad\u00E3o");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		Cidadao p = new Cidadao();

		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBackground(SystemColor.textHighlightText);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(47, 79, 79));
		lblNewLabel.setBounds(214, 177, 121, 23);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("CPF/RNE");
		lblNewLabel_1.setBackground(SystemColor.textHighlightText);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_1.setBounds(214, 246, 63, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Data de nascimento");
		lblNewLabel_2.setBackground(SystemColor.textHighlightText);
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_2.setForeground(new Color(47, 79, 79));
		lblNewLabel_2.setBounds(214, 277, 147, 23);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Telefone");
		lblNewLabel_3.setBackground(SystemColor.textHighlightText);
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_3.setForeground(new Color(47, 79, 79));
		lblNewLabel_3.setBounds(214, 311, 78, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("E-mail");
		lblNewLabel_4.setBackground(SystemColor.textHighlightText);
		lblNewLabel_4.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_4.setForeground(new Color(47, 79, 79));
		lblNewLabel_4.setBounds(214, 352, 46, 14);
		contentPane.add(lblNewLabel_4);
		
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

		nomeField = new JTextField();
		nomeField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0987654321@!#&*()+$%-=[]:><,?|^~´`¨{};'";
				if (caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});

		nomeField.setBounds(327, 180, 158, 20);
		nomeField.setColumns(10);
		contentPane.add(nomeField);

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
		cpfField.setBounds(327, 245, 158, 20);
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
		telefoneField.setBounds(327, 313, 158, 20);
		contentPane.add(telefoneField);

		emailField = new JTextField();
		String caracteres = "!#&*()+$%-=[]:><,?|^~´`¨{};'";
		emailField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});

		emailField.setColumns(10);
		emailField.setBounds(327, 351, 158, 20);
		contentPane.add(emailField);

		dataDeNascimentoField = new JFormattedTextField(mascaraData);
		dataDeNascimentoField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0987654321/";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		dataDeNascimentoField.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
		dataDeNascimentoField.setBounds(368, 278, 117, 20);
		dataDeNascimentoField.setColumns(10);
		contentPane.add(dataDeNascimentoField);

		JButton btnCadastrarVacina = new JButton("Cadastrar");
		btnCadastrarVacina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (susField.getText().equals("               ") || susField.getText().length() != 15) {
					JOptionPane.showMessageDialog(null, "Campo SUS não preenchido ou menor que 15 dígitos!");
				} else if (nomeField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Campo nome não preenchido");
				} else if (dataDeNascimentoField.getText().equals("  /  /    ")
						|| dataDeNascimentoField.getText().equals(p.getFormatarData())) {
					JOptionPane.showMessageDialog(null, "Campo data de nascimento não preenchido");
				} else if (cpfField.getText().equals("___.___.___-__") || cpfField.getText().length() != 14) {
					JOptionPane.showMessageDialog(null, "Campo CPF não preenchido ou incompleto");
				} else if (telefoneField.getText().equals("(__)____-____") || telefoneField.getText().length() < 13 || telefoneField.getText().length() > 15) {
					JOptionPane.showMessageDialog(null, "Campo telefone não preenchido");
				} else if (emailField.getText().isEmpty() || emailField.getText().equals(caracteres)) {
					JOptionPane.showMessageDialog(null, "Campo e-mail em branco ou com caracter inválido");
				} else {
					String sus = susField.getText();
					String nome = nomeField.getText();
					String dataDeNascimento = dataDeNascimentoField.getText();
					// System.out.println(dataDeNascimento);
					String cpf = cpfField.getText();
					String telefone = telefoneField.getText();
					String email = emailField.getText();
					p.setSus(sus);
					p.setNome(nome);
					p.setDataDeNascimento(dataDeNascimento);
					p.setTelefone(telefone);
					p.setEmail(email);
					p.setCpf(cpf);
					p.inserir();
					TelaCidadao cadastroCidadao = new TelaCidadao();
					cadastroCidadao.setVisible(true);
					cadastroCidadao.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					dispose();
					
				}
			}
		});

		btnCadastrarVacina.setForeground(new Color(47, 79, 79));
		btnCadastrarVacina.setFont(new Font("Dialog", Font.BOLD, 14));
		btnCadastrarVacina.setBackground(Color.WHITE);
		btnCadastrarVacina.setBounds(389, 411, 140, 27);
		contentPane.add(btnCadastrarVacina);

		JButton sair = new JButton("Sair");
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCidadao.this.dispose();
				MenuCidadao frame = new MenuCidadao();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		sair.setForeground(new Color(47, 79, 79));
		sair.setFont(new Font("Dialog", Font.BOLD, 14));
		sair.setBackground(Color.WHITE);
		sair.setBounds(177, 411, 140, 27);
		contentPane.add(sair);

		JLabel lblNewLabel_5 = new JLabel("Carteira Sus");
		lblNewLabel_5.setBackground(SystemColor.textHighlightText);
		lblNewLabel_5.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_5.setForeground(new Color(47, 79, 79));
		lblNewLabel_5.setBounds(214, 212, 103, 14);
		contentPane.add(lblNewLabel_5);

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
		susField.setBounds(327, 211, 158, 20);
		contentPane.add(susField);

		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setBackground(SystemColor.textHighlightText);
		lblNewLabel_6.setIcon(new ImageIcon(TelaCidadao.class.getResource("/icons/TelaCadastroCidadao.png")));
		lblNewLabel_6.setBounds(0, 0, 684, 461);
		contentPane.add(lblNewLabel_6);
	}
}
