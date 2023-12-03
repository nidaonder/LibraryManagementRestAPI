package com.nidaonder.library.core.config;

import com.nidaonder.library.core.exception.NotFoundException;
import com.nidaonder.library.core.exception.StockErrorException;
import com.nidaonder.library.core.result.Result;
import com.nidaonder.library.core.result.ResultData;
import com.nidaonder.library.core.utilities.ResultHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Result> handleNotFoundException(NotFoundException e){
        return new ResponseEntity<>(ResultHelper.notFoundError(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StockErrorException.class)
    public ResponseEntity<Result> handleStockErrorException(StockErrorException e){
        return new ResponseEntity<>(ResultHelper.stockError(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultData<List<String>>> handleValidationError(MethodArgumentNotValidException e){

        // id sıfırdan büyük olmalı, name dolu olmalı vs gibi.
        List<String> validationErrorList = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(ResultHelper.validateError(validationErrorList), HttpStatus.BAD_REQUEST);
    }
}
