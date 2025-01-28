package com.loja.autos.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("batch")
public class JobController {
	
	@Autowired
	@Qualifier("productJob")
	private Job productJob;
	
	@Autowired
	@Qualifier("clientJob")
	private Job clientJob;
	
	@Autowired
	@Qualifier("veiculoJob")
	private Job veiculoJob;
	
	
	@Autowired
	private JobLauncher jobLauncher;

	@GetMapping("/load")
	public ResponseEntity productLoad() {
		try {
			JobParameters parameters = new JobParametersBuilder().addLong("Start-At", System.currentTimeMillis())
					.toJobParameters();
			jobLauncher.run(productJob, parameters);
			return ResponseEntity.ok("job run");

		} catch (Exception e) {
			return new ResponseEntity("job error ", HttpStatus.EXPECTATION_FAILED);
		}

	}
	
	
	@GetMapping("/load2")
	public ResponseEntity clienteLoad() {
		try {
			JobParameters parameters = new JobParametersBuilder().addLong("Start-At", System.currentTimeMillis())
					.toJobParameters();
			jobLauncher.run(clientJob, parameters);
			return ResponseEntity.ok("job run");

		} catch (Exception e) {
			return new ResponseEntity("job error ", HttpStatus.EXPECTATION_FAILED);
		}

	}
	
	
	@GetMapping("/load3")
	public ResponseEntity veiculoLoad() {
		try {
			JobParameters parameters = new JobParametersBuilder().addLong("Start-At", System.currentTimeMillis())
					.toJobParameters();
			jobLauncher.run(veiculoJob, parameters);
			return ResponseEntity.ok("job run");

		} catch (Exception e) {
			return new ResponseEntity("job error ", HttpStatus.EXPECTATION_FAILED);
		}

	}
	
	
	
}
