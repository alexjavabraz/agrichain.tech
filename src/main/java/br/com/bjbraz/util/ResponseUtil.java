package br.com.bjbraz.util;

import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.bjbraz.dto.ErrorDTO;

/**
 * Utility class for ResponseEntity creation.
 * @author Alex Simas Braz
 */
public interface ResponseUtil {

    /**
     * Wrap the optional into a {@link ResponseEntity} with an {@link HttpStatus#OK} status, or if it's empty, it
     * returns a {@link ResponseEntity} with {@link HttpStatus#NOT_FOUND}.
     *
     * @param <X>           type of the response
     * @param maybeResponse response to return if present
     * @return response containing {@code maybeResponse} if present or {@link HttpStatus#NOT_FOUND}
     */
    public static <X> ResponseEntity<X> wrapOrNotFound(Optional<X> maybeResponse, String message) {
            return wrapOrNotFound(maybeResponse, null, message);
    }
    
    /**
     * Wrap the optional into a {@link ResponseEntity} with an {@link HttpStatus#OK} status, or if it's empty, it
     * returns a {@link ResponseEntity} with {@link HttpStatus#NOT_FOUND}.
     *
     * @param <X>           type of the response
     * @param maybeResponse response to return if present
     * @return response containing {@code maybeResponse} if present or {@link HttpStatus#NOT_FOUND}
     */
    public static <X> ResponseEntity<X> wrapOrNotFound(Optional<X> maybeResponse) {
            return wrapOrNotFound(maybeResponse, null, "Nenhum resultado encontrado");
    }
    
    public static <X> ResponseEntity<X> wrapCreationOrNotFound(Optional<X> maybeResponse) {
        return wrapCreationOrNotFound(maybeResponse, null, "Nenhum resultado encontrado");
}

    
    /**
     * Wrap the optional into a {@link ResponseEntity} with an {@link HttpStatus#OK} status with the headers, or if it's
     * empty, it returns a {@link ResponseEntity} with {@link HttpStatus#NOT_FOUND}.
     *
     * @param <X>           type of the response
     * @param maybeResponse response to return if present
     * @param header        headers to be added to the response
     * @return response containing {@code maybeResponse} if present or {@link HttpStatus#NOT_FOUND}
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static <X> ResponseEntity<X> wrapOrNotFound(Optional<X> maybeResponse, HttpHeaders header, String message) {
        return maybeResponse.map(response -> ResponseEntity.ok().headers(header).body(response))
            .orElse(
            		new ResponseEntity(new ErrorDTO(message, "-99"), HttpStatus.BAD_REQUEST)
            		);
    }
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <X> ResponseEntity<X> wrapCreationOrNotFound(Optional<X> maybeResponse, HttpHeaders header, String message) {
        return maybeResponse.map(response -> new ResponseEntity(response, header, HttpStatus.CREATED))
            .orElse(
            		new ResponseEntity(new ErrorDTO(message, "-99"), HttpStatus.BAD_REQUEST)
            		);
    }


	public static final int SUCCESS_CODE = 0;
	public static final int ERROR_CODE = 99;
	public static final String OCORREU_UM_ERRO = "Ocorreu um erro";
	public static final String OCORREU_UM_ERRO_GRAVACAO = "Ocorreu um erro na gravacao";
	public static final String NENHUM_RESULTADO_ENCONTRADO = "Nenhum resultado encontrado";
	public static final String SUCCESS_MESSAGE = "Operacao realizada com sucesso";
	public static final String CADASTRO_EFETUADO_COM_SUCESSO = "Cadastro com sucesso";
	public static final String CONSULTA_REALIZADA_COM_SUCESSO = "Consulta realizada com sucesso";
	public static final String OPERACAO_REALIZADA_COM_SUCESSO = "Operacao realizada com sucesso";
	public static final String REGISTRO_JA_EXISTE = "Tentativa de inserção de registro duplicado";

}
