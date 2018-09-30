package br.com.bjbraz.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateDeserializer;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor
@ToString @EqualsAndHashCode
public class ContractDeployDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3556901534796930341L;
	
	@ApiModelProperty(allowEmptyValue = true,
			  required=false, 
			  notes = "Este campo sera retornado, deverá ser enviado vazio", 
			  example="0x4155ff81da7f2e8ee102fa10fcebaee85f4d1fe503599c34f129470d36e7fb78",
			  readOnly=true)
	private String transactionHash;
	
	@ApiModelProperty(allowEmptyValue = true,
			  required=false, 
			  notes = "Este campo sera retornado, deverá ser enviado vazio", 
			  example="0x5e8d0eedbd53b89a693c57273590dfff5c70bb67", 
			  readOnly=true )
	private String contractAddress;
	
	@JsonFormat(pattern="dd/MM/yyyy", timezone = "America/Sao_Paulo")
	@JsonSerialize(using = DateSerializer.class)
	@JsonDeserialize(using = DateDeserializer.class)
	@ApiModelProperty(
			example="31/12/2010 13:15",
			notes = "Este campo sera retornado", 
			readOnly=true)
    private Date deployDate;
	
	@ApiModelProperty(allowEmptyValue = false,
			  required=true, 
			  notes = "Este campo serve de input, devera ser enviado ou o valor padrão será considerado", 
			  example="0x00b2B34e7244d06f2A50E099cF5b05538960232B" )
	private String ownerAddress;
	
	@ApiModelProperty(allowEmptyValue = false,
			  required=true, 
			  notes = "Este campo serve de input, devera ser enviado ou o valor padrão será considerado", 
			  example="0x00E484afDeB10F5372b8B259118c20FeA8da9aCe" )
	private String senderAddress;
	
	@ApiModelProperty(allowEmptyValue = false,
			  required=true, 
			  notes = "Este campo serve de input, devera ser enviado ou o valor padrão será considerado", 
			  example="0x00D44F69e43E49E5345b2A26E9A359F8Ad315F31" )
	private String receiverAddress;
	
	@ApiModelProperty(allowEmptyValue = false,
			  required=true, 
			  notes = "Este campo serve de input, deverá ser enviado com valor inteiro", 
			  example="30" )
	private Integer maxTemperatureAccepted;
	
	@ApiModelProperty(allowEmptyValue = true,
			  required=false, 
			  notes = "Este campo sera retornado, deverá ser enviado vazio", 
			  example="123423123", readOnly=true )
	private String id;

}
