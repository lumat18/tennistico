package com.gruzini.tennistico.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {
   public EmailAlreadyExistsException() {
      super();
   }

   public EmailAlreadyExistsException(final String message) {
      super(message);
   }

   public EmailAlreadyExistsException(final String message, final Throwable cause) {
      super(message, cause);
   }

   public EmailAlreadyExistsException(final Throwable cause) {
      super(cause);
   }

   protected EmailAlreadyExistsException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
   }
}
