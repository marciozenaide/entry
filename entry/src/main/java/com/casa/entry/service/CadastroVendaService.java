package com.casa.entry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.casa.entry.model.Venda;
import com.casa.entry.repository.Vendas;

@Service
public class CadastroVendaService {

	@Autowired
	private Vendas vendas;
	
	@Transactional
	public Venda salvar(Venda venda){
		return vendas.saveAndFlush(venda);
	}
}
