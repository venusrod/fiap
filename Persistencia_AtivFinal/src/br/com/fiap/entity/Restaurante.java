package br.com.fiap.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="RESTAURANTE", catalog="dbtarefa")
public class Restaurante implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_RESTAURANTE", unique=true, nullable=false)
	private Integer idRestaurante;

	@Column(name="NOME_RESTAURANTE", unique=false, nullable=false,
			length=45)
	private String nomeRestaurante;
	
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "restaurante")
	private List<CategoriaProduto> listaCategoriaProduto;

	public Integer getIdRestaurante() {
		return idRestaurante;
	}

	public void setIdRestaurante(Integer idRestaurante) {
		this.idRestaurante = idRestaurante;
	}

	public String getNomeRestaurante() {
		return nomeRestaurante;
	}

	public void setNomeRestaurante(String nomeRestaurante) {
		this.nomeRestaurante = nomeRestaurante;
	}

	public List<CategoriaProduto> getListaCategoriaProduto() {
		return listaCategoriaProduto;
	}

	public void setListaCategoriaProduto(List<CategoriaProduto> listaCategoriaProduto) {
		this.listaCategoriaProduto = listaCategoriaProduto;
	}
	
	
}
