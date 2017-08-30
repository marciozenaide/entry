package com.casa.entry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casa.entry.model.Processamento;

@Repository
public interface Processamentos extends JpaRepository<Processamento, Long> {
	
	public List<Processamento> findByArquivoIgnoreCaseContaining(String arquivo);
	
	public List<Processamento> findByArquivoIsNull();
}
