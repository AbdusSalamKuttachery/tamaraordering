package com.salam.ec.tamara.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

   // @GetMapping(path = "/{id}")
   // public @ResponseBody QuestionResponse getNextQuestions(@PathVariable(value = "id") final Long
   // id)
   // {
   // QuestionResponse newQuery = new QuestionResponse();
   // newQuery.setQuestionString(quesHelp.getNewQuestionForTemplate(id));
   // return newQuery;
   // }

   @PostMapping(path = "/save")
   @ResponseStatus(HttpStatus.CREATED)
   public void saveOrder(@RequestBody @Validated OrderRequest orderReq)
   {

      orderService.createOrder(orderReq);
   }
}