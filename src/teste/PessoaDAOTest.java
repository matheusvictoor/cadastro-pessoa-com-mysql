package teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cadastros.Pessoa;
import cadastros.PessoaDAO;

/**
 * 
 * @author Matheus Victor
 *
 */

public class PessoaDAOTest {
	
	private PessoaDAO pessoaDAO;
	
	@Before
	public void setUp() throws Exception {
		pessoaDAO = new PessoaDAO();
	}
	
	@After
	public void tearDown() throws Exception {
		pessoaDAO = null;
	}
	
	@Test
	public void testAdicionar() throws Exception {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("João");
		pessoa.setEmail("joao@gmail.com");
		
		pessoaDAO.adicionar(pessoa);
		
		List<Pessoa> pessoas = pessoaDAO.listarPessoas();
		assertEquals(1, pessoas.size());
		
		Pessoa pessoaAdicionada = pessoas.get(0);
		assertEquals("João", pessoaAdicionada.getNome());
		assertEquals("joao@gmail.com", pessoaAdicionada.getEmail());
	}
	
	@Test
	public void testListarPessoas() throws Exception {
		Pessoa pessoa1 = new Pessoa();
		pessoa1.setNome("Maria");
		pessoa1.setEmail("maria@gmail.com");
		
		Pessoa pessoa2 = new Pessoa();
		pessoa2.setNome("Pedro");
		pessoa2.setEmail("pedro@gmail.com");
		
		pessoaDAO.adicionar(pessoa1);
		pessoaDAO.adicionar(pessoa2);
		
		List<Pessoa> pessoas = pessoaDAO.listarPessoas();
		assertEquals(2, pessoas.size());
		
		Pessoa pessoaListada1 = pessoas.get(0);
		assertEquals("Maria", pessoaListada1.getNome());
		assertEquals("maria@gmail.com", pessoaListada1.getEmail());
		
		Pessoa pessoaListada2 = pessoas.get(1);
		assertEquals("Pedro", pessoaListada2.getNome());
		assertEquals("pedro@gmail.com", pessoaListada2.getEmail());
	}
	
	@Test
	public void testConsultarPessoa() throws Exception {
		Pessoa pessoa1 = new Pessoa();
		pessoa1.setNome("João");
		pessoa1.setEmail("joao@gmail.com");
		
		Pessoa pessoa2 = new Pessoa();
		pessoa2.setNome("Maria");
		pessoa2.setEmail("maria@gmail.com");
		
		pessoaDAO.adicionar(pessoa1);
		pessoaDAO.adicionar(pessoa2);
		
		Pessoa pessoaConsultada = pessoaDAO.consultarPessoa(1);
		assertEquals("João", pessoaConsultada.getNome());
		assertEquals("joao@gmail.com", pessoaConsultada.getEmail());
		
		pessoaConsultada = pessoaDAO.consultarPessoa(2);
		assertEquals("Maria", pessoaConsultada.getNome());
		assertEquals("maria@gmail.com", pessoaConsultada.getEmail());
		
		assertNull(pessoaDAO.consultarPessoa(3));
	}
	
	@Test
	public void testEditar() throws Exception {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("João");
		pessoa.setEmail("joao@gmail.com");
		
		pessoaDAO.adicionar(pessoa);
		
		pessoa.setNome("João Silva");
		pessoa.setEmail("joaosilva@gmail.com");
		
		pessoaDAO.editar(pessoa);
		
		Pessoa pessoaEditada = pessoaDAO.consultarPessoa(1);
		assertEquals("João Silva", pessoaEditada.getNome());
		assertEquals("joaosilva@gmail.com", pessoaEditada.getEmail());
	}
	
	@Test
	public void testDeletar() throws Exception {
	    // Cria uma nova pessoa para ser adicionada ao banco de dados
	    Pessoa pessoa = new Pessoa();
	    pessoa.setNome("Maria");
	    pessoa.setEmail("maria@teste.com");

	    // Adiciona a pessoa ao banco de dados
	    PessoaDAO dao = new PessoaDAO();
	    dao.adicionar(pessoa);

	    // Verifica se a pessoa foi adicionada corretamente
	    Pessoa pessoaAdicionada = dao.consultarPessoa(pessoa.getId());
	    assertNotNull(pessoaAdicionada);

	    // Deleta a pessoa do banco de dados
	    dao.deletar(pessoa.getId());

	    // Verifica se a pessoa foi removida do banco de dados
	    Pessoa pessoaDeletada = dao.consultarPessoa(pessoa.getId());
	    assertNull(pessoaDeletada);
	}
}
