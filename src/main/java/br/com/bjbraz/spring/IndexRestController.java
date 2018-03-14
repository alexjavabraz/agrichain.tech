package br.com.bjbraz.spring;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.bjbraz.domain.Conta;
import br.com.bjbraz.domain.Transacao;
import br.com.bjbraz.dto.ConsultaTransacoesDTO;
import br.com.bjbraz.dto.TransacaoDTO;
import br.com.bjbraz.service.ContaService;
import br.com.bjbraz.service.TransacaoService;
import org.springframework.web.bind.annotation.RestController;


/**
 * 
 * @author alex.braz
 *
 */
@RestController
public class IndexRestController {

	private TransacaoService transacaoService;
	private ContaService contaService;

	private static final String RETORNO_ERRO = "Erro valores inválidos";

	@Autowired
	IndexRestController(TransacaoService t, ContaService c) {
		this.transacaoService = t;
		this.contaService = c;
	}

	@CrossOrigin
	@RequestMapping(value = ContractRestURIConstants.SALDO, method = RequestMethod.GET)
	public String consultarSaldo(@PathVariable("id") String sconta) {
		Conta conta = contaService.consultarPorId(Long.parseLong(sconta));

		String retorno = "";

		try {
			if (conta != null && conta.getSaldo() != null) {
				BigDecimal bd = conta.getSaldo();

				DecimalFormatSymbols symbol = new DecimalFormatSymbols();
				symbol.setDecimalSeparator(' ');
				symbol.setGroupingSeparator(' ');

				DecimalFormat df = new DecimalFormat("#,###", symbol);
				retorno = df.format(bd);
			}
		} catch (Exception e) {
			retorno = "0";
		}

		return retorno;
	}

	@CrossOrigin
	@RequestMapping(value = ContractRestURIConstants.SAQUE, method = RequestMethod.POST)
	public TransacaoDTO saque(@RequestBody TransacaoDTO simpleTransacao) {

		/**
		 * Verifica valores nulos
		 */
		if (simpleTransacao == null || simpleTransacao.getValor() == null
				|| simpleTransacao.getValor().doubleValue() < 10) {
			simpleTransacao.setMensagem(RETORNO_ERRO);
			return simpleTransacao;
		}

		/**
		 * Não permitir casa decimal
		 */
		if (simpleTransacao.getValor() % 1 != 0) {
			simpleTransacao.setMensagem(RETORNO_ERRO);
			return simpleTransacao;
		}

		try {
			/**
			 * Efetua o processamento do saque, persistência e lógica de contagem de notas
			 */
			simpleTransacao = transacaoService.saque(simpleTransacao);

		} catch (IllegalArgumentException e) {
			simpleTransacao.setMensagem(e.getMessage());
			return simpleTransacao;
		} catch (Exception ex) {
			simpleTransacao.setMensagem(ex.getMessage());
			return simpleTransacao;
		}

		return simpleTransacao;

	}

	@CrossOrigin
	@RequestMapping(value = ContractRestURIConstants.LISTAR_TODAS_TRANSACOES, method = RequestMethod.GET)
	public ConsultaTransacoesDTO listarTodasTransacoes() {
		ConsultaTransacoesDTO retorno = new ConsultaTransacoesDTO();
		List<Transacao> entidades = transacaoService.listarTodos();
		try {
			/**
			 * 
			 */
			if (entidades != null && !entidades.isEmpty()) {
				entidades.forEach((item) -> retorno.getItens().add(new TransacaoDTO(item)));
			}

			return retorno;
		} catch (Exception e) {
			return null;
		}

	}

}
