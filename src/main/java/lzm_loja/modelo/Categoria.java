package lzm_loja.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // classe categoria da mapeando uma tabela no DB
@Table(name = "categorias") // diz qual o nome da tabela no DB
public class Categoria {
	
	@Id // indentificar chave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY) // identificar que o DB fica responsavel por gerar chaves
	private Long id;
	private String nome;
	
	public Categoria() {				
	}
	
	public Categoria(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
