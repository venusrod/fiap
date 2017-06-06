package br.com.fiap.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="PRODUTO", catalog="dbtarefa")
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_PRODUTO", unique=true, nullable=false)
	private Integer idProduto;
	
	@Column(name="NOME_PRODUTO", unique=false, nullable=false,
			length=45)
	private String nomeProduto;
	
	@Column(name="VALOR_PRODUTO", unique=false, nullable=false)
	private double valorProduto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CATEGORIA_PRODUTO")
	private CategoriaProduto categoriaProduto;

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public double getValorProduto() {
		return valorProduto;
	}

	public void setValorProduto(double valorProduto) {
		this.valorProduto = valorProduto;
	}

	public CategoriaProduto getCategoriaProduto() {
		return categoriaProduto;
	}

	public void setCategoriaProduto(CategoriaProduto categoriaProduto) {
		this.categoriaProduto = categoriaProduto;
	}

	
	
}
