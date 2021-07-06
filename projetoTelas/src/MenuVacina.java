import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class MenuVacina extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuVacina frame = new MenuVacina();
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
	public MenuVacina() {
		setFont(new Font("Arial", Font.BOLD, 14));
		setTitle("Op\u00E7\u00F5es da Vacina");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JButton btnNewButton_1 = new JButton("CADASTRAR  VACINA");
		btnNewButton_1.setForeground(new Color(47, 79, 79));
		btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastroVacina frame = new cadastroVacina();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dispose();
			}
		});
		
		JButton btnNewButton = new JButton("CONSULTAR VACINAS");
		btnNewButton.setForeground(new Color(47, 79, 79));
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultarApagarVacina frame = new ConsultarApagarVacina();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dispose();
			}
		});
		btnNewButton.setBounds(186, 258, 301, 30);
		contentPane.add(btnNewButton);
		btnNewButton_1.setBounds(186, 188, 301, 30);
		contentPane.add(btnNewButton_1);
		
		JButton btnVoltarParaMenu = new JButton("VOLTAR PARA HOME");
		btnVoltarParaMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home frame = new Home();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dispose();
			}
		});
		btnVoltarParaMenu.setForeground(new Color(47, 79, 79));
		btnVoltarParaMenu.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVoltarParaMenu.setBounds(186, 328, 301, 30);
		contentPane.add(btnVoltarParaMenu);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(MenuVacina.class.getResource("/icons/TelaMenuVacina.png")));
		lblNewLabel.setBounds(0, 0, 684, 461);
		contentPane.add(lblNewLabel);
	}
}
