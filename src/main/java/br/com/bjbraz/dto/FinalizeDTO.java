package br.com.bjbraz.dto;

import java.io.Serializable;
import java.util.List;

import br.com.bjbraz.dto.account.SensorBlockchainDTO;
import br.com.bjbraz.enums.StatusDelivery;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor
@ToString @EqualsAndHashCode
public class FinalizeDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3496850096415324017L;
	
	@ApiModelProperty(
			  allowEmptyValue = true,
			  required=false, 
			  readOnly=true,
			  notes = "Este campo sera retornado, deverá ser enviado vazio", 
			  example="0x4155ff81da7f2e8ee102fa10fcebaee85f4d1fe503599c34f129470d36e7fb78"
			  )
	private String transactionHash;
	
	@ApiModelProperty(
			  allowEmptyValue = false,
			  required=true, 
			  readOnly=false,
			  notes = "Este campo deve ser enviado, não pode ser enviado vazio", 
			  example="0x5e8d0eedbd53b89a693c57273590dfff5c70bb67"
			  )
	private String contractAddress;
	
	
	@ApiModelProperty(
			  allowEmptyValue = true,
			  required=false, 
			  readOnly=true,
			  notes = "Este campo será retornado pelo SmartContract. Temperatura máxima recebida durante a medição.", 
			  example="30"
			  )
	private StatusDelivery status;
	
	@ApiModelProperty(
			  allowEmptyValue = true,
			  required=false, 
			  readOnly=true,
			  notes = "Este campo será retornado pelo SmartContract. Valores máximos obtidos durante a medição."
			  )
	private List valuesAboveAllowed;
	
	
	@ApiModelProperty(
			  allowEmptyValue = true,
			  required=false, 
			  readOnly=true,
			  notes = "Este campo será retornado pelo SmartContract. Valores minimos obtidos durante a medição."
			  )
	private List valuesBelowAllowed;
	
	
	
	

}