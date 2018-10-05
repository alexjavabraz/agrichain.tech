package br.com.bjbraz.spring;


import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		final List<ResponseMessage> responseMessageList = new ArrayList<ResponseMessage>();
		responseMessageList.add(new ResponseMessageBuilder().code(400).message("Registro nao encontrado").build());

		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.bjbraz.spring"))
				.paths(PathSelectors.any()).build().apiInfo(this.informacoesApi()).useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.POST, responseMessageList);
	}

	private ApiInfo informacoesApi() {

		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

		apiInfoBuilder.title("AGRICHAIN-API : IOT + Blockchain");
		apiInfoBuilder.description("API Rest para integração do DEVICE, do app MOBILE e do SITE com a rede BLOCKCHAIN pública (Ethereum + Nem). O método " + 
				ContractRestURIConstants.DEPLOY_SMART_CONTRACT_ETHEREUM + " serve para criar o SmartContract na rede Ethereum. Em seguida depois de feito o deploy na Blockchain, deve ser chamado o método "+
				ContractRestURIConstants.SETUP_SMART_CONTRACT_ETHEREUM + " para configurar atributos como TEMPERATURA MAXIMA ACEITA (maxTemperatureAccepted), TEMPERATURA MINIMA ACEITA (minTemperatureAccepted). Em seguida, o dispositivo IOT está apto para enviar as medições, através do método "+
				ContractRestURIConstants.SALVAR_ESTATS_BLOCKCHAIN + " para gravar na Blockchain os dados, como temperatura, humidade, latitude, longitude, juntamente com a data, hora e o ID do dispositivo. "+
				" O método " + ContractRestURIConstants.FINALIZAR_SMART_CONTRACT_APURAR  + " finaliza a apuração dos dados e efetua o pagamento caso os parametros estejam dentro dos valores mínimos e máximos. A API dispõe de outros métodos acessórios como:  "+
				ContractRestURIConstants.LISTAR_ESTATS_SMART_CONTRACTS+" " +
				ContractRestURIConstants.LISTAR_ESTATS_BLOCKCHAIN + " " +
				ContractRestURIConstants.LISTAR_POR_ID_BLOCKCHAIN + " " + 
				ContractRestURIConstants.LISTAR_TEMPERATURAS + " "
				
				);
		apiInfoBuilder.version("2.0");
		apiInfoBuilder.contact(this.contato());

		return apiInfoBuilder.build();

	}

	private Contact contato() {

		return new Contact("Micro Servico AGRICHAIN-API ", "http://agrichain.tech", "alex@agrichain.tech");
	}

}
