package com.novotempo.endofyeartoast.config;



import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.novotempo.endofyeartoast.config.ResourceException.ResponseCode;

@ControllerAdvice
public class Exceptionhandler extends ResponseEntityExceptionHandler {


	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String messageUser = "";
		String messageDeveloper = "";
		
		if(status.equals(HttpStatus.BAD_REQUEST)) {
		    messageUser = "Corpo da requisição está vazia ou incompleta!";
		    messageDeveloper = "Verifique o body do método post, possívelmente está vazio ou incompleto!";
		}
		
		if(status.equals(HttpStatus.NOT_FOUND)) {
			 messageUser = "Url incorreta!";
			 messageDeveloper = "Não existe esse tipo de serviço em nosso sistema!";
		}
		
		return handleExceptionInternal(ex, new ResponseError(messageUser, messageDeveloper, ResponseCode.BAD_REQUEST.getInternalCode()), headers, HttpStatus.BAD_REQUEST, request);
	}

	/**
	 * Erro comum de validações de atributos dos objetos
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ResponseError> exceptionIllegalArgumen(Exception ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError(ex));
	}
	/**
	 * Erro de integridade no momeento de salvar os objetos
	 * @param ex
	 * @return 
	 */
	@ExceptionHandler(InvalidDataAccessApiUsageException.class)
	public ResponseEntity<ResponseError> exceptionAccessApiUsage(InvalidDataAccessApiUsageException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseError("Existem objetos nulos, não foi possivel inserir!", ex.getCause().getMessage(), ResponseCode.BAD_REQUEST.getInternalCode()));
	}

	@ExceptionHandler(ResourceException.class)
	public ResponseEntity<ResponseError> resourceException(ResourceException ex) {
		String msgUser = ex.getMessage();
		String msgDev = ex.getMessage();
		if(ex.getMessage().contains("#break#")) {
			String[] split = ex.getMessage().split("#break#");
			msgUser = split[0];
			msgDev = split[1];
		}
		
		ResponseError responseError = new ResponseError(msgUser, msgDev, ex.getCode());
		return new ResponseEntity<ResponseError>(responseError, HttpStatus.valueOf(ex.getHttpStatusValue()));
	}
	
	/**
	 * Retorna error no formato padrão criado --> mensagem usuário e mensagem desenvolvedor, quebrada por a expressão (#break#)
	 * @param ex
	 * @return
	 */
	private ResponseError responseError(Exception ex) {
		if(ex.getMessage().contains("#break#")) {
			String[] split = ex.getMessage().split("#break#");
			return new ResponseError(split[0], split[1], ResponseCode.BAD_REQUEST.getInternalCode());
		}
		return new ResponseError(ex.getMessage(), ex.getMessage(), ResponseCode.BAD_REQUEST.getInternalCode());
	}

	/**
	 * Classe interna para erro no sistema 
	 */
	public static class ResponseError {

		private String messageUser;
		private String messageDeveloper;
		private Integer messageCode;

		public ResponseError(String messageUser, String messageDeveloper, Integer messageCode) {
			this.messageUser = messageUser;
			this.messageDeveloper = messageDeveloper;
			this.messageCode = messageCode;
		}

		public String getMessageUser() {
			return messageUser;
		}

		public String getMessageDeveloper() {
			return messageDeveloper;
		}
		
		public Integer getMessageCode() {
			return messageCode;
		}

	}
}
