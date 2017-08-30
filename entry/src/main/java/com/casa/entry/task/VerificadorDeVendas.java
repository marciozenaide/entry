package com.casa.entry.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.casa.entry.model.Processamento;
import com.casa.entry.model.Venda;
import com.casa.entry.repository.Vendas;
import com.casa.entry.service.CadastroProcessamentoService;
import com.casa.entry.service.CadastroVendaService;

@Component
public class VerificadorDeVendas {

	private final String NAO_PROCESSADO = "NÃO PROCESSADO";
	private final long SEGUNDO = 1000;
	private final long MINUTO = SEGUNDO * 60;

	@Autowired
	private CadastroVendaService cadastroVendaService;

	@Autowired
	private CadastroProcessamentoService cadastroProcessmentoService;

	private Processamento processamento;

	@Autowired
	private Vendas vendas;
	
	@Scheduled(fixedRate = MINUTO)
	public void vendasPorMinuto() {
		List<Venda> list = new ArrayList<Venda>();
		list = vendas.findAll();

		Venda venda = null;
		for (Venda vendaElement : list) {
			if (vendaElement.getStatus().equals(NAO_PROCESSADO)) {
				venda = vendaElement;
				break;
			}
		}
		if (venda!=null){			
			processamento = new Processamento();
			processamento.setId(venda.getId());
			processamento.setData(new Date());
			processamento.setLoja(venda.getLoja());
			processamento.setPdv(venda.getPdv());
			processamento.setStatus("PENDENTE");
			//System.out.println(processamento.toString());
			cadastroProcessmentoService.salvar(processamento);
			//System.out.println(processamento);
			venda.setStatus("Ok");
			cadastroVendaService.salvar(venda);
			//System.out.println(venda);
		}
	}
}
