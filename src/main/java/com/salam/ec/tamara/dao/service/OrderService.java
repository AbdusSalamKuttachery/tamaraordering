package com.salam.ec.tamara.dao.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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
         orderData.setToorderstatus("Pending");
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
      ordResp.setOrderStatus(order.getToorderstatus());

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

   public String updateOrder(OrderRequest newOrder)
   {

      TamaraOrderMasterEntity orderData = newOrder.extractOrder();
      orderData.setId(newOrder.getOrderID());
      orderData.setToorderstatus("Paid");
      if ("Success".equals(callExternalGateway(newOrder)))
      {
         tamaraOrderMasterRepo.save(orderData);
         return "Success";
      }
      else
      {
         return "Payment Failed";
      }
   }

   public String callExternalGateway(OrderRequest newOrder)
   {
      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://httpbin.org/"))
         .build();

      try
      {
         HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
         System.out.println(response.body());
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
      catch (InterruptedException e)
      {
         e.printStackTrace();
      }
      return "Success";
   }
}
