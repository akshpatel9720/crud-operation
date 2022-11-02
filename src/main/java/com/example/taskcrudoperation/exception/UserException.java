package com.example.taskcrudoperation.exception;

public class UserException extends Exception {
    public static class HandleException extends RuntimeException {
        public HandleException(String message) {
            super(message);
        }
    }

    public static class ForgetPasswordHandler extends RuntimeException{
        public ForgetPasswordHandler(String message){
            super(message);
        }
    }

    public static class ResetPasswordHandler extends RuntimeException{
        public ResetPasswordHandler(String message){
            super(message);
        }
    }

    public static class VerifyAccountHandler extends RuntimeException{
        public VerifyAccountHandler(String message){
            super(message);
        }
    }

    public static class AllUserHandle extends RuntimeException{
        public AllUserHandle(String message){
            super(message);
        }
    }

    public static class GetUserByIdHandler extends RuntimeException{
        public GetUserByIdHandler(String message){
            super(message);
        }
    }

    public static class UpdateHandler extends RuntimeException{
        public UpdateHandler(String message){
            super(message);
        }
    }

    public static class DeleteHandler extends RuntimeException{
        public DeleteHandler(String message){
            super(message);
        }
    }

    public static class ResetPasswordFromOldHandler extends RuntimeException{
        public ResetPasswordFromOldHandler(String message){
            super(message);
        }
    }

    public static class SearchHandler extends RuntimeException{
        public SearchHandler(String message){
            super(message);
        }
    }
}
