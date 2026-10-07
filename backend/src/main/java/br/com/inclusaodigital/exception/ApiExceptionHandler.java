package br.com.inclusaodigital.exception;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<Map<String,Object>> notFound(ResourceNotFoundException e){return response(HttpStatus.NOT_FOUND,e.getMessage());}
    @ExceptionHandler(BusinessException.class)
    ResponseEntity<Map<String,Object>> business(BusinessException e){return response(HttpStatus.BAD_REQUEST,e.getMessage());}
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Map<String,Object>> validation(MethodArgumentNotValidException e){
        List<String> errors=e.getBindingResult().getFieldErrors().stream().map(x->x.getField()+": "+x.getDefaultMessage()).toList();
        return response(HttpStatus.BAD_REQUEST,String.join("; ",errors));
    }
    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    ResponseEntity<Map<String,Object>> integrity(Exception e){return response(HttpStatus.CONFLICT,"Operação não permitida: o registro possui vínculos ou viola uma restrição de unicidade.");}
    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    ResponseEntity<Map<String,Object>> unreadable(Exception e){return response(HttpStatus.BAD_REQUEST,"Dados enviados em formato inválido.");}
    @ExceptionHandler(org.springframework.web.servlet.resource.NoResourceFoundException.class)
    ResponseEntity<Map<String,Object>> noResource(Exception e){return response(HttpStatus.NOT_FOUND,"Recurso não encontrado.");}
    @ExceptionHandler(Exception.class)
    ResponseEntity<Map<String,Object>> generic(Exception e){return response(HttpStatus.INTERNAL_SERVER_ERROR,"Não foi possível concluir a operação.");}
    private ResponseEntity<Map<String,Object>> response(HttpStatus status,String message){return ResponseEntity.status(status).body(Map.of("timestamp", LocalDateTime.now(),"status",status.value(),"message",message));}
}
