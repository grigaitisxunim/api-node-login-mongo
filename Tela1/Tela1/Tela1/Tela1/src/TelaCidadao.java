

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import java.awt.Button;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.TextField;
import javax.swing.JFormattedTextField;
import javax.swing.JSeparator;
import javax.swing.text.DateFormatter;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.mysql.cj.x.protobuf.MysqlxCrud.DataModel;
import javax.swing.JProgressBar;
import javax.swing.JList;
import javax.swing.JSplitPane;
import java.awt.Panel;
import javax.swing.JEditorPane;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.SystemColor;


public class TelaCidadao extends JFrame {

	private JPanel contentPane;
	private JTextField nomeField;
	private JTextField cpfField;
	private JTextField telefoneField;
	private JTextField emailField;
	private JTextField susField;
	protected JOptionPane Dataspinner;
	protected String title;
	

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 622, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBackground(SystemColor.textHighlightText);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(175, 86, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("CPF/RNE");
		lblNewLabel_1.setBackground(SystemColor.textHighlightText);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(175, 120, 63, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Data de nascimento");
		lblNewLabel_2.setBackground(SystemColor.textHighlightText);
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(124, 145, 147, 23);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Telefone");
		lblNewLabel_3.setBackground(SystemColor.textHighlightText);
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(160, 187, 78, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("E-mail");
		lblNewLabel_4.setBackground(SystemColor.textHighlightText);
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setBounds(175, 223, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		nomeField = new JTextField();
		nomeField.setBounds(253, 83, 158, 20);
		contentPane.add(nomeField);
		nomeField.setColumns(10);
		
		cpfField = new JTextField();
		cpfField.setColumns(10);
		cpfField.setBounds(253, 117, 158, 20);
		contentPane.add(cpfField);
		
		telefoneField = new JTextField();
		telefoneField.setColumns(10);
		telefoneField.setBounds(253, 185, 158, 20);
		contentPane.add(telefoneField);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(253, 223, 158, 20);
		contentPane.add(emailField);
		
		
		
		Button button = new Button("Cadastrar");
		button.setFont(new Font("Arial", Font.BOLD, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sus= Integer.parseInt(susField.getText());
				String nome = nomeField.getText();
				String telefone = telefoneField.getText();
				String email = emailField.getText();
				String cpf = cpfField.getText();
				String dataDeNacimento=Dataspinner.getValue().toString();
				Cidadao p = new Cidadao(nome, telefone, email, cpf,null, sus);
					p.setSus(sus);
					p.setNome(nome);
					p.setTelefone(telefone);
					p.setEmail(email);
					p.setCpf(cpf);
					p.setDtNascimento(dataDeNacimento);
					p.inserir();
			}
		});
		
		button.setBounds(483, 298, 84, 22);
		contentPane.add(button);
		
		Button button_1 = new Button("Sair");
		button_1.setBackground(UIManager.getColor("Button.background"));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCidadao.this.dispose();
			}
		});
		button_1.setFont(new Font("Arial", Font.BOLD, 12));
		button_1.setBounds(143, 298, 70, 22);
		contentPane.add(button_1);
		
		Button Atualizar = new Button("Atualizar");
		Atualizar.setActionCommand("");
		Atualizar.setFont(new Font("Arial", Font.BOLD, 12));
		Atualizar.setBackground(SystemColor.textHighlightText);
		Atualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sus =Integer.parseInt(JOptionPane.showInputDialog("Carteira do Sus?"));
				String nome = nomeField.getText();
				String telefone = telefoneField.getText();
				String email = emailField.getText();
				String cpf = cpfField.getText();
				String dataDeNacimento=Dataspinner.getValue().toString();
				Cidadao p = new Cidadao(nome, telefone, email, cpf,null, sus);
				p.setSus(sus);
				p.setNome(nome);
				p.setTelefone(telefone);
				p.setEmail(email);
				p.setCpf(cpf);
				p.setDtNascimento(dataDeNacimento);
				p.atualizar();
			
				
			}
		});
		Atualizar.setBounds(40, 298, 70, 22);
		contentPane.add(Atualizar);
		
		JLabel lblNewLabel_5 = new JLabel("Carteira Sus");
		lblNewLabel_5.setBackground(SystemColor.textHighlightText);
		lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setBounds(124, 51, 121, 23);
		contentPane.add(lblNewLabel_5);
		
		susField = new JTextField();
		susField.setColumns(10);
		susField.setBounds(253, 52, 158, 20);
		contentPane.add(susField);
		
		JSpinner Dataspinner = new JSpinner();
		Dataspinner.setModel(new SpinnerDateModel(new Date(1620356400000L), null, null, Calendar.DAY_OF_WEEK));
		Dataspinner.setBounds(281, 147, 117, 20);
		contentPane.add(Dataspinner);
		
		Button button_2 = new Button("Consultar");
		button_2.setFont(new Font("Arial", Font.BOLD, 12));
		button_2.setBackground(SystemColor.textHighlightText);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sus =Integer.parseInt(JOptionPane.showInputDialog("Carteira do Sus?"));
				String nome = nomeField.getText();
				String telefone = telefoneField.getText();
				String email = emailField.getText();
				String cpf = cpfField.getText();
				String dataDeNacimento=Dataspinner.getValue().toString();
				Cidadao p = new Cidadao(nome, telefone, email, cpf,null, sus);
				p.setSus(sus);
				p.setNome(nome);
				p.setTelefone(telefone);
				p.setEmail(email);
				p.setCpf(cpf);
				p.setDtNascimento(dataDeNacimento);
				p.consultar();
			
			}
		});
		button_2.setBounds(379, 298, 70, 22);
		contentPane.add(button_2);
		
		JButton Apagar = new JButton("Apagar");
		Apagar.setBackground(UIManager.getColor("Button.background"));
		Apagar.setFont(new Font("Arial", Font.BOLD, 12));
		Apagar.setBackground(SystemColor.textHighlightText);
		Apagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sus =Integer.parseInt(JOptionPane.showInputDialog("Carteira Sus?"));
				Cidadao p = new Cidadao(null, title, title, title, null, sus);
						p.setSus(sus);
						p.apagar();
			}
		});
		Apagar.setBounds(252, 297, 89, 23);
		contentPane.add(Apagar);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setBackground(SystemColor.textHighlightText);
		lblNewLabel_6.setIcon(new ImageIcon("C:\\Users\\lu99-\\eclipse-workspace\\icons\\fundo-abstrato-colorido_23-2148807053.jpg"));
		lblNewLabel_6.setBounds(0, 0, 622, 362);
		contentPane.add(lblNewLabel_6);
		
	
		
		
		
	}
}
	
	