package com.loja.autos.batch.product;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.loja.autos.batch.cliente.TaskLetTest;

@Configuration
public class CsvBatchConfig {

	@Value("${file.input}")
	private String source;
	
	@Autowired
	private ProductRepository productRepository;

	// item reader
	@Bean
	FlatFileItemReader<Product> productItemReader() {
		return new FlatFileItemReaderBuilder<Product>().name("productItemReader")
				.resource(new ClassPathResource(source)).linesToSkip(1).delimited()
				.names(new String[] { "name", "price" }).fieldSetMapper(new BeanWrapperFieldSetMapper<>() {
					{
						setTargetType(Product.class);
					}
				}).build();
	}

	// processor
	@Bean
	ProductProcessor createItemProcessor() {

		return new ProductProcessor();
	}

	// writer

	@Bean
	RepositoryItemWriter<Product> productWriter() {
		RepositoryItemWriter<Product> repositoryItemWriter = new RepositoryItemWriter<>();
		repositoryItemWriter.setRepository(productRepository);
		repositoryItemWriter.setMethodName("save");
		return repositoryItemWriter;
	}

	// step
	@Bean
	Step stepProduct(JobRepository jobRepository, PlatformTransactionManager transactionManage) {
		var step = new StepBuilder("stepProduct", jobRepository).<Product, Product>chunk(10, transactionManage)
				.reader(productItemReader()).processor(createItemProcessor()).writer(productWriter()).build();
		return step;
	}

	// step 2 use a tasklet
	@Bean
	Tasklet taskletTest() {
		return new TaskLetTest();
	}

	@Bean
	Step stepTest(JobRepository jobRepository, PlatformTransactionManager transactionManage) {
		var step = new StepBuilder("stepTest", jobRepository).tasklet(taskletTest(), transactionManage).build();
		return step;
	}

	// job
	@Bean
	Job productJob(JobRepository jobRepository, @Qualifier("stepProduct") Step step1,
			@Qualifier("stepTest") Step step2) {
		return new JobBuilder("productJob", jobRepository).incrementer(new RunIdIncrementer()).start(step1).next(step2)
				.build();
	}

}
