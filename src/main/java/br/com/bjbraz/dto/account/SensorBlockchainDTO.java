package br.com.bjbraz.dto.account;

import java.io.Serializable;

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

/**
 * 
 * @author asimas
 *
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class SensorBlockchainDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8572773231768100051L;
	// {"humidity": 52.0, "hora": "09:58:18", "temperature": 24.0, "data":"12/04/2018"}
	private String externalIdentifier;
	
	
	@JsonFormat(pattern="dd/MM/yyyy", timezone = "America/Sao_Paulo")
	@ApiModelProperty(
			required=true,
			example="31/12/2010",
			notes = "Este campo sera enviado pelo dispositivo IOT", 
			readOnly=false)
	private String data;
	
	@JsonFormat(pattern = "HH:mm")
	@ApiModelProperty(
			required=true,
			example="13:15",
			notes = "Este campo sera enviado pelo dispositivo IOT", 
			readOnly=false)
	private String hora;
	 
	@ApiModelProperty(allowEmptyValue = false,
			  required=true, 
			  notes = "Este campo serve de input, devera ser enviado ou o valor padrão será considerado", 
			  example="15" )
	private Integer temperature;
	
	@ApiModelProperty(allowEmptyValue = false,
			  required=true, 
			  notes = "Este campo serve de input, devera ser enviado ou o valor padrão será considerado", 
			  example="15" )
	private String humidity;
	
	@ApiModelProperty(allowEmptyValue = false,
			  required=true, 
			  notes = "Este campo serve de input, devera ser enviado ou o valor padrão será considerado", 
			  example="15" )
	private String latitude;
	
	@ApiModelProperty(allowEmptyValue = false,
			  required=true, 
			  notes = "Este campo serve de input, devera ser enviado ou o valor padrão será considerado", 
			  example="15" )
	private String longitude;
	
	
	@ApiModelProperty(allowEmptyValue = false,
			  required=true, 
			  notes = "Este campo serve de input, ele é o endereço do smart contract recém deployado para esta entrega", 
			  example="0xd495a69094548889ef7be8c6487b2d05bc974396" )
	private String smartContractAddress;
 

}
