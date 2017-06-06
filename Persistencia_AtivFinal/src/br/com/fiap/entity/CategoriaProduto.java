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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CATEGORIA_PRODUTO", catalog="dbtarefa")
public class CategoriaProduto implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_CATEGORIA", unique=true, nullable=false)
	private Integer idCategoriaProduto;

	@Column(name="NOME_CATEGORIA_PRODUTO", unique=false, nullable=false,
			length=45)
	private String nomeCategoriaProduto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_RESTAURANTE")
	private Restaurante restaurante;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="categoriaProduto")
	private List<Produto> listaProduto;

	public Integer getIdCategoriaProduto() {
		return idCategoriaProduto;
	}

	public void setIdCategoriaProduto(Integer idCategoriaProduto) {
		this.idCategoriaProduto = idCategoriaProduto;
	}

	public String getNomeCategoriaProduto() {
		return nomeCategoriaProduto;
	}

	public void setNomeCategoriaProduto(String nomeCategoriaProduto) {
		this.nomeCategoriaProduto = nomeCategoriaProduto;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public List<Produto> getListaProduto() {
		return listaProduto;
	}

	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}
	
	
}
