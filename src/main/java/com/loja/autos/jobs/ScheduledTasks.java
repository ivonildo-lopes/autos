package com.loja.autos.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.loja.autos.repository.VeiculoRepository;

@Component
public class ScheduledTasks {
	
	@Autowired
	private VeiculoRepository repository;

    // Executa a cada 5 segundos
//    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        System.out.println("================ >Tarefa agendada executada - " + System.currentTimeMillis() + " TOTAL VEICULOS: " + repository.count());
    }
}