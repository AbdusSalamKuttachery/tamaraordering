package com.salam.ec.tamara.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salam.ec.tamara.dao.repo.TamaraOrderMasterRepo;
import com.salam.ec.tamara.domain.OrderRequest;

@Service
public class OrderService
{
   TamaraOrderMasterRepo tamaraOrderMasterRepo;

   @Autowired
   public OrderService(TamaraOrderMasterRepo orderMasterRepo)
   {
      this.tamaraOrderMasterRepo = orderMasterRepo;
   }



   public void createOrder(OrderRequest orderRequest)
   {
      tamaraOrderMasterRepo.save(orderRequest.extractOrder());
   }

}
