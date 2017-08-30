package com.casa.entry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casa.entry.model.Item;
import com.casa.entry.model.Venda;

@Repository
public interface Itens  extends JpaRepository<Item, Long> {
	
	public List<Item> findByVenda(Venda venda);

}
