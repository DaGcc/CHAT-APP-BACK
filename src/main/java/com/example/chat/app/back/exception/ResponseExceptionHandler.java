package com.example.chat.app.back.exception;

import java.util.Date;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//esta clasee intercepta todos los errores del backend de cualquier capa y lo expone coomo un servicio rest en json



@ControllerAdvice //esto sirve para ser transversal. es decir que si sucede una excepcion este saca el pechito dando la exepcion de lo que programamos    
@RestController //para exponer el codigo de errores como servicio rest
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler{//esta clase padre tiene varios metodos para trabajar excepciones

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        //ex.getBindingResult().getAllErrors();
        System.out.println("aqui status"+ status);
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),"Validacion fallida",request.getDescription(false));
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    
    
    
    
//    @ExceptionHandler(Exception.class)
//    public final ResponseEntity<ExceptionResponse> manejarModeloExepciones(Exception ex, WebRequest request){
//        System.out.println("aqui interno status");
//        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));     
//        return new ResponseEntity<ExceptionResponse>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
//    }
    
    //INTERNAL SERVER ERROR
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        System.out.println("Estado de error interno:" + status);
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<Object>(exceptionResponse, status);
    }


    
    //creamos un metodo de manejar modelo excepciones: maneja los modelos que no existan con el ingreso de /id
    @ExceptionHandler(ModelNotFoundException.class)//esta anotacion sirve pra manejar el exception del dica clase mnfo
    public final ResponseEntity<ExceptionResponse> manejarModeloExepciones(ModelNotFoundException ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
        //aqui es el objeto pojo: nos dara la fecha del error, el mensaje y una descripsion adicional
        return new ResponseEntity<ExceptionResponse>(exceptionResponse,HttpStatus.NOT_FOUND);
    
    } //esta clase va interceptar(apoderar) los errores de modelnotfoudexeption
    
    //resppnseentity es de un servicio rest
    
    
    
}
