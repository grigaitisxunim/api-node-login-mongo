import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Vacina {
	private int idvacina;
	private String vacina;
	private int periodo;
	private int qtdDoses;
	// public static int id;
	private static int codvacina;

	public Vacina(String vacina, int periodo, int qtdDoses, int idvacina, /* int id, */ int codvacina) {
		super();
		this.idvacina = idvacina;
		this.vacina = vacina;
		this.periodo = periodo;
		this.qtdDoses = qtdDoses;
		// this.id = id;
		this.codvacina = codvacina;

	}

	public Vacina() {
		// TODO Auto-generated constructor stub
	}

	public int getCodvacina() {
		return codvacina;
	}

	public void setCodvacina(int codvacina) {
		this.codvacina = codvacina;
	}

	public int getIdvacina() {
		return idvacina;
	}

	/*
	 * public int getId() { return id; }
	 * 
	 * public void setId(int id) { this.id = id; }
	 */

	public void setIdvacina(int idvacina) {
		this.idvacina = idvacina;
	}

	public String getVacina() {
		return vacina;
	}

	public void setVacina(String vacina) {
		this.vacina = vacina;
	}

	public int getPeriodo() {
		return periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public int getQtdDoses() {
		return qtdDoses;
	}

	public void setQtdDoses(int qtdDoses) {
		this.qtdDoses = qtdDoses;
	}

	public void inserir() {
		String sql = "INSERT INTO tb_vacina(nomevacina, periodo,doses,codvacina) VALUES (?, ?, ?,?)";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, vacina);
			ps.setInt(2, periodo);
			ps.setInt(3, qtdDoses);
			ps.setInt(4, codvacina);
			ps.execute();
			JOptionPane.showMessageDialog(null, vacina + " Foi cadastrada! Seu código é " + codvacina);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * public void complemento() { String sql2 =
	 * "SELECT * FROM db_projeto.tb_vacina WHERE nomevacina =?"; ConnectionFactory
	 * factory = new ConnectionFactory(); try (Connection c =
	 * factory.obtemConexao()) { PreparedStatement ps = c.prepareStatement(sql2);
	 * ps.setString(1, vacina); ResultSet rs = ps.executeQuery();
	 * rs.getInt("codvacina"); JOptionPane.showMessageDialog(null,
	 * "Vacina cadastrada sei ID é: " + codvacina); } catch (Exception e) {
	 * e.printStackTrace(); }
	 * 
	 * }
	 */

	public String[] listarVacinas() {

		ArrayList<String> vacinas = new ArrayList<>();


		String sql = "SELECT nomevacina FROM db_projeto.tb_vacina";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				vacinas.add(rs.getString("nomevacina"));
			} 

		} catch (Exception e) {
			e.printStackTrace();
		}
		return vacinas.toArray(new String[vacinas.size()]);
	}

	public void atualizar() {
		String sql = "UPDATE db_projeto.tb_vacina SET nomevacina = ?, periodo =?, doses = ? WHERE codvacina= ?";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, vacina);
			ps.setInt(2, periodo);
			ps.setInt(3, qtdDoses);
			ps.setInt(4, codvacina);
			ps.execute();
			JOptionPane.showMessageDialog(null, "A vacina" + codvacina + vacina + " foi alterada!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void apagar() {
		String sql = "DELETE FROM tb_vacina WHERE idvacina = ?";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, vacina);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void listar() {
		String sql = "SELECT * FROM tb_vacina";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {

			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String vacina = rs.getString("nomevacina");
				int periodo = rs.getInt("periodo");
				int qtdDoses = rs.getInt("doses");
				String aux = String.format(" nomevaina: %s, periodo: %s, doses: %s", vacina, periodo, qtdDoses);
				JOptionPane.showMessageDialog(null, aux);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
