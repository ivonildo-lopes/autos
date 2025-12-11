package com.loja.autos.jobs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.loja.autos.entity.Cliente;
import com.loja.autos.repository.ClienteRepository;
import com.loja.autos.service.EmailService;

import jakarta.transaction.Transactional;

@Component
public class AlertaCliente {
	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
    private EmailService emailService;

    // Executa a cada 20 segundos
//    @Scheduled(fixedRate = 20000)
	@Scheduled(cron = "0 0 3 * * *") // rodar todos os dias 3 da manhã
	@Transactional
    public void alertaClienteAusente30Dias() {
    	
    	List<Cliente> clientesAusente = repository.consultaClientesAusentePor30dias();
    	
       for(Cliente cliente : clientesAusente) {
    	   
    	   if(cliente.getNotificacaoAusencia1() == null) {
    		   emailService.sendSimpleEmail(cliente.getEmail(), "xxxxxxxxxxxxxxxx titulo xxxxxxxxxxxxxxxxxxxxxxxx", 
    				   "xxxxxxxxxxxxxxxxxxxxxxxxx CORPO xxxxxxxxxxxxxxxxxxxxxxxxxxxx ");
    	   cliente.setNotificacaoAusencia1(true);
    	   repository.save(cliente);
    	   }
    	   
       }
    }
	
//    @Scheduled(fixedRate = 30000)
	@Scheduled(cron = "0 0 4 * * *") // rodar todos os dias 4 da manhã
	@Transactional
    public void alertaClienteAusente60Dias() {
    	
    	List<Cliente> clientesAusente = repository.consultaClientesAusentePor60dias();
    	
       for(Cliente cliente : clientesAusente) {
    	   
    	   if(cliente.getNotificacaoAusencia2() == null) {
    		   emailService.sendSimpleEmail(cliente.getEmail(), "xxxxxxxxxxxxxxxx titulo xxxxxxxxxxxxxxxxxxxxxxxx", 
    				   "xxxxxxxxxxxxxxxxxxxxxxxxx CORPO xxxxxxxxxxxxxxxxxxxxxxxxxxxx ");
    	   cliente.setNotificacaoAusencia2(true);
    	   repository.save(cliente);
    	   }
    	   
       }
    }
    
    
//    @Scheduled(fixedRate = 40000)
	@Scheduled(cron = "0 0 4 * * *") // rodar todos os dias 4 da manhã
	@Transactional
    public void desativarCliente() {
    	
    	List<Cliente> clientesAusente = repository.consultaClientesAusentePor90dias();
    	
       for(Cliente cliente : clientesAusente) {
    	   
    	   if(cliente.getAtivo() == true) {
    		   cliente.setAtivo(false);
    		   repository.save(cliente);
    	   }
    	   
       }
    }
}