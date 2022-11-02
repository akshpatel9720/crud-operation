package com.example.taskcrudoperation.dto;

public class ExceptionDTO {
    public boolean status;
    public String message;

    public ExceptionDTO(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ExceptionDTO{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}