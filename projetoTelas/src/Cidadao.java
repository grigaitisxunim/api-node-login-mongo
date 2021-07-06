
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Cidadao {
	private String sus;
	private String nome;
	private String telefone;
	private String email;
	private String cpf;
	private int idcidadao;
	private LocalDate dataDeNascimento;
	private LocalDate dataAtual = LocalDate.now();
	DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private TelaCarteiraVacinacao carteiraVacina;

	public Cidadao() {

	}

	public Cidadao(String sus, String nome, String telefone, String email, String cpf, LocalDate dataDeNascimento) {
		super();
		this.sus = sus;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.cpf = cpf;
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getSus() {
		return sus;
	}

	public void setSus(String sus) {
		this.sus = sus;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public TelaCarteiraVacinacao getCarteiraVacina() {
		return carteiraVacina;
	}

	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setCarteiraVacina(TelaCarteiraVacinacao carteiraVacina) {
		this.carteiraVacina = carteiraVacina;
	}

	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getDataDeNascimentoFormatado() {
		return outputFormatter.format(dataDeNascimento);
	}

	public void setDataDeNascimento(String d) {
		dataDeNascimento = LocalDate.parse(d, inputFormatter);
	}

	public String getFormatarData() {
		return inputFormatter.format(dataAtual);
	}

	public void inserir() {
		String sql = "INSERT INTO tb_cidadao(nsus, nome, datanasc, cpfrne, fone, email) VALUES (?, ?, ?, ?, ?, ?)";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, sus);
			ps.setString(2, nome);
			ps.setDate(3, java.sql.Date.valueOf(dataDeNascimento));
			ps.setString(4, cpf);
			ps.setString(5, telefone);
			ps.setString(6, email);
			ps.execute();
			JOptionPane.showMessageDialog(null, "O cidadão " + nome + " foi cadastrado com sucesso!!");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Cidadão já existente!!");
		}
	}

	public void atualizar() throws SQLException, NullPointerException {
		//String sql = "UPDATE tb_cidadao  SET nome = ?, datanasc = ?, cpfrne = ?, fone = ?, email = ?, nsus = ? WHERE idcidadao = ?";
		String sql = "UPDATE tb_cidadao p join tb_carteira c on p.idcidadao=c.idcidadao SET  p.nome = ?, p.datanasc = ?, p.cpfrne = ?, p.fone = ?, p.email = ?, p.nsus = ?, c.nsus =? WHERE p.idcidadao = ?";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, nome);
			ps.setDate(2, java.sql.Date.valueOf(dataDeNascimento));
			ps.setString(3, cpf);
			ps.setString(4, telefone);
			ps.setString(5, email);
			ps.setString(6, sus);
			ps.setString(7, sus);
			ps.setInt(8, idcidadao);
			ps.execute();
			JOptionPane.showMessageDialog(null, "Cidadão " + nome + " atualizado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void apagar() throws SQLException, NullPointerException,SQLIntegrityConstraintViolationException {
		String sql = "DELETE FROM tb_cidadao WHERE idcidadao = ?";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, idcidadao);
			ps.execute();
			ConsultaCidadao consultaCidadao = new ConsultaCidadao();
			consultaCidadao.setVisible(true);
			consultaCidadao.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			JOptionPane.showMessageDialog(null, "O Cidadão " +nome + " foi apagado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Cidadão já foi vacinado e não pode ser APAGADO!");
		}
		
	}

	public void ConsultarCidadao() throws SQLException, NullPointerException {
		String sql = "SELECT nome,datanasc,cpfrne,fone,email FROM db_projeto.tb_cidadao WHERE idcidadao = ?;";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, idcidadao);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				this.nome = rs.getString("nome");
				this.dataDeNascimento = rs.getDate("datanasc").toLocalDate();
				this.cpf = rs.getString("cpfrne");
				this.telefone = rs.getString("fone");
				this.email = rs.getString("email");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public int pesquisarIdCidadao() throws SQLException, NullPointerException {// pesquisarIdCidadão
		String sql = "SELECT idcidadao  FROM db_projeto.tb_cidadao WHERE nsus =?";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, sus);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				idcidadao = rs.getInt("idcidadao");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.idcidadao;

	}
}
