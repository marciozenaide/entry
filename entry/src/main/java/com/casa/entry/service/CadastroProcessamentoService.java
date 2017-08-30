package com.casa.entry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.casa.entry.model.Processamento;
import com.casa.entry.repository.Processamentos;

@Service
public class CadastroProcessamentoService {

	@Autowired
	private Processamentos processamentos;
	
	@Transactional
	public void salvar(Processamento processamento){
		processamentos.saveAndFlush(processamento);
	}
}
