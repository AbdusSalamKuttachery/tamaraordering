package com.salam.ec.tamara.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salam.ec.tamara.dao.domain.TamaraKeyGenerator;
import com.salam.ec.tamara.dao.domain.TamaraOrderItemEntity;
import com.salam.ec.tamara.dao.domain.TamaraOrderMasterEntity;
import com.salam.ec.tamara.dao.repo.TamaraKeyGeneratorRepo;
import com.salam.ec.tamara.dao.repo.TamaraOrderItemsRepo;
import com.salam.ec.tamara.dao.repo.TamaraOrderMasterRepo;
import com.salam.ec.tamara.domain.OrderItem;
import com.salam.ec.tamara.domain.OrderRequest;

@Service
@Transactional
public class OrderService
{
   @Autowired
   TamaraOrderMasterRepo tamaraOrderMasterRepo;

   @Autowired
   TamaraOrderItemsRepo tamaraOrderItemsRepo;

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
         keyGenerator.save(new TamaraKeyGenerator("Order", orderKey.getKeyValue() + 1L));

         List<TamaraOrderItemEntity> itemList = orderRequest.extractItems(orderNumber);
         orderData.setId(orderNumber);
         tamaraOrderItemsRepo.saveAll(itemList);
         // orderData.setOrderItems(itemList);
         // System.out.println("Items: " + orderData.getOrderItems());
         tamaraOrderMasterRepo.save(orderData);

      }

      return orderNumber;
   }

   public TamaraKeyGenerator getOrCreate(String entity)
   {
      return keyGenerator.findById(entity)
         .orElseGet(() -> keyGenerator.save(new TamaraKeyGenerator(entity, 1L)));
   }

   public OrderRequest getOrderNumber(Long id)
   {
      // TODO Auto-generated method stub
      TamaraOrderMasterEntity order = getOrderFromDb(id);
      OrderRequest ordResp = new OrderRequest();
      ordResp.setOrderDiscountAmt(order.getTodiscountamt());
      ordResp.setOrderID(order.getId());
      ordResp.setOrderTotalAmt(order.getTototalamt());
      ordResp.setOrderTaxAmt(order.getTotaxamt());
      ordResp.setOrderUserId(order.getTouserid());

      List<TamaraOrderItemEntity> orderItems = getOrderItemFromDb(id);
      List<OrderItem> respItems = new ArrayList<>();
      for (TamaraOrderItemEntity tamaraOrderItemEntity : orderItems)
      {
         OrderItem item = new OrderItem();
         item.setItemCount(tamaraOrderItemEntity.getItemCount());
         item.setItemId(tamaraOrderItemEntity.getItemId());
         item.setItemName(tamaraOrderItemEntity.getItemName());
         respItems.add(item);
      }
      ordResp.setItemsList(respItems);
      return ordResp;
   }

   private List<TamaraOrderItemEntity> getOrderItemFromDb(Long id)
   {
      return tamaraOrderItemsRepo.findByOrderId(id);
      // .orElseThrow(() -> new RuntimeException("Template Id not found!"));
   }

   public TamaraOrderMasterEntity getOrderFromDb(Long id)
   {
      return tamaraOrderMasterRepo.findById(id)
         .orElseThrow(() -> new RuntimeException("Template Id not found!"));
   }

}
