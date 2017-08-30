package com.casa.entry.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.casa.entry.repository.Vendas;

@Controller
public class VendasController {

	@Autowired
	private Vendas vendas;
	
	@RequestMapping("/vendas/consulta")
	public ModelAndView consulta() {
		ModelAndView mv = new ModelAndView("venda/consulta");
		mv.addObject("vendas", vendas.findAll());
		System.out.println(mv.toString());
		return mv;
	}
}
