package com.casa.entry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.casa.entry.model.Item;
import com.casa.entry.repository.Itens;

@Service
public class CadastroItemService {

	@Autowired
	private Itens itens;
	
	@Transactional
	public void salvar(Item item){
		itens.saveAndFlush(item);
	}
}
