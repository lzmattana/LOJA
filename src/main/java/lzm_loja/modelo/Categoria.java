package lzm_loja.modelo;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // classe categoria da mapeando uma tabela no DB
@Table(name = "categorias") // diz qual o nome da tabela no DB
public class Categoria {
	
	@EmbeddedId
	private CategoriaId id;
	
	public Categoria() {
	}
	
	public Categoria(String nome) {
		this.id = new CategoriaId(nome, "xpto");
	}

	public String getNome() {
		return this.id.getNome();
	}

}
