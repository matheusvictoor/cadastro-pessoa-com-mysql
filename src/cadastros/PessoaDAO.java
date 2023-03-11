package cadastros;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.ConnectionFactory;

/**
 * 
 * @author Matheus Victor
 *
 */

public class PessoaDAO extends ConnectionFactory{
	
	public void adicionar(Pessoa pessoa) throws Exception {
		super.getConnection();
		String sql = "INSERT INTO pessoa (nome, email) VALUES(?, ?)";

		try {
			super.stmt = con.prepareStatement(sql);
			super.stmt.setString(1, pessoa.getNome());
			super.stmt.setString(2, pessoa.getEmail());
			super.stmt.execute();
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao tentar adicionar o contato " + e.getMessage(), e);
		} finally {
			super.closeConnection();
		}	
	}
	
	public List<Pessoa> listarPessoas() throws Exception {
		super.getConnection();
		String sql = "SELECT * FROM pessoa";
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		
		try {
			super.stmt = con.prepareStatement(sql);
			resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				Pessoa pessoa = new Pessoa();				
				pessoa.setId(resultSet.getInt("id"));
				pessoa.setNome(resultSet.getString("nome"));
				pessoa.setEmail(resultSet.getString("email"));			
				pessoas.add(pessoa);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao tentar listar os contatos " + e.getMessage(), e);
		} finally {
			super.closeConnection();
		}	
			
		return pessoas;
	}
	
	public Pessoa consultarPessoa(int id) throws Exception {	
		super.getConnection();
		String sql = "SELECT * FROM pessoa WHERE id = ?";
		super.stmt = con.prepareStatement(sql);
		super.stmt.setInt(1, id);
		Pessoa pessoa = null;
		
		try {
			super.resultSet = stmt.executeQuery();
			
			if(resultSet != null) {
				if(resultSet.next()) {
					pessoa = new Pessoa();
					pessoa.setId(resultSet.getInt("id"));
					pessoa.setNome(resultSet.getString("nome"));
					pessoa.setEmail(resultSet.getString("email"));
				} else {
					System.out.println("Registro não encontrado!");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao tentar consultar o contato " + e.getMessage(), e);
		} finally {
			super.closeConnection();
		}	
			
		return pessoa;
	}
	
	public void editar(Pessoa pessoa) throws Exception {
		super.getConnection();
		String sql = "UPDATE pessoa SET nome = ?, email = ? WHERE id = ?";
		
		try {
			super.stmt = con.prepareStatement(sql);
			super.stmt.setString(1, pessoa.getNome());
			super.stmt.setString(2, pessoa.getEmail());
			super.stmt.setInt(3, pessoa.getId());
			super.stmt.execute();
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao tentar editar o contato " + e.getMessage(), e);
		} finally {
			super.closeConnection();
		}	
	}
	
	public void deletar(int id) throws Exception {
		super.getConnection();
		String sql = "DELETE FROM pessoa WHERE id = ?";
		
		try {
			super.stmt = con.prepareStatement(sql);
			super.stmt.setInt(1, id);
			super.stmt.execute();
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao tentar deletar o contato " + e.getMessage(), e);
		} finally {
			super.closeConnection();
		}
	}
}
