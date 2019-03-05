package library.controller;

import library.application.BaseResponse;
import library.common.ErrorCode;
import library.common.ErrorDto;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class BaseController {
  @ExceptionHandler
  protected BaseResponse handleValidationException(MethodArgumentNotValidException exception) {
    return new BaseResponse(
        new ErrorDto(
            ErrorCode.BAD_DATA_FORMAT, exception.getBindingResult().getFieldErrors().toString()));
  }
}
