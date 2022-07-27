package com.salam.ec.tamara.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InvalidCredentials
// extends Exception
{

   /**
    * 
    */
   private static final long serialVersionUID = -4228931518180758886L;

   @ResponseStatus(HttpStatus.UNAUTHORIZED)
   @ExceptionHandler(Exception.class)
   public void handleException()
   {
      // System.out.println(message);
   }

}
