package com.example.taskcrudoperation.exception.advice;

import com.example.taskcrudoperation.dto.ExceptionDTO;
import com.example.taskcrudoperation.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = UserException.HandleException.class)
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    @ResponseBody
    public ExceptionDTO handleUserException(UserException.HandleException exception) {
        return new ExceptionDTO(false, exception.getMessage());
    }

    @ExceptionHandler(value = UserException.ForgetPasswordHandler.class)
    @ResponseStatus(HttpStatus.IM_USED)
    @ResponseBody
    public ExceptionDTO handleForgetPasswordException(UserException.ForgetPasswordHandler exception) {
        return new ExceptionDTO(false, exception.getMessage());
    }

    @ExceptionHandler(value = UserException.ResetPasswordHandler.class)
    @ResponseStatus(HttpStatus.NOT_MODIFIED)
    @ResponseBody
    public ExceptionDTO handleResetPasswordException(UserException.ResetPasswordHandler resetPasswordHandler) {
        return new ExceptionDTO(false, resetPasswordHandler.getMessage());
    }

    @ExceptionHandler(value = UserException.VerifyAccountHandler.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ExceptionDTO handleVerifyAccount(UserException.VerifyAccountHandler verifyAccountHandler) {
        return new ExceptionDTO(false, verifyAccountHandler.getMessage());
    }

    @ExceptionHandler(value = UserException.AllUserHandle.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ExceptionDTO handleAllUser(UserException.AllUserHandle allUserHandle) {
        return new ExceptionDTO(false, allUserHandle.getMessage());
    }

    @ExceptionHandler(value = UserException.GetUserByIdHandler.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ExceptionDTO getUserById(UserException.GetUserByIdHandler getUserByIdHandler) {
        return new ExceptionDTO(false, getUserByIdHandler.getMessage());
    }

    @ExceptionHandler(value = UserException.UpdateHandler.class)
    @ResponseStatus(HttpStatus.NOT_MODIFIED)
    @ResponseBody
    public ExceptionDTO handleUpdateException(UserException.UpdateHandler updateHandler) {
        return new ExceptionDTO(false, updateHandler.getMessage());
    }

    @ExceptionHandler(value = UserException.DeleteHandler.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public ExceptionDTO handleDeleteException(UserException.DeleteHandler deleteHandler) {
        return new ExceptionDTO(false, deleteHandler.getMessage());
    }

    @ExceptionHandler(value = UserException.ResetPasswordFromOldHandler.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ExceptionDTO handleResetFromOldException(UserException.ResetPasswordFromOldHandler resetPasswordFromOldHandler) {
        return new ExceptionDTO(false, resetPasswordFromOldHandler.getMessage());
    }

    @ExceptionHandler(value = UserException.SearchHandler.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ExceptionDTO handleSearchException(UserException.SearchHandler searchHandler) {
        return new ExceptionDTO(false, searchHandler.getMessage());
    }
}
