package com.casa.entry.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import com.casa.entry.task.VerificadorDeVendas;
import com.casa.entry.task.VerificarProcessamentoPendente;

@Configuration
@EnableScheduling
public class TaskConfig implements SchedulingConfigurer {

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		// TODO Auto-generated method stub
		taskRegistrar.setScheduler(taskExecutor());
	}

	@Bean(destroyMethod = "shutdown")
	public Executor taskExecutor() {
		return Executors.newScheduledThreadPool(100);
	}

	@Bean
	public VerificadorDeVendas verificarVenda() {
		 return new VerificadorDeVendas();
	}
	
	@Bean
	public VerificarProcessamentoPendente verificarProcessamento(){
		return new VerificarProcessamentoPendente();
	}

}
