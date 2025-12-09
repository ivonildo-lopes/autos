package com.loja.autos.batch.cliente;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ClienteBatchConfig {

	@Bean
	ClienteItemProcessor createClienteItemProcessor() {
		return new ClienteItemProcessor();
	}
	
	@Bean
	ClienteItemWriter clienteItemWriter() {
		return new ClienteItemWriter();
	}
	
	@Bean
	ClienteItemReader clienteItemReader() {
		return new ClienteItemReader();
	}
	
	
	// step
	@Bean
	Step stepCliente(JobRepository jobRepository, PlatformTransactionManager transactionManage) {
	    return new StepBuilder("stepCliente", jobRepository)
	            .<Cliente, Cliente>chunk(10, transactionManage)
	            .reader(clienteItemReader())
	            .processor(createClienteItemProcessor())
	            .writer(clienteItemWriter())
	            .build();
	}

	// step 2 use a tasklet
	@Bean
	Tasklet taskletTestClient() {
		return new TaskLetTest();
	}

	@Bean
	Step stepTestCliente(JobRepository jobRepository, PlatformTransactionManager transactionManage) {
		var step = new StepBuilder("stepTestCliente", jobRepository).tasklet(taskletTestClient(), transactionManage).build();
		return step;
	}

	// job
	@Bean
	Job clientJob(JobRepository jobRepository, @Qualifier("stepCliente") Step step1,
	        @Qualifier("stepTestCliente") Step step2) {
	    return new JobBuilder("clientJob", jobRepository)
	            .incrementer(new RunIdIncrementer())
	            .start(step1)
	            .next(step2)
	            .build();
	}
}
