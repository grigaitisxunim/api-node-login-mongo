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

public class Home extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setFont(new Font("Arial", Font.BOLD, 14));
		setTitle("Menu Cidad\u00E3o");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);

		JButton cidadao = new JButton("CIDAD\u00C3O");
		cidadao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuCidadao frame = new MenuCidadao();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dispose();
			}
		});
		cidadao.setIcon(new ImageIcon(Home.class.getResource("/icons/IconeCidadao.png")));
		cidadao.setForeground(new Color(47, 79, 79));
		cidadao.setFont(new Font("Dialog", Font.BOLD, 14));
		cidadao.setBackground(Color.WHITE);
		cidadao.setBounds(231, 186, 237, 30);
		contentPane.add(cidadao);

		JButton btnVacina = new JButton("VACINA");
		btnVacina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuVacina frame = new MenuVacina();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dispose();
			}
		});
		btnVacina.setIcon(new ImageIcon(Home.class.getResource("/icons/IcomeVacina.png")));
		btnVacina.setForeground(new Color(47, 79, 79));
		btnVacina.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVacina.setBackground(Color.WHITE);
		btnVacina.setBounds(231, 235, 237, 30);
		contentPane.add(btnVacina);

		JButton btnCarteiraDeVacinao = new JButton("CARTEIRA DE VACINA\u00C7\u00C3O");
		btnCarteiraDeVacinao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuCarteira frame = new MenuCarteira();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dispose();
			}
		});
		btnCarteiraDeVacinao.setIcon(new ImageIcon(Home.class.getResource("/icons/IconeCarteira.png")));
		btnCarteiraDeVacinao.setForeground(new Color(47, 79, 79));
		btnCarteiraDeVacinao.setFont(new Font("Dialog", Font.BOLD, 14));
		btnCarteiraDeVacinao.setBackground(Color.WHITE);
		btnCarteiraDeVacinao.setBounds(231, 284, 237, 30);
		contentPane.add(btnCarteiraDeVacinao);

		JButton btnRel = new JButton("RELAT\u00D3RIO");
		btnRel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TelaRelatorio frame = new TelaRelatorio();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
			}
		});
		btnRel.setIcon(new ImageIcon(Home.class.getResource("/icons/IconeRelatorio.png")));
		btnRel.setForeground(new Color(47, 79, 79));
		btnRel.setFont(new Font("Dialog", Font.BOLD, 14));
		btnRel.setBackground(Color.WHITE);
		btnRel.setBounds(231, 335, 237, 30);
		contentPane.add(btnRel);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Home.class.getResource("/icons/TelaHome.png")));
		lblNewLabel.setBounds(0, 0, 684, 461);
		contentPane.add(lblNewLabel);
	}
}
