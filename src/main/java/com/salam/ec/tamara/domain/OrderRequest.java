package com.salam.ec.tamara.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.salam.ec.tamara.dao.domain.TamaraOrderItemEntity;
import com.salam.ec.tamara.dao.domain.TamaraOrderMasterEntity;

public class OrderRequest
{

   Long orderID;
   String orderUserId;
   BigDecimal orderTotalAmt;
   BigDecimal orderTaxAmt;
   BigDecimal orderDiscountAmt;
   List<OrderItem> itemsList;

   public TamaraOrderMasterEntity extractOrder()
   {

      TamaraOrderMasterEntity newOrder = new TamaraOrderMasterEntity();
      newOrder.setTouserid(this.getOrderUserId());
      newOrder.setTotaxamt(this.getOrderTaxAmt());
      newOrder.setTototalamt(this.getOrderTotalAmt());
      newOrder.setTodiscountamt(this.getOrderDiscountAmt());
      return newOrder;
   }

   public List<TamaraOrderItemEntity> extractItems(Long orderNumber)
   {

      List<TamaraOrderItemEntity> itemList = new ArrayList<>();
      for (OrderItem item : itemsList)
      {

      TamaraOrderItemEntity newItem = new TamaraOrderItemEntity();

      newItem.setOrderId(orderNumber);
      newItem.setItemName(item.getItemName());
      newItem.setItemCount(item.getItemCount());
      newItem.setItemId(item.getItemId());
      itemList.add(newItem);
   }

      return itemList;
   }
   // List<OrderItem> item;
   public String getOrderUserId()
   {
      return orderUserId;
   }

   public void setOrderUserId(String orderUserId)
   {
      this.orderUserId = orderUserId;
   }

   public BigDecimal getOrderTotalAmt()
   {
      return orderTotalAmt;
   }

   public void setOrderTotalAmt(BigDecimal orderTotalAmt)
   {
      this.orderTotalAmt = orderTotalAmt;
   }

   public BigDecimal getOrderTaxAmt()
   {
      return orderTaxAmt;
   }

   public void setOrderTaxAmt(BigDecimal orderTaxAmt)
   {
      this.orderTaxAmt = orderTaxAmt;
   }

   public BigDecimal getOrderDiscountAmt()
   {
      return orderDiscountAmt;
   }

   public void setOrderDiscountAmt(BigDecimal orderDiscountAmt)
   {
      this.orderDiscountAmt = orderDiscountAmt;
   }

   public List<OrderItem> getItemsList()
   {
      return itemsList;
   }

   public void setItemsList(List<OrderItem> itemsList)
   {
      this.itemsList = itemsList;
   }

   public Long getOrderID()
   {
      return orderID;
   }

   public void setOrderID(Long orderID)
   {
      this.orderID = orderID;
   }

}
