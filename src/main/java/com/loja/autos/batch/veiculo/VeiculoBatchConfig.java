package com.loja.autos.batch.veiculo;

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

import com.loja.autos.batch.cliente.TaskLetTest;
import com.loja.autos.dto.request.VeiculoRequest;

@Configuration
public class VeiculoBatchConfig {

	@Bean
	VeiculoItemProcessor createVeiculoItemProcessor() {
		return new VeiculoItemProcessor();
	}
	
	@Bean
	VeiculoItemWriter veiculoItemWriter() {
		return new VeiculoItemWriter();
	}
	
	@Bean
	VeiculoItemReader veiculoItemReader() throws Exception {
		return new VeiculoItemReader();
	}
	
	
	// step
	@Bean
	Step stepVeiculo(JobRepository jobRepository, PlatformTransactionManager transactionManage) throws Exception {
	    return new StepBuilder("stepVeiculo", jobRepository)
	            .<VeiculoRequest, VeiculoRequest>chunk(10, transactionManage)
	            .reader(veiculoItemReader())
	            .processor(createVeiculoItemProcessor())
	            .writer(veiculoItemWriter())
	            .build();
	}

	// step 2 use a tasklet
	@Bean
	Tasklet taskletTestVeiculo() {
		return new TaskLetTest();
	}

	@Bean
	Step stepTestVeiculo(JobRepository jobRepository, PlatformTransactionManager transactionManage) {
		var step = new StepBuilder("stepTestVeiculo", jobRepository).tasklet(taskletTestVeiculo(), transactionManage).build();
		return step;
	}

	// job
	@Bean
	Job veiculoJob(JobRepository jobRepository, @Qualifier("stepVeiculo") Step step1,
	        @Qualifier("stepTestVeiculo") Step step2) {
	    return new JobBuilder("veiculoJob", jobRepository)
	            .incrementer(new RunIdIncrementer())
	            .start(step1)
	            .next(step2)
	            .build();
	}
}
