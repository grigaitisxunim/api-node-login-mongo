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

public class MenuCarteira extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuCarteira frame = new MenuCarteira();
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
	public MenuCarteira() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JButton btnNewButton_1 = new JButton("CADASTRAR CARTEIRA DE VACINA\u00C7\u00C3O");
		btnNewButton_1.setForeground(new Color(47, 79, 79));
		btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCarteiraVacinacao vacina = new TelaCarteiraVacinacao();
				vacina.setVisible(true);
				vacina.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dispose();
			}
		});
		
		JButton btnNewButton = new JButton("CADASTRAR SEGUNDA DOSE OU ALTERAR ");
		btnNewButton.setForeground(new Color(47, 79, 79));
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultarApagarCarteira frame = new ConsultarApagarCarteira();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dispose();
			}
		});
		btnNewButton.setBounds(170, 258, 350, 30);
		contentPane.add(btnNewButton);
		btnNewButton_1.setBounds(170, 188, 350, 30);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("VOLTAR PARA HOME");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home frame = new Home();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dispose();
			}
		});
		btnNewButton_1_1.setForeground(new Color(47, 79, 79));
		btnNewButton_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton_1_1.setBounds(170, 328, 350, 30);
		contentPane.add(btnNewButton_1_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(MenuCarteira.class.getResource("/icons/TelaMenuCarteira.png")));
		lblNewLabel.setBounds(0, -5, 689, 467);
		contentPane.add(lblNewLabel);
	}
}
