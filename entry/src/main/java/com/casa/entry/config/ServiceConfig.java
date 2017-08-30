package com.casa.entry.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.casa.entry.service.CadastroVendaService;

@Configuration
@ComponentScan(basePackageClasses = CadastroVendaService.class)
public class ServiceConfig {

}
