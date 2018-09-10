package br.com.bjbraz.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.bjbraz.domain.BlockchainData;
import br.com.bjbraz.dto.account.SensorBlockchainDTO;
import br.com.bjbraz.service.BlockchainService;
import br.com.bjbraz.util.Response;
import br.com.bjbraz.util.ResponseUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author alex.braz
 *
 */
@RestController
public class BlockchainRestController {

	private BlockchainService transacaoService;

	private static final String RETORNO_ERRO = "Invalid Values";

	@Autowired
	BlockchainRestController(BlockchainService t) {
			this.transacaoService = t;
	}

	@ApiOperation(value = "Salva as informações do device IOT para a chamada do Smart Contract", notes = "Essa operação realiza o cadastro do Produto SVA")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = ResponseUtil.CADASTRO_EFETUADO_COM_SUCESSO, response = String.class),
			@ApiResponse(code = 400, message = ResponseUtil.OCORREU_UM_ERRO),
			@ApiResponse(code = 409, message = ResponseUtil.REGISTRO_JA_EXISTE)
	})
	@CrossOrigin
	@RequestMapping(value = ContractRestURIConstants.SALVAR_ESTATS_BLOCKCHAIN, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<String>> setData(
			@RequestBody SensorBlockchainDTO create) {
		String retorno            = setDataGet(create);
		Response<String> resposta = new Response<String>();
		resposta.setData(retorno);
		resposta.setCode(ResponseUtil.SUCCESS_CODE);
		resposta.setMessage(ResponseUtil.CADASTRO_EFETUADO_COM_SUCESSO);
		return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
	}
	
	@ApiOperation(value = "Salva as informações do device IOT para a chamada do Smart Contract", notes = "Essa operação realiza o cadastro do Produto SVA")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = ResponseUtil.CADASTRO_EFETUADO_COM_SUCESSO, response = String.class),
			@ApiResponse(code = 400, message = ResponseUtil.OCORREU_UM_ERRO),
			@ApiResponse(code = 409, message = ResponseUtil.REGISTRO_JA_EXISTE)
	})
	@CrossOrigin
	@RequestMapping(value = ContractRestURIConstants.SALVAR_ESTATS_BLOCKCHAIN, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String setDataGet(
			@RequestBody SensorBlockchainDTO create) {

		BlockchainData retorno = transacaoService.salvar(create);
		
		return retorno.getTransactionHash();
	}	

}
