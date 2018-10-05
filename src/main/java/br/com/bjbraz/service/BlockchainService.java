package br.com.bjbraz.service;

import java.io.File;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import br.com.bjbraz.domain.BlockchainData;
import br.com.bjbraz.domain.SmartContract;
import br.com.bjbraz.dto.ContractDeployDTO;
import br.com.bjbraz.dto.FinalizeDTO;
import br.com.bjbraz.dto.SetupDTO;
import br.com.bjbraz.dto.account.SensorBlockchainDTO;
import br.com.bjbraz.repo.BlockchainRepository;
import br.com.bjbraz.repo.SmartContractRepository;
import br.com.bjbraz.smartcontract.Tracking;
import br.com.bjbraz.util.StringUtil;

/**
 * 
 * @author asimas
 *
 */
@Service
public class BlockchainService {

	@Autowired
	private BlockchainRepository transacaoRepo;
	
	@Autowired
	private SmartContractRepository smartContractRepo;

	private static final String RETORNO_ERRO_VALOR_NAO_PERMITIDO = "Value not allowed";
	private static final String RETORNO_ERRO_VALOR_NEGATIVO = "Negative value not allowed";
	private static final String RETORNO_SUCESSO = "Success";

	@Value("${ethereum.credential.file}")
	private String fileBlockchainCredential;

	@Value("${ethereum.credential.password}")
	private String fileBlockchainPassword;
	
	@Value("${ethereum.blockchain.host}")
	private String BLOCKCHAIN_HOST;
	
	@Autowired
	private Web3j web3j;

	@Transactional
	public BlockchainData salvar(BlockchainData c) {
		return transacaoRepo.save(c);
	}

	public List<BlockchainData> listarTodos() {
		return transacaoRepo.findAll();
	}

	/**
	 * 
	 * @return
	 */
	public ContractDeployDTO deploySmartContract(ContractDeployDTO inputs) throws Exception {
		
		/**
		 * 
		 */
		inativarTodosSmartContractsAtivos();
		
		
		Credentials credentials = getCredentials();
		
		 
		Tracking contract = Tracking.deploy(web3j, credentials, StringUtil.GAS_PRICE, StringUtil.GAS_LIMIT).send();
		
		SmartContract sm = new SmartContract();
		sm.setAddress(contract.getContractAddress());
		sm.setDeployDate(StringUtil.hojeDataHoraTimestamp());
		sm.setMaxTemp(inputs.getMaxTemperatureAccepted());
		sm.setName("Contract Customer 1");
		sm.setOwnerAddress(credentials.getAddress());
		sm.setReceiverAddress(inputs.getReceiverAddress());
		sm.setSenderAddress(inputs.getSenderAddress());
		sm.setAtivo(true);
		sm = smartContractRepo.save(sm);
		
		ContractDeployDTO retorno = new ContractDeployDTO();

		retorno.setOwnerAddress(sm.getOwnerAddress());
		retorno.setSenderAddress(inputs.getSenderAddress());
		retorno.setReceiverAddress(inputs.getReceiverAddress());
		retorno.setContractAddress(contract.getContractAddress());
		retorno.setTransactionHash(contract.getTransactionReceipt().get().getTransactionHash());
		retorno.setMaxTemperatureAccepted(sm.getMaxTemp());
		retorno.setDeployDate(sm.getDeployDate());
		retorno.setId(String.valueOf(sm.getId()));

		return retorno;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	private Credentials getCredentials() throws Exception {
//		File file = new ClassPathResource("account-kovan.json").getInputStream();
		
//		File file = ResourceUtils.getFile("classpath:account-kovan.json");
		
		ClassPathResource classPathResource = new ClassPathResource("solidity/account-kovan.json");

		InputStream inputStream = classPathResource.getInputStream();
		File somethingFile = File.createTempFile("test", ".txt");
		try {
		    FileUtils.copyInputStreamToFile(inputStream, somethingFile);
		} finally {
		    IOUtils.closeQuietly(inputStream);
		}
		
		Credentials credentials = WalletUtils.loadCredentials(fileBlockchainPassword, somethingFile);
		return credentials;
	}

	/**
	 * 
	 */
	@Transactional
	public void inativarTodosSmartContractsAtivos() {
		List<SmartContract> contratos = smartContractRepo.findByAtivo(true);
		contratos.forEach(
					(item) -> 
						{
							item.setAtivo(false);
							smartContractRepo.save(item);
						}
					);
	}

	/**
	 * 
	 * @param create
	 * @return
	 */
	@Transactional
	public BlockchainData salvar(SensorBlockchainDTO create) {
		BlockchainData data = new BlockchainData();

		try {
			
			String transactionHash = createBlockchainTransaction(create);
			
			data.setDateSensor(create.getData());
			data.setHourSensor(create.getHora());
			data.setHumidity(create.getHumidity());
			data.setTemperature(create.getTemperature());
			data.setTransactionDate(StringUtil.hojeDataHoraTimestamp());
			data.setTransactionHash(transactionHash);
			data.setLatitude(create.getLatitude());
			data.setLongitude(create.getLongitude());
			data.setSmartContract(smartContractRepo.findByAtivo(true).get(0));
			data.setSmartContractAddress(data.getSmartContract().getAddress());
 			data = this.salvar(data);
		} catch (Exception e) {
			e.printStackTrace();
			data.setTransactionHash(e.getMessage());
		}
		return data;
	}

	/**
	 * 
	 * 
	 * @param create
	 * @return
	 */
	private String createBlockchainTransaction(SensorBlockchainDTO create) throws Exception {
		
		Credentials credentials = getCredentials();
		//Web3j web3 = getWeb3();
		
		Tracking contract = Tracking.load(
				create.getSmartContractAddress(), web3j, credentials, StringUtil.GAS_PRICE, StringUtil.GAS_LIMIT);
		
		List<SmartContract> contratos = smartContractRepo.findByAtivo(true);
		long i = contratos.get(0).getId();
		String id = String.valueOf(i);
		
		BigInteger temp = BigInteger.valueOf(create.getTemperature());
		
		CompletableFuture<TransactionReceipt> transaction = 
				contract.addTrack(id, create.getData(), create.getHora(), temp, create.getHumidity(), create.getLatitude(), create.getLongitude()).sendAsync();
		
		TransactionReceipt receipt = transaction.get(10, TimeUnit.MINUTES);
		
		return receipt.getTransactionHash();
	}

	/**
	 * 
	 * @param idTransacao
	 * @return
	 */
	public BlockchainData listarPorId(String idTransacao) {
		Optional<BlockchainData> retorno = transacaoRepo.findById(Long.parseLong(idTransacao));

		if (retorno.isPresent()) {
			return retorno.get();
		}

		return null;
	}

	/**
	 * 
	 * @return
	 */
	public List<SmartContract> listarTodosSmartContracts() {
		return smartContractRepo.findAll();
	}

	/**
	 * Faz o setup do smart contract na rede Ethereum, passando os valores máximos aceitaveis para o Tracking
	 * @param setup
	 * @return
	 */
	public SetupDTO setupSmartContract(SetupDTO setup) throws Exception {
		Credentials credentials = getCredentials();
		
		Optional<SmartContract> contratos = smartContractRepo.findOneByAddress(setup.getContractAddress());
		
		/**
		 * Se o Smart Contract não existir ou não estiver ativo, retorna erro
		 */
		if(contratos == null || !contratos.isPresent()) {
			throw new NullPointerException("No Smart Contract Active Found");
		}
		
		SmartContract contrato = contratos.get();
		
		Tracking contract = Tracking.load(
				contrato.getAddress(), web3j, credentials, StringUtil.GAS_PRICE, StringUtil.GAS_LIMIT);
		
		BigInteger weiValue = BigInteger.valueOf(1000000000000000000L); // ENVIAR ACIMA DE 0.001 Ether https://etherconverter.online/
		BigInteger _maxTempAccepted = BigInteger.valueOf(setup.getMaxTemperatureAccepted());
		BigInteger _minTempAccepted = BigInteger.valueOf(setup.getMinTemperatureAccepted());
		String _sender   = contrato.getSenderAddress();
		String _receiver = contrato.getReceiverAddress();
		
		TransactionReceipt transaction = contract.setup(_maxTempAccepted, _minTempAccepted, _sender, _receiver, weiValue).send();
		
		setup.setTransactionHash(transaction.getTransactionHash());
		
		return setup;
	}
	
	
	/**
	 * Este metodo é chamado para finalizar o SmartContract, quando a entrega chega a seu destino
	 * Os resultados são apurados e avisamos ao recebedor se a entrega atendeu aos parametros pré-definidos
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public FinalizeDTO verifyAndFinalize(FinalizeDTO input) throws Exception {
		Optional<SmartContract> contratos = smartContractRepo.findOneByAddress(input.getContractAddress());
		
		/**
		 * Se o Smart Contract não existir ou não estiver ativo, retorna erro
		 */
		if(contratos == null || !contratos.isPresent()) {
			throw new NullPointerException("No Smart Contract Active Found");
		}
		
		SmartContract contrato  = contratos.get();
		Credentials credentials = getCredentials();
		Tracking contract       = Tracking.load(contrato.getAddress(), web3j, credentials, StringUtil.GAS_PRICE, StringUtil.GAS_LIMIT);
		TransactionReceipt transaction = contract.finalizePolicy().send();
		
		//
		input.setTransactionHash(transaction.getTransactionHash());
		
		return input;
	}
	
	/**
	 * Este método retorna os valores maximos registrados no SmartContract
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public List getMaximumValues(String smartContractAddress) throws Exception {
		Optional<SmartContract> contratos = smartContractRepo.findOneByAddress(smartContractAddress);
		
		/**
		 * Se o Smart Contract não existir ou não estiver ativo, retorna erro
		 */
		if(contratos == null || !contratos.isPresent()) {
			throw new NullPointerException("No Smart Contract Active Found");
		}
		
		SmartContract contrato  = contratos.get();
		Credentials credentials = getCredentials();
		Tracking contract       = Tracking.load(contrato.getAddress(), web3j, credentials, StringUtil.GAS_PRICE, StringUtil.GAS_LIMIT);
		List maxTracked = contract.getMaxTracked().send();
		
		return maxTracked;
	}
	

	/**
	 * Este método retorna os valores minimos registrados no SmartContract
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public List getMinimumValues(String smartContractAddress) throws Exception {
		Optional<SmartContract> contratos = smartContractRepo.findOneByAddress(smartContractAddress);
		FinalizeDTO retorno = new FinalizeDTO();
		
		/**
		 * Se o Smart Contract não existir ou não estiver ativo, retorna erro
		 */
		if(contratos == null || !contratos.isPresent()) {
			throw new NullPointerException("No Smart Contract Active Found");
		}
		
		SmartContract contrato  = contratos.get();
		Credentials credentials = getCredentials();
		Tracking contract       = Tracking.load(contrato.getAddress(), web3j, credentials, StringUtil.GAS_PRICE, StringUtil.GAS_LIMIT);
		List maxTracked = contract.getMaxTracked().send();
		
		return maxTracked;
	}

}
