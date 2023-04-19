package lzm_loja.testes;

import java.math.BigDecimal;
import java.util.List;

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
import lzm_loja.vo.RelatorioDeVendasVo;

public class CadastroDePedido {

	private static final Cliente Cliente = null;

	public static void main(String[] args) {
		popularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager(); // usa JPAUtil para abrir conexão
		ProdutoDao produtoDao = new ProdutoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);

		Produto produto = produtoDao.buscarPorId(1l);
		Produto produto2 = produtoDao.buscarPorId(2l);
		Produto produto3 = produtoDao.buscarPorId(3l);
		
		Cliente cliente = clienteDao.buscarPorId(1l);

		em.getTransaction().begin();

		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(50, pedido, produto));
		pedido.adicionarItem(new ItemPedido(30, pedido, produto2));
		
		Pedido pedido2 = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(2, pedido, produto3));

		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido);

		em.getTransaction().commit();
		
		BigDecimal totalVendido = pedidoDao.valorTotalVendido();
		System.out.println("O VALOR TOTAL: "+ totalVendido);
		
		List<RelatorioDeVendasVo> relatorio = pedidoDao.relatorioDeVendas();
		relatorio.forEach(System.out::println); // percorrer cada elemento 

	}

	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES"); // INSTANCIANDO CATEGORIA
		Categoria videogames = new Categoria("VIDEOGAMES");
		Categoria informatica = new Categoria("INFORMATICA");
		
		Produto celular = new Produto("Xiomi Redmi", "8gb 128gb", new BigDecimal("800"), celulares);
		Produto videogame = new Produto("PS5", "Playstation 5", new BigDecimal("3500"), videogames);
		Produto macbook = new Produto("MacBook", "MacBook Pro 2023", new BigDecimal("5500"), informatica);
		
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
		categoriaDao.cadastrar(videogames);
		categoriaDao.cadastrar(informatica);
		
		produtoDao.cadastrar(celular);
		produtoDao.cadastrar(videogame);
		produtoDao.cadastrar(macbook);

		clienteDao.cadastrar(cliente);
		

		em.getTransaction().commit();
		em.close();

	}
}
