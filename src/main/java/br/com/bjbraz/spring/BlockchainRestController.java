package br.com.bjbraz.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.bjbraz.domain.BlockchainData;
import br.com.bjbraz.domain.SmartContract;
import br.com.bjbraz.dto.ContractDeployDTO;
import br.com.bjbraz.dto.FinalizeDTO;
import br.com.bjbraz.dto.SetupDTO;
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
@RestController()
public class BlockchainRestController {

	private BlockchainService transacaoService;

	private static final String RETORNO_ERRO = "Invalid Values";

	@Autowired
	BlockchainRestController(BlockchainService t) {
			this.transacaoService = t;
	}
	
	@ApiOperation(value = "Efetua o deploy de um novo Smart Contract", notes = "Essa operação realiza o deploy de um novo Smart Contract na rede Ethereum")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = ResponseUtil.CADASTRO_EFETUADO_COM_SUCESSO, response = String.class),
			@ApiResponse(code = 400, message = ResponseUtil.OCORREU_UM_ERRO),
			@ApiResponse(code = 409, message = ResponseUtil.REGISTRO_JA_EXISTE)
	})
	@CrossOrigin
	@RequestMapping(value = ContractRestURIConstants.DEPLOY_SMART_CONTRACT_ETHEREUM, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<ContractDeployDTO>> deploy(@RequestBody ContractDeployDTO deploy) {
		try {
			ContractDeployDTO retorno            = transacaoService.deploySmartContract(deploy);
			Response<ContractDeployDTO> resposta = new Response<ContractDeployDTO>();
			resposta.setData(retorno);
			resposta.setCode(ResponseUtil.SUCCESS_CODE);
			resposta.setMessage(ResponseUtil.CADASTRO_EFETUADO_COM_SUCESSO);
			return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
		}catch(Exception e) {
			Response<ContractDeployDTO> resposta = new Response<ContractDeployDTO>();
			resposta.setData(new ContractDeployDTO());
			resposta.getData().setTransactionHash(e.getMessage());
			resposta.setCode(ResponseUtil.ERROR_CODE);
			resposta.setMessage(ResponseUtil.OCORREU_UM_ERRO);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
		}
	}
	
	@ApiOperation(value = "Efetua o setup do novo Smart Contract", notes = "Essa operação realiza o setup do Smart Contract na rede Ethereum, passando os paramtros aceitaveis de temperatura, humidade, etc")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = ResponseUtil.CADASTRO_EFETUADO_COM_SUCESSO),
			@ApiResponse(code = 400, message = ResponseUtil.OCORREU_UM_ERRO),
			@ApiResponse(code = 409, message = ResponseUtil.REGISTRO_JA_EXISTE)
	})
	@CrossOrigin
	@RequestMapping(value = ContractRestURIConstants.SETUP_SMART_CONTRACT_ETHEREUM, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<SetupDTO>> setupSmartContract(@RequestBody SetupDTO setup) {
		try {
			SetupDTO retorno            = transacaoService.setupSmartContract(setup);
			Response<SetupDTO> resposta = new Response<SetupDTO>();
			resposta.setData(retorno);
			resposta.setCode(ResponseUtil.SUCCESS_CODE);
			resposta.setMessage(ResponseUtil.CADASTRO_EFETUADO_COM_SUCESSO);
			return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
		}catch(Exception e) {
			Response<SetupDTO> resposta = new Response<SetupDTO>();
			resposta.setData(new SetupDTO());
			resposta.getData().setTransactionHash(e.getMessage());
			resposta.setCode(ResponseUtil.ERROR_CODE);
			resposta.setMessage(ResponseUtil.OCORREU_UM_ERRO);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
		}
	}
	

	@ApiOperation(value = "Salva as informações do device IOT para a chamada do Smart Contract", notes = "Essa operação realiza o cadastro das estatisticas do dispositivo IOT")
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
	
	/**
	 * 
	 * @param create
	 * @return
	 */
	public String setDataGet(SensorBlockchainDTO create) {

		BlockchainData retorno = transacaoService.salvar(create);
		
		return retorno.getTransactionHash();
	}
	
	@ApiOperation(value = "Lista os SmartContracts deployados", notes = "Essa operação realiza a consulta dos SmartContracts da Rede Ethereum")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = ResponseUtil.SUCCESS_MESSAGE, response = String.class),
			@ApiResponse(code = 400, message = ResponseUtil.OCORREU_UM_ERRO)
	})
	@CrossOrigin
	@RequestMapping(value = ContractRestURIConstants.LISTAR_ESTATS_SMART_CONTRACTS, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<List<SmartContract>>> listAllSmartContracts() {
		List<SmartContract> retorno = transacaoService.listarTodosSmartContracts();
		Response<List<SmartContract>> resposta = new Response<List<SmartContract>>();
		resposta.setData(retorno);
		resposta.setCode(ResponseUtil.SUCCESS_CODE);
		resposta.setMessage(ResponseUtil.SUCCESS_MESSAGE);
		return ResponseEntity.status(HttpStatus.OK).body(resposta);
	}
	
	@ApiOperation(value = "Lista as informações do device IOT para a chamada do Smart Contract", notes = "Essa operação realiza a consulta das estatisticas do dispositivo IOT")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = ResponseUtil.SUCCESS_MESSAGE),
			@ApiResponse(code = 400, message = ResponseUtil.OCORREU_UM_ERRO)
	})
	@CrossOrigin
	@RequestMapping(value = ContractRestURIConstants.LISTAR_ESTATS_BLOCKCHAIN, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<List<BlockchainData>>> listAll() {
		List<BlockchainData> retorno = transacaoService.listarTodos();
		Response<List<BlockchainData>> resposta = new Response<List<BlockchainData>>();
		resposta.setData(retorno);
		resposta.setCode(ResponseUtil.SUCCESS_CODE);
		resposta.setMessage(ResponseUtil.SUCCESS_MESSAGE);
		return ResponseEntity.status(HttpStatus.OK).body(resposta);
	}
	
	@ApiOperation(value = "Lista as informações do device IOT para a chamada do Smart Contract", notes = "Essa operação realiza a consulta das estatisticas do dispositivo IOT")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = ResponseUtil.SUCCESS_MESSAGE, response = String.class),
			@ApiResponse(code = 400, message = ResponseUtil.OCORREU_UM_ERRO)
	})
	@CrossOrigin
	@RequestMapping(value = ContractRestURIConstants.LISTAR_POR_ID_BLOCKCHAIN, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<BlockchainData>> findById(@Validated @PathVariable(name = "id") String idTransacao) {
		BlockchainData data = transacaoService.listarPorId(idTransacao);
		Response<BlockchainData> resposta = new Response<BlockchainData>();
		resposta.setData(data);
		resposta.setCode(ResponseUtil.SUCCESS_CODE);
		resposta.setMessage(ResponseUtil.SUCCESS_MESSAGE);
		return ResponseEntity.status(HttpStatus.OK).body(resposta);
	}
	
	@ApiOperation(
			value = "Finaliza o SmartContract e apura o resultado da entrega, caso a entrega tenha atendido aos parametros configurados, envia o valor em Ether para o TRANSPORTADOR", 
			notes = "Finaliza o SmartContract e faz a aputacao dos resultados enviando os valores para o TRANSPORTADOR se os padrões configurados estiverem normais")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = ResponseUtil.SUCCESS_MESSAGE),
			@ApiResponse(code = 400, message = ResponseUtil.OCORREU_UM_ERRO)
	})
	@CrossOrigin
	@RequestMapping(value = ContractRestURIConstants.FINALIZAR_SMART_CONTRACT_APURAR, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<FinalizeDTO>> finalizarSmartContract(@Validated 
			@RequestBody FinalizeDTO finalizar) {
		try {
			FinalizeDTO data = transacaoService.verifyAndFinalize(finalizar);
			Response<FinalizeDTO> resposta = new Response<FinalizeDTO>();
			resposta.setData(data);
			resposta.setCode(ResponseUtil.SUCCESS_CODE);
			resposta.setMessage(ResponseUtil.SUCCESS_MESSAGE);
			return ResponseEntity.status(HttpStatus.OK).body(resposta);
		}catch(Exception e){
			Response<FinalizeDTO> resposta = new Response<FinalizeDTO>();
			resposta.setCode(ResponseUtil.ERROR_CODE);
			resposta.setMessage(ResponseUtil.OCORREU_UM_ERRO);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
		}
	}
	
	@ApiOperation(
			value = "Lista os parametros minimo e máximo", 
			notes = "Lista os parametros minimo e máximo")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = ResponseUtil.SUCCESS_MESSAGE),
			@ApiResponse(code = 400, message = ResponseUtil.OCORREU_UM_ERRO)
	})
	@CrossOrigin
	@RequestMapping(value = ContractRestURIConstants.LISTAR_TEMPERATURAS, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<FinalizeDTO>> listarTemperaturas(@Validated 
			@RequestBody FinalizeDTO finalizar) {
		try {
			FinalizeDTO data = new FinalizeDTO();
			data.setValuesAboveAllowed(transacaoService.getMaximumValues(finalizar.getContractAddress()));
			data.setValuesBelowAllowed(transacaoService.getMinimumValues(finalizar.getContractAddress()));
			Response<FinalizeDTO> resposta = new Response<FinalizeDTO>();
			resposta.setData(data);
			resposta.setCode(ResponseUtil.SUCCESS_CODE);
			resposta.setMessage(ResponseUtil.SUCCESS_MESSAGE);
			return ResponseEntity.status(HttpStatus.OK).body(resposta);
		}catch(Exception e){
			Response<FinalizeDTO> resposta = new Response<FinalizeDTO>();
			resposta.setCode(ResponseUtil.ERROR_CODE);
			resposta.setMessage(ResponseUtil.OCORREU_UM_ERRO);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
		}
	}

}
