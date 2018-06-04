package br.com.bjbraz.spring;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.bjbraz.domain.Account;
import br.com.bjbraz.domain.ContactInfo;
import br.com.bjbraz.domain.ContactType;
import br.com.bjbraz.domain.Corporate;
import br.com.bjbraz.domain.User;
import br.com.bjbraz.dto.ContactTypeDTO;
import br.com.bjbraz.service.AccountService;
import br.com.bjbraz.service.ContactService;
import br.com.bjbraz.service.CorporateService;
import br.com.bjbraz.service.UserService;


/**
 * 
 * @author asimas
 *
 */
@RestController
public class RegisterRestController {
	private CorporateService registerService;
	private UserService userService;
	private AccountService accountService;
	private ContactService contactService;

	
	@Autowired
	RegisterRestController(CorporateService register, UserService user, AccountService account, ContactService contact) {
			this.registerService = register;
			this.userService     = user;
			this.accountService  = account;
			this.contactService  = contact;
	}
	
	@CrossOrigin
	@RequestMapping(value = ContractRestURIConstants.CREATE_CORPORATE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)	
	public String createCorporate(@RequestBody Corporate corporate) {
		try {
			registerService.salvar(corporate);
		}catch(Exception e) {
			return "Error";
		}
		
		return "Success";
	}
	
	
	@CrossOrigin
	@RequestMapping(value = ContractRestURIConstants.LIST_CONTACT_TYPE, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)	
	public ContactTypeDTO listContactType() {
		ContactTypeDTO contacts = new ContactTypeDTO();
		contacts.setContactTypes(contactService.findAll());
		return contacts;
	}	
	
	@CrossOrigin
	@RequestMapping(value = ContractRestURIConstants.CREATE_ACCOUNT, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)	
	public String createAccount(@RequestBody Account account) {
		try {
			account.setUsuario(userService.findById(account.getUsuario().getId()));
			accountService.salvar(account);
		}catch(Exception e) {
			return "Error";
		}
		
		return "Success";
	}
	
	
	@CrossOrigin
	@RequestMapping(value = ContractRestURIConstants.CREATE_USER, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)	
	public String createUser(@RequestBody User user) {
		try {
			userService.salvar(user);
		}catch(Exception e) {
			return "Error";
		}
		
		return "Success";
	}
	

	
}
