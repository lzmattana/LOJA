package lzm_loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import lzm_loja.dao.CategoriaDao;
import lzm_loja.dao.ProdutoDao;
import lzm_loja.modelo.Categoria;
import lzm_loja.modelo.Produto;
import lzm_loja.util.JPAUtil;

public class CadastroDeProduto {
	public static void main(String[] args) {
		
		CadastrarProduto();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		Produto p = produtoDao.buscarPorId(1l);
		System.out.println(p.getPreco());
//		List<Produto> todos = produtoDao.buscarTodos();
//		todos.forEach(p2 -> System.out.println(p.getNome()));
		List<Produto> todos = produtoDao.buscarPorNomeDaCategoria("CELULARES");
		todos.forEach(p2 -> System.out.println(p.getNome()));
	}

	private static void CadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES"); //INSTANCIANDO CATEGORIA
		
		Produto celular = new Produto("Xiomi Redmi","8gb 128gb",new BigDecimal("800"), celulares);
//		celular.setNome("Xiomi Redmi");
//		celular.setDescricao("8gb 128gb");
//		celular.setPreco(new BigDecimal("800"));
		
		// usa inves de connection do JDBC, conectar com a DB loja
//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("loja");
//		EntityManager em = factory.createEntityManager();
		
		EntityManager em = JPAUtil.getEntityManager();	// usa JPAUtil para abrir conexão	
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);

		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		
		em.getTransaction().commit();
		em.close();
	
	}
}
