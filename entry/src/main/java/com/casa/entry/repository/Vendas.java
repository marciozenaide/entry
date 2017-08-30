package com.casa.entry.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casa.entry.model.Venda;

@Repository
public interface Vendas extends JpaRepository<Venda, Long>{
	
	//public Optional<Venda> findStatusIgnoreCase(String status);
	
	public Optional<Venda> findFirstByStatus(String status);

}
