package br.com.bjbraz.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class ErrorDTO  {
 
	private String message;
	private String codigo;
	
	public ErrorDTO(String message, String codigo) {
		this.message = message;
		this.codigo = codigo;
	}

}
