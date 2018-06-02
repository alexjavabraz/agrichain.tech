package br.com.bjbraz.spring;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.bjbraz.service.AccountService;
import br.com.bjbraz.service.TransactionService;

/**
 * 
 * @author alex.braz
 *
 */
@Controller
public class IndexController {
 
	private TransactionService transacaoService;
	private AccountService contaService;
	
	private static final String RETORNO_ERRO = "Erro valores inválidos";
	

	@Autowired
	IndexController(TransactionService t, AccountService c) {
		this.transacaoService = t;
		this.contaService     = c;
	}
	
	@RequestMapping(value = ContractRestURIConstants.ROOT, method = RequestMethod.GET)
	public ModelAndView inicio(Model model, Principal principal) {
		System.out.println("Executou... /index");
		ModelAndView mav = new ModelAndView("/index");
		return mav;
	}
	
	@RequestMapping(value = ContractRestURIConstants.CONTRATAR, method = RequestMethod.GET)
	public ModelAndView contratar(Model model, Principal principal) {
		System.out.println("Executou... /contratar");
		ModelAndView mav = new ModelAndView("/contratar");
		return mav;
	}	
	
	@RequestMapping(value = ContractRestURIConstants.LOGIN, method = RequestMethod.GET)
	public ModelAndView login() {
		System.out.println("Executou... /login");
		ModelAndView mav = new ModelAndView("/login");
		return mav;
	}
	
	@RequestMapping(value = "/login2", method = RequestMethod.GET)
	public ModelAndView login2() {
		System.out.println("Executou... /login2");
		ModelAndView mav = new ModelAndView("/login2");
		return mav;
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
	
	@RequestMapping(value = ContractRestURIConstants.DASHBOARD_CRYPTO, method = RequestMethod.GET)
	public ModelAndView dashbordCripto() {
		System.out.println("Executou... /dashboard-crypto");
		ModelAndView mav = new ModelAndView("/dashboard-crypto");
		return mav;
	}		


}
