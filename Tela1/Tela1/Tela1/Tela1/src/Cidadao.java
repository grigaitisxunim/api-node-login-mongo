

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

public class Cidadao {
	private int sus;
	private String nome;
	private String telefone;
	private String email;
	private String cpf;
	private LocalDate dataDeNascimento;
	DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private carteiraVacina carteiraVacina;


	public Cidadao(String nome, String telefone, String email, String cpf, LocalDate dataDeNacimento, int sus) {
		super();
		this.sus=sus;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.cpf = cpf;
		this.dataDeNascimento = dataDeNacimento;
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

	
	public carteiraVacina getcarteiraVacina() {
		return carteiraVacina;
	}
	
	
	public void setCarteiraVacina(carteiraVacina carteiraVacina) {
		this.carteiraVacina = carteiraVacina;
	}
	
	

	public int getSus() {
		return sus;
	}

	public void setSus(int sus) {
		this.sus = sus;
	}
	
	
	 public LocalDate getDtNascimento(){ 
		 return dataDeNascimento; 
		 } 
		 /** 
		 * retorna a data formatada “dd/MM/yyyy” 
		 * @return String  
		 */ 
		 public String getDtNascimentoFormatado(){ 
		 return formato.format(dataDeNascimento); 
		 } 
		 // passando como parâmetro uma data no formato string “dd/MM/yyyy” 
		 public void setDtNascimento(String d){ 
			 dataDeNascimento = LocalDate.parse(d, formato); 
		 } 
		 // passando como parâmetro uma data vinda do banco de dados. 
		public LocalDate setDtNascimento(java.sql.Date d){ 
			return dataDeNascimento = d.toLocalDate(); 
		 }

	

	public void inserir() {
		String sql = "INSERT INTO tb_cidadao(nome, fone, email, cpfrne, datanasc) VALUES (?, ?, ?, ?, ?)";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, nome);
			ps.setString(1, nome);
			ps.setString(2, telefone);
			ps.setString(3, email);
			ps.setString(4, cpf);
			ps.setDate(5, java.sql.Date.valueOf(dataDeNascimento));
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualizar() {
		String sql = "UPDATE cidadao SET nsus= ?,nome = ?, fone = ?, email = ?, cpfrne = ?, datanasc = ? WHERE nsus = ?";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, sus);
			ps.setString(2, nome);
			ps.setString(3, telefone);
			ps.setString(4, email);
			ps.setString(5, cpf);
			ps.setDate(6, java.sql.Date.valueOf(dataDeNascimento)); 
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void apagar() {
		String sql = "DELETE FROM tb_cidadao WHERE nsus = ?";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, sus);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void consultar() {
		
		String sql = "SELECT nsus,nome,fone,email,cpfrne,datanasc FROM db_projeto.tb_cidadao WHERE nsus = ? ";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, sus);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				nome = rs.getString("nome");
				telefone = rs.getString("fone");
				email=rs.getString("email");
				cpf=rs.getString("cpfrene");
				dataDeNascimento=setDtNascimento(rs.getDate("datanascimento"));}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


				
				
	

	public void listar() {
		String sql = "SELECT * FROM db_projeto.tb_cidadao;";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {

			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String cpf = rs.getString("cpfrne");
				String nome = rs.getString("nome");
				String telefone = rs.getString("fone");
				String email = rs.getString("email");
				String dataDeNascimento = rs.getString("datanasc");
				String aux = String.format("cpfrne: %d, nome: %s, fone: %s, email: %s", cpf, nome, telefone, email,
						dataDeNascimento);
				JOptionPane.showMessageDialog(null, aux);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
