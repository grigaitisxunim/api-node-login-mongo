import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Panel;

public class home {

	private JFrame frame;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					home window = new home();
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
	public home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(UIManager.getColor("InternalFrame.inactiveTitleGradient"));
		frame.setBounds(100, 100, 627, 395);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("VACINA");
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 12));
		btnNewButton.setBackground(SystemColor.textHighlightText);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastroVacina cvacina = new cadastroVacina();
				cvacina.setVisible(true);
				cvacina.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnNewButton.setBounds(206, 131, 208, 30);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("CIDAD\u00C3O");
		btnNewButton_1.setIcon(null);
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 12));
		btnNewButton_1.setBackground(SystemColor.textHighlightText);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCidadao cadastroCidadao = new TelaCidadao();
				cadastroCidadao .setVisible(true);
				cadastroCidadao .setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnNewButton_1.setBounds(206, 77, 208, 30);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnCarteiraDeVacinao = new JButton("RELAT\u00D3RIO");
		btnCarteiraDeVacinao.setFont(new Font("Arial", Font.BOLD, 12));
		btnCarteiraDeVacinao.setBackground(SystemColor.textHighlightText);
		btnCarteiraDeVacinao.setBounds(206, 232, 208, 30);
		frame.getContentPane().add(btnCarteiraDeVacinao);
		
		
		JButton btnCarteiraDeVacinao_1 = new JButton("CARTEIRA DE VACINA\u00C7\u00C3O");
		btnCarteiraDeVacinao_1.setFont(new Font("Arial", Font.BOLD, 12));
		btnCarteiraDeVacinao_1.setBackground(SystemColor.textHighlightText);
		btnCarteiraDeVacinao_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carteiraVacina vacina = new carteiraVacina();
				vacina.setVisible(true);
				vacina.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnCarteiraDeVacinao_1.setBounds(206, 182, 208, 30);
		frame.getContentPane().add(btnCarteiraDeVacinao_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\lu99-\\eclipse-workspace\\icons\\fundo-abstrato-colorido_23-2148807053.jpg"));
		lblNewLabel.setBounds(0, 0, 629, 356);
		frame.getContentPane().add(lblNewLabel);
	}
}
