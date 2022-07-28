package com.salam.ec.tamara.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

   @GetMapping(path = "/{id}")
   public @ResponseBody OrderRequest getOrderNumber(@PathVariable(value = "id") final Long id)
   {
      OrderRequest newOrder = orderService.getOrderNumber(id);
      return newOrder;
   }

   @PatchMapping(path = "/pay/{id}")
   public ResponseEntity<String> orderPayment(@PathVariable(value = "id") final Long id)
   {
      OrderRequest newOrder = orderService.getOrderNumber(id);
      if ("Pending".equals(newOrder.getOrderStatus()))
      {
         if ("Success".equals(orderService.updateOrder(newOrder)))
         {
            return new ResponseEntity<String>("Payment Success for Order :" + newOrder.getOrderID(),
               HttpStatus.OK);
         }
         else
         {
            return new ResponseEntity<String>("Payment Failed!", HttpStatus.BAD_GATEWAY);
         }

      }
      return new ResponseEntity<String>("Order already Paid:" + newOrder.getOrderID(),
         HttpStatus.BAD_REQUEST);
   }

   @PostMapping(path = "/save")
   @ResponseStatus(HttpStatus.OK)
   public ResponseEntity<String> saveOrder(@RequestHeader("Authorization") String usernamePass,
      @RequestBody @Validated OrderRequest orderReq) throws Exception
   {

      if (validateUser(usernamePass))
      {
         if (invalidRequest(orderReq))
         {
            return new ResponseEntity<String>("Invalid Data", HttpStatus.BAD_REQUEST);
         }

         Long orderNumber = orderService.createOrder(orderReq);
         return new ResponseEntity<String>("Order Number is : " + orderNumber, HttpStatus.OK);
      }
      return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);

   }

   private boolean invalidRequest(OrderRequest orderReq)
   {
      if (orderReq.getOrderTotalAmt() == null)
      {
         return true;
      }
      return false;
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