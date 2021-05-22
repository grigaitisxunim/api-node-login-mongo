

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
		private int nsus;
		private int sus;
		public String aux;
		
		
		public Carteira() {
			
		}
		public Carteira(String vacina, int periodo, int qtdDoses, String  primeiraDose, String  segundaDose,int idcidadao,int nsus) {
			super();
			this.vacina = vacina;
			this.qtdDoses = qtdDoses;
			this.primeiraDose = primeiraDose;
			this.segundaDose=segundaDose;
			this.idcidadao=idcidadao;
			this.nsus=nsus;
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
		public int getNsus() {
			return nsus;
		}
		public void setNsus(int nsus) {
			this.nsus = nsus;
		}
		public void inserir() {
			String sql = "INSERT INTO tb_carteira(nomevacina,qtdDoses,primeiradose,segundadose) VALUES (?, ?, ?,?)";
			ConnectionFactory factory = new ConnectionFactory();
			try (Connection c = factory.obtemConexao()) {
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setString(1, vacina);
				ps.setInt(2, qtdDoses);
				ps.setString(3,primeiraDose);
				ps.setString(4,segundaDose);
				ps.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void atualizar() {
			String sql = "UPDATE tb_vacina SET vacina = ?, periodo = ?, qtdDoses = ? WHERE idvacina = ?";
			ConnectionFactory factory = new ConnectionFactory();
			try (Connection c = factory.obtemConexao()) {
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setString(1, vacina);
				ps.setInt(3, qtdDoses);
				ps.execute();
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
					String aux = String.format(" nomevaina: %s, periodo: %s, doses: %s",vacina,periodo, qtdDoses);
					JOptionPane.showMessageDialog(null, aux);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/*public void pesquisarCliente() {
			String sql = "SELECT idcidadao  FROM db_projeto.tb_cidadao WHERE nsus =?";
			ConnectionFactory factory = new ConnectionFactory();
			try (Connection c = factory.obtemConexao()) {
				PreparedStatement ps = c.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					idcidadao = rs.getInt("idcidadao");
					sus = rs.getInt("nsus");
					aux = String.format(" idcidadao: %d");
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}*/
	
	}




