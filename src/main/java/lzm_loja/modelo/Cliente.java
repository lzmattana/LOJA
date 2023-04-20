package lzm_loja.modelo;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// MAPEAMENTO DA ENTIDADE CLIENTES:
@Entity
@Table(name = "clientes")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Embedded
	private DadosPessoais dadosPessoais;

	public Cliente() {

	}

	public Cliente(String nome, String cpf) {
		this.dadosPessoais = new DadosPessoais(nome, cpf);
	}
	
	public String getNome() { // metodo delegado para dados pessoais
		return this.dadosPessoais.getNome();
	}
	
	public String getCpf() { // metodo delegado para dados pessoais
		return this.dadosPessoais.getCpf();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public DadosPessoais getDadosPessoais() {
		return dadosPessoais;
	}

}
