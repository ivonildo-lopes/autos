package com.loja.autos.exceptions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.loja.autos.dto.ResponseDto;

import jakarta.annotation.Nullable;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<?> handlePSQLException(DataIntegrityViolationException ex, WebRequest request) {
        LOGGER.error(" =============== DataIntegrityViolationException ==========================");

        String message = ex.getCause().getMessage();
        String fieldName = extractFieldName(ex.getMessage());
        String errorMsg;

        if (fieldName != null) {
            errorMsg = fieldName;
        } else {
            errorMsg = "Violação de integridade: " + message;
        }

        ResponseDto<?> obj = ResponseDto.fromData(errorMsg, HttpStatus.BAD_REQUEST, errorMsg, Arrays.asList(ex.getMessage()));
        return handleExceptionInternal(ex, obj, null, HttpStatus.BAD_REQUEST, request);
    }

    private String extractFieldName(String message) {
        LOGGER.error("============== " + message);
        
        String msg = null;
        
        msg =  tratamentoCamposVeiculo(message);
        
        if (message != null && (message.contains("cpf") || message.contains("CPF"))) {
            msg = "Valor duplicado encontrado para o campo: CPF";
        } else if (message != null && message.contains("email")) {
        	msg = "Valor duplicado encontrado para o campo: EMAIL";
        } else if (message != null && message.contains("codigo")) {
        	msg = "Valor duplicado encontrado para o campo: CODIGO";
        } else if(message != null && message.contains("fk_tbtiporeceitaorigem_tborigemdebito")) {
        	msg = "Não foi possível realizar a exclusão! A Origem do Débito está sendo utilizada para um Tipo de Receita cadastrada.";
        } 
        
        return msg;
    }

    private String tratamentoCamposVeiculo(String message) {
    	if (message != null && (message.contains("tb_veiculo_chassi_key") || message.contains("CHASSI"))) {
            return "Valor duplicado encontrado para o campo: chassi";
        } else if (message != null && message.contains("tb_veiculo_placa_key")) {
        	return  "Valor duplicado encontrado para o campo: PLACA";
        } else if (message != null && message.contains("tb_veiculo_renavam_key")) {
        	return  "Valor duplicado encontrado para o campo: RENAVAM";
        } 
    	
    	return null;
	}

	@Override
    @Nullable
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

    	LOGGER.error(" =============== Campos do DTO que não passaram na validação ==========================");

        List<String> errosParaUsuario = ex.getBindingResult().getFieldErrors().stream().map(e -> e.getDefaultMessage())
                .collect(Collectors.toList());

        ResponseDto<?> res = ResponseDto.fromData(null, HttpStatus.BAD_REQUEST,
                "Favor verifique todos os campos com validação", errosParaUsuario);
        return handleExceptionInternal(ex, res, headers, HttpStatus.BAD_REQUEST, request);
	}
    
    @Override
    @Nullable
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
			HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        LOGGER.error(" =============== HttpMessageNotReadableException validation ENUM ==========================");

        Throwable rootCause = ex.getRootCause();
        if (rootCause instanceof InvalidFormatException) {
            InvalidFormatException ife = (InvalidFormatException) rootCause;
            String invalidValue = ife.getValue().toString();
            String enumType = ife.getTargetType().getSimpleName();
            List<String> enumValues = Arrays.stream(ife.getTargetType().getEnumConstants())
                    .map(Object::toString)
                    .collect(Collectors.toList());

            String errorMessage = String.format(
                    "Valor inválido para o tipo %s: %s. Valores aceitos são: %s",
                    enumType, invalidValue, enumValues);

            ResponseDto<?> res = ResponseDto.fromData(null, HttpStatus.BAD_REQUEST,errorMessage);
            return handleExceptionInternal(ex, res, headers, HttpStatus.BAD_REQUEST, request);
        }

        return handleExceptionInternal(ex, null, headers, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * EXCEÇÕES PERSONALIZADAS
     **/
    @ExceptionHandler({ BadValueException.class })
    public ResponseEntity<?> handleBadValueException(BadValueException ex, WebRequest request) {
        LOGGER.error(" =============== Bad request front ==========================");
        Object obj = ResponseDto.fromData(null, HttpStatus.BAD_REQUEST, ex.getMessage(),
                Arrays.asList(ex.getMessage()));
        return handleExceptionInternal(ex, obj, null, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ NegocioException.class })
    public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request) {
        LOGGER.error(" =============== NegocioException ==========================");
        Object obj = ResponseDto.fromData(null, HttpStatus.BAD_REQUEST, ex.getMessage(),
                Arrays.asList(ex.getMessage()));
        return handleExceptionInternal(ex, obj, null, HttpStatus.BAD_REQUEST, request);
    }
    
    @ExceptionHandler({ JwtException.class })
    public ResponseEntity<?> handleJwtCreateException(JwtException ex, WebRequest request) {
        LOGGER.error(" =============== JwtException ==========================");
        Object obj = ResponseDto.fromData(null, HttpStatus.FORBIDDEN, ex.getMessage(),
                Arrays.asList(ex.getMessage()));
        return handleExceptionInternal(ex, obj, null, HttpStatus.FORBIDDEN, request);
    }

}