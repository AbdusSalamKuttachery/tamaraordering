package com.salam.ec.tamara.dao.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salam.ec.tamara.dao.domain.TamaraKeyGenerator;
import com.salam.ec.tamara.dao.domain.TamaraOrderMasterEntity;
import com.salam.ec.tamara.dao.repo.TamaraKeyGeneratorRepo;
import com.salam.ec.tamara.dao.repo.TamaraOrderMasterRepo;
import com.salam.ec.tamara.domain.OrderRequest;

@Service
@Transactional
public class OrderService
{
   @Autowired
   TamaraOrderMasterRepo tamaraOrderMasterRepo;

   @Autowired
   TamaraKeyGeneratorRepo keyGenerator;

   @Autowired
   public OrderService(TamaraOrderMasterRepo orderMasterRepo)
   {
      this.tamaraOrderMasterRepo = orderMasterRepo;
   }



   public Long createOrder(OrderRequest orderRequest)
   {
      Long orderNumber = 0L;
      TamaraKeyGenerator orderKey = getOrCreate("Order");
      if (orderKey == null)
      {
         System.out.println("Unexpected scenario");
      }
      else
      {
         System.out.println("found key:" + orderKey.getKeyValue());
         TamaraOrderMasterEntity orderData = orderRequest.extractOrder();
         orderNumber = orderKey.getKeyValue();
         orderData.setId(orderKey.getKeyValue());

         tamaraOrderMasterRepo.save(orderData);
         keyGenerator.save(new TamaraKeyGenerator("Order", orderKey.getKeyValue() + 1L));
      }

      return orderNumber;
   }

   public TamaraKeyGenerator getOrCreate(String entity)
   {
      return keyGenerator.findById(entity)
         .orElseGet(() -> keyGenerator.save(new TamaraKeyGenerator(entity, 1L)));
   }

}
