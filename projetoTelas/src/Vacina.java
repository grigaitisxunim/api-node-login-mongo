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
	private int codvacina;

	public Vacina(String vacina, int periodo, int qtdDoses, int idvacina /* int id, int codvacina */) {
		super();
		this.idvacina = idvacina;
		this.vacina = vacina;
		this.periodo = periodo;
		this.qtdDoses = qtdDoses;
		// this.id = id;
		// this.codvacina = codvacina;

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
		String sql = "INSERT INTO tb_vacina(nomevacina, periodo,doses) VALUES (?, ?, ?)";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, vacina);
			ps.setInt(2, periodo);
			ps.setInt(3, qtdDoses);
			// ps.setInt(4, codvacina);
			ps.execute();
			JOptionPane.showMessageDialog(null, "A Vacina " + vacina + " foi cadastrada com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar a vacina " + vacina + "!!");
		}
	}

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
		String sql = "UPDATE db_projeto.tb_vacina SET nomevacina = ?, periodo =?, doses = ? WHERE idvacina= ?";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, vacina);
			ps.setInt(2, periodo);
			ps.setInt(3, qtdDoses);
			ps.setInt(4, idvacina);
			ps.execute();
			JOptionPane.showMessageDialog(null, "A vacina " + vacina + " foi alterada com sucesso!!");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possivel alterar a vacina " + vacina + "!!");
		}
	}

	public void apagar() {
		String sql = "DELETE FROM tb_vacina WHERE idvacina = ?";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, idvacina);
			ps.execute();
			JOptionPane.showMessageDialog(null, "A vacina " + vacina + " foi apagada com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possivel alterar a vacina " + vacina + "!!");
		}
	}

	public void listar() {
		String sql = "SELECT doses, nomevacina, periodo FROM db_projeto.tb_vacina WHERE idvacina = ?";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, idvacina);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				this.qtdDoses = rs.getInt("doses");
				this.vacina = rs.getString("nomevacina");
				this.periodo = rs.getInt("periodo");
				// codvacina = rs.getInt("codvacina");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int pesquisarVacina() {
		String sql = "SELECT idvacina  FROM db_projeto.tb_vacina WHERE nomevacina =?";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, vacina);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				idvacina = rs.getInt("idvacina");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.idvacina;

	}

}
