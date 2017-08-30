package com.casa.entry.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.casa.entry.model.Item;
import com.casa.entry.model.Processamento;
import com.casa.entry.model.Venda;
import com.casa.entry.repository.Itens;
import com.casa.entry.repository.Processamentos;
import com.casa.entry.repository.Vendas;
import com.casa.entry.service.CadastroItemService;
import com.casa.entry.service.CadastroVendaService;

@RestController
@RequestMapping("/vendas")
public class VendaResource {

	@Autowired
	private Processamentos processamentos;

	@Autowired
	private Vendas vendas;

	@Autowired
	private Itens itens;

	@Autowired
	private CadastroVendaService cadastroVendaService;

	@Autowired
	private CadastroItemService cadastroItemService;

	@GetMapping
	public List<Processamento> listar() {
		return processamentos.findAll();
	}

	@GetMapping("/teste")
	public List<Venda> listarVendas() {
		List<Venda> vendasEItens = new ArrayList<Venda>();
		for (Venda venda : vendas.findAll()) {
			venda.setItens(itens.findByVenda(venda));
			vendasEItens.add(venda);
		}
		return vendasEItens;
	}

	@PostMapping
	// @ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Venda> gerarNovaVenda(@RequestBody Venda venda, HttpServletResponse response) {

		venda.setStatus("NÃO PROCESSADO");
		Venda vendaSalva = cadastroVendaService.salvar(venda);

		for (Item item : venda.getItens()) {
			BigDecimal desc = new BigDecimal(venda.getItens().get(0).getDesconto().toString());
			item.setDesconto(desc);
			BigDecimal precoUnit = new BigDecimal(venda.getItens().get(0).getPrecoUnitario().toString());
			item.setPrecoUnitario(precoUnit);
			item.setProduto(venda.getItens().get(0).getProduto());
			item.setVenda(vendaSalva);
			cadastroItemService.salvar(item);
		}

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(vendaSalva.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());

		return ResponseEntity.created(uri).body(vendaSalva);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscarPeloCodigo(@PathVariable Long codigo) {
		Venda venda = null;
		venda = vendas.findOne(codigo);
		return venda != null ? ResponseEntity.ok(venda) : ResponseEntity.notFound().build();
	}

}
