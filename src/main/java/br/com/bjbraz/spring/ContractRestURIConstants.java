package br.com.bjbraz.spring;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.bjbraz.dto.account.CreateAccountDTO;

/**
 * 
 * @author asimas
 *
 */
public class ContractRestURIConstants {
	
    private static final Logger log = LoggerFactory.getLogger(ContractRestURIConstants.class);
    private static final SimpleDateFormat YYYYMMDD = new SimpleDateFormat("YYYYMMDD");
	
	/**
	 * Parametros DATA BASE
	 */
	public static final String CHAVE_URL_MATERA = "";
	public static final String CHAVE_CONTA_FCH  = "";	

	public static final String LOGIN = "/login";
	public static final String SALDO = "/rest/eth/saldo/{id}";
	public static final String SAQUE = "/rest/eth/saque";
	public static final String LISTAR_TODAS_TRANSACOES = "/rest/listTodasTrancoes";
	public static final String LISTAR_TRANSACOES_POR_CONTA = "/rest/listTrancoes/{id}";
	public static final String CADASTRO_USUARIO = "/rest/eth/cadastroUsuario";
	public static final String INDEX = "/index";
	public static final String ROOT = "/";
	public static final String DASHBOARD = "/dashboard";
	public static final String DASHBOARD_CRYPTO = "dashboard-crypto";
	public static final String CONTRATAR = "/contratar";
	public static final String CRIAR_CONTA_PF    = "/rest/createAccountpf";
	public static final String CRIAR_CONTA_PF_TEST = "/rest/createAccountpf/test";
	public static final String CONTA_PF = "PERSONAL";
	public static final String CONTA_PJ = "CORPORATE";
	
	public static final String API_KEY = "36BE5D3F-61DD-4836-AE21-39D7A8A686D6";
	public static final String CYPHER = "C0F5471D-86D6-4C46-B6C9-7A6F46AA16DF";
	public static final String CPF = "15420825872";
	public static final String URI = "http://public-api-elb-1090807689.us-west-2.elb.amazonaws.com/v1/accounts";//"http://localhost:8080/rest/createAccountpf" ; // 
	public static final String URI_SEARCH_ACCOUNTS = "http://public-api-elb-1090807689.us-west-2.elb.amazonaws.com/v1/accounts/";//"http://localhost:8080/rest/createAccountpf" ; //
	public static final String MEDIATOR_ACCOUNT_ID = "03828F0A-F12A-D2F0-5F78-A2C62E820FF2"; //mediator account id

	public static final String DEPLOY_SMART_CONTRACT_ETHEREUM    = "/rest/eth/deploy";
	public static final String SETUP_SMART_CONTRACT_ETHEREUM    = "/rest/eth/setup";
	public static final String SALVAR_ESTATS_BLOCKCHAIN    = "/rest/eth/setData";
	public static final String LISTAR_ESTATS_BLOCKCHAIN    = "/rest/eth/listAll";
	public static final String LISTAR_ESTATS_SMART_CONTRACTS  = "/rest/eth/contracts/listAlldeployed";
	public static final String LISTAR_POR_ID_BLOCKCHAIN    = "/rest/eth/findById/{id}";
	public static final String CREATE_CORPORATE    = "/rest/createCorporate";
	public static final String CREATE_ACCOUNT      = "/rest/createAccount";
	public static final String CREATE_USER         = "/rest/createUser";
	public static final String LIST_CONTACT_TYPE   = "/rest/listContactType";
	
	public static final String CREATE_CORPORATE_TST    = "/rest/createCorporateTest";
	public static final String CREATE_ACCOUNT_TST      = "/rest/createAccountTest";
	public static final String CREATE_USER_TST         = "/rest/createUserTest";
	
	

	public static String toJson(CreateAccountDTO dto) {
		ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(dto);
            log.info("JSON = " + json);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        
        return null;
	}
	
	/**
	 * 
	 * @param key
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encode(String key, String data) throws Exception {
		  Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
		  SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
		  sha256_HMAC.init(secret_key);

		  return Hex.encodeHexString(sha256_HMAC.doFinal(data.getBytes("UTF-8")));
	}
	
	/**
	 * 
	 * @return
	 */
	public static String generateExternalIdentifier() {
		/**
		 * Formação do campo "externalIdentifier"
			O campo 'externalIdentifier' suporta até 30 dígitos e visando não ocorrer duplicidades para diferentes parceiros, pois esse número deve ser único no nosso sistema, solicitamos:
			1) Da posição 01 a 14 - Informar o CNPJ (Exemplo: 87262488000103);
			2) Da posição 15 a 22 - Usar a data no formato AAAAMMDD
			3) Da posição 22 a 30 - Usar um sequencial (que pode ou não ser reinicializado diariamente)
			
			Exemplo de formação do campo "externalIdentifier": 872624880001032017071100000001
		 */
		String cnpj = "87262488000103";
		String data = YYYYMMDD.format(new Date());
		String unique = String.valueOf(System.currentTimeMillis());
		String sequencial = unique.substring(0 ,  8);
		
		return cnpj+data+sequencial;
	}	

}