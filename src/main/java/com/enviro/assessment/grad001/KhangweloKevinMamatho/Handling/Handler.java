// package com.enviro.assessment.grad001.KhangweloKevinMamatho.Handling;

// import java.util.HashMap;
// import java.util.Map;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.validation.FieldError;
// import org.springframework.web.bind.MethodArgumentNotValidException;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// import jakarta.persistence.EntityNotFoundException;

// @ControllerAdvice
// public class Handler extends ResponseEntityExceptionHandler {

//     @ExceptionHandler(EntityNotFoundException.class)
//     public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
//         return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//     }

//     @ExceptionHandler(Exception.class)
//     public ResponseEntity<Object> handleAllExceptions(Exception ex) {
//         return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//     }

//     // Handle MethodArgumentNotValidException explicitly
//     @ExceptionHandler(MethodArgumentNotValidException.class)
//     public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
//         Map<String, String> errors = new HashMap<>();
//         ex.getBindingResult().getAllErrors().forEach(error -> {
//             String fieldName = ((FieldError) error).getField();
//             String errorMessage = error.getDefaultMessage();
//             errors.put(fieldName, errorMessage);
//         });
//         return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//     }
// }
package com.enviro.assessment.grad001.KhangweloKevinMamatho.Handling;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Handler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body("Validation error: " + ex.getMessage());
    }
}

