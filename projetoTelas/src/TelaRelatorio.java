import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TelaRelatorio extends JFrame {

	private JPanel contentPane;
	protected String title;
	private JTextField dataInicio;
	private JTextField dataFinal;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaRelatorio frame = new TelaRelatorio();
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
	public TelaRelatorio() {
		setFont(new Font("Arial", Font.BOLD, 14));
		setTitle("Cadastro do cidad\u00E3o");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		Relatorio r = new Relatorio();

		dataInicio = new JTextField();
		dataInicio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0987654321/";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		dataInicio.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
		dataInicio.setBounds(169, 173, 117, 20);
		dataInicio.setColumns(10);
		contentPane.add(dataInicio);

		dataFinal = new JTextField();
		dataFinal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0987654321/";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		dataFinal.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
		dataFinal.setBounds(479, 175, 117, 20);
		dataFinal.setColumns(10);
		contentPane.add(dataFinal);

		JButton sair = new JButton("Sair");
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaRelatorio.this.dispose();
				Home frame = new Home();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		sair.setForeground(new Color(47, 79, 79));
		sair.setFont(new Font("Dialog", Font.BOLD, 14));
		sair.setBackground(Color.WHITE);
		sair.setBounds(144, 419, 140, 27);
		contentPane.add(sair);

		String col[] = { "sus", "nome", "nascimento", "cpf", "telefone", "vacina", "primeira dose", "segunda dose" };
		String lin[] = { "N\u00BA SUS", "Nome", "Nascimento", "CPF", "Telefone", "Vacina", "Primeira Dose",
				"previsão" };

		JButton btnCadastrarVacina = new JButton("Consultar");
		btnCadastrarVacina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String datainicio = dataInicio.getText();
				String datafinal = dataFinal.getText();
				r.setDataInicio(datainicio);
				r.setDataFinal(datafinal);

				DefaultTableModel tableModel = new DefaultTableModel(col, 0);

				table = new JTable(tableModel);
				tableModel.addRow(lin);
				table.setSurrendersFocusOnKeystroke(true);
				table.setFillsViewportHeight(true);
				table.setCellSelectionEnabled(true);
				table.setColumnSelectionAllowed(true);
				table.setBounds(59, 225, 581, 175);
				contentPane.add(table);

				ArrayList<String[]> str = r.listarPrimeiraDose();
				for (int i = 0; i < str.size(); i++) {
					tableModel.addRow(str.get(i));
				}
			}
		});

		btnCadastrarVacina.setForeground(new Color(47, 79, 79));
		btnCadastrarVacina.setFont(new Font("Dialog", Font.BOLD, 14));
		btnCadastrarVacina.setBackground(Color.WHITE);
		btnCadastrarVacina.setBounds(389, 419, 140, 27);
		contentPane.add(btnCadastrarVacina);

		JLabel lblNewLabel_1 = new JLabel("Data de inicio");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_1.setBounds(86, 178, 76, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Data Final");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_2.setBounds(417, 179, 55, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setBackground(SystemColor.textHighlightText);
		lblNewLabel_6.setIcon(new ImageIcon(TelaRelatorio.class.getResource("/icons/Relatorio.png")));
		lblNewLabel_6.setBounds(0, 0, 684, 461);
		contentPane.add(lblNewLabel_6);
	}
}
