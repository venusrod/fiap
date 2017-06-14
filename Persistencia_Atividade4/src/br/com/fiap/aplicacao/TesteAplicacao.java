package br.com.fiap.aplicacao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import br.com.fiap.entity.Agenda;
import br.com.fiap.entity.AgendaPaciente;
import br.com.fiap.entity.MatMed;
import br.com.fiap.entity.Paciente;
import br.com.fiap.entity.Procedimento;
import br.com.fiap.helper.Helper;

public class TesteAplicacao {
	
	public static void main(String[] args) {
		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("jpaPU");
		EntityManager em = emf.createEntityManager();
		incluirAgenda(em);
		listarAgenda(em);
//		buscarAgenda(em, 1);
		buscarPaciente(em,"16107582851");
	}
	
	private static void incluirAgenda(EntityManager em){
		Helper dao = new Helper(em);
		
		Date data = new Date();
		
		
		Agenda agenda = new Agenda();
		agenda.setData(data);
		agenda.setHora(data); 
		agenda.setDescricao("Descricao Agenda 1");
		
		
		Paciente paciente = new Paciente();
		paciente.setCPF("16107582851");
		paciente.setDatanasc(data);  
		paciente.setNome("Venus");
		paciente.setTelefone("4566-7788");
		paciente.getAgenda().add(agenda);
		agenda.getPaciente().add(paciente);
		
		Procedimento procedimento = new Procedimento();
		procedimento.setDescricao("Descricao Procedimento 1");
		procedimento.setPaciente(paciente);
		procedimento.setPreco(10.5);
		
		Procedimento procedimento2 = new Procedimento();
		procedimento2.setDescricao("Descricao Procedimento 2");
		procedimento2.setPaciente(paciente);
		procedimento2.setPreco(12.5);
		
		List<Procedimento> listaProcedimento = new ArrayList<Procedimento>();
		listaProcedimento.add(procedimento);
		listaProcedimento.add(procedimento2);
		
		paciente.setListaProcedimento(listaProcedimento);
		
		MatMed matMed = new MatMed();
        matMed.setDescricao("Descricao Material 1");
        matMed.setFabricante("Fabricante 1");
        matMed.setPreco(15.5);
        matMed.setPaciente(paciente);

		MatMed matMed2 = new MatMed();
        matMed2.setDescricao("Descricao Material 2");
        matMed2.setFabricante("Fabricante 2");
        matMed2.setPreco(25.5);
        matMed2.setPaciente(paciente);

             
		List<MatMed> listaMatMed = new ArrayList<MatMed>();
		listaMatMed.add(matMed);
		listaMatMed.add(matMed2);
		
		paciente.setListaMatMed(listaMatMed);
		
		Paciente paciente2 = new Paciente();
		paciente2.setCPF("12345678911");
		paciente2.setDatanasc(data);  
		paciente2.setNome("Cecilia");
		paciente2.setTelefone("1234-5678");
		paciente2.getAgenda().add(agenda);
		agenda.getPaciente().add(paciente2);

		Agenda agenda2 = new Agenda();
		agenda2.setData(data);
		agenda2.setHora(data); 
		agenda2.setDescricao("Descricao Agenda 1");
		
		
		Paciente paciente3 = new Paciente();
		paciente3.setCPF("55577788899");
		paciente3.setDatanasc(data);  
		paciente3.setNome("Marcio");
		paciente3.setTelefone("1122-4455");
		paciente3.getAgenda().add(agenda2);
		agenda2.getPaciente().add(paciente3);

		
		try {
			dao.salvar(agenda);
			dao.salvar(agenda2);
			System.out.println("Dados Cadastrados");
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void listarAgenda(EntityManager em){
		Helper dao = new Helper(em);
		List<Agenda> agenda = dao.listarTodos();
		
		System.out.println("");
		System.out.println("Listar as Agendas...");
		
		for (Agenda agenda_for : agenda) {
			
			System.out.println("AGENDA: " + agenda_for.getId() + " - " + agenda_for.getDescricao());
			
			Set<Paciente> paciente = dao.listarAgendaPaciente(agenda_for);
            System.out.println("     PACIENTES: ");
			for (Paciente paciente_for : paciente) {
	        	System.out.println("     CPF: " + paciente_for.getCPF() + " - NOME: " + paciente_for.getNome());
	        }
		}
	}

	
	private static void buscarAgenda(EntityManager em, int id){
		Helper dao = new Helper(em);
		Agenda a = dao.buscarAgenda(id);
		System.out.println(a.getId()+ ": " + a.getDescricao());
	}
	
	private static void buscarPaciente(EntityManager em, String cpf){
		Helper dao = new Helper(em);
	    Paciente p = dao.buscarPaciente(cpf);
	    
		System.out.println("");
	    System.out.println("Listar Paciente e seus Procedimentos/Materiais");
	    
		System.out.println("CPF: " + p.getCPF() + " - NOME: " + p.getNome());
		
		List<Procedimento> procedimento = dao.buscarProcedimento(cpf);

		for (Procedimento procedimento_for : procedimento) {
			System.out.println("     PROCEDIMENTO - DESCRICAO: " + procedimento_for.getDescricao() + " - PRECO: " + procedimento_for.getPreco());
		}
		
		List<MatMed> matMed = dao.buscarMatMed(cpf);

		for (MatMed matMed_for : matMed) {
			System.out.println("     MATERIAIS MEDICOS - DESCRICAO: " + matMed_for.getDescricao() + " - FABRICANTE: " + matMed_for.getFabricante() + " - PRECO: " + matMed_for.getPreco());
		}
	}
	
}