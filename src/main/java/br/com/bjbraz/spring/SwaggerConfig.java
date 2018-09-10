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

		apiInfoBuilder.title("AGRICHAIN-API");
		apiInfoBuilder.description("AGRICHAIN-API : IOT + Blockchain");
		apiInfoBuilder.version("1.0");
		apiInfoBuilder.contact(this.contato());

		return apiInfoBuilder.build();

	}

	private Contact contato() {

		return new Contact("Micro Servico AGRICHAIN-API ", null, null);
	}

}
