package br.com.fiap.entity;

public class Estoque {

	private int id;
	private String nome;
	private double qtde;
	
	public Estoque() {
		
	}
	
	public Estoque(String nome, double qtde) {
		super();
		
		this.nome = nome;
		this.qtde = qtde;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getQtde() {
		return qtde;
	}

	public void setQtde(double qtde) {
		this.qtde = qtde;
	}
}
