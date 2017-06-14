package br.com.fiap.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="PACIENTE", catalog="dbtarefa")
public class Paciente implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CPF", unique=true, nullable=false, length = 11)
	private String CPF;
	
	@Column(name = "NOME", nullable = false, length=45)
	private String nome;
	
	@Temporal(value=TemporalType.DATE)
	@Column(name = "DATANASC", nullable = false)
	private Date datanasc;
	
	@Column(name = "TELEFONE", nullable = false, length=20)
	private String telefone;
	
	
	@ManyToMany(fetch=FetchType.LAZY, mappedBy="paciente")
	private Set<Agenda> agenda = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "paciente")
	private List<Procedimento> listaProcedimento;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "paciente")
	private List<MatMed> listaMatMed;

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDatanasc() {
		return datanasc;
	}

	public void setDatanasc(Date datanasc) {
		this.datanasc = datanasc;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Set<Agenda> getAgenda() {
		return agenda;
	}

	public void setAgenda(Set<Agenda> agenda) {
		this.agenda = agenda;
	}

	public List<Procedimento> getListaProcedimento() {
		return listaProcedimento;
	}

	public void setListaProcedimento(List<Procedimento> listaProcedimento) {
		this.listaProcedimento = listaProcedimento;
	}

	public List<MatMed> getListaMatMed() {
		return listaMatMed;
	}

	public void setListaMatMed(List<MatMed> listaMatMed) {
		this.listaMatMed = listaMatMed;
	}

	
	
}
