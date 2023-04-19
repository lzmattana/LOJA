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

public class CadastroDePedido {

	private static final Cliente Cliente = null;

	public static void main(String[] args) {
		popularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager(); // usa JPAUtil para abrir conexão
		ProdutoDao produtoDao = new ProdutoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);

		Produto produto = produtoDao.buscarPorId(1l);
		Cliente cliente = clienteDao.buscarPorId(1l);

		em.getTransaction().begin();

		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(50, pedido, produto));

		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido);

		em.getTransaction().commit();
		
		BigDecimal totalVendido = pedidoDao.valorTotalVendido();
		System.out.println("O VALOR TOTAL: "+ totalVendido);

	}

	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES"); // INSTANCIANDO CATEGORIA

		Produto celular = new Produto("Xiomi Redmi", "8gb 128gb", new BigDecimal("800"), celulares);
		Cliente cliente = new Cliente("Leonardo", "10101010");
//		celular.setNome("Xiomi Redmi");
//		celular.setDescricao("8gb 128gb");
//		celular.setPreco(new BigDecimal("800"));

		// usa inves de connection do JDBC, conectar com a DB loja
//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("loja");
//		EntityManager em = factory.createEntityManager();

		EntityManager em = JPAUtil.getEntityManager(); // usa JPAUtil para abrir conexão
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);

		em.getTransaction().begin();

		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		clienteDao.cadastrar(cliente);

		em.getTransaction().commit();
		em.close();

	}
}
