package lzm_loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "valor_total") // mudando o nome do camelCase
	private BigDecimal valorTotal = BigDecimal.ZERO; // instanciando o objeto iniciando em zero
	private LocalDate data = LocalDate.now();

	@ManyToOne
	private Cliente cliente;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL) // mapeando para nao criar tabelas desmecessarias, 
	private List<ItemPedido> itens = new ArrayList<>();

	public Pedido() {
	}

	public void adicionarItem(ItemPedido item) {
		item.setPedido(this); // o item conhece o pedido
		this.itens.add(item); // o pedido conhece o item
		this.valorTotal = this.valorTotal.add(item.getValor());
	}

	public Pedido(Cliente cliente) {
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}