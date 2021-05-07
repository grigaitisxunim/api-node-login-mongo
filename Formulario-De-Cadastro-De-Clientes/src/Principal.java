import javax.swing.JOptionPane;

public class Principal {
	public static void main(String[] args) {
		String menu = "1-Cadastrar\n2-Atualizar\n3-Apagar\n4-Listar\n0-Sair";
		int op;
		do {
			op = Integer.parseInt(JOptionPane.showInputDialog(menu));
			switch (op) {
			// cria uma pessoa
			case 1: {
				String nome = JOptionPane.showInputDialog("Nome?");
				String fone = JOptionPane.showInputDialog("Fone?");
				String email = JOptionPane.showInputDialog("Email?");
				Pessoa p = new Pessoa();

				if (!fone.isEmpty() || !nome.isEmpty() || !email.isEmpty()) {
					p.setNome(nome);
					p.setFone(fone);
					p.setEmail(email);
					p.inserir();
				} else {
					JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios");
					break;
				}
			}
			// atualiza uma pessoa
			case 2: {
				String nome = JOptionPane.showInputDialog("Nome?");
				String fone = JOptionPane.showInputDialog("Fone?");
				String email = JOptionPane.showInputDialog("Email?");
				int codigo = Integer.parseInt(JOptionPane.showInputDialog("Codigo?"));
				Pessoa p = new Pessoa();
				p.setNome(nome);
				p.setFone(fone);
				p.setEmail(email);
				p.setCodigo(codigo);
				p.atualizar();
				break;
			}
			// apaga uma pessoa
			case 3: {
				int codigo = Integer.parseInt(JOptionPane.showInputDialog("Codigo?"));
				Pessoa p = new Pessoa();
				p.setCodigo(codigo);
				p.apagar();
				break;
			}
			// lista as pessoas cadastradas
			case 4: {
				Pessoa p = new Pessoa();
				p.listar();
				break;
			}
			case 0:
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opção inválida");
			}
		} while (op != 0);
	}

}
