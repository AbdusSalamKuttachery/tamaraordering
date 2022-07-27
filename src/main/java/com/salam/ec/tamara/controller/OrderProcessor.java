package com.salam.ec.tamara.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.salam.ec.tamara.dao.service.OrderService;
import com.salam.ec.tamara.domain.OrderRequest;

@RestController
@RequestMapping(path = "/order")
public class OrderProcessor
{

   @Autowired
   OrderService orderService;

   @PostMapping(path = "/save")
   @ResponseStatus(HttpStatus.OK)
   public ResponseEntity<String> saveOrder(@RequestHeader("Authorization") String usernamePass,
      @RequestBody @Validated OrderRequest orderReq) throws Exception
   {

      if (validateUser(usernamePass))
      {
         Long orderNumber = orderService.createOrder(orderReq);
         return new ResponseEntity<String>("Order Number is : " + orderNumber, HttpStatus.OK);
      }
      return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);

   }

   // c2FsYW0gdGFtYXJh

   public boolean validateUser(String usernamePass) throws Exception
   {

      byte[] decodedBytes = Base64.getDecoder().decode(usernamePass);
      String decodedString = new String(decodedBytes);
      if (decodedString.equals("salam tamara"))
      {
         return true;
      }
      else
      {
         return false;
      }
   }

}