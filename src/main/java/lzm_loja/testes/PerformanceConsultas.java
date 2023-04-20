package lzm_loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import lzm_loja.dao.CategoriaDao;
import lzm_loja.dao.ClienteDao;
import lzm_loja.dao.PedidoDao;
import lzm_loja.dao.ProdutoDao;
import lzm_loja.modelo.Categoria;
import lzm_loja.modelo.Cliente;
import lzm_loja.modelo.ItemPedido;
import lzm_loja.modelo.Pedido;
import lzm_loja.modelo.Produto;
import lzm_loja.util.JPAUtil;

public class PerformanceConsultas {
	public static void main(String[] args) {
		popularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager(); // usa JPAUtil para abrir conexão
		PedidoDao pedidoDao = new PedidoDao(em);
		Pedido pedido = pedidoDao.buscarPedidoComCliente(1l);
		em.close();
		System.out.println(pedido.getCliente().getNome());
	}
	
	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria videogames = new Categoria("VIDEOGAMES");
		Categoria informatica = new Categoria("INFORMATICA");

		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
		Produto videogame = new Produto("PS5", "Playstation 5", new BigDecimal("8000"), videogames);
		Produto macbook = new Produto("Macbook", "Macboo pro retina", new BigDecimal("14000"), informatica);

		Cliente cliente = new Cliente("Rodrigo", "123456");

		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, pedido, celular));
		pedido.adicionarItem(new ItemPedido(40, pedido, videogame));

		Pedido pedido2 = new Pedido(cliente);
		pedido2.adicionarItem(new ItemPedido(2, pedido, macbook));

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		PedidoDao pedidoDao = new PedidoDao(em);

		em.getTransaction().begin();

		categoriaDao.cadastrar(celulares);
		categoriaDao.cadastrar(videogames);
		categoriaDao.cadastrar(informatica);

		produtoDao.cadastrar(celular);
		produtoDao.cadastrar(videogame);
		produtoDao.cadastrar(macbook);

		clienteDao.cadastrar(cliente);

		pedidoDao.cadastrar(pedido);
		pedidoDao.cadastrar(pedido2);

		em.getTransaction().commit();
		em.close();

	}
}