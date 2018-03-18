package br.com.bjbraz.spring;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.bjbraz.service.ContaService;
import br.com.bjbraz.service.TransacaoService;

/**
 * 
 * @author alex.braz
 *
 */
@Controller
public class IndexController {
 
	private TransacaoService transacaoService;
	private ContaService contaService;
	
	private static final String RETORNO_ERRO = "Erro valores inválidos";
	

	@Autowired
	IndexController(TransacaoService t, ContaService c) {
		this.transacaoService = t;
		this.contaService     = c;
	}
	
	@RequestMapping(value = ContractRestURIConstants.ROOT, method = RequestMethod.GET)
	public ModelAndView inicio(Model model, Principal principal) {
		System.out.println("Executou... /index");
		ModelAndView mav = new ModelAndView("/index");
		return mav;
	}
	
	@RequestMapping(value = ContractRestURIConstants.LOGIN, method = RequestMethod.GET)
	public String login() {
		System.out.println("Executou... /login");
		ModelAndView mav = new ModelAndView("/login");
		return "login";
	}
	
	@RequestMapping(value = ContractRestURIConstants.INDEX, method = RequestMethod.GET)
	public ModelAndView index() {
		System.out.println("Executou... /index");
		ModelAndView mav = new ModelAndView("/index");
		return mav;
	}
	
	@RequestMapping(value = ContractRestURIConstants.DASHBOARD, method = RequestMethod.GET)
	public ModelAndView dashbord() {
		System.out.println("Executou... /dashboard");
		ModelAndView mav = new ModelAndView("/dashboard");
		return mav;
	}	


}
