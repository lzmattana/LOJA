package lzm_loja.modelo;

import javax.persistence.Embeddable;

@Embeddable // classe embutivel
public class DadosPessoais {
	private String nome;
	private String cpf;
	
	public DadosPessoais() {
		
	}
	
	public DadosPessoais(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	
}
