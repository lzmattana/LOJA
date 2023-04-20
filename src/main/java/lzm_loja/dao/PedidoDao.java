package lzm_loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import lzm_loja.modelo.Pedido;
import lzm_loja.vo.RelatorioDeVendasVo;

public class PedidoDao {
	private EntityManager em;

	public PedidoDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Pedido pedido) {
		this.em.persist(pedido);
	}
	
	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return em.createQuery(jpql, BigDecimal.class).getSingleResult();
	}
	
	//public List<Object[]> relatorioDeVendas() { nao é melhor pratica colocar objeto
	public List<RelatorioDeVendasVo> relatorioDeVendas() { // classe value object
		//String jpql = "SELECT produto.nome, " para uma entidade usando List<>
		String jpql = "SELECT new lzm_loja.vo.RelatorioDeVendasVo("
				+ "produto.nome, "
				+ "SUM(item.quantidade), "
				+ "MAX(pedido.data)) "
				+ "FROM Pedido pedido "
				+ "JOIN pedido.itens item JOIN item.produto produto GROUP BY produto.nome "
				+ "ORDER BY item.quantidade DESC";
		return em.createQuery(jpql, RelatorioDeVendasVo.class).getResultList();
	}
	
	public Pedido buscarPedidoComCliente(Long id) {
		return em.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id", Pedido.class) // transforma essa consulta em eager por conta do join fetch
				.setParameter("id", id)
				.getSingleResult();
	}

}
