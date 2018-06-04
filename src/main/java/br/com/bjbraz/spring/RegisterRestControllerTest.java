package br.com.bjbraz.spring;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.bjbraz.domain.Account;
import br.com.bjbraz.domain.ContactInfo;
import br.com.bjbraz.domain.ContactType;
import br.com.bjbraz.domain.Corporate;
import br.com.bjbraz.domain.User;
import br.com.bjbraz.service.AccountService;
import br.com.bjbraz.service.ContactService;
import br.com.bjbraz.service.CorporateService;
import br.com.bjbraz.service.UserService;

@RestController
public class RegisterRestControllerTest {

	private CorporateService registerService;
	private UserService userService;
	private AccountService accountService;
	private ContactService contactService;

	@Autowired
	RegisterRestControllerTest(CorporateService register, UserService user, AccountService account,
			ContactService contact) {
		this.registerService = register;
		this.userService = user;
		this.accountService = account;
		this.contactService = contact;
	}

	@CrossOrigin
	@RequestMapping(value = ContractRestURIConstants.CREATE_CORPORATE_TST, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Corporate createCorporate() {

		Corporate corporate = new Corporate();
		corporate.setAddress("address");
		corporate.setAlias("alias");
		corporate.setCity("city");
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setId(1l);
		ContactType contactType = new ContactType();
		contactType.setId(2l);
		contactType.setName("ContactType");
		contactInfo.setContactType(contactType);
		contactInfo.setValue("Valor");
		corporate.setContactInfo(contactInfo);
		corporate.setCountry("BRA");
		corporate.setDateLastChanged(new Date());
		corporate.setDateRegister(new Date());
		corporate.setEnabled(Boolean.TRUE);
		corporate.setName("Corporate 1");
		corporate.setNeighborhood("Itaim");
		corporate.setState("SP");

		return corporate;
	}

	@CrossOrigin
	@RequestMapping(value = ContractRestURIConstants.CREATE_USER_TST, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public User createUser() {
		User user = new User();
		user.setEmail("EMAIL@EMAIL.COM");
		user.setId(1L);
		user.setNome("Usuario");
		user.setPassword("password");
		return user;
	}

	@CrossOrigin
	@RequestMapping(value = ContractRestURIConstants.CREATE_ACCOUNT_TST, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Account createAccount() {
		Account a = new Account();
		a.setCorporate(new Corporate());
		a.getCorporate().setAddress("Address");
		a.getCorporate().setAlias("Alias");
		a.getCorporate().setCity("City");
		a.getCorporate().setContactInfo(new ContactInfo());
		a.getCorporate().getContactInfo().setId(1L);
		a.getCorporate().getContactInfo().setValue("VALUE");
		a.getCorporate().setCountry("Brazil");
		a.setDataAtualizacao(new Date());
		a.setEndereco("Address");
		a.setNome("Account Test");
		a.setSaldo(BigDecimal.TEN);
		a.setUsuario(new User());
		a.getUsuario().setEmail("email@email.com");
		a.getUsuario().setNome("NOME DO USUARIO");
		a.getUsuario().setPassword("Password");

		return a;
	}

}
