
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

public class Relatorio {
	public int idcarteira;
	private int idvacina;
	private int idcidadao;
	private String vacina;
	private int qtdDoses;
	private LocalDate primeiraDose;
	private LocalDate segundaDose;
	private LocalDate datainicio;
	private LocalDate datafinal;
	private String sus;
	private String nome;
	private int telefone;
	private String email;
	private String cpf;
	private LocalDate dataDeNascimento;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	DateTimeFormatter formatterSet = DateTimeFormatter.ofPattern("yyyy/MM/dd");

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

	public int getIdcidadao() {
		return idcidadao;
	}

	public void setIdcidadao(int idcidadao) {
		this.idcidadao = idcidadao;
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

	public String getPrimeiraDoseFormatado() {
		return formatter.format(primeiraDose);
	}

	public void setPrimeiraDose(String d) {
		primeiraDose = LocalDate.parse(d, formatterSet);
	}

	public String getSegundaDoseFormatado() {
		return formatter.format(segundaDose);
	}

	public void setSegundaDose(String d) {
		segundaDose = LocalDate.parse(d, formatterSet);
	}

	public String getDataInicioFormatado() {
		return formatter.format(datainicio);
	}

	public void setDataInicio(String d) {
		datainicio = LocalDate.parse(d, formatter);
	}

	public String getDataFinalFormatado() {
		return formatter.format(datafinal);
	}

	public void setDataFinal(String d) {
		datafinal = LocalDate.parse(d, formatter);
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

	public int getTelefone() {
		return telefone;
	}

	public void setTelefone(int telefone) {
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

	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getDataDeNascimentoFormatado() {
		return formatter.format(dataDeNascimento);
	}

	public void setDataDeNascimento(String d) {
		dataDeNascimento = LocalDate.parse(d, formatterSet);
		System.out.println(d);
	}

	public DateTimeFormatter getInputFormatter() {
		return formatter;
	}

	public void setInputFormatter(DateTimeFormatter inputFormatter) {
		this.formatter = inputFormatter;
	}

	public ArrayList<String[]> listarPrimeiraDose() {

		ArrayList<String[]> relatorio = new ArrayList<>();

		String sql = "SELECT p.nsus,p.nome,p.datanasc,p.cpfrne,p.fone,c.nomevacina,c.primeiradose,c.primeiradose + interval v.periodo day\r\n"
				+ "from tb_carteira c, tb_vacina v, tb_cidadao p \r\n"
				+ "where  c.idvacina =v.idvacina and p.idcidadao = c.idcidadao\r\n"
				+ "and v.doses >1\r\n"
				+ "and c.primeiradose + interval v.periodo day \r\n"
				+ "between ? and ?\r\n"
				+ "and c.segundadose is null";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setDate(1, java.sql.Date.valueOf(datainicio));
			ps.setDate(2, java.sql.Date.valueOf(datafinal));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String col[] = { (rs.getString("nsus")), (rs.getString("nome")), (rs.getString("datanasc")),
						(rs.getString("cpfrne")), (rs.getString("fone")), (rs.getString("nomevacina")),
						(rs.getString("primeiradose")), (rs.getString("c.primeiradose + interval v.periodo day"))};
			
				relatorio.add(col);
				if(col == null) {
					JOptionPane.showMessageDialog(null, "Não existem cidadãos para esse intervalo de data!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return relatorio;

	}

	public ArrayList<String[]> listarDuasDose() {

		ArrayList<String[]> relatorio = new ArrayList<>();

		String sql = "SELECT p.nsus,p.nome,p.datanasc,p.cpfrne,p.fone,c.nomevacina,c.primeiradose,c.primeiradose + interval v.periodo day\r\n"
				+ "from tb_carteira c, tb_vacina v, tb_cidadao p \r\n"
				+ "where  c.idvacina =v.idvacina and p.idcidadao = c.idcidadao\r\n"
				+ "and c.primeiradose + interval v.periodo day \r\n"
				+ "between ? and ?\r\n"
				+ "and c.segundadose is null";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setDate(1, java.sql.Date.valueOf(datainicio));
			ps.setDate(2, java.sql.Date.valueOf(datafinal));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String col[] = { (rs.getString("nsus")), (rs.getString("nome")), (rs.getString("datanasc")),
						(rs.getString("cpfrne")), (rs.getString("fone")), (rs.getString("nomevacina")),
						(rs.getString("primeiradose")), (rs.getString("c.primeiradose + interval v.periodo day"))};
			
				relatorio.add(col);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return relatorio;

	}
}
