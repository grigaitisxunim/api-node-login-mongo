

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

public class Cidadao {
	private String sus;
	private String nome;
	private String telefone;
	private String email;
	private String cpf;
	private String dataDeNascimento;
	DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private TelaCarteiraVacinacao carteiraVacina;


	public Cidadao(String sus,String nome, String telefone, String email, String cpf, String dataDeNacimento) {
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

	
	public TelaCarteiraVacinacao getcarteiraVacina() {
		return carteiraVacina;
	}
	
	
	public void setCarteiraVacina(TelaCarteiraVacinacao carteiraVacina) {
		this.carteiraVacina = carteiraVacina;
	}
	
	

	public String getSus() {
		return sus;
	}

	public void setSus(String sus) {
		this.sus = sus;
	}
	
	
	 public String getDtNascimento(){ 
		 return dataDeNascimento; 
		 } 
		 /** 
		 * retorna a data formatada “dd/MM/yyyy” 
		 * @return String  
		 */ 
		

	

	public void inserir() {
		String sql = "INSERT INTO tb_cidadao(nsus, nome, datanasc, cpfrne, fone, email) VALUES (?, ?, ?, ?, ?, ?)";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, sus);
			ps.setString(2, nome);
			ps.setString(3,dataDeNascimento);
			ps.setString(4, cpf);
			ps.setString(5, telefone);
			ps.setString(6, email);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualizar() {
		String sql = "UPDATE tb_cidadao SET nome = ?, datanasc = ?, cpfrne = ? fone = ?, email = ?,  WHERE nsus = ?";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, nome);
			ps.setString(2, dataDeNascimento);
			ps.setString(3, cpf);
			ps.setString(4, telefone);
			ps.setString(5, email); 
			ps.setString(6, sus);
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
			ps.setString(1, sus);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void consultar() {
		
			//1: Definir o comando SQL
			String sql = "SELECT  nome, datanasc, cpfrne ,fone, email FROM db_projeto.tb_cidadao  WHERE nsus=?";
			//2: Abrir uma conexão
			ConnectionFactory factory = new ConnectionFactory();
			try (Connection c = factory.obtemConexao()){
			//3: Pré compila o comando
			PreparedStatement ps = c.prepareStatement(sql);
			//4: Preenche os dados faltante
			ps.setString(1,nome);
			ps.setString(2, dataDeNascimento);
			ps.setString(3, cpf);
			ps.setString(4,telefone);
			ps.setString(5, email);
			ps.setString(6, sus);
			//5: Executa o comando
			ps.execute();
			}
			catch (Exception e){
			e.printStackTrace();
			}
			}


				
				
	

	public String getDataDeNascimento() {
		return dataDeNascimento;
	}



	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}



	public void listar() {
		String sql = "SELECT * FROM db_projeto.tb_cidadao;";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obtemConexao()) {

			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String nome = rs.getString("nome");
				String dataDeNascimento = rs.getString("datanasc");
				String cpf = rs.getString("cpfrne");
				int telefone = rs.getInt("fone");
				String email = rs.getString("email");
				String aux = String.format("nome: %d, datanasc: %s, fone: %s, email: %s", nome, dataDeNascimento, telefone, email);
				JOptionPane.showMessageDialog(null, aux);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
