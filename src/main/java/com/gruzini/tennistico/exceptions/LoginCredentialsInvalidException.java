package com.gruzini.tennistico.exceptions;

public class LoginCredentialsInvalidException extends RuntimeException{
   public LoginCredentialsInvalidException() {
      super();
   }

   public LoginCredentialsInvalidException(final String message) {
      super(message);
   }

   public LoginCredentialsInvalidException(final String message, final Throwable cause) {
      super(message, cause);
   }

   public LoginCredentialsInvalidException(final Throwable cause) {
      super(cause);
   }

   protected LoginCredentialsInvalidException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
   }
}
