package br.com.bjbraz.spring;

import java.net.URI;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.bjbraz.dto.StatusLogin;
import br.com.bjbraz.dto.account.AdditionalDetailsPersonDTO;
import br.com.bjbraz.dto.account.BillingAddressDTO;
import br.com.bjbraz.dto.account.ClientDTO;
import br.com.bjbraz.dto.account.CreateAccountDTO;
import br.com.bjbraz.dto.account.MobilePhoneDTO;
import br.com.bjbraz.dto.account.RgDTO;
import br.com.bjbraz.dto.account.TaxIdentifierDTO;
import br.com.bjbraz.exception.UserNotFoundException;
import br.com.bjbraz.service.AccountService;
import br.com.bjbraz.service.TransactionService;
import br.com.bjbraz.service.UserService;


/**
 * 
 * @author alex.braz
 *
 */
@RestController
public class IndexRestController {

	private TransactionService transacaoService;
	private AccountService accountService;
	private UserService userService;

	private static final String RETORNO_ERRO = "Invalid Values";

	@ModelAttribute("todos")
	public StatusLogin todos() {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
			HttpSession session= attr.getRequest().getSession(true); // true == allow create
			session.getId();
		
		return new StatusLogin();
	}

	@Autowired
	IndexRestController(TransactionService t, AccountService c, UserService u) {
		this.transacaoService = t;
		this.accountService = c;
		this.userService = u;
	}

	@CrossOrigin
	@RequestMapping(value = ContractRestURIConstants.CRIAR_CONTA_PF, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)	
	public String createAccount(@RequestHeader(value="transaction-hash") String hash, @RequestBody CreateAccountDTO create) {

		if(hash == null) {
			return null;
		}

		this.validateCreate(create);

		create.setAccountType(ContractRestURIConstants.CONTA_PF);

		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(create);
			System.out.println("JSON = " + json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return "";
	}

	@CrossOrigin
	@RequestMapping(value = ContractRestURIConstants.CRIAR_CONTA_PF_TEST, method = RequestMethod.GET)	
	public CreateAccountDTO teste() {

		CreateAccountDTO dto = new CreateAccountDTO();

		dto.setExternalIdentifier("11975132627");
		dto.setClient(new ClientDTO());
		dto.getClient().setName("ALEX SIMAS BRAZ");
		dto.getClient().setTaxIdentifier(new TaxIdentifierDTO());
		dto.getClient().getTaxIdentifier().setTaxId("82344612068");
		dto.getClient().getTaxIdentifier().setCountry("BRA");
		dto.getClient().setMobilePhone(new MobilePhoneDTO());
		dto.getClient().getMobilePhone().setPhoneNumber("11975132627");
		dto.getClient().setEmail("alexjavabraz@gmail.com");
		dto.setBillingAddress(new BillingAddressDTO());
		dto.getBillingAddress().setLogradouro("lOGRADOURO UNICO");
		dto.getBillingAddress().setNumero("999");
		dto.getBillingAddress().setBairro("BAIRRO");
		dto.getBillingAddress().setCidade("CIDADE");
		dto.getBillingAddress().setEstado("SP");
		dto.getBillingAddress().setCep("13100321");
		dto.getBillingAddress().setPais("BRA");

		dto.setClientType("PERSON");

		dto.setAccountType("ORDINARY");
		dto.setAdditionalDetailsPerson(new AdditionalDetailsPersonDTO());
		dto.getAdditionalDetailsPerson().setGender("M");
		dto.getAdditionalDetailsPerson().setFather("PAI DO CLIENTE 1");
		dto.getAdditionalDetailsPerson().setMother("MAE DO CLIENTE 1");
		dto.getAdditionalDetailsPerson().setBirthDate("1970-01-01");
		dto.getAdditionalDetailsPerson().setBirthCity("CIDADE");
		dto.getAdditionalDetailsPerson().setBirthState("SP");
		dto.getAdditionalDetailsPerson().setBirthCountry("BRA");
		dto.getAdditionalDetailsPerson().setRg(new RgDTO());
		dto.getAdditionalDetailsPerson().getRg().setNumber("325912840");
		dto.getAdditionalDetailsPerson().getRg().setIssueDate("1980-01-01");
		dto.getAdditionalDetailsPerson().getRg().setIssuer("SSP");
		dto.getAdditionalDetailsPerson().getRg().setState("SP");
		dto.getAdditionalDetailsPerson().setMaritalStatus("SINGLE");
		return dto;
	}	



	private void validateCreate(CreateAccountDTO create) {
		// TODO Auto-generated method stub

	}

	private void validateUser(String userId) {
		this.userService.findByUserName(userId).orElseThrow(
				() -> new UserNotFoundException(userId));
	}


	public ResponseEntity<?> todo(CreateAccountDTO create){
		return this.accountService
				.findByUserNameLike(create.getClient().getName())
				.map(account -> {

					//					Account result = bookmarkRepository.save(new Bookmark(account,
					//							input.getUri(), input.getDescription()));

					account = accountService.salvar(account);

					URI location = ServletUriComponentsBuilder
							.fromCurrentRequest().path("/{id}")
							.buildAndExpand(account.getId()).toUri();

					return ResponseEntity.created(location).build();
				})
				.orElse(ResponseEntity.noContent().build());
	}



}
