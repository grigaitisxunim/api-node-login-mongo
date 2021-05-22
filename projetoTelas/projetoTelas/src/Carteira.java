
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

public class Carteira {

	private String vacina;
	private int qtdDoses;
	private String primeiraDose;
	private String segundaDose;
	private int idcidadao;
	private String sus;
	public int aux;
	public int aux1;
	private int idvacina;
	

	public Carteira() {

	}

	public Carteira(String vacina, int periodo, int qtdDoses, String primeiraDose, String segundaDose, int idcidadao,
			String sus) {
		super();
		this.vacina = vacina;
		this.qtdDoses = qtdDoses;
		this.primeiraDose = primeiraDose;
		this.segundaDose = segundaDose;
		this.idcidadao = idcidadao;
		this.sus = sus;
	}

	public int getAux1() {
		return aux1;
	}

	public void setAux1(int aux1) {
		this.aux1 = aux1;
	}

	public int getIdvacina() {
		return idvacina;
	}

	public void setIdvacina(int idvacina) {
		this.idvacina = idvacina;
	}

	public int getAux() {
		return aux;
	}

	public void setAux(int aux) {
		this.aux = aux;
	}

	public String getSus() {
		return sus;
	}

	public void setSus(String sus) {
		this.sus = sus;
	}

	public String getVacina() {
		return vacina;
	}

	public void setVacina(String vacina) {
		this.vacina = vacina;
	}

	public int getQtdDoses() {
		return qtdDoses;
	}

	public void setQtdDoses(int qtdDoses) {
		this.qtdDoses = qtdDoses;
	}

	public String getPrimeiraDose() {
		return primeiraDose;
	}

	public void setPrimeiraDose(String primeiraDose) {
		this.primeiraDose = primeiraDose;
	}

	public String getSegundaDose() {
		return segundaDose;
	}

	public void setSegundaDose(String segundaDose) {
		this.segundaDose = segundaDose;
	}

	public int getIdcidadao() {
		return idcidadao;
	}

	public void setIdcidadao(int idcidadao) {
		this.idcidadao = idcidadao;
	}

	public void inserir() {
		String sql = "INSERT INTO db_projeto.tb_carteira (idvacina, idcidadao, nomevacina, qtddoses,primeiradose, segundadose,nsus) VALUES (?,?,?, ?, ?,?,?)";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1,aux1);
			ps.setInt(2, idcidadao);
			ps.setString(3, vacina);
			ps.setInt(4, qtdDoses);
			ps.setString(5, primeiraDose);
			ps.setString(6, segundaDose);
			ps.setString(7,sus);
			ps.execute();
			JOptionPane.showMessageDialog(null, "A Cateira de vacinação foi incluida no Nº SUS " + sus);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualizar() {
		String sql = "UPDATE db_projeto.tb_carteira SET idvacina = ?, nomevacina = ?, qtddoses = ?,primeiradose =?,segundadose=?  WHERE (idcarteira = ?);";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			System.out.println(primeiraDose);
			ps.setInt(1,aux1);
			ps.setString(2, vacina);
			ps.setInt(3, qtdDoses);
			ps.setString(4, primeiraDose);
			ps.setString(5, segundaDose);
			ps.setInt(6, aux);
			ps.execute();
			JOptionPane.showMessageDialog(null, "A Cateira de vacinação foi atualizada no Nº SUS " + sus);
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
		String sql = "SELECT nomevacina, qtddoses, primeiradose,segundadose FROM db_projeto.tb_carteira WHERE nsus = ?";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, sus);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				this.vacina = rs.getString("nomevacina");
				this.qtdDoses = rs.getInt("qtddoses");
				this.primeiraDose = rs.getString("primeiradose");
				this.segundaDose = rs.getString("segundadose");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int pesquisarCidadao() {
		String sql = "SELECT idcidadao  FROM db_projeto.tb_cidadao WHERE nsus =?";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, sus);			
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
			idcidadao = rs.getInt("idcidadao");
			this.idcidadao =(idcidadao);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.idcidadao;

	}
	
	public int pesquisarVacina() {
		String sql = "SELECT idvacina  FROM db_projeto.tb_vacina WHERE nomevacina =?";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1,vacina);			
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
			idvacina = rs.getInt("idvacina");
			this.aux1 =(idvacina);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.aux1;

	}
	public int pesquisarCarteira() {
		String sql = "SELECT idcarteira  FROM db_projeto.tb_carteira WHERE =?";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1,idcidadao);			
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
			idvacina = rs.getInt("idvacina");
			this.aux1 =(idvacina);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.aux1;

	}
	
}
