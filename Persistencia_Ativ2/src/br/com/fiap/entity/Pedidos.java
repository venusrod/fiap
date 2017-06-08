package br.com.fiap.entity;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "PEDIDOS" , catalog = "dbtarefa")

public class Pedidos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IDPEDIDO", unique=true, nullable=false)
	private Integer idPedido;

	@Column(name="DATA", unique=false, nullable=false,
			length=45)
	private Date data;
		
	@Column(name="DESCRICAO", unique=false, nullable=false,
			length=45)
	private String descricao;
	
	@Column(name="VALOR", unique=false, nullable=false)
	private double valor;
	
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "IDCLIENTE") 
	private Clientes clientes;

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Clientes getClientes() {
		return clientes;
	}

	public void setClientes(Clientes clientes) {
		this.clientes = clientes;
	}
	
	
}
