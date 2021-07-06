
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Carteira extends Cidadao{

	private String vacina;
	private int qtdDoses;
	private LocalDate primeiraDose;
	private LocalDate segundaDose;
	private int idcidadao;
	private String sus;
	public int idcarteira;
	private int idvacina;
	DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	

	public Carteira() {

	}

	public Carteira(String vacina, int periodo, int qtdDoses, LocalDate primeiraDose, LocalDate segundaDose, int idcidadao,
			String sus) {
		super();
		this.vacina = vacina;
		this.qtdDoses = qtdDoses;
		this.primeiraDose = primeiraDose;
		this.segundaDose = segundaDose;
		this.idcidadao = idcidadao;
		this.sus = sus;
	}


	public int getIdcarteira() {
		return idcarteira;
	}

	public void setIdcarteira(int idcarteira) {
		this.idcarteira = idcarteira;
	}

	public int getIdvacina() {
		return idvacina;
	}

	public void setIdvacina(int idvacina) {
		this.idvacina = idvacina;
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
		return this.qtdDoses;
	}

	public void setQtdDoses(int qtdDoses) {
		this.qtdDoses = qtdDoses;
	}

	public LocalDate getPrimeiraDose() {
		return primeiraDose;
	}

	public void setPrimeiraDose(LocalDate primeiraDose) {
		this.primeiraDose = primeiraDose;
	}

	public LocalDate getSegundaDose() {
		return segundaDose;
	}

	public void setSegundaDose(LocalDate segundaDose) {
		this.segundaDose = segundaDose;
	}

	public int getIdcidadao() {
		return idcidadao;
	}

	public void setIdcidadao(int idcidadao) {
		this.idcidadao = idcidadao;
	}
	public String getPrimeiraDoseFormatado(){
		return  formato.format(primeiraDose);
	}
	
	public void setPrimeiraDose(String d){
	primeiraDose = LocalDate.parse(d, formato);
	}
	public String getSegundaDoseFormatado(){
		return formato.format(segundaDose);
	}
	public void setSegundaDose(String d){
	segundaDose = LocalDate.parse(d, formato);
	}
	public void setResultPrimeiraDose() {
	//primeiraDose = rs.getDate("dt_nascimento");
	}

	public void inserir() {
		String sql = "INSERT INTO db_projeto.tb_carteira (idvacina, idcidadao, nomevacina, qtddoses,primeiradose, segundadose, nsus) VALUES (?, ?, ?, ?, ?, ?, ?)";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1,idvacina);
			ps.setInt(2, idcidadao);
			ps.setString(3, vacina);
			ps.setInt(4, qtdDoses);
			ps.setDate(5, java.sql.Date.valueOf(primeiraDose));
			ps.setDate(6, java.sql.Date.valueOf(segundaDose));
			ps.setString(7,sus);
			ps.execute();
			JOptionPane.showMessageDialog(null, "Carteira de vacinação cadastrada com sucesso!!");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Cidadão informado já possui carteira de vacinação!");
		}
	}
	public void inserirPrimeiraDos() {
		String sql = "INSERT INTO db_projeto.tb_carteira (idvacina, idcidadao, nomevacina, qtddoses,primeiradose, nsus) VALUES (?, ?, ?, ?, ?, ?)";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1,idvacina);
			ps.setInt(2, idcidadao);
			ps.setString(3, vacina);
			ps.setInt(4, qtdDoses);
			ps.setDate(5, java.sql.Date.valueOf(primeiraDose));
			//ps.setDate(6, java.sql.Date.valueOf(segundaDose));
			ps.setString(6,sus);
			ps.execute();
			JOptionPane.showMessageDialog(null, "Primeira dose da vacinação cadastrada com sucesso!!");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar Primeira DOSE!!");
		}
	}

	public void atualizar() {
		String sql = "UPDATE db_projeto.tb_carteira SET idvacina = ?, nomevacina = ?, qtddoses = ?,primeiradose =?,segundadose=?  WHERE (idcarteira = ?);";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, idvacina);
			ps.setString(2, vacina);
			ps.setInt(3, qtdDoses);
			ps.setDate(4, java.sql.Date.valueOf(primeiraDose));
			ps.setDate(5, java.sql.Date.valueOf(segundaDose));
			ps.setInt(6, idcarteira);
			ps.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void apagar() {
		String sql = "DELETE FROM tb_carteira WHERE idcarteira = ?";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, idcarteira);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}JOptionPane.showMessageDialog(null, "Carteira de vacinação deletada com sucesso!");
	}

	public void listar()throws SQLException, NullPointerException {
		String sql = "SELECT nomevacina, qtddoses, primeiradose FROM db_projeto.tb_carteira WHERE idcidadao = ?";
		String sql1 = "SELECT segundadose FROM db_projeto.tb_carteira WHERE idcidadao = ?";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, idcidadao);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				this.vacina = rs.getString("nomevacina");
				this.qtdDoses = rs.getInt("qtddoses");
				this.primeiraDose = rs.getDate("primeiradose").toLocalDate();
			}if(qtdDoses == 2) {
				PreparedStatement pss = c.prepareStatement(sql1);
				pss.setInt(1, idcidadao);
				ResultSet rss = pss.executeQuery();
				//System.out.println(qtdDoses);
				while (rss.next()) {
				this.segundaDose = rss.getDate("segundadose").toLocalDate();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public int pesquisarCidadao()throws SQLException, NullPointerException {
		String sql = "SELECT idcidadao  FROM db_projeto.tb_cidadao WHERE nsus =?";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, sus);			
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
			idcidadao = rs.getInt("idcidadao");
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
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.idvacina;

	}
	public int pesquisarCarteira()throws SQLException, NullPointerException {
		String sql = "SELECT idcarteira  FROM db_projeto.tb_carteira WHERE idcidadao =?";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1,idcidadao);			
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
			idcarteira = rs.getInt("idcarteira");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.idcarteira;

	}	
}
