package lzm_loja.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import lzm_loja.modelo.Produto;

public class ProdutoDao {
	private EntityManager em;

	public ProdutoDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Produto produto) {
		this.em.persist(produto);
	}

	public void atualizar(Produto produto) {
		this.em.merge(produto);
	}

	public void remover(Produto produto) {
		produto = em.merge(produto);
		this.em.remove(produto);
	}

	public Produto buscarPorId(Long id) {
		return em.find(Produto.class, id);
	}

	public List<Produto> buscarTodos() {
		String jpql = "SELECT p FROM Produto p"; // diferente do SQL, seria SELECT * FROM produtos;
		return em.createQuery(jpql, Produto.class).getResultList();
	}

	public List<Produto> buscarPorNome(String nome) {
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome"; // diferente do SQL, seria SELECT * FROM produtos;
		return em.createQuery(jpql, Produto.class).setParameter("nome", nome).getResultList();
	}

	public List<Produto> buscarPorNomeDaCategoria(String nome) {
		String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome"; // // diferente onde usaria JOIN aqui
																				// usa p.cat.nome
		return em.createQuery(jpql, Produto.class).setParameter("nome", nome).getResultList();
	}

	public BigDecimal buscarPrecoDoProdutoComNome(String nome) {
		String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome"; //// diferente onde usaria JOIN aqui usa
																			//// p.cat.nome
		return em.createQuery(jpql, BigDecimal.class).setParameter("nome", nome).getSingleResult(); // metodo para
																									// retornar somente
																									// um resultado

	}

	public List<Produto> buscarPorParametros(String nome, BigDecimal preco, LocalDate dataCadastro) {
		String jpql = "SELECT p FROM Produto p WHERE 1=1 "; // gambiarra pra forçar o where na querry
		if (nome != null && !nome.trim().isEmpty()) {
			jpql = " AND p.nome = :nome ";
		}
		if (preco != null) {
			jpql = " AND p.preco = :preco ";
		}
		if (dataCadastro != null) {
			jpql = " AND p.dataCadastro = :dataCadastro ";
		}
		TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
		if (nome != null && !nome.trim().isEmpty()) {
			query.setParameter("nome", query);
		}
		if (preco != null) {
			query.setParameter("preco", query);
		}
		if (dataCadastro != null) {
			query.setParameter("dataCadastro", query);
		}
		return query.getResultList();

	}

}
