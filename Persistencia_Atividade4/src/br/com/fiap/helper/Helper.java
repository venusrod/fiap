package br.com.fiap.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.fiap.entity.Agenda;
import br.com.fiap.entity.AgendaPaciente;
import br.com.fiap.entity.MatMed;
import br.com.fiap.entity.Paciente;
import br.com.fiap.entity.Procedimento;

public class Helper {
	private EntityManager em;
	public Helper(EntityManager em){
		this.em = em;
	}
	public void salvar(Agenda agenda) throws Exception {
		try {
			em.getTransaction().begin();
			em.persist(agenda);
			em.getTransaction().commit();
		}
		catch (Exception e) {
			throw e;
		}	
	}

		@SuppressWarnings("unchecked")
	public List<Agenda> listarAgenda(){
		Query query = em.createQuery("select a from Agenda a");
		return query.getResultList();
		

	}
	
	public Paciente buscarPaciente(String cpf) {
		Query query = em.createQuery("select p from Paciente p where cpf = :cpf");
		query.setParameter("cpf", cpf);
		Paciente p = (Paciente)query.getSingleResult();
        return p;
		
	}

	public List<Procedimento> buscarProcedimento(String cpf) {
		Query query = em.createQuery("select p from Procedimento p where cpfpac = :cpf");
		query.setParameter("cpf", cpf);
		return query.getResultList();
		
	}

	public List<MatMed> buscarMatMed(String cpf) {
		Query query = em.createQuery("select m from MatMed m where cpfpac = :cpf");
		query.setParameter("cpf", cpf);
		return query.getResultList();
	}

	
	
	public Agenda buscarAgenda(int id){
		Query query = em.createQuery("select a from Agenda a where id = :id");
		query.setParameter("id", id);
		Agenda a = (Agenda)query.getSingleResult();
	    return a;
	}

	public Set<Paciente> listarAgendaPaciente(Agenda agenda){
        Set<Paciente> paciente = agenda.getPaciente();
        
		return paciente;

	}

	
	@SuppressWarnings("unchecked")
	public List<Agenda> listarTodos(){
		Query query = em.createNamedQuery("Agenda.findAll");
		return query.getResultList();
				
	}

	
}