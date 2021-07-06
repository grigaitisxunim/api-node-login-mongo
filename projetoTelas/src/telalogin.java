

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Color;

public class telalogin {

	private JFrame frame;
	private JTextField txtEmail;
	private JTextField textSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telalogin window = new telalogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public telalogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 628, 401);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(47, 79, 79));
		lblEmail.setFont(new Font("Dialog", Font.BOLD, 14));
		lblEmail.setBounds(110, 106, 69, 20);
		frame.getContentPane().add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(227, 104, 257, 26);
		frame.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setForeground(new Color(47, 79, 79));
		lblSenha.setFont(new Font("Dialog", Font.BOLD, 14));
		lblSenha.setBounds(110, 156, 69, 20);
		frame.getContentPane().add(lblSenha);
		
		textSenha = new JTextField();
		textSenha.setBounds(227, 141, 257, 26);
		frame.getContentPane().add(textSenha);
		textSenha.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(new Color(47, 79, 79));
		btnLogin.setFont(new Font("Dialog", Font.BOLD, 14));
		btnLogin.setBounds(135, 280, 115, 29);
		frame.getContentPane().add(btnLogin);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setForeground(new Color(47, 79, 79));
		btnCadastrar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnCadastrar.setBounds(340, 280, 115, 29);
		frame.getContentPane().add(btnCadastrar);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setForeground(UIManager.getColor("TextPane.inactiveBackground"));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setIcon(new ImageIcon(telalogin.class.getResource("/icons/fundo.JPG")));
		lblNewLabel.setBounds(0, 0, 612, 362);
		frame.getContentPane().add(lblNewLabel);
	}
}
